package com.elvarg.world.content.skill.crafting.jewelry;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.engine.task.Task;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Item;

public class JewelryTask extends Task {
	
	private Player player;
	private JewelryData jewelry;
	private int amount;
	
	public JewelryTask(Player player, JewelryData jewelry, int amount) {
		super(2);
		this.player = player;
		this.jewelry = jewelry;
		this.amount = amount;
	}

	@Override
	protected void execute() {

		if(amount <= 0) {
			stop();
			return;
		}
		
		for(int itemRequired : jewelry.getMaterialsRequired()) {
			if(!player.getInventory().contains(itemRequired)) {
				player.getPacketSender().sendMessage("You have run out of materials.");
				stop();
				return;
			}
		}
		
		player.performAnimation(new Animation(899));
		
		for(int itemToDelete : jewelry.getMaterialsRequired()) {
			player.getInventory().delete(new Item(itemToDelete));
		}
		
		player.getInventory().add(jewelry.getReward());
		player.getSkillManager().addExperience(Skill.CRAFTING, jewelry.getExperience());
		player.getPacketSender().sendMessage("You craft a "+ItemDefinition.forId(jewelry.getReward().getId()).getName()+".");
		amount--;
		
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
