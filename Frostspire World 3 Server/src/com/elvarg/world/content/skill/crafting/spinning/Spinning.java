package com.elvarg.world.content.skill.crafting.spinning;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Item;

public class Spinning {
	
	private static final int SPINNING_WHEEL = 4309;
	
	public static boolean attemptSpinning(Player player, GameObject object) {
		if(object.getId() != SPINNING_WHEEL) {
			return false;
		}
		
		SpinningData item = null;
		
		for(Item i : player.getInventory().getItems()) {
			if(SpinningData.getItem(i.getId()) != null) {
				if(stealthMeetsRequirements(player, SpinningData.getItem(i.getId()))) {
					item = SpinningData.getItem(i.getId());
					break;
				} else if(item == null) {
					item = SpinningData.getItem(i.getId());
				}
			}
		}
		
		if(item == null) {
			player.getPacketSender().sendMessage("You don't have anything to spin!");
			return true;
		} else if(meetsRequirements(player, item)) {
			player.getSkillManager().stopSkilling();
			openSpinningInterface(player, item);
			return true;
		} else {
			return true;
		}
	}
	
	private static void openSpinningInterface(Player player, SpinningData item) {
		player.setSkillingItem(new Item(item.getIngredientId()));
		player.getPacketSender().sendString(2799, ItemDefinition.forId(item.getProductId()).getName()).sendInterfaceModel(1746, item.getProductId(), 150).sendChatboxInterface(4429);
		player.getPacketSender().sendString(2800, "How many would you like to make?");
	}
	
	public static boolean handleButtonClick(Player player, int buttonId) {
		
		if(player.getSkillingItem() == null) {
			return false;
		}
		
		SpinningData item = SpinningData.getItem(player.getSkillingItem().getId());
		
		if(item == null) {
			return false;
		}
		
		player.getPacketSender().sendInterfaceRemoval();
		
		if(meetsRequirements(player, item)) {
		
			switch(buttonId) {
			case 2799:
				player.setCurrentSkillingTask(new SpinningTask(player, item, 1));
				return true;
			case 2798:
				player.setCurrentSkillingTask(new SpinningTask(player, item, 5));
				return true;
			case 1748:
				player.setCurrentSkillingTask(new SpinningTask(player, item, 10));
				return true;
			case 1747:
				player.setCurrentSkillingTask(new SpinningTask(player, item, 28));
				return true;
			}
			
		}
		
		return false;
	}
	
	private static boolean meetsRequirements(Player player, SpinningData item) {
		if(player.getSkillManager().getMaxLevel(Skill.CRAFTING) < item.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a crafting level of "+item.getLevelRequired()+" to spin this item.");
			return false;
		}
		return true;
	}
	
	private static boolean stealthMeetsRequirements(Player player, SpinningData item) {
		if(player.getSkillManager().getMaxLevel(Skill.CRAFTING) < item.getLevelRequired()) {
			return false;
		}
		return true;
	}

}
