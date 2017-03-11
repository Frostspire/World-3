package com.elvarg.world.content.skill.woodcutting;

import com.elvarg.cache.impl.definitions.ObjectDefinition;

public enum TreeData {
	NORMAL_FREE(1, 1511, 1342, 22, 25, 4),
	DEAD_TREE(1, 1511, 1342, 22, 25, 4),
	OAK_TREE(15, 1521, 1342, 30, 37, 5),
	WILLOW_TREE(30, 1519, 1342, 38, 67, 6),
	MAPLE_TREE(45, 1517, 1343, 45, 100, 6),
	YEW_TREE(60, 1515, 1342, 50, 175, 8),
	MAGIC_TREE(75, 1513, 1342, 55, 250, 9);

	int levelRequired;
	int reward;
	int replacementId;
	int respawnTimer;
	int experience;
	int ticks;

	private TreeData(int level, int reward, int replacement, int respawnTimer, int experience, int ticks) {
		levelRequired = level;
		this.reward = reward;
		replacementId = replacement;
		this.respawnTimer = respawnTimer;
		this.experience = experience;
		this.ticks = ticks;
	}

	public int getExperience() {
		return experience;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public int getReplacement() {
		return replacementId;
	}

	public int getRespawnTimer() {
		return respawnTimer;
	}

	public int getReward() {
		return reward;
	}
	
	public int getTicks() {
		return ticks;
	}
	
	public static TreeData getTree(int objectId) {
		ObjectDefinition def = ObjectDefinition.forId(objectId);

		if (def == null || def.name == null) {
			return null;
		}

		String name = def.name.toLowerCase().trim();

		switch (name) {
		case "dead tree":
			return DEAD_TREE;
		case "oak tree":
		case "oak":
			return OAK_TREE;
		case "willow tree":
		case "willow":
			return WILLOW_TREE;
		case "maple tree":
		case "maple":
			return MAPLE_TREE;
		case "yew tree":
		case "yew":
			return YEW_TREE;
		case "magic tree":
		case "magic":
			return MAGIC_TREE;
		case "tree":
			return NORMAL_FREE;
		default:
			return null;
		}
	}
}
