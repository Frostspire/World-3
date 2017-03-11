package com.elvarg.world.content.skill.crafting.jewelry;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Item;

public class Jewelry {

	public static boolean attemptJewelryMaking(Player player, GameObject object) {
		if(isFurnace(object.getId())) {
			for(Item item : player.getInventory().getItems()) {
				if(item.getId() == RING_MOULD || item.getId() == NECKLACE_MOULD || item.getId() == AMULET_MOULD) {
					openJewelryInterface(player);
					return true;
				}
			}
		}
		return false;
	}
	
	public static void handleItemClick(Player player, int itemId, int amount) {
		JewelryData jewelry = JewelryData.getJewelry(itemId);
		if(jewelry == null) {
			return;
		} else if(meetsRequirements(player, jewelry)) {
			if(!player.getClickDelay().elapsed(2000)) {
				return;
			}
			player.getClickDelay().reset();
			player.getPacketSender().sendInterfaceRemoval();
			player.setCurrentSkillingTask(new JewelryTask(player, jewelry, amount));
		}
	}
	
	private static boolean meetsRequirements(Player player, JewelryData jewelry) {
		if(player.getSkillManager().getMaxLevel(Skill.CRAFTING) < jewelry.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a crafting level of "+jewelry.getLevelRequired()+" to craft this item.");
			return false;
		}
		
		for(int itemRequired : jewelry.getMaterialsRequired()) {
			if(!player.getInventory().contains(itemRequired)) {
				player.getPacketSender().sendMessage("You need a "+ItemDefinition.forId(itemRequired).getName()+" to craft this item.");
				return false;
			}
		}
		
		return true;
	}
	
	private static void openJewelryInterface(Player player) {
		for (int k = 0; k < 3; k++) {
			int interfaceId = JEWELRY_INTERFACE_ROW_1;

			if (k == 1)
				interfaceId = JEWELRY_INTERFACE_ROW_2;
			else if (k == 2) {
				interfaceId = JEWELRY_INTERFACE_ROW_3;
			}
			if(k == 0 && !player.getInventory().contains(RING_MOULD)) {
				continue;
			}
			if(k == 1 && !player.getInventory().contains(NECKLACE_MOULD)) {
				continue;
			}
			if(k == 2 && !player.getInventory().contains(AMULET_MOULD)) {
				continue;
			}
			for (int i = 0; i < JEWELRY_INTERFACE_ITEMS[k].length; i++) {
				//p.getClient().queueOutgoingPacket(new SendUpdateItemsAlt(interfaceId, JEWELRY_INTERFACE_ITEMS[k][i], 1, i));
				player.getPacketSender().sendItemOnInterface(interfaceId, JEWELRY_INTERFACE_ITEMS[k][i], i, 1);
			}
		}
		
		if(player.getInventory().contains(RING_MOULD)) {
			player.getPacketSender().sendInterfaceModel(4229, -1, 0);
			player.getPacketSender().sendString(4230, "");
		} else {
			player.getPacketSender().sendString(4230, "You need a ring mould to craft rings.");
		}
		
		if(player.getInventory().contains(NECKLACE_MOULD)) {
			player.getPacketSender().sendInterfaceModel(4235, -1, 0);
			player.getPacketSender().sendString(4236, "");
		} else {
			player.getPacketSender().sendString(4236, "You need a necklace mould to craft necklaces.");
		}
		
		if(player.getInventory().contains(AMULET_MOULD)) {
			player.getPacketSender().sendInterfaceModel(4241, -1, 0);
			player.getPacketSender().sendString(4242, "");
		} else {
			player.getPacketSender().sendString(4242, "You need an amulet mould to craft amulets.");
		}

		player.getPacketSender().sendInterface(4161);
	}
	
	public static final int RING_MOULD = 1592;
	public static final int NECKLACE_MOULD = 1597;
	public static final int AMULET_MOULD = 1595;
	
	public static final int[][] JEWELRY_INTERFACE_ITEMS = { 
			{ 1635, 1637, 1639, 1641, 1643, 1645, 6575 }, 
			
			{ 1654, 1656, 1658, 1660, 1662, 1664, 6577 }, 
			
			{ 1673, 1675, 1677, 1679, 1681, 1683, 6579 } 
		};
	
	public static boolean isFurnace(int objectId) {
		for(int i : FURNACES) {
			if(i == objectId) {
				return true;
			}
		}
		return false;
	}
	
	private static final int[] FURNACES = { 16469 };
	
	public static final int JEWELRY_INTERFACE_ROW_1 = 4233;
	public static final int JEWELRY_INTERFACE_ROW_2 = 4239;
	public static final int JEWELRY_INTERFACE_ROW_3 = 4245;
	
}
