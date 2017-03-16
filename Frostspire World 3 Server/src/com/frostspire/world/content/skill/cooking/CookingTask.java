package com.frostspire.world.content.skill.cooking;

import com.frostspire.engine.task.Task;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.npc.NPC;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;

public class CookingTask extends Task {
	
	private Player player;
	private CookingData data;
	
	public CookingTask(Player player, CookingData data) {
		super(2);
		this.player = player;
		this.data = data;
	}

	@Override
	protected void execute() {
		player.performAnimation(new Animation(896));
		if(!player.getInventory().contains(data.getRawId())) {
			player.getPacketSender().sendMessage("You have run out of fish.");
			stop();
			return;
		}
		player.performAnimation(new Animation(896));
		player.getInventory().delete(data.getRawId(), 1);
		if(!Cooking.success(player, 3, data.getLevelRequired(), data.getNoBurnLevel())) {
			player.getInventory().add(data.getBurntId(), 1);
			player.getPacketSender().sendMessage("You accidently burn the "+data.getName()+".");
		} else {
			player.getInventory().add(data.getCookedId(), 1);
			player.getSkillManager().addExperience(Skill.COOKING, data.getExperience());
			/*if(data == CookingData.SALMON) {
				Achievements.finishAchievement(player, AchievementData.COOK_A_SALMON);
			} else if(fish == CookingData.ROCKTAIL) {
				Achievements.doProgress(player, AchievementData.COOK_25_ROCKTAILS);
				Achievements.doProgress(player, AchievementData.COOK_1000_ROCKTAILS);
			}*/
		}
		/*amountCooked++;
		if(amountCooked >= amount)
			stop();
		}*/
	}

	@Override
	public void stop() {
		setEventRunning(false);
		//player.setSelectedSkillingItem(-1);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
