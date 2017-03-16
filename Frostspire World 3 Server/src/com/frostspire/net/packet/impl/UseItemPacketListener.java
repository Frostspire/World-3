package com.frostspire.net.packet.impl;

import com.frostspire.engine.task.impl.WalkToTask;
import com.frostspire.engine.task.impl.WalkToTask.FinalizedMovementTask;
import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketConstants;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.collision.region.RegionClipping;
import com.frostspire.world.content.skill.cooking.Cooking;
import com.frostspire.world.content.skill.crafting.gems.CuttingGems;
import com.frostspire.world.content.skill.firemaking.Firemaking;
import com.frostspire.world.content.skill.fletching.Fletching;
import com.frostspire.world.content.skill.herblore.Herblore;
import com.frostspire.world.content.skill.prayer.Prayer;
import com.frostspire.world.content.skill.smithing.Smelting;
import com.frostspire.world.content.skill.smithing.Smithing;
import com.frostspire.world.entity.impl.npc.NPC;
import com.frostspire.world.entity.impl.object.GameObject;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Item;
import com.frostspire.world.model.PlayerRights;
import com.frostspire.world.model.Position;

public class UseItemPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		if (player.getHitpoints() <= 0)
			return;
		switch (packet.getOpcode()) {
		case PacketConstants.ITEM_ON_ITEM:
			itemOnItem(player, packet);
			break;
		case PacketConstants.ITEM_ON_OBJECT:
			itemOnObject(player, packet);
			break;
		case PacketConstants.ITEM_ON_GROUND_ITEM:
			// TODO
			break;
		case PacketConstants.ITEM_ON_NPC:
			itemOnNpc(player, packet);
			break;
		case PacketConstants.ITEM_ON_PLAYER:
			itemOnPlayer(player, packet);
			break;
		}
	}

	private void itemOnItem(Player player, Packet packet) {
		int usedWithSlot = packet.readUnsignedShort();
		int itemUsedSlot = packet.readUnsignedShortA();
		if (usedWithSlot < 0 || itemUsedSlot < 0
				|| itemUsedSlot > player.getInventory().capacity()
				|| usedWithSlot > player.getInventory().capacity())
			return;
		Item usedWith = player.getInventory().getItems()[usedWithSlot];
		Item itemUsedWith = player.getInventory().getItems()[itemUsedSlot];
		
		if (usedWith == null || itemUsedWith == null) {
			return;
		}
		if (!player.getInventory().contains(new Item[] { usedWith, itemUsedWith })) {
			return;
		}
		
		if(player.getRights() == PlayerRights.ADMINISTRATOR)
			player.getPacketSender().sendMessage("Item on item; [item id] [item id] : ["+usedWith.getId()+"] [" + itemUsedWith.getId());
		
		if(itemOnItemHandlers(player, usedWith, itemUsedWith)) {		
			return;
		}
		
	}

	private void itemOnObject(Player player, Packet packet) {
		int interfaceType = packet.readShort();
		final int objectId = packet.readShort();
		final int objectY = packet.readLEShortA();
		final int itemSlot = packet.readLEShort();
		final int objectX = packet.readLEShortA();
		final int itemId = packet.readShort();
		//final Position position = new Position(objectX, objectY, player.getPosition().getZ());
		
		if (itemSlot < 0 || itemSlot > player.getInventory().capacity())
			return;
		final Item item = player.getInventory().getItems()[itemSlot];
		if (item == null)
			return;
		final GameObject gameObject = new GameObject(objectId, new Position(
				objectX, objectY, player.getPosition().getZ()));
		if(objectId > 0 && objectId != 6 && !RegionClipping.objectExists(gameObject)) {
				//player.getPacketSender().sendMessage("An error occured. Error code: "+gameObject.getId()).sendMessage("Please report the error to a staff member.");
			return;
		}
		
		if(player.getRights() == PlayerRights.ADMINISTRATOR)
			player.getPacketSender().sendMessage("Item on object; [item id] [id, position] : ["+itemId+"] [" + objectId + ", " + gameObject.getPosition().toString() + "]");

		player.setWalkToTask(new WalkToTask(player, gameObject.getPosition(), gameObject.getSize(), new FinalizedMovementTask() {
			@Override
			public void execute() {
				
				if(itemOnObjectHandlers(player, itemId, gameObject)) {
					return;
				}
				
				switch(objectId) {
				
				}
			}
		}));
	}

	private void itemOnNpc(Player player, Packet packet) {
		// TODO Auto-generated method stub
		
	}

	private void itemOnPlayer(Player player, Packet packet) {
		// TODO Auto-generated method stub
		
	}
	
	public static boolean itemOnObjectHandlers(Player player, int itemId, GameObject object) {
		if(Cooking.canCookingHandle(player, itemId, object)) {
			return true;
		}
		if(Prayer.attemptBoneOnAltar(player, itemId, object)) {
			return true;
		}
		if(Smelting.attemptSmelting(player, itemId)) {
			return true;
		}
		if(Smithing.attemptSmithing(player, itemId, object)) {
			return true;
		}
		return false;
	}
	
	public static boolean itemOnItemHandlers(Player player, Item firstItem, Item secondItem) {
		if(Firemaking.attemptFiremaking(player, firstItem, secondItem)) {
			return true;
		}
		if(Herblore.attemptPotionMaking(player, firstItem, secondItem)) {
			return true;
		}
		if(Fletching.SINGLETON.itemOnItem(player, firstItem, secondItem)) {
			return true;
		}
		if(CuttingGems.attemptCutGems(player, firstItem, secondItem)) {
			return true;
		}
		return false;
	}
	
	public static boolean itemOnNpcHandlers(Player player, int itemId, NPC npc) {

		return false;
	}
	
	public static boolean itemOnPlayerHandlers(Player player, int itemId, Player otherPlayer) {

		return false;
	}

}
