package com.elvarg.world.content.skill.smithing;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.engine.task.Task;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;

public class SmithingTask extends Task {
	
	private Player player;
	private SmithingData item;
	private int barId;
	private int amount;
	
	public SmithingTask(Player player, SmithingData item, int barId, int amount) {
		super(2);
		this.player = player;
		this.item = item;
		this.barId = barId;
		this.amount = amount;
	}

	@Override
	protected void execute() {
		
		if(amount <= 0) {
			stop();
			return;
		}

		//check if they have amount of bars required
		if(player.getInventory().getAmount(barId) < item.getBarsRequired()) {
			player.getPacketSender().sendMessage("You don't have enough "+ItemDefinition.forId(barId).getName()+"s to smith this item.");
			stop();
			return;
		}
		
		//perform animation
		player.performAnimation(new Animation(898));
		
		player.getInventory().delete(barId, item.getBarsRequired());
		player.getInventory().add(item.getItem());
		player.getSkillManager().addExperience(Skill.SMITHING, item.getExperience());
		player.getPacketSender().sendMessage("You create a "+ItemDefinition.forId(item.getItem().getId()).getName()+".");
		amount--;
		
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
