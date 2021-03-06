package com.frostspire.net.packet.impl;

import com.frostspire.net.ByteBufUtils;
import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketConstants;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.entity.impl.player.Player;

/**
 * This packet manages the input taken from chat box interfaces that allow input,
 * such as withdraw x, bank x, enter name of friend, etc.
 * 
 * @author Gabriel Hannason
 */

public class EnterInputPacketListener implements PacketListener {


	@Override
	public void handleMessage(Player player, Packet packet) {
		switch (packet.getOpcode()) {
		case PacketConstants.ENTER_SYNTAX_OPCODE:
			String name = ByteBufUtils.readString(packet.getBuffer());
			if(name == null)
				return;
			if(player.getEnterSyntax() != null) {
				player.getEnterSyntax().handleSyntax(player, name);
			}
			player.setEnterSyntax(null);
			break;
		case PacketConstants.ENTER_AMOUNT_OPCODE:
			int amount = packet.readInt();
			if(amount <= 0)
				return;
			if(player.getEnterSyntax() != null) {
				player.getEnterSyntax().handleSyntax(player, amount);
			}
			player.setEnterSyntax(null);
			break;
		}
	}
}
