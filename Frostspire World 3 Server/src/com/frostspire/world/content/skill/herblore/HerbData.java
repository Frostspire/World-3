package com.frostspire.world.content.skill.herblore;

import com.frostspire.world.model.Item;

public enum HerbData {

	GUAM(new Item(199), new Item(249), 1, 2.75),

	MARRENTILL(new Item(201), new Item(251), 5, 3.75),

	TARROMIN(new Item(203), new Item(253), 11, 5),

	HARRALANDER(new Item(205), new Item(255), 20, 6.25),

	RANARR(new Item(207), new Item(257), 25, 7.5),

	TOADFLAX(new Item(3049), new Item(2998), 30, 8),

	IRIT(new Item(209), new Item(259), 40, 8.75),

	AVANTOE(new Item(211), new Item(261), 48, 10),

	KWUARM(new Item(213), new Item(263), 54, 11.25),

	SNAPDRAGON(new Item(3051), new Item(3000), 59, 11.75),

	CADANTINE(new Item(215), new Item(265), 65, 12.5),

	LANTADYME(new Item(2485), new Item(2481), 67, 13.125),

	DWARF_WEED(new Item(217), new Item(267), 70, 13.75),

	TORSTOL(new Item(219), new Item(269), 75, 15);

	private Item grimyHerb, cleanHerb;

	private int levelRequired;

	private double xpReward;

	private HerbData(final Item grimyHerb, final Item cleanHerb, final int levelRequired,
			final double xpReward) {
		this.grimyHerb = grimyHerb;
		this.cleanHerb = cleanHerb;
		this.levelRequired = levelRequired;
		this.xpReward = xpReward;
	}

	public Item getGrimyHerb() {
		return grimyHerb;
	}

	public Item getCleanHerb() {
		return cleanHerb;
	}

	public int getRequired() {
		return levelRequired;
	}

	public double getExperience() {
		return xpReward;
	}
	
	public static HerbData getHerb(Item item) {
		for(HerbData herb : HerbData.values()) {
			if(herb.getGrimyHerb().getId() == item.getId()) {
				return herb;
			}
		}
		return null;
	}

}