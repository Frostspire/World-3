package com.elvarg.world.content.skill.fishing;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.util.Misc;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.npc.NPC;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;

public class Fishing {
	
	public static boolean setupFishing(Player player, NPC npc, int option) {
		FishingSpots spot = FishingSpots.getSpot(npc.getId(), option);
		if(spot == null) {
			return false;
		} else {
			player.setPositionToFace(npc.getPosition());
			startFishing(player, spot);
			return true;
		}
	}
	
	public static boolean canFish(Player player, FishingSpots spot) {
		if(player.getInventory().getFreeSlots() <= 0) {
			player.getPacketSender().sendMessage("You do not have any free inventory space.");
			player.getSkillManager().stopSkilling();
			return false;
		}
		
		if (player.getSkillManager().getCurrentLevel(Skill.FISHING) >= spot.getLevelReq()[0]) {
			if (player.getInventory().contains(spot.getEquipment())) {
				if (spot.getBait() != -1) {
					if (player.getInventory().contains(spot.getBait())) {
						return true;
					} else {
						String baitName = ItemDefinition.forId(spot.getBait()).getName();
						if(baitName.contains("Feather") || baitName.contains("Red Vine Worm"))
							baitName += "s";
						player.getPacketSender().sendMessage("You need some "+baitName+" to fish here.");
						player.performAnimation(new Animation(65535));
					}
				} else {
					return true;
				}
			} else {
				String def = ItemDefinition.forId(spot.getEquipment()).getName().toLowerCase();
				player.getPacketSender().sendMessage("You need "+Misc.anOrA(def)+" "+def+" to fish here.");
			}
		} else {
			player.getPacketSender().sendMessage("You need a fishing level of at least "+spot.getLevelReq()[0]+" to fish here.");
		}
		
		return false;
		
	}
	
	public static void startFishing(final Player player, final FishingSpots spot) {
		
		player.getSkillManager().stopSkilling();
		
		if(canFish(player, spot)) {
			player.performAnimation(new Animation(spot.getAnim()));
			player.setCurrentSkillingTask(new FishingTask(player, spot));
		}
		
	}
	
	
	public static final int SHRIMP = 317;
	public static final int ANCHOVIES = 321;
	public static final int MACKEREL = 353;
	public static final int BASS = 363;
	public static final int COD = 341;
	public static final int SARDINE = 327;
	public static final int HERRING = 345;
	public static final int PIKE = 349;
	public static final int TROUT = 335;
	public static final int SALMON = 331;
	public static final int LOBSTER = 377;
	public static final int TUNA = 359;
	public static final int SWORDFISH = 371;
	public static final int MONKFISH = 7944;
	public static final int SHARK = 383;
	public static final int MANTA_RAY = 389;
	public static final int DARK_CRAB = 11934;
	public static final int KARAMBWAN = 3142;
	
	public static final int LOBSTER_POT = 301;
	public static final int SMALL_FISHING_NET = 303;
	public static final int LARGE_FISHING_NET = 305;
	public static final int FISHING_ROD = 307;
	public static final int FLY_FISHING_ROD = 309;
	public static final int HARPOON = 311;
	public static final int FISHING_BAIT = 313;
	public static final int DARK_FISHING_BAIT = 11940;
	public static final int KARAMBWAN_VESSEL = 3157;
	public static final int FEATHER = 314;

}
