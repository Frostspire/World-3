package com.frostspire.world.content.skill.mining;

import com.frostspire.world.model.Animation;

public enum PickaxeData {
	BRONZE_PICKAXE(1265, 1, 1.0, new Animation(625)),
	IRON_PICKAXE(1267, 1, 1.05, new Animation(626)),
	STEEL_PICKAXE(1269, 6, 1.1, new Animation(627)),
	MITHRIL_PICKAXE(1273, 21, 1.2, new Animation(629)),
	ADAMANT_PICKAXE(1271, 31, 1.3, new Animation(628)),
	RUNE_PICKAXE(1275, 41, 1.4, new Animation(624)),			
	DRAGON_PICKAXE(11920, 61, 1.5, new Animation(6758)),
	DRAGON_PICKAXE_OR(12797, 61, 1.5, new Animation(335)); //add infernal pickaxe

	private final int item;
	private final int level;
	private final double speed;
	private final Animation animation;

	private PickaxeData(int item, int level, double speed, Animation animation) {
		this.item = item;
		this.level = level;
		this.speed = speed;
		this.animation = animation;
	}

	public int getId() {
		return item;
	}

	public int getLevelRequired() {
		return level;
	}
	
	public double getSpeed() {
		return speed;
	}

	public Animation getAnimation() {
		return animation;
	}
	
	public static PickaxeData getPickaxe(int itemId) {
		for(PickaxeData item : PickaxeData.values()) {
			if(item.getId() == itemId) {
				return item;
			}
		}
		return null;
	}

}
