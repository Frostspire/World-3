package com.elvarg.world.content.skill.fletching.fletchable;

import com.elvarg.world.model.Item;

public final class FletchableItem {
	
	private final Item product;
	
	private final int level;
	
	private final int experience;

	public FletchableItem(Item product, int level, int experience) {
		this.product = product;
		this.level = level;
		this.experience = experience;
	}

	public Item getProduct() {
		return product;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}
}
