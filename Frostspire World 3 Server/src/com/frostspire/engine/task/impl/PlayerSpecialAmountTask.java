package com.frostspire.engine.task.impl;

import com.frostspire.engine.task.Task;
import com.frostspire.world.entity.combat.CombatSpecial;
import com.frostspire.world.entity.impl.player.Player;

/**
 * A {@link Task} implementation which handles
 * the regeneration of special attack.
 * 
 * @author Professor Oak
 */
public class PlayerSpecialAmountTask extends Task {

	public PlayerSpecialAmountTask(Player player) {
		super(20, player, false);
		this.player = player;
		player.setRecoveringSpecialAttack(true);
	}

	private final Player player;

	@Override
	public void execute() {
		if (player == null || !player.isRegistered() || player.getSpecialPercentage() >= 100 || !player.isRecoveringSpecialAttack()) {
			player.setRecoveringSpecialAttack(false);
			stop();
			return;
		}
		int amount = player.getSpecialPercentage() + 5;
		if (amount >= 100) {
			amount = 100;
			player.setRecoveringSpecialAttack(false);
			stop();
		}
		player.setSpecialPercentage(amount);
		CombatSpecial.updateBar(player);
		if(amount == 25 || amount == 50 || amount == 75 || amount == 100) {
			player.getPacketSender().sendMessage("Your special attack energy is now " + player.getSpecialPercentage() + "%.");
		}
	}
}