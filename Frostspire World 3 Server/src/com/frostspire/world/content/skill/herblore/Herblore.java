package com.frostspire.world.content.skill.herblore;

import com.frostspire.cache.impl.definitions.ItemDefinition;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Item;
import com.frostspire.world.model.Priority;

public class Herblore {
	
	public static boolean attemptPotionMaking(Player player, Item first, Item second) {
		if ((ItemDefinition.forId(first.getId()).getName().contains("(unf)")) || (ItemDefinition.forId(second.getId()).getName().contains("(unf)"))) {
			
			FinishedPotionData potion = FinishedPotionData.getPotion(first);
			if (potion == null) {
				potion = FinishedPotionData.getPotion(second);
			}
			if(potion == null) {
				return false;
			} else {
				if(player.getClickDelay().elapsed(1000)) {
					if(meetsFinishedPotionRequirements(player, potion)) {
						createFinishedPotion(player, potion);
						return true;
					}
				} else {
					return true;
				}
			}
		}
		
		if ((ItemDefinition.forId(second.getId()).getName().contains("weed") || ItemDefinition.forId(second.getId()).getName().contains("leaf")) ||
				(ItemDefinition.forId(first.getId()).getName().contains("weed") || ItemDefinition.forId(first.getId()).getName().contains("leaf"))) {
			
			UnfinishedPotionData potion = first.getId() == VIAL_OF_WATER ? UnfinishedPotionData.getPotion(second) : UnfinishedPotionData.getPotion(first);
			if (potion == null) {
				return false;
			} else {
				if(player.getClickDelay().elapsed(1000)) {
					if(meetsUnfinishedPotionRequirements(player, potion)) {
						createUnfinishedPotion(player, potion);
						return true;
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean attemptCleanHerb(Player player, Item item) {
		if (ItemDefinition.forId(item.getId()).getName().contains("Grimy")) {
			HerbData herb = HerbData.getHerb(item);
			if(herb == null) {
				return false;
			} else {
				if(player.getClickDelay().elapsed(1000)) {
					player.getClickDelay().reset();
					cleanHerb(player, item, herb);
					return true;
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean meetsFinishedPotionRequirements(Player player, FinishedPotionData potion) {
		if (player.getSkillManager().getCurrentLevel(Skill.HERBLORE) < potion.getRequirement()) {
			player.getPacketSender().sendMessage("You need a Herblore level of atleast " + potion.getRequirement() + " to make this potion.");
			return false;
		}
		return true;
	}
	
	public static boolean meetsUnfinishedPotionRequirements(Player player, UnfinishedPotionData potion) {
		if (player.getSkillManager().getCurrentLevel(Skill.HERBLORE) < potion.getRequirement()) {
			player.getPacketSender().sendMessage("You need a Herblore level of atleast " + potion.getRequirement() + " to make this potion.");
			return false;
		}
		return true;
	}
	
	public static boolean meetsHerbRequirements(Player player, HerbData herb) {
		if (player.getSkillManager().getCurrentLevel(Skill.HERBLORE) < herb.getRequired()) {
			player.getPacketSender().sendMessage("You need a Herblore level of atleast " + herb.getRequired() + " to clean this herb.");
			return false;
		}
		return true;
	}
	
	public static void createFinishedPotion(Player player, FinishedPotionData potion) {
		player.performAnimation(CREATE_POTION_ANIMATION);
		player.getInventory().delete(potion.getIngredient());
		player.getInventory().delete(potion.getUnfinishedPotion());
		player.getInventory().add(potion.getFinishedPotion());
		player.getSkillManager().addExperience(Skill.HERBLORE, potion.getExperience());
		player.getPacketSender().sendMessage("You mix the " + ItemDefinition.forId(potion.getIngredient().getId()).getName().toLowerCase() + " into your potion.");
	}
	
	public static void createUnfinishedPotion(Player player, UnfinishedPotionData potion) {
		player.performAnimation(CREATE_POTION_ANIMATION);
		player.getInventory().delete(potion.getIngredient());
		player.getInventory().delete(VIAL_OF_WATER, 1);
		player.getInventory().add(potion.getUnfinished());
		player.getPacketSender().sendMessage("You mix the "+ ItemDefinition.forId(potion.getIngredient().getId()).getName().toLowerCase()+ " into your potion.");
		
	}
	
	public static void cleanHerb(Player player, Item item, HerbData herb) {
		if (meetsHerbRequirements(player, herb)) {
			player.getInventory().delete(herb.getGrimyHerb());
			player.getInventory().add(herb.getCleanHerb());
			player.getSkillManager().addExperience(Skill.HERBLORE, (int) herb.getExperience());
			player.getPacketSender().sendMessage("You clean the dirt off the " + ItemDefinition.forId(herb.getGrimyHerb().getId()).getName().toLowerCase() + ".");
		}
	}
	
	public static final int VIAL_OF_WATER = 227;
	private static final Animation CREATE_POTION_ANIMATION = new Animation(363, Priority.LOW);

}
