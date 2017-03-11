package com.elvarg.world.content.skill.woodcutting;

import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Item;
import com.elvarg.world.model.container.impl.Equipment;

public class Woodcutting {
	
	public static boolean attemptWoodcutting(Player player, GameObject gameObject) {
		TreeData tree = TreeData.getTree(gameObject.getId());
		if (tree == null) {
			return false;
		} else {
			player.setPositionToFace(gameObject.getPosition());
			startWoodcutting(player, tree);
			return true;
		}
	}
	
	public static void startWoodcutting(Player player, TreeData tree) {
		AxeData axe = getBestAxe(player);
		
		if (!meetsRequirements(player, tree, axe)) {
			return;
		}

		player.getPacketSender().sendMessage("You swing your axe at the tree..");
		player.performAnimation(axe.getAnimation());
		player.setCurrentSkillingTask(new WoodcuttingTask(player, tree, axe));
		
	}
	
	public static AxeData getBestAxe(Player player) {
		AxeData axe = null;
		
		if(AxeData.getAxe(player.getEquipment().get(Equipment.WEAPON_SLOT).getId()) != null) {
			axe = AxeData.getAxe(player.getEquipment().get(Equipment.WEAPON_SLOT).getId());
		}	
		
		for(Item item : player.getInventory().getItems()) {
			AxeData newAxe = AxeData.getAxe(item.getId());
			if(newAxe != null) {
				if(axe == null) {
					axe = newAxe;
				} else if(((newAxe.ordinal() > axe.ordinal()) && meetsAxeRequirements(player, newAxe)) || !meetsAxeRequirements(player, axe)) {
					axe = newAxe;
				}
			}
		}
		return axe;
	}
	
	private static boolean meetsAxeRequirements(Player player, AxeData data) {
		if (data == null) {
			return false;
		}
		if (player.getSkillManager().getCurrentLevel(Skill.WOODCUTTING) < data.getLevelRequired()) {
			return false;
		}
		return true;
	}
	
	private static boolean meetsRequirements(Player player, TreeData data, AxeData axe) {
		if(axe == null) {
			player.getPacketSender().sendMessage("This requires an axe.");
			return false;
		}
		
		if(!meetsAxeRequirements(player, axe)) {
			player.getPacketSender().sendMessage("You need a woodcutting level of "+axe.getLevelRequired()+" to use this axe.");
			return false;
		}
		
		if (player.getSkillManager().getCurrentLevel(Skill.WOODCUTTING) < data.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a woodcutting level of " + data.getLevelRequired() + " to cut this tree.");
			return false;
		}

		if (player.getInventory().getFreeSlots() == 0) {
			player.performAnimation(new Animation(65535));
			player.getPacketSender().sendMessage("You don't have enough inventory space to cut this.");
			return false;
		}
		return true;
	}
	
	public static int getChopTimer(Player player, AxeData axe) {
		int skillReducement = (int) (player.getSkillManager().getMaxLevel(Skill.WOODCUTTING) * 0.05);
		int axeReducement = (int) axe.getSpeed();
		return skillReducement + axeReducement;
	}
	
	public static boolean infernoAdze(Player player) {
		return player.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 13661;
	}

}
