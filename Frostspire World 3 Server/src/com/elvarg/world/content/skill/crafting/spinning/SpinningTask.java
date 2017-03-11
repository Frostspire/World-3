package com.elvarg.world.content.skill.crafting.spinning;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.engine.task.Task;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Item;

public class SpinningTask extends Task {
	
	private Player player;
	private SpinningData item;
	private int amount;
	
	public SpinningTask(Player player, SpinningData item, int amount) {
		super(2);
		this.player = player;
		this.item = item;
		this.amount = amount;
	}

	@Override
	protected void execute() {
		
		if(amount <= 0) {
			stop();
			return;
		}
		
		if(player.getInventory().getAmount(item.getIngredientId()) < 1) {
			player.getPacketSender().sendMessage("You have run out of materials to spin.");
			stop();
			return;
		}
		
		player.performAnimation(new Animation(896));
		
		player.getInventory().delete(new Item(item.getIngredientId()));
		player.getInventory().add(new Item(item.getProductId()));
		player.getPacketSender().sendMessage("You spin the "+ItemDefinition.forId(item.getIngredientId()).getName()+" into a "+ItemDefinition.forId(item.getProductId()).getName());
		player.getSkillManager().addExperience(Skill.CRAFTING, item.getExperience());
		amount--;
		
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
