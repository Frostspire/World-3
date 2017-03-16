package com.frostspire.world.content.skill.fishing;

import com.frostspire.cache.impl.definitions.ItemDefinition;
import com.frostspire.engine.task.Task;
import com.frostspire.util.Misc;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;

public class FishingTask extends Task {
	
	private Player player;
	private FishingSpots spot;
	private int ticks = 0;
	private int reqCycle;
	
	public FishingTask(Player player, FishingSpots spot) {
		super(2);
		this.player = player;
		this.spot = spot;
	}

	@Override
	protected void execute() {
		
		final int fishIndex = Misc.getRandom(100) >= 70 ? getMax(player, spot.fishingReqs) : (getMax(player, spot.fishingReqs) != 0 ? getMax(player, spot.fishingReqs) - 1 : 0);
		reqCycle = getDelay(spot.getLevelReq()[fishIndex]);
		
		if(player.getInventory().getFreeSlots() == 0) {
			player.getPacketSender().sendMessage("You have run out of inventory space.");
			stop();
			return;
		}
		
		if(!player.getInventory().contains(spot.getBait())) {
			player.getPacketSender().sendMessage("You have run out of bait.");
			stop();
			return;
		}
		
		if (ticks >= Misc.getRandom(1) + reqCycle) {
			
			String def = ItemDefinition.forId(spot.getRawFish()[fishIndex]).getName();
			if(def.endsWith("s"))
				def = def.substring(0, def.length()-1);
			player.getPacketSender().sendMessage("You catch "+Misc.anOrA(def)+" "+def.toLowerCase().replace("_", " ")+".");
			
			if (spot.getBait() != -1)
				player.getInventory().delete(spot.getBait(), 1);
			
			player.getInventory().add(spot.getRawFish()[fishIndex], 1);	
			player.getSkillManager().addExperience(Skill.FISHING, spot.getXp()[fishIndex]);
			
			player.performAnimation(new Animation(spot.getAnim()));
			
			ticks = 0;
			
		}
		
		ticks++;
	}

	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}
	
	public static int getMax(Player p, int[] reqs) {
		int tempInt = -1;
		for (int i : reqs) {
			if (p.getSkillManager().getCurrentLevel(Skill.FISHING) >= i) {
				tempInt++;
			}
		}
		return tempInt;
	}

	private static int getDelay(int req) {
		int timer = 1;
		timer += (int) req * 0.08;
		return timer;
	}

}
