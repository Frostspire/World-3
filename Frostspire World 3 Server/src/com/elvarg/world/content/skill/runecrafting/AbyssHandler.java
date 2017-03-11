package com.elvarg.world.content.skill.runecrafting;

import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.teleportation.TeleportHandler;
import com.elvarg.world.model.teleportation.TeleportType;

public class AbyssHandler {

	public static boolean clickObject(Player player, int id) {
		switch (id) {
		
		case 25376:// Water
			TeleportHandler.teleport(player, RunecraftingTeleports.WATER.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 24976: // Chaos
			TeleportHandler.teleport(player, RunecraftingTeleports.CHAOS.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 25034: // Law
			TeleportHandler.teleport(player, RunecraftingTeleports.LAW.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 25035: // Death
			TeleportHandler.teleport(player, RunecraftingTeleports.DEATH.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 25377: // Soul
			TeleportHandler.teleport(player, RunecraftingTeleports.SOUL.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 25378: // Air
			TeleportHandler.teleport(player, RunecraftingTeleports.AIR.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 25379: // Mind
			TeleportHandler.teleport(player, RunecraftingTeleports.MIND.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 24972: // Earth
			TeleportHandler.teleport(player, RunecraftingTeleports.EARTH.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 24971: // Fire
			TeleportHandler.teleport(player, RunecraftingTeleports.FIRE.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 25380: // Blood
			TeleportHandler.teleport(player, RunecraftingTeleports.BLOOD.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 24974: // Cosmic
			TeleportHandler.teleport(player, RunecraftingTeleports.COSMIC.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 24975: // Nature
			TeleportHandler.teleport(player, RunecraftingTeleports.NATURE.getPosition(), TeleportType.NORMAL);
			return true;
			
		case 24973: // Body
			TeleportHandler.teleport(player, RunecraftingTeleports.BODY.getPosition(), TeleportType.NORMAL);
			return true;
		}
		return false;
	}
	
}
