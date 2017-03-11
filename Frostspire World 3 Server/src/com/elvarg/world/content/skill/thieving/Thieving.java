package com.elvarg.world.content.skill.thieving;

import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.combat.CombatFactory;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;

public class Thieving {
	
	public static boolean attemptThieving(Player player, GameObject object) {
		StallData stall = StallData.getStall(object.getId());
		
		if(stall == null) {
			return false;
		} else {
			player.setPositionToFace(object.getPosition());
			stealFromStall(player, stall);
			return true;
		}
	}
	
	private static void stealFromStall(Player player, StallData stall) {
		if(meetsRequirements(player, stall)) {
			player.performAnimation(new Animation(881));
			player.getPacketSender().sendMessage("You steal some loot from the "+stall.getName()+" stall.");
			player.getPacketSender().sendInterfaceRemoval();
			player.getSkillManager().addExperience(Skill.THIEVING, stall.getExperience());
			player.getClickDelay().reset();
			player.getInventory().add(stall.getItemId(), 1);
		}
	}
	
	private static boolean meetsRequirements(Player player, StallData stall) {
		if(player.getInventory().getFreeSlots() < 1) {
			player.getPacketSender().sendMessage("You need some more inventory space to do this.");
			return false;
		}
		if (CombatFactory.isBeingAttacked(player)) {
			player.getPacketSender().sendMessage("You must wait a few seconds after being out of combat before doing this.");
			return false;
		}
		if(!player.getClickDelay().elapsed(2000))
			return false;
		if(player.getSkillManager().getMaxLevel(Skill.THIEVING) < stall.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a Thieving level of at least " + stall.getLevelRequired() + " to steal from this stall.");
			return false;
		}
		return true;
	}

}
