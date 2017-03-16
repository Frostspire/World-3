package com.frostspire.world.model.syntax;

import com.frostspire.world.entity.impl.player.Player;

public interface EnterSyntax {

	public abstract void handleSyntax(Player player, String input);
	public abstract void handleSyntax(Player player, int input);
}
