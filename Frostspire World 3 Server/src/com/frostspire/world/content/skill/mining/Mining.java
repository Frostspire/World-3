package com.frostspire.world.content.skill.mining;

import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.object.GameObject;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Item;
import com.frostspire.world.model.container.impl.Equipment;

public class Mining {

	public static boolean attemptMining(Player player, GameObject object) {
		OreData ore = OreData.getOre(object.getId());
		
		if(ore == null) {
			return false;
		} else {
			player.setPositionToFace(object.getPosition());
			startMining(player, ore);
			return true;
		}
	}
	
	public static void startMining(Player player, OreData ore) {
		PickaxeData pickaxe = getBestPickaxe(player);
		
		if (!meetsRequirements(player, ore, pickaxe)) {
			return;
		}

		player.getPacketSender().sendMessage("You swing your pickaxe at the rocks..");
		//player.performAnimation(pickaxe.getAnimation());
		player.setCurrentSkillingTask(new MiningTask(player, ore, pickaxe));
		
	}
	
	public static PickaxeData getBestPickaxe(Player player) {
		PickaxeData pickaxe = null;
		
		if(PickaxeData.getPickaxe(player.getEquipment().get(Equipment.WEAPON_SLOT).getId()) != null) {
			pickaxe = PickaxeData.getPickaxe(player.getEquipment().get(Equipment.WEAPON_SLOT).getId());
		}	
		
		for(Item item : player.getInventory().getItems()) {
			PickaxeData newPickaxe = PickaxeData.getPickaxe(item.getId());
			if(newPickaxe != null) {
				if(pickaxe == null) {
					pickaxe = newPickaxe;
				} else if(((newPickaxe.ordinal() > newPickaxe.ordinal()) && meetsPickaxeRequirements(player, newPickaxe)) || !meetsPickaxeRequirements(player, pickaxe)) {
					pickaxe = newPickaxe;
				}
			}
		}
		return pickaxe;
	}
	
	private static boolean meetsPickaxeRequirements(Player player, PickaxeData data) {
		if (data == null) {
			return false;
		}
		if (player.getSkillManager().getCurrentLevel(Skill.MINING) < data.getLevelRequired()) {
			return false;
		}
		return true;
	}
	
	private static boolean meetsRequirements(Player player, OreData ore, PickaxeData pickaxe) {
		if(pickaxe == null) {
			player.getPacketSender().sendMessage("This requires an pickaxe.");
			return false;
		}
		
		if(!meetsPickaxeRequirements(player, pickaxe)) {
			player.getPacketSender().sendMessage("You need a mining level of "+pickaxe.getLevelRequired()+" to use this pickaxe.");
			return false;
		}
		
		if (player.getSkillManager().getCurrentLevel(Skill.MINING) < ore.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a mining level of " + ore.getLevelRequired() + " to mine this ore.");
			return false;
		}

		if (player.getInventory().getFreeSlots() == 0) {
			player.performAnimation(new Animation(65535));
			player.getPacketSender().sendMessage("You don't have enough inventory space to cut this.");
			return false;
		}
		return true;
	}
	
	public static int getMiningTimer(Player player, PickaxeData pickaxe) {
		int skillReducement = (int) (player.getSkillManager().getMaxLevel(Skill.MINING) * 0.05);
		int axeReducement = (int) pickaxe.getSpeed();
		return skillReducement + axeReducement;
	}
	
}
