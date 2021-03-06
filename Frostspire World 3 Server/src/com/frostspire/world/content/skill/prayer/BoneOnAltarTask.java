package com.frostspire.world.content.skill.prayer;

import com.frostspire.engine.task.Task;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Item;

public class BoneOnAltarTask extends Task {
	
	private Player player;
	private BoneData bone;
	
	public BoneOnAltarTask(Player player, BoneData bone) {
		super(2);
		this.player = player;
		this.bone = bone;
	}

	@Override
	protected void execute() {
		
		if(!player.getInventory().contains(bone.getId())) {
			player.getPacketSender().sendMessage("You have run out of bones.");
			stop();
			return;
		}
		
		player.performAnimation(new Animation(645));
		player.getInventory().delete(new Item(bone.getId()));
		player.getSkillManager().addExperience(Skill.PRAYER, (int) (bone.getExperience() * 1.5));
		
	}
	
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
