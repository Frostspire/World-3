package com.frostspire.net.packet.impl;

import com.frostspire.cache.impl.definitions.NpcDefinition;
import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.entity.impl.player.Player;

public class ExamineNpcPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int npc = packet.readShort();
		if(npc <= 0) {
			return;
		}
		System.out.println("NPC: "+npc);
		NpcDefinition npcDef = NpcDefinition.get(npc);
		if(npcDef != null) {
			player.getPacketSender().sendMessage(npcDef.getDescription());
		}
	}

}
