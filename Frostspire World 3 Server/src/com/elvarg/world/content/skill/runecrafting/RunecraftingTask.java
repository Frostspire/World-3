package com.elvarg.world.content.skill.runecrafting;

import com.elvarg.engine.task.Task;
import com.elvarg.world.entity.impl.player.Player;

public class RunecraftingTask extends Task {
	
	private Player player;
	private RunecraftingData rune;
	private int essenceId;
	
	public RunecraftingTask(Player player, RunecraftingData rune, int essenceId) {
		super(1);
		this.player = player;
		this.rune = rune;
		this.essenceId = essenceId;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}
	
	

}
