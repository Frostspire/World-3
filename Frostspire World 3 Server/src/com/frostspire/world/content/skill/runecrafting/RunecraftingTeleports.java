package com.frostspire.world.content.skill.runecrafting;

import com.frostspire.world.model.Position;

public enum RunecraftingTeleports {
	AIR(new Position(2841, 4829)),
	MIND(new Position(2793, 4828)),
	WATER(new Position(2723, 4832)),
	EARTH(new Position(2655, 4830)),
	FIRE(new Position(2577, 4846)),
	BODY(new Position(2521, 4834)),
	COSMIC(new Position(2162, 4833)),
	CHAOS(new Position(2281, 4837)),
	NATURE(new Position(2400, 4835)),
	LAW(new Position(2464, 4818)),
	DEATH(new Position(2208, 4830)),
	BLOOD(new Position(1720, 3827)),
	SOUL(new Position(1817, 3858));
	
	
	private Position position;
	
	private RunecraftingTeleports(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

}
