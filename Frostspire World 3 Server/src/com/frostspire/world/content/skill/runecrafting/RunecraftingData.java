package com.frostspire.world.content.skill.runecrafting;

public enum RunecraftingData {
	
	AIR(14897, 556, 5, new int[] { 1, 11, 22, 33, 44, 55, 66, 77, 88, 99 }),
	MIND(14898, 558, 6, new int[] { 1, 14, 28, 42, 56, 70, 84, 98 }),
	WATER(14899, 555, 7, new int[] { 5, 19, 38, 57, 76, 95 }),
	EARTH(14900, 557, 8, new int[] { 9, 26, 52, 78 }),
	FIRE(14901, 554, 9, new int[] { 14, 35, 70 }),
	BODY(14902, 559, 10, new int[] { 20, 46, 92 }),
	COSMIC(14903, 564, 11, new int[] { 27, 59 }),
	CHAOS(14906, 562, 12, new int[] { 35, 74 }),
	NATURE(14905, 561, 13, new int[] { 44, 91 }),
	LAW(14904, 563, 14, new int[] { 54 }),
	DEATH(14907, 560, 15, new int[] { 65 }),
	BLOOD(27978, 565, 24, new int[] { 77 }),
	SOUL(27980, 566, 30, new int[] { 90 });

	private int altarId;
	private int runeId;
	private int xp;

	private int[] multiplier;

	private RunecraftingData(int altarId, int runeId, int xp, int[] multiplier) {
		this.altarId = altarId;
		this.runeId = runeId;
		this.xp = xp;
		this.multiplier = multiplier;
	}

	public int getAltarId() {
		return altarId;
	}

	public int getLevelRequired() {
		return multiplier[0];
	}

	public int[] getMultiplier() {
		return multiplier;
	}

	public int getRuneId() {
		return runeId;
	}

	public int getExperience() {
		return xp;
	}
	
	public static RunecraftingData getRune(int objectId) {
		for(RunecraftingData rune : RunecraftingData.values()) {
			if(rune.getAltarId() == objectId) {
				return rune;
			}
		}
		return null;
	}
}

