package com.frostspire.world.content.skill.fishing;

public enum FishingSpots {

	SMALL_NET(1497, 1, new int[]{Fishing.SHRIMP, Fishing.ANCHOVIES}, Fishing.SMALL_FISHING_NET, -1, new int[]{1, 15}, true, new int[]{100, 400}, 621), //621
	
	BAIT(1497, 2, new int[]{Fishing.SARDINE, Fishing.HERRING, Fishing.PIKE}, Fishing.FISHING_ROD, Fishing.FISHING_BAIT, new int[]{5, 10, 25}, false, new int[]{2980}, 623), //623
	
	LURE(1498, 1, new int[]{Fishing.TROUT, Fishing.SALMON}, Fishing.FLY_FISHING_ROD, Fishing.FEATHER, new int[]{20, 30}, false, new int[]{780, 1130, 2120}, 623),
	
	BAIT2(1498, 2, new int[]{Fishing.SARDINE, Fishing.HERRING, Fishing.PIKE}, Fishing.FISHING_ROD, Fishing.FISHING_BAIT, new int[]{5, 10, 25}, false, new int[]{9000, 10122}, 623),
	
	CAGE(1499, 1, new int[]{Fishing.LOBSTER}, Fishing.LOBSTER_POT, -1, new int[]{40}, false, new int[]{290, 550}, 619), //619
	
	HARPOON(1499, 2, new int[]{Fishing.TUNA, Fishing.SWORDFISH}, Fishing.HARPOON, -1, new int[]{35, 50}, false, new int[]{6985}, 618),
	
	LARGE_NET(1500, 1, new int[]{Fishing.MACKEREL, Fishing.COD, Fishing.BASS}, Fishing.LARGE_FISHING_NET, -1, new int[]{16, 23, 46}, true, new int[]{2530, 5100}, 620), //620
	
	HARPOON2(1500, 2, new int[]{Fishing.SHARK}, Fishing.HARPOON, -1, new int[]{76}, true, new int[]{8547}, 618),
	
	HARPOON3(2028, 1, new int[]{Fishing.MONKFISH}, Fishing.HARPOON, -1, new int[]{62}, true, new int[]{650, 980}, 618),
	
	SMALL_NET2(10091, 2, new int[]{Fishing.TUNA, Fishing.SWORDFISH}, Fishing.SMALL_FISHING_NET, -1, new int[]{35, 50}, false, new int[]{18010}, 621),
	
	MANTA_RAY(2028, 1, new int[]{Fishing.MANTA_RAY}, 307, -1, new int[]{81}, true, new int[]{650, 980}, 618),
	
	DARK_CRAB(2028, 1, new int[]{Fishing.DARK_CRAB}, 307, Fishing.DARK_FISHING_BAIT, new int[]{85}, true, new int[]{650, 980}, 619),
	
	KARAMBWAN(2028, 1, new int[]{Fishing.KARAMBWAN}, 307, -1, new int[]{65}, true, new int[]{650, 980}, 619);

	int npcId, option, equipment, bait, anim;
	int[] rawFish, fishingReqs, xp;
	boolean second;
	private FishingSpots(int npcId, int option, int[] rawFish, int equipment, int bait, int[] fishingReqs, boolean second, int[] xp, int anim) {
		this.npcId = npcId;
		this.option = option;
		this.rawFish = rawFish;
		this.equipment = equipment;
		this.bait = bait;
		this.fishingReqs = fishingReqs;
		this.second = second;
		this.xp = xp;
		this.anim = anim;
	}

	public int getNPCId() {
		return npcId;
	}
	
	public int getOption() {
		return option;
	}

	public int[] getRawFish() {
		return rawFish;
	}

	public int getEquipment() {
		return equipment;
	}

	public int getBait() {
		return bait;
	}

	public int[] getLevelReq() {
		return fishingReqs;
	}

	public boolean getSecond() {
		return second;
	}

	public int[] getXp() {
		return xp;
	}

	public int getAnim() {
		return anim;
	}
	
	public static FishingSpots getSpot(int id, int option) {
		for(FishingSpots item : FishingSpots.values()) {
			if(item.getNPCId() == id && item.getOption() == option) {
				return item;
			}
		}
		return null;
	}
	
}
