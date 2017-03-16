package com.frostspire.net.packet.impl;

import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketConstants;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.dialogue.DialogueManager;

/**
 * This packet listener handles player's mouse click on the
 * "Click here to continue" option, etc.
 * 
 * @author relex lawl
 */

public class DialoguePacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		switch (packet.getOpcode()) {
		case PacketConstants.DIALOGUE_OPCODE:
			DialogueManager.next(player);
			break;
		}
	}
}
