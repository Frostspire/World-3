package com.elvarg.world.content.skill.firemaking;

import com.elvarg.world.content.skill.cooking.CookingData;
import com.elvarg.world.entity.impl.player.Player;

public enum LogData {
	
	LOG(1511, 1, 4125, 30),
	ACHEY(2862, 1, 4125, 30),
	OAK(1521, 15, 5890, 40),
	WILLOW(1519, 30, 7952, 45),
	TEAK(6333, 35, 8120, 45),
	ARCTIC_PINE(10810, 42, 8560, 45),
	MAPLE(1517, 45, 8680, 45),
	MAHOGANY(6332, 50, 8930, 45),
	EUCALYPTUS(12581, 58, 9870, 45),
	YEW(1515, 60, 9960, 50),
	MAGIC(1513, 75, 13477, 50);
	
	private int logId, level, burnTime;
	private int xp;
	
	private LogData(int logId, int level, int xp, int burnTime) {
		this.logId = logId;
		this.level = level;
		this.xp = xp;
		this.burnTime = burnTime;
	}
	
	public int getLogId() {
		return logId;
	}
	
	public int getLevelRequired() {
		return level;
	}
	
	public int getXp() {
		return xp;
	}		
	
	public int getBurnTime() {
		return this.burnTime;
	}

	public static LogData getLog(int id) {
		for(LogData item : LogData.values()) {
			if(item.getLogId() == id) {
				return item;
			}
		}
		return null;
	}

}
