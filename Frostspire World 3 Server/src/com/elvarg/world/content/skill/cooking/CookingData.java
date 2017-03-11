package com.elvarg.world.content.skill.cooking;

import java.util.HashMap;
import java.util.Map;

public enum CookingData {

	RAW_SHRIMP(317, 1, 34, 315, 323, 30, "shrimp"),
	SARDINE(327, 1, 38, 325, 369, 40, "sardine"),
	ANCHOVIES(321, 1, 34, 319, 323, 50, "anchovie"),
	HERRING(345, 5, 41, 347, 353, 50, "herring"),
	MACKEREL(353, 10, 45, 355, 353, 60, "mackerel"),
	TROUT(335, 15, 50, 333, 343, 70, "trout"),
	COD(341, 18, 52, 339, 343, 75, "cod"),
	PIKE(349, 20, 53, 351, 343, 80, "pike"),
	SALMON(331, 25, 58, 329, 343, 90, "salmon"),
	SLIMY_EEL(3379, 28, 58, 3381, 3383, 95, "slimy eel"),
	TUNA(359, 30, 65, 361, 367, 100, "tuna"),
	KARAMBWAN(3142, 30, 200, 3144, 3148, 190, "karambwan"),
	RAINBOW_FISH(10138, 35, 60, 10136, 10140, 110, "rainbow fish"),
	CAVE_EEL(5001, 38, 40, 4003, 5002, 115, "cave eel"),
	LOBSTER(377, 40, 74, 379, 381, 120, "lobster"),
	BASS(363, 43, 80, 365, 367, 130, "bass"),
	SWORDFISH(371, 45, 86, 373, 375, 140, "swordfish"),
	LAVA_EEL(2148, 53, 53, 2149, 3383, 40, "lava eel"),
	MONKFISH(7944, 62, 92, 7946, 7948, 150, "monkfish"),
	SHARK(383, 80, 99, 385, 387, 210, "shark"),
	SEA_TURTLE(395, 82, 150, 397, 399, 212, "sea turtle"),
	CAVEFISH(15264, 88, 150, 15266, 15268, 214, "cavefish"),
	MANTA_RAY(389, 91, 150, 391, 393, 216, "manta ray"),
	DARK_CRAB(11934, 90, 185, 11936, 11938, 225, "dark crab");

	int rawId;
	int levelRequired;
	int noBurnLevel;
	int cookedId;
	int burntId;
	int experience;
	String name;
	
	public static final int[] cookingRanges = {12269, 2732, 114};	
	
	public static boolean isRange(int object) {
		for(int i : cookingRanges)
			if(object == i)
				return true;
		return false;
	}

	public static CookingData getItem(int id) {
		for(CookingData item : CookingData.values()) {
			if(item.getRawId() == id) {
				return item;
			}
		}
		return null;
	}

	private CookingData(int rawId, int levelRequired, int noBurnLevel, int cookedId, int burntId, int exp, String name) {
		this.rawId = rawId;
		this.levelRequired = levelRequired;
		this.noBurnLevel = noBurnLevel;
		this.experience = exp;
		this.cookedId = cookedId;
		this.burntId = burntId;
		this.name = name;
	}

	public int getBurntId() {
		return burntId;
	}

	public int getExperience() {
		return experience;
	}

	public int getRawId() {
		return rawId;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public int getNoBurnLevel() {
		return noBurnLevel;
	}

	public int getCookedId() {
		return cookedId;
	}
	
	public String getName() {
		return name;
	}
}
