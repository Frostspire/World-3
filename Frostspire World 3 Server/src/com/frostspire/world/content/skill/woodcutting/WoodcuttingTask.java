package com.frostspire.world.content.skill.woodcutting;

import com.frostspire.engine.task.Task;
import com.frostspire.util.Misc;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;

public class WoodcuttingTask extends Task {
	
	private Player player;
	private TreeData tree;
	private AxeData axe;
	private int cycle = 0;
	int delay;
	int reqCycle;
	
	public WoodcuttingTask(Player player, TreeData tree, AxeData axe) {
		super(1);
		this.player = player;
		this.tree = tree;
		this.axe = axe;
		delay = Misc.getRandom(tree.getTicks() - Woodcutting.getChopTimer(player, axe)) +1;
		reqCycle = delay >= 2 ? delay : Misc.getRandom(1) + 1;
	}

	@Override
	protected void execute() {
		if(player.getInventory().getFreeSlots() == 0) {
			player.performAnimation(new Animation(65535));
			player.getPacketSender().sendMessage("You don't have enough free inventory space.");
			this.stop();
			return;
		}
		
		player.performAnimation(axe.getAnimation());
		
		if (cycle != reqCycle) {
			cycle++;
		} else if (cycle >= reqCycle) {
			player.getSkillManager().addExperience(Skill.WOODCUTTING, tree.getExperience());

			if(Woodcutting.infernoAdze(player)) {
				//player.getSkillManager().addExperience(Skill.FIREMAKING, fmLog.getXp()); re add when firemaking is added
				player.getPacketSender().sendMessage("Your Inferno Adze burns the log, granting you Firemaking experience.");
			} else {
				player.getInventory().add(tree.getReward(), 1);
			}
			
			delay = Misc.getRandom(tree.getTicks() - Woodcutting.getChopTimer(player, axe)) +1;
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
