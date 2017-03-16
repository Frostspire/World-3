package com.frostspire.world.content.skill.crafting.spinning;

public enum SpinningData {
	BALL_OF_WOOL(1759, 1737, 1, 2),
	BOW_STRING(1777, 1779, 10, 15),
	CROSSBOW_STRING(9438, 9436, 10, 15),
	ROPE(954, 10814, 30, 25);
	
	private int productId;
	private int ingredientId;
	private int levelRequired;
	private int experience;
	
	private SpinningData(int productId, int ingredientId, int levelRequired, int experience) {
		this.productId = productId;
		this.ingredientId = ingredientId;
		this.levelRequired = levelRequired;
		this.experience = experience;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public int getIngredientId() {
		return ingredientId;
	}
	
	public int getLevelRequired() {
		return levelRequired;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public static SpinningData getItem(int itemId) {
		for(SpinningData item : SpinningData.values()) {
			if(item.getIngredientId() == itemId) {
				return item;
			}
		}
		return null;
	}

}
