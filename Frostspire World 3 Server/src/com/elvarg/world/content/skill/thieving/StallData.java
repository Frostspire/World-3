package com.elvarg.world.content.skill.thieving;

public enum StallData {
	
	FOOD("food", 4875, 1963, 1500, 1, 30),
	GENERAL("general", 4876, 2313, 2500, 25, 50),
	CRAFT("crafting", 4874, 1635, 5500, 50, 70),
	MAGIC("magic", 4877, 8788, 1, 75, 90),
	SCIMITAR("scimitar", 4878, 6721, 12000, 85, 125);

	private String name;
	private int objectId;
	private int itemId;
	private int levelReq;
	private int xpGained;

	private StallData(String name, int objectId, int itemId, int itemAmount, int levelReq, int xpGained) {
		this.name = name;
		this.objectId = objectId;
		this.itemId = itemId;
		this.levelReq = levelReq;
		this.xpGained = xpGained;
	}
	
	public String getName() {
		return name;
	}
	
	public int getObjectId() {
		return objectId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public int getLevelRequired() {
		return levelReq;
	}
	
	public int getExperience() {
		return xpGained;
	}
	
	public static StallData getStall(int id) {
		for(StallData stall : StallData.values()) {
			if(stall.getObjectId() == id) {
				return stall;
			}
		}
		return null;
	}
	
}
