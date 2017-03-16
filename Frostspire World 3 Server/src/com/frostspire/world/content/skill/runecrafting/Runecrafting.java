package com.frostspire.world.content.skill.runecrafting;

import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.object.GameObject;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Graphic;
import com.frostspire.world.model.GraphicHeight;
import com.frostspire.world.model.Item;

public class Runecrafting {

	public static boolean attemptRunecrafting(Player player, GameObject object) {
		RunecraftingData data = RunecraftingData.getRune(object.getId());

		if (data == null) {
			return false;
		}

		if (!meetsRequirements(player, data, object)) {
			return true;
		}

		//TaskQueue.queue(new RunecraftingTask(player, data, getEssenceId(player)));
		craftRunes(player, data, getEssenceId(player));
		return true;
	}

	private static int getEssenceId(Player player) {
		if (player.getInventory().contains(7936)) {
			return 7936;
		}
		if (player.getInventory().contains(1436)) {
			return 1436;
		}
		return -1;
	}

	private static boolean meetsRequirements(Player player, RunecraftingData rune, GameObject object) {
		if(!player.getClickDelay().elapsed(2000)) {
			return false;
		}
		if (player.getSkillManager().getMaxLevel(Skill.RUNECRAFTING) < rune.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a runecrafting level of " + rune.getLevelRequired() + " to craft this rune.");
			return false;
		}
		if (getEssenceId(player) == -1) {
			player.getPacketSender().sendMessage("You don't have any essence to craft runes with.");
			return false;
		}
		return true;
	}
	
	private static void craftRunes(Player player, RunecraftingData rune, int essenceId) {
		player.getClickDelay().reset();
		player.performAnimation(new Animation(791));
		player.performGraphic(new Graphic(186, GraphicHeight.HIGH));
		
		int amount = player.getInventory().getAmount(essenceId);
		
		if (essenceId == 7936) {
			amount *= 2;
		}

		player.getSkillManager().addExperience(Skill.RUNECRAFTING, amount * rune.getExperience());

		player.getInventory().delete(new Item(essenceId, amount / (essenceId == 7926 ? 2 : 1)));
		player.getInventory().add(new Item(rune.getRuneId(), amount * getMultiplier(player, rune)));
	}
	
	private static int getMultiplier(Player player, RunecraftingData rune) {
		int multiplier = 1;
		for (int i = 1; i < rune.getMultiplier().length; i++) {
			if (player.getSkillManager().getMaxLevel(Skill.RUNECRAFTING) >= rune.getMultiplier()[i]) {
				multiplier = i;
			}
		}

		return multiplier;
	}
	
}
