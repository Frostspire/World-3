package com.frostspire.net.packet.impl;

import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.collision.region.RegionClipping;
import com.frostspire.world.entity.impl.object.ObjectHandler;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.grounditems.GroundItemManager;


public class RegionChangePacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		if(player.isAllowRegionChangePacket()) {
			/*RegionClipping.loadRegion(player.getPosition().getX(), player.getPosition().getY());
			player.getPacketSender().sendMapRegion();
			CustomObjects.handleRegionChange(player);
			GroundItemManager.handleRegionChange(player);
			Sounds.handleRegionChange(player);
			player.getTolerance().reset();
			Hunter.handleRegionChange(player);
			if(player.getRegionInstance() != null && player.getPosition().getX() != 1 && player.getPosition().getY() != 1) {
				if(player.getRegionInstance().equals(RegionInstanceType.BARROWS) || player.getRegionInstance().equals(RegionInstanceType.WARRIORS_GUILD))
					player.getRegionInstance().destruct();
			}
			player.getNpcFacesUpdated().clear();*/
			RegionClipping.loadRegion(player.getPosition().getX(), player.getPosition().getY());
			ObjectHandler.onRegionChange(player);
			
			
			//CustomObjects.handleRegionChange(player);
			GroundItemManager.onRegionChange(player);
			player.getTolerance().reset();
			player.setRegionChange(false).setAllowRegionChangePacket(false);
		}
	}
}
