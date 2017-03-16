package com.frostspire.world.content.skill.crafting.gems;

import com.frostspire.world.model.Item;

public enum GemData {
	OPAL(new Item(1625), new Item(1609), 1, 15),
	JADE(new Item(1627), new Item(1611), 13, 20),
	RED_TOPAZ(new Item(1629), new Item(1613), 16, 25),
	SAPPHIRE(new Item(1623), new Item(1607), 20, 50),
	EMERALD(new Item(1621), new Item(1605), 27, 67),
	RUBY(new Item(1619), new Item(1603), 34, 85),
	DIAMOND(new Item(1617), new Item(1601), 43, 107),
	DRAGONSTONE(new Item(1631), new Item(1615), 55, 137),
	ONYX(new Item(6571), new Item(6573), 67, 167);

	private Item gem;
	private Item product;
	private int levelRequired;
	private int experience;

	private GemData(Item gem, Item product, int levelRequired, int experience) {
		this.gem = gem;
		this.product = product;
		this.levelRequired = levelRequired;
		this.experience = experience;
	}

	public int getAnimation() {
		switch (this) {
		case OPAL:
		case JADE:
		default:
			return 891;
		case RED_TOPAZ:
			return 892;
		case SAPPHIRE:
			return 888;
		case EMERALD:
			return 889;
		case RUBY:
			return 887;
		case DIAMOND:
			return 890;
		case DRAGONSTONE:
			return 890;
		case ONYX:
			return 2717;
		}
	}

	public Item getGem() {
		return gem;
	}
	
	public Item getProduct() {
		return product;
	}
	
	public int getLevelRequired() {
		return levelRequired;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public static GemData getGem(int itemId) {
		for(GemData gem : GemData.values()) {
			if(gem.getGem().getId() == itemId) {
				return gem;
			}
		}
		return null;
	}
	
}
