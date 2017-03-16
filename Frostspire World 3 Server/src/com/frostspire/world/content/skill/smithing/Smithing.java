package com.frostspire.world.content.skill.smithing;

import com.frostspire.cache.impl.definitions.ItemDefinition;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.object.GameObject;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Item;

public class Smithing {
	
	public static boolean attemptSmithing(Player player, int itemId, GameObject object) {
		if(isAnvil(object.getId())) {
			player.getSkillManager().stopSkilling();
			BarData bar = null;
			if(itemId == 2347) {
				bar = findBar(player);
			} else {
				if(!player.getInventory().contains(2347)) {
					player.getPacketSender().sendMessage("You need a hammer to do this.");
					return true;
				}
				bar = BarData.getBar(itemId);
			}
			
			if(bar == null) {
				return false;
			} else {
				player.setPositionToFace(object.getPosition());
				openSmithingInterface(player, bar.getResult().getId());
			}
			
		}
		return false;
	}
	
	public static BarData findBar(Player player) {
		BarData bar = null;
		for(Item item : player.getInventory().getItems()) {
			if(BarData.getBar(item.getId()) != null) {
				bar = BarData.getBar(item.getId());
			}
		}
		return bar;
	}
	
	public static boolean handleItemClick(Player player, int itemId, int amount) {
		SmithingData item = SmithingData.getItem(itemId);
		if(item == null) {
			return false;
		} else if(meetsRequirements(player, item)) {
			if(!player.getClickDelay().elapsed(2000)) {
				return true;
			}
			player.getClickDelay().reset();
			player.getPacketSender().sendInterfaceRemoval();
			player.setCurrentSkillingTask(new SmithingTask(player, item, item.getBar().getId(), amount));
			return true;
		}
		return false;
	}
	
	public static boolean meetsRequirements(Player player, SmithingData item) {
		if(player.getSkillManager().getMaxLevel(Skill.SMITHING) < item.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a smithing level of "+item.getLevelRequired()+" to smith this item.");
			return false;
		}
		if(player.getInventory().getAmount(item.getBar().getId()) < item.getBarsRequired()) {
			player.getPacketSender().sendMessage("You don't have enough "+ItemDefinition.forId(item.getBar().getId()).getName()+"s to smith this time.");
			return false;
		}
		return true;
	}
	
