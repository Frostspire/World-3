package com.elvarg.world.content.skill.smithing;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Item;

public class Smelting {

	public static boolean attemptSmelting(Player player, GameObject object) {
		if(isFurnace(object.getId())) {
			for(Item item : player.getInventory().getItems()) {
				if(isOre(item.getId())) {
					openSmeltInterface(player);
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean attemptSmelting(Player player, int itemId) {
		if(isOre(itemId)) {
			openSmeltInterface(player);
			return true;
		}
		return false;
	}
	
	public static boolean handleButtonClick(Player player, int buttonId) {
		int barId = -1;
		int amount = 0;
		switch(buttonId) {
		/** Bronze bar actions **/
		case 3987:
			barId = 2349;
			amount = 1;
			break;
		case 3986:
			barId = 2349;
			amount = 5;
			break;
		case 2807:
			barId = 2349;
			amount = 10;
			break;
		case 2414:
			barId = 2349;
			amount = 28;
			break;
		
		/** Iron bar actions **/
		case 3991:
			barId = 2351;
			amount = 1;
			break;
		case 3990:
			barId = 2351;
			amount = 5;
			break;
		case 3989:
			barId = 2351;
			amount = 10;
			break;
		case 3988:
			barId = 2351;
			amount = 28;
			break;
		
		/** Silver bar actions **/
		case 3995:
			barId = 2355;
			amount = 1;
			break;
		case 3994:
			barId = 2355;
			amount = 5;
			break;
		case 3993:
			barId = 2355;
			amount = 10;
			break;
		case 3992:
			barId = 2355;
			amount = 28;
			break;
			
		/** Steel bar actions **/
		case 3999:
			barId = 2353;
			amount = 1;
			break;
		case 3998:
			barId = 2353;
			amount = 5;
			break;
		case 3997:
			barId = 2353;
			amount = 10;
			break;
		case 3996:
			barId = 2353;
			amount = 28;
			break;
			
		/** Gold bar actions **/
		case 4003:
			barId = 2357;
			amount = 1;
			break;
		case 4002:
			barId = 2357;
			amount = 5;
			break;
		case 4001:
			barId = 2357;
			amount = 10;
			break;
		case 4000:
			barId = 2357;
			amount = 28;
			break;
			
		/** Mithril bar actions **/
		case 7441:
			barId = 2359;
			amount = 1;
			break;
		case 7440:
			barId = 2359;
			amount = 5;
			break;
		case 6397:
			barId = 2359;
			amount = 10;
			break;
		case 4158:
			barId = 2359;
			amount = 28;
			break;
			
		/** Adamant bar actions **/
		case 7446:
			barId = 2361;
			amount = 1;
			break;
		case 7444:
			barId = 2361;
			amount = 5;
			break;
		case 7443:
			barId = 2361;
			amount = 10;
			break;
		case 7442:
			barId = 2361;
			amount = 28;
			break;
			
		/** Rune bar actions **/
		case 7450:
			barId = 2363;
			amount = 1;
			break;
		case 7449:
			barId = 2363;
			amount = 5;
			break;
		case 7448:
			barId = 2363;
			amount = 10;
			break;
		case 7447:
			barId = 2363;
			amount = 28;
			break;
		}
		
		BarData bar = BarData.getBar(barId);
		if(bar == null) {
			return false;
		} else if(meetsRequirements(player, bar)) {
			player.getPacketSender().sendInterfaceRemoval();
			player.setCurrentSkillingTask(new SmeltBarTask(player, bar, amount));
			return true;
		}
		return false;
	}
	
	//make void meetsRequirements() to get for level and ore requirements
	public static boolean meetsRequirements(Player player, BarData bar) {
		if(player.getSkillManager().getMaxLevel(Skill.SMITHING) < bar.getLevelRequired()) {
			player.getPacketSender().sendInterfaceRemoval();
			player.getPacketSender().sendMessage("You need a smithing level of "+bar.getLevelRequired()+" to smelt this bar.");
			return false;
		}
		
		if(!hasOres(player, bar)) {
			player.getPacketSender().sendInterfaceRemoval();
			return false;
		}
		
		return true;
	}
	
	public static boolean hasOres(Player player, BarData bar) {
		
		for(Item item : bar.getRequiredOres()) {
			if(player.getInventory().getAmount(item.getId()) < item.getAmount()) {
				player.getPacketSender().sendMessage("You don't have enough "+ItemDefinition.forId(item.getId()).getName()+" to smelt this bar.");
				return false;
			}
		}
		
		return true;
	}
	
	public static void openSmeltInterface(Player player) {
		player.getSkillManager().stopSkilling();
		player.getPacketSender().sendInterfaceRemoval();
		
		for (int j = 0; j < BarData.getBars().length; j++)
			player.getPacketSender().sendInterfaceModel(BarData.getSmeltFrames()[j], BarData.getBars()[j], 150);
		player.getPacketSender().sendChatboxInterface(2400);
	}
	
	public static boolean isOre(int itemId) {
		for(int i : ORE) {
			if(itemId == i) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isFurnace(int objectId) {
		for(int i : FURNACES) {
			if(i == objectId) {
				return true;
			}
		}
		return false;
	}
	
	private static final int[] FURNACES = { 16469 };
	private static final int[] ORE = {436, 668, 440, 442, 444, 446, 447, 449, 451, 453};
	
}
