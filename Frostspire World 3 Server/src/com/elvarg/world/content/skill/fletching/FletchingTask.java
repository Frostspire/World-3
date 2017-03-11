package com.elvarg.world.content.skill.fletching;

import com.elvarg.engine.task.Task;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.content.skill.fletching.fletchable.Fletchable;
import com.elvarg.world.content.skill.fletching.fletchable.FletchableItem;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;

public class FletchingTask extends Task {
	
	private Player player;
	private Fletchable fletchable;
	private FletchableItem item;
	private int amount;
	
	public FletchingTask(Player player, Fletchable fletchable, FletchableItem item, int amount) {
		super(2);
		this.player = player;
		this.fletchable = fletchable;
		this.item = item;
		this.amount = amount;
	}

	@Override
	protected void execute() {
		
		if(amount <= 0) {
			stop();
			return;
		}
		
		if (!(player.getInventory().contains(fletchable.getIngediants()))) {
			stop();
			player.getPacketSender().sendMessage("<col=369>You have run out of materials.");
			return;
		}
		
		player.performAnimation(new Animation(fletchable.getAnimation()));
		player.getSkillManager().addExperience(Skill.FLETCHING, item.getExperience());
		player.getInventory().deleteItemSet(fletchable.getIngediants());
		player.getInventory().add(item.getProduct());

		if (fletchable.getProductionMessage() != null) {
			player.getPacketSender().sendMessage(fletchable.getProductionMessage());
		}
		
		amount--;
		
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}
