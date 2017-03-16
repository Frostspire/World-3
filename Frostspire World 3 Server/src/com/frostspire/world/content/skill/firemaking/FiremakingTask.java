package com.frostspire.world.content.skill.firemaking;

import com.frostspire.engine.task.Task;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;

public class FiremakingTask extends Task {
	
	private Player player;
	private LogData log;
	private int ticks;
	private int animationCycle = 0;
	
	public FiremakingTask(Player player, LogData log) {
		super(2);
		this.player = player;
		this.log = log;
	}

	@Override
	protected void execute() {
		
		if(ticks > 6) {
			player.getPacketSender().sendMessage("You fail to light the fire.");
			stop();
		}
		
		if(player.getSkillManager().isSuccess(player.getSkillManager().getMaxLevel(Skill.FIREMAKING), log.getLevelRequired())) {
			Firemaking.successfulFire(player, log);
			stop();
		}
		
		if (animationCycle < 6) {
			animationCycle += 1;
		} else {
			player.performAnimation(new Animation(733));
			animationCycle = 0;
		}
		
		ticks++;
		
		//System.out.println("running");
		
	}

	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}
	
}
