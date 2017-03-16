package com.frostspire.net.packet.impl;

import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.content.clan.ClanChatManager;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.container.impl.Bank;

/**
 * This packet listener reads a button click WITH an action.
 * @author Gabriel Hannason
 */

public class ButtonWithActionPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int button = packet.readInt();
		int action = packet.readByte();
		
		if(Bank.handleButton(player, button, action)) {
			return;
		}
		if(ClanChatManager.handleButton(player, button, action)) {
			return;
		}
	}
}
