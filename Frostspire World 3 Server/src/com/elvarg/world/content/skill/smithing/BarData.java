package com.elvarg.world.content.skill.smithing;

import com.elvarg.world.model.Item;

public enum BarData {
	BRONZE_BAR(new Item[] { new Item(436), new Item(438) }, new Item(2349), 1, 6),
	BLURITE_BAR(new Item[] { new Item(668) }, new Item(9467), 8, 8),
	IRON_BAR(new Item[] { new Item(440) }, new Item(2351), 15, 12),
	SILVER_BAR(new Item[] { new Item(442) }, new Item(2355), 20, 13),
	STEEL_BAR(new Item[] { new Item(440), new Item(453, 2) }, new Item(2353), 30, 17),
	GOLD_BAR(new Item[] { new Item(444) }, new Item(2357), 40, 22),
	PERFECT_GOLD_BAR(new Item[] { new Item(446) }, new Item(2365), 40, 22),
	MITHRIL_BAR(new Item[] { new Item(447), new Item(453, 4) }, new Item(2359), 50, 30),
	ADAMANITE_BAR(new Item[] { new Item(449), new Item(453, 6) }, new Item(2361), 70, 37),
	RUNE_BAR(new Item[] { new Item(451), new Item(453, 8) }, new Item(2363), 85, 50);

	final Item[] requiredOres;
	final Item result;
	final int levelRequired;
	final int exp;
	
	final static int[] bars = { 2349, 2351, 2355, 2353, 2357, 2359, 2361, 2363 };
	static final int[] smeltFrames = { 2405, 2406, 2407, 2409, 2410, 2411, 2412, 2413};

	private BarData(Item[] requiredOres, Item result, int levelRequired, int exp) {
		this.requiredOres = requiredOres;
		this.result = result;
		this.levelRequired = levelRequired;
		this.exp = exp;
	}

	public int getExperience() {
		return exp;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public Item[] getRequiredOres() {
		return requiredOres;
	}

	public Item getResult() {
		return result;
	}
	
	public static int[] getBars() {
		return bars;
	}
	
	public static int[] getSmeltFrames() {
		return smeltFrames;
	}
	
	public static BarData getBar(int itemId) {
		for(BarData item : BarData.values()) {
			if(item.getResult().getId() == itemId) {
				return item;
			}
		}
		return null;
	}
}
