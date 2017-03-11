package com.elvarg.world.content.skill.smithing;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.engine.task.Task;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Item;

public class SmeltBarTask extends Task {
	
	private Player player;
	private BarData bar;
	private int amount;
	
	public SmeltBarTask(Player player, BarData bar, int amount) {
		super(2);
		this.player = player;
		this.bar = bar;
		this.amount = amount;
	}

	@Override
	protected void execute() {
		
		if(amount <= 0) {
			stop();
			return;
		}
		
		if(!Smelting.hasOres(player, bar)) {
			player.getPacketSender().sendMessage("You have run out of ore to smelt.");
			stop();
			return;
		}
		
		player.performAnimation(new Animation(899));
		
		for(Item item : bar.getRequiredOres()) {
			player.getInventory().delete(item);
		}
		
		player.getInventory().add(bar.getResult());
		player.getPacketSender().sendMessage("You smelt the ore into a "+ItemDefinition.forId(bar.getResult().getId()).getName()+".");
		player.getSkillManager().addExperience(Skill.SMITHING, bar.getExperience());
		amount--;
		
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
