package com.frostspire.world.content.skill.woodcutting;

import com.frostspire.world.model.Animation;

public enum AxeData {
	BRONZE_AXE(1351, 1, 1, new Animation(879), 1.0),
	IRON_AXE(1349, 1, 2, new Animation(877), 1.3),
	STEEL_AXE(1353, 6, 3, new Animation(875), 1.5),
	BLACK_AXE(1361, 6, 4, new Animation(873), 1.7),
	MITHRIL_AXE(1355, 21, 5, new Animation(871), 1.9),
	ADAMANT_AXE(1357, 31, 6, new Animation(869), 2.0),
	RUNE_AXE(1359, 41, 7, new Animation(867), 2.2),
	DRAGON_AXE(6739, 61, 8, new Animation(2846), 2.3),
	INFERNAL_ADZE(13661, 41, 8, new Animation(10251), 2.5); //replace with infernal axe

	int itemId;
	int levelRequired;
	int bonus;
	Animation animation;
	double speed;

	private AxeData(int id, int level, int bonus, Animation animation, double speed) {
		itemId = id;
		levelRequired = level;
		this.bonus = bonus;
		this.animation = animation;
		this.speed = speed;
	}

	public Animation getAnimation() {
		return animation;
	}

	public int getBonus() {
		return bonus;
	}

	public int getId() {
		return itemId;
	}

	public int getLevelRequired() {
		return levelRequired;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public static AxeData getAxe(int id) {
		for(AxeData item : AxeData.values()) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
}
