package com.frostspire.net.packet.impl;

import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.entity.impl.player.Player;

public class PlayerInactivePacketListener implements PacketListener {

	//CALLED EVERY 3 MINUTES OF INACTIVITY
	
	@Override
	public void handleMessage(Player player, Packet packet) {
		
	}
}
