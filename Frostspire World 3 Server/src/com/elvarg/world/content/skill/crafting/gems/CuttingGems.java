package com.elvarg.world.content.skill.crafting.gems;

import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Item;

public class CuttingGems {
	
	private static final int CHISEL = 1755;
	
	public static boolean attemptCutGems(Player player, Item used, Item with) {
		if(used.getId() != CHISEL && with.getId() != CHISEL) {
			return false;
		}
		
		GemData gem = used.getId() == CHISEL ? GemData.getGem(with.getId()) : GemData.getGem(used.getId());
		
		if(gem == null) {
			return false;
		} else if(meetsRequirements(player, gem)) {
			cutGem(player, gem);
		}
		return false;
	}
	
	private static void cutGem(Player player, GemData gem) {
		if(!player.getClickDelay().elapsed(1000)) {
			return;
		}
		
		player.performAnimation(new Animation(gem.getAnimation()));
		player.getInventory().delete(gem.getGem());
		player.getInventory().add(gem.getProduct());
		player.getSkillManager().addExperience(Skill.CRAFTING, gem.getExperience());
		player.getClickDelay().reset();
		
	}
	
	private static boolean meetsRequirements(Player player, GemData gem) {
		if(player.getSkillManager().getMaxLevel(Skill.CRAFTING) < gem.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a crafting level of "+gem.getLevelRequired()+" to cut this gem.");
			return false;
		}
		return true;
	}

}
