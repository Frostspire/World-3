package com.frostspire.world.content.skill.mining;

import com.frostspire.engine.task.Task;
import com.frostspire.util.Misc;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.content.skill.woodcutting.Woodcutting;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;

public class MiningTask extends Task {
	
	private Player player;
	private OreData ore;
	private PickaxeData pickaxe;
	private int cycle = 0;
	int delay;
	int reqCycle;
	boolean firstTick;
	
	public MiningTask(Player player, OreData ore, PickaxeData pickaxe) {
		super(1);
		this.player = player;
		this.ore = ore;
		this.pickaxe = pickaxe;
		this.firstTick = true;
		delay = Misc.getRandom(ore.getTicks() - Mining.getMiningTimer(player, pickaxe)) +1;
		reqCycle = delay >= 2 ? delay : Misc.getRandom(1) + 1;
	}

	@Override
	protected void execute() {
		//skip the first tick because the mining animation happens too early, interrupts walkToTask
		if(firstTick) {
			firstTick = false;
			return;
		}
		
		if(player.getInventory().getFreeSlots() == 0) {
			player.performAnimation(new Animation(65535));
			player.getPacketSender().sendMessage("You don't have enough free inventory space.");
			this.stop();
			return;
		}
		
		player.performAnimation(pickaxe.getAnimation());
		
		if (cycle != reqCycle) {
			cycle++;
		} else if (cycle >= reqCycle) {
			player.getSkillManager().addExperience(Skill.MINING, ore.getExperience());

			/*if(Woodcutting.infernoAdze(player)) { //change to infernal pickaxe, and make it have a chance to smelt
				//player.getSkillManager().addExperience(Skill.FIREMAKING, fmLog.getXp()); re add when firemaking is added
				player.getPacketSender().sendMessage("Your Inferno Adze burns the log, granting you Firemaking experience.");
			} else {*/
				if(ore.getName().equals("Gem Rock")) {
					player.getInventory().add(ore.getOre()[Misc.getRandom(ore.getOre().length)], 1);
				} else {
					player.getInventory().add(ore.getOre()[0], 1);
				}
			//}
			
			delay = Misc.getRandom(ore.getTicks() - Mining.getMiningTimer(player, pickaxe)) +1;
			reqCycle = delay >= 2 ? delay : Misc.getRandom(1) + 1;
			cycle = 0;
		}
		
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
