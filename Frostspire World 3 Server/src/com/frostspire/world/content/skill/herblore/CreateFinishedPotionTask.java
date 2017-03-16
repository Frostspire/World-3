package com.frostspire.world.content.skill.herblore;

import java.util.Optional;

import com.frostspire.cache.impl.definitions.ItemDefinition;
import com.frostspire.engine.task.Task;
import com.frostspire.engine.task.TaskManager;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Item;
import com.frostspire.world.model.Priority;
import com.frostspire.world.model.dialogue.DialogueManager;

/**
 * The task regarding the creation of a finished potion.
 * 
 * @author Vl1 - www.rune-server.org/members/Valii
 * @since March 4th, 2017.
 *
 */
public class CreateFinishedPotionTask extends Task {

	private static final Animation CREATE_POTION_ANIMATION = new Animation(363, Priority.LOW);

	private final Player player;

	private final Optional<FinishedPotionData> potion;

	private int amount;

	public CreateFinishedPotionTask(Player player, Optional<FinishedPotionData> potion, int amount) {
		super(3, player, true);
		this.player = player;
		this.potion = potion;
		this.amount = amount;
	}

	private void create(Player player) {
		if (potion.isPresent()) {
			player.getInventory().delete(potion.get().getIngredient());
			player.getInventory().delete(potion.get().getUnfinishedPotion());
			player.getInventory().add(potion.get().getFinishedPotion());
			player.getSkillManager().addExperience(Skill.HERBLORE, potion.get().getExperience());
		}
	}

	@Override
	protected void execute() {
		if (potion.isPresent()) {
			if (player.getInventory()
					.contains(new Item[] { potion.get().getIngredient(), potion.get().getUnfinishedPotion() })) {
				player.performAnimation(CREATE_POTION_ANIMATION);
				player.getPacketSender()
						.sendMessage("You mix the "
								+ ItemDefinition.forId(potion.get().getIngredient().getId()).getName().toLowerCase()
								+ " into your potion.");
				create(player);
				amount--;
				if (amount == 0) {
					this.stop();
				}
			} else {
				DialogueManager.sendStatement(player, "You have ran out of the ingredients required.");
				player.getPacketSender().sendMessage("You have ran out of the ingredients required.");
				this.stop();
			}
		}
	}
	
	@Override
	public void stop() {
		setEventRunning(false);
		player.resetCurrentSkillingTask();
		player.performAnimation(new Animation(65535));
	}

}