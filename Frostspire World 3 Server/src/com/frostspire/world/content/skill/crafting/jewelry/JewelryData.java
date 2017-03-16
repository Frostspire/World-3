package com.frostspire.world.content.skill.crafting.jewelry;

import com.frostspire.world.model.Item;

public enum JewelryData {
	/* Rings */
	GOLD_RING(1635, 5, 15, new int[] {1592, 2357}),
	SAPPHIRE_RING(1637, 20, 40, new int[] {1592, 2357, 1607}),
	EMERALD_RING(1639, 27, 55, new int[] {1592, 2357, 1605}),
	RUBY_RING(1641, 34, 70, new int[] {1592, 2357, 1603}),
	DIAMOND_RING(1643, 43, 85, new int[] {1592, 2357, 1601}),
	DRAGONSTONE_RING(1645, 55, 100, new int[] {1592, 2357, 1615}),
	ONYX_RING(6575, 67, 85, new int[] {1592, 2357, 6573}),
	
	/* Necklace */
	GOLD_NECKLACE(1654, 6, 20, new int[] {1597, 2357}),
	SAPPHIRE_NECKLACE(1656, 22, 55, new int[] {1597, 2357, 1607}),
	EMERALD_NECKLACE(1658, 29, 60, new int[] {1597, 2357, 1605}),
	RUBY_NECKLACE(1660, 40, 75, new int[] {1597, 2357, 1603}),
	DIAMOND_NECKLACE(1662, 56, 90, new int[] {1597, 2357, 1601}),
	DRAGONSTONE_NECKLACE(1664, 72, 105, new int[] {1597, 2357, 1615}),
	ONYX_NECKLACE(6577, 82, 120, new int[] {1597, 2357, 6573}),
	
	/* Amulet */
	GOLD_AMULET(1673, 8, 30, new int[] {1595, 2357}),
	SAPPHIRE_AMULET(1675, 24, 65, new int[] {1595, 2357, 1607}),
	EMERALD_AMULET(1677, 31, 61, new int[] {1595, 2357, 1605}),
	RUBY_AMULET(1679, 50, 85, new int[] {1595, 2357, 1603}),
	DIAMOND_AMULET(1681, 70, 100, new int[] {1595, 2357, 1601}),
	DRAGONSTONE_AMULET(1683, 85, 125, new int[] {1595, 2357, 1615}),
	ONYX_AMULET(6579, 90, 150, new int[] {1595, 2357, 6573});

	private Item reward;
	private short levelRequired;
	private int experienceGain;
	private int[] materialsRequired;

	private JewelryData(int rewardId, int levelRequired, int experienceGain, int[] materialsRequired) {
		this.reward = new Item(rewardId);
		this.levelRequired = ((short) levelRequired);
		this.experienceGain = experienceGain;
		this.materialsRequired = materialsRequired;
	}

	public int getExperience() {
		return experienceGain;
	}

	public int[] getMaterialsRequired() {
		return materialsRequired;
	}

	public short getLevelRequired() {
		return levelRequired;
	}

	public Item getReward() {
		return reward;
	}
	
	public static JewelryData getJewelry(int itemId) {
		for(JewelryData jewelry : JewelryData.values()) {
			if(jewelry.getReward().getId() == itemId) {
				return jewelry;
			}
		}
		return null;
	}
}