	public static void openSmithingInterface(Player player, int barId) {
		
		player.getSkillManager().stopSkilling();
		player.getPacketSender().sendInterfaceRemoval();
		
		SmithingData[] items = SmithingData.getItems(ItemDefinition.forId(barId).getName().substring(0, 3));
		
		if(items[0] == null) {
			return;
		}
		
		//add interface text. this is very ugly code
		String fiveb = GetForBars(barId, 5, player);
		String threeb = GetForBars(barId, 3, player);
		String twob = GetForBars(barId, 2, player);
		String oneb = GetForBars(barId, 1, player);
		player.getPacketSender().sendString(1112, fiveb+"5 Bars");
		player.getPacketSender().sendString(1109, threeb+"3 Bars");
		player.getPacketSender().sendString(1110, threeb+"3 Bars");
		player.getPacketSender().sendString(1118, threeb+"3 Bars");
		player.getPacketSender().sendString(1111, threeb+"3 Bars");
		player.getPacketSender().sendString(1095, threeb+"3 Bars");
		player.getPacketSender().sendString(1115, threeb+"3 Bars");
		player.getPacketSender().sendString(1090, threeb+"3 Bars");
		player.getPacketSender().sendString(1113, twob+"2 Bars");
		player.getPacketSender().sendString(1116, twob+"2 Bars");
		player.getPacketSender().sendString(1114, twob+"2 Bars");
		player.getPacketSender().sendString(1089, twob+"2 Bars");
		player.getPacketSender().sendString(8428, twob+"2 Bars");
		player.getPacketSender().sendString(1124, oneb+"1 Bar");
		player.getPacketSender().sendString(1125, oneb+"1 Bar");
		player.getPacketSender().sendString(1126, oneb+"1 Bar");
		player.getPacketSender().sendString(1127, oneb+"1 Bar");
		player.getPacketSender().sendString(1128, oneb+"1 Bar");
		player.getPacketSender().sendString(1129, oneb+"1 Bar");
		player.getPacketSender().sendString(1130, oneb+"1 Bar");
		player.getPacketSender().sendString(1131, oneb+"1 Bar");
		player.getPacketSender().sendString(13357, oneb+"1 Bar");
		player.getPacketSender().sendString(11459, oneb+"1 Bar");
		player.getPacketSender().sendString(1101, hasLevelForItem(player, items[13])+"Plate Body");
		player.getPacketSender().sendString(1099, hasLevelForItem(player, items[11])+"Plate Legs");
		player.getPacketSender().sendString(1100, hasLevelForItem(player, items[12])+"Plate Skirt");
		player.getPacketSender().sendString(1088, hasLevelForItem(player, items[4])+"2 Hand Sword");
		player.getPacketSender().sendString(1105, hasLevelForItem(player, items[17])+"Kite Shield");
		player.getPacketSender().sendString(1098, hasLevelForItem(player, items[10])+"Chain Body");
		player.getPacketSender().sendString(1092, hasLevelForItem(player, items[8])+"Battle Axe");
		player.getPacketSender().sendString(1083, hasLevelForItem(player, items[7])+"Warhammer");
		player.getPacketSender().sendString(1104, hasLevelForItem(player, items[16])+"Square Shield");
		player.getPacketSender().sendString(1103, hasLevelForItem(player, items[15])+"Full Helm");
		player.getPacketSender().sendString(1106, hasLevelForItem(player, items[21])+"Throwing Knives");
		player.getPacketSender().sendString(1086, hasLevelForItem(player, items[3])+"Long Sword");
		player.getPacketSender().sendString(1087, hasLevelForItem(player, items[2])+"Scimitar");
		player.getPacketSender().sendString(1108, hasLevelForItem(player, items[20])+"Arrowtips");
		player.getPacketSender().sendString(1085, hasLevelForItem(player, items[1])+"Sword");
		player.getPacketSender().sendString(1096, hasLevelForItem(player, items[22])+"Bolts");
		player.getPacketSender().sendString(13358, hasLevelForItem(player, items[18])+"Nails");
		player.getPacketSender().sendString(1102, hasLevelForItem(player, items[14])+"Medium Helm");
		player.getPacketSender().sendString(1093, hasLevelForItem(player, items[6])+"Mace");
		player.getPacketSender().sendString(1094, hasLevelForItem(player, items[0])+"Dagger");
		player.getPacketSender().sendString(1091, hasLevelForItem(player, items[5])+"Axe");
		player.getPacketSender().sendString(8429, hasLevelForItem(player, items[9]) + "Claws");
		player.getPacketSender().sendString(1107, hasLevelForItem(player, items[19]) + "Dart tips");
		
		//loop through items and add them to interface
		int itemCount = 0;
		
		for (int i = 0; i < 5; i++) {
			for(int b = 0; b < 5; b++) {
				if(i == 2 && b == 4) { //skip the empty space on column 3 row 5
					continue;
				}
				if(itemCount > 22) { //stop after last item, there is 1 empty space
					break;
				}
				player.getPacketSender().sendItemOnInterface(1119+i, items[itemCount].getItem().getId(), b, items[itemCount].getItem().getAmount());
				itemCount++;
			}
		}
		
		player.getPacketSender().sendString(1135, "");
		player.getPacketSender().sendString(1134, "");
		player.getPacketSender().sendString(11461, "");
		player.getPacketSender().sendString(11459, "");
		player.getPacketSender().sendString(1132, "");
		player.getPacketSender().sendString(1096, "");

		player.getPacketSender().sendInterface(994);
	}
	
	public static String hasLevelForItem(Player player, SmithingData item) {
		if(player.getSkillManager().getMaxLevel(Skill.SMITHING) < item.getLevelRequired()) {
			return "@bla@";
		} else {
			return "@whi@";
		}
	}

	private static String GetForBars(int i, int j, Player player) {
		if (player.getInventory().getAmount(i) >= j)
			return "@gre@";
		return "@red@";
	}
	
	public static boolean isAnvil(int objectId) {
		for(int i : ANVILS) {
			if(i == objectId) {
				return true;
			}
		}
		return false;
	}
	
	private static final int[] ANVILS = { 2097 };
	
	public static final int SMITHING_INTERFACE_ROW_1 = 1119;
	public static final int SMITHING_INTERFACE_ROW_2 = 1120;
	public static final int SMITHING_INTERFACE_ROW_3 = 1121;
	public static final int SMITHING_INTERFACE_ROW_4 = 1122;
	public static final int SMITHING_INTERFACE_ROW_5 = 1123;
	
}
