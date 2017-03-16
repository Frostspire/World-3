package com.frostspire.world.model.syntax.impl;

import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.container.impl.Bank;
import com.frostspire.world.model.syntax.EnterSyntax;

public class SearchBank implements EnterSyntax {
	
	@Override
	public void handleSyntax(Player player, String input) {
		Bank.search(player, input);
	}

	@Override
	public void handleSyntax(Player player, int input) {
		
	}

}
