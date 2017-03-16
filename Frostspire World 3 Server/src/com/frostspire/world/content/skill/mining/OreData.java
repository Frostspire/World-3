package com.frostspire.world.content.skill.mining;

public enum OreData {
	COPPER("Copper ore", new int[] { 7487, 7454, 7453 }, 1, 17, new int[] { 436 }, 4),
	TIN("Tin ore", new int[] { 7457, 7490 }, 1, 17, new int[] { 438 }, 4),
	IRON("Iron ore", new int[] { 7455, 7488 }, 15, 35, new int[] { 440 }, 5),
	SILVER_ORE("Silver ore", new int[] { 13716, 13717 }, 20, 40, new int[] { 442 }, 5),
	COAL_ORE("Coal ore", new int[] { 7456, 7489 }, 30, 50, new int[] { 453 }, 5),
	GOLD_ORE("Gold ore", new int[] { 7491, 7458 }, 40, 65, new int[] { 444 }, 5),
	MITHRIL_ORE("Mithril ore", new int[] { 7459, 7492 }, 55, 80, new int[] { 447 }, 6),
	ADAMANT_ORE("Adamant ore", new int[] { 7460, 7493}, 70, 95, new int[] { 449 }, 7),
	RUNITE_ORE("Runite ore", new int[] { 7461 }, 85, 125, new int[] { 451 }, 8),
	PURE_ESSENCE("Essence", new int[] { 14912, 2491 }, 30, 10, new int[] { 1436 }, 2),
	GEM_ROCK("Gem Rock", new int[] { 14856, 14855, 14854 }, 40, 65, new int[] { 1625, 1627, 1629, 1623, 1621, 1619, 1617 }, 10);
	
	private final String name;
	private int[] objects;
	private final int level;
	private final int exp;
	private final int[] ore;
	private int ticks;
	
	private OreData(String name, int[] objects, int level, int exp, int[] ore, int ticks) {
		this.name = name;
		this.objects = objects;
		this.level = level;
		this.exp = exp;
		this.ore = ore;
		this.ticks = ticks;
	}
	
	public String getName() {
		return name;
	}

	public int getLevelRequired() {
		return level;
	}

	public int getExperience() {
		return exp;
	}

	public int[] getOre() {
		return ore;
	}
	
	public int[] getObjects() {
		return objects;
	}
	
	public int getTicks() {
		return ticks;
	}
	
	public static OreData getOre(int objectId) {
		for(OreData ore : OreData.values()) {
			for(int oreId : ore.getObjects()) {
				if(oreId == objectId) {
					return ore;
				}
			}
		}
		return null;
	}

}
