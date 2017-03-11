package com.elvarg.world.content.skill;

import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Position;
import com.elvarg.world.model.teleportation.TeleportHandler;
import com.elvarg.world.model.teleportation.TeleportType;

public enum SkillTeleportHandler {
	//ATTACK(24211, 3094, 3504),
	//HITPOINTS(24212, 3094, 3504),
	MINING(24213, 3300, 3310),
	//STRENGTH(24214, 3094, 3504),
	AGILITY(24215, 3094, 3504),
	SMITHING(24216, 3107, 3499),
	//DEFENCE(24217, 3094, 3504),
	HERBLORE(24218, 3094, 3504),
	FISHING(24219, 2597, 3409),
	//RANGED(24220, 3094, 3504),
	THIEVING(24221, 3085, 3491),
	COOKING(24222, 3079, 3495),
	PRAYER(24223, 3092, 3505),
	CRAFTING(24224, 2933, 3286),
	FIREMAKING(24225, 2725, 3485),
	//MAGIC(24226, 3094, 3504),
	FLETCHING(24227, 3094, 3504),
	WOODCUTTING(24228, 2721, 3472),
	RUNECRAFTING(24229, 3094, 3504),
	SLAYER(24230, 3094, 3504),
	FARMING(24231, 3094, 3504),
	HUNTER(24232, 3094, 3504);
	
	int buttonId, teleX, teleY;
	
	private SkillTeleportHandler(int buttonId, int teleX, int teleY) {
		this.buttonId = buttonId;
		this.teleX = teleX;
		this.teleY = teleY;
	}
	
	public int getButtonId() {
		return buttonId;
	}
	
	public int getTeleX() {
		return teleX;
	}
	
	public int getTeleY() {
		return teleY;
	}
	
	public static SkillTeleportHandler getItem(int id) {
		for(SkillTeleportHandler item : SkillTeleportHandler.values()) {
			if(item.getButtonId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public static boolean handleButton(Player player, int buttonId) {
		SkillTeleportHandler button = getItem(buttonId);
		if(button != null) {
			player.getPacketSender().sendInterfaceRemoval();
			TeleportHandler.teleport(player, new Position(button.getTeleX(), button.getTeleY()), TeleportType.NORMAL);
			return true;
		}
		return false;
	}

}
