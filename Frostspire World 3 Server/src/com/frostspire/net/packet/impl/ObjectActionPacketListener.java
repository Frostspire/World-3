package com.frostspire.net.packet.impl;

import com.frostspire.GameLoader;
import com.frostspire.cache.impl.definitions.ObjectDefinition;
import com.frostspire.engine.task.TaskManager;
import com.frostspire.engine.task.impl.ForceMovementTask;
import com.frostspire.engine.task.impl.WalkToTask;
import com.frostspire.engine.task.impl.WalkToTask.FinalizedMovementTask;
import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketConstants;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.world.collision.region.RegionClipping;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.content.skill.crafting.jewelry.Jewelry;
import com.frostspire.world.content.skill.crafting.spinning.Spinning;
import com.frostspire.world.content.skill.mining.Mining;
import com.frostspire.world.content.skill.runecrafting.AbyssHandler;
import com.frostspire.world.content.skill.runecrafting.Runecrafting;
import com.frostspire.world.content.skill.smithing.Smelting;
import com.frostspire.world.content.skill.thieving.Thieving;
import com.frostspire.world.content.skill.woodcutting.Woodcutting;
import com.frostspire.world.entity.impl.object.GameObject;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.ForceMovement;
import com.frostspire.world.model.MagicSpellbook;
import com.frostspire.world.model.PlayerRights;
import com.frostspire.world.model.Position;
import com.frostspire.world.model.dialogue.DialogueManager;
import com.frostspire.world.model.dialogue.DialogueOptions;

/**
 * This packet listener is called when a player clicked
 * on a game object.
 * 
 * @author relex lawl
 */

public class ObjectActionPacketListener implements PacketListener {

	/**
	 * Handles the first click option on an object.
	 * @param player		The player that clicked on the object.
	 * @param packet		The packet containing the object's information.
	 */
	private static void firstClick(final Player player, Packet packet) {
		final int x = packet.readLEShortA();
		final int id = packet.readUnsignedShort();
		final int y = packet.readUnsignedShortA();
		final Position position = new Position(x, y, player.getPosition().getZ());
		final GameObject gameObject = new GameObject(id, position);

		//Make sure object exists...
		if(!RegionClipping.objectExists(id, position)) {
			GameLoader.getLogger().info("Object with id "+id+" does not exist in region.");
			return;
		}

		//Get object definition
		final ObjectDefinition def = ObjectDefinition.forId(id);
		if(def == null) {
			GameLoader.getLogger().info("ObjectDefinition for object "+id+" is null.");
			return;
		}

		//Calculate object size...
		final int size = (def.getSizeX() + def.getSizeY()) - 1;

		//Face object..
		player.setPositionToFace(position);
		
		if(player.getRights() == PlayerRights.ADMINISTRATOR)
			player.getPacketSender().sendMessage("First click object [objectId] [position]: ["+gameObject.getId()+"] ["+gameObject.getPosition());

		player.setWalkToTask(new WalkToTask(player, position, size, new FinalizedMovementTask() {
			@Override
			public void execute() {
				
				if(handlers(player, gameObject)) {
					return;
				}
				
				switch(id) {

				case WILDERNESS_DITCH:
					player.getMovementQueue().reset();
					if(player.getForceMovement() == null) {
						final Position crossDitch = new Position(0, player.getPosition().getY() < 3522 ? 3 : -3);
						TaskManager.submit(new ForceMovementTask(player, 3, new ForceMovement(player.getPosition().copy(), crossDitch, 0, 70, crossDitch.getY() == 3 ? 0 : 2, 6132)));
					}
					break;

				case MAGICAL_ALTAR:
					player.setDialogueOptions(new DialogueOptions() {
						@Override
						public void handleOption(Player player, int option) {
							switch(option) {
							case 1: //Normal spellbook option
								player.getPacketSender().sendInterfaceRemoval();
								MagicSpellbook.changeSpellbook(player, MagicSpellbook.NORMAL);
								break;
							case 2: //Ancient spellbook option
								player.getPacketSender().sendInterfaceRemoval();
								MagicSpellbook.changeSpellbook(player, MagicSpellbook.ANCIENT);
								break;
							case 3: //Lunar spellbook option
								player.getPacketSender().sendInterfaceRemoval();
								MagicSpellbook.changeSpellbook(player, MagicSpellbook.LUNAR);
								break;
							case 4: //Cancel option
								player.getPacketSender().sendInterfaceRemoval();
								break;
							}
						}
					});
					DialogueManager.start(player, 11);					
					break;

				case PRAYER_ALTAR:
					if(player.getSkillManager().getCurrentLevel(Skill.PRAYER) < player.getSkillManager().getMaxLevel(Skill.PRAYER)) {
						player.performAnimation(new Animation(645));
						player.getPacketSender().sendMessage("You recharge your Prayer points.");
						player.getSkillManager().setCurrentLevel(Skill.PRAYER, player.getSkillManager().getMaxLevel(Skill.PRAYER), true);
					} else {
						player.getPacketSender().sendMessage("You don't need to recharge your Prayer points right now.");
					}
					break;

				}
			}
		}));
	}

	/**
	 * Handles the second click option on an object.
	 * @param player		The player that clicked on the object.
	 * @param packet		The packet containing the object's information.
	 */
	private static void secondClick(final Player player, Packet packet) {
		final int id = packet.readLEShortA();
		final int y = packet.readLEShort();
		final int x = packet.readUnsignedShortA();
		final Position position = new Position(x, y, player.getPosition().getZ());
		final GameObject gameObject = new GameObject(id, position);

		//Make sure object exists...
		if(!RegionClipping.objectExists(id, position) && id != 16469) {
			GameLoader.getLogger().info("Object with id "+id+" does not exist in region.");
			return;
		}

		//Get object definition
		final ObjectDefinition def = ObjectDefinition.forId(id);
		if(def == null) {
			GameLoader.getLogger().info("ObjectDefinition for object "+id+" is null.");
			return;
		}

		//Calculate object size...
		final int size = (def.getSizeX() + def.getSizeY()) - 1;

		//Face object..
		player.setPositionToFace(position);
		
		if(player.getRights() == PlayerRights.ADMINISTRATOR)
			player.getPacketSender().sendMessage("Second click object [objectId] [position]: ["+gameObject.getId()+"] ["+gameObject.getPosition());

		player.setWalkToTask(new WalkToTask(player, position, size, new FinalizedMovementTask() {
			public void execute() {
				
				if(handlers(player, gameObject)) {
					return;
				}
				
				switch(id) {
				case EDGEVILLE_BANK:
					player.getBank(player.getCurrentBankTab()).open();
					break;
				case COOKS_GUILD_BANK:
					player.getBank(player.getCurrentBankTab()).open();
					break;
				case MAGICAL_ALTAR:
					player.getPacketSender().sendInterfaceRemoval();
					MagicSpellbook.changeSpellbook(player, MagicSpellbook.NORMAL);
					break;
				}
			}
		}));
	}

	/**
	 * Handles the third click option on an object.
	 * @param player		The player that clicked on the object.
	 * @param packet		The packet containing the object's information.
	 */
	private static void thirdClick(Player player, Packet packet) {
		final int x = packet.readLEShort();
		final int y = packet.readShort();
		final int id = packet.readLEShortA();
		final Position position = new Position(x, y, player.getPosition().getZ());
		final GameObject gameObject = new GameObject(id, position);

		//Make sure object exists...
		if(!RegionClipping.objectExists(id, position)) {
			GameLoader.getLogger().info("Object with id "+id+" does not exist in region.");
			return;
		}

		//Get object definition
		final ObjectDefinition def = ObjectDefinition.forId(id);
		if(def == null) {
			GameLoader.getLogger().info("ObjectDefinition for object "+id+" is null.");
			return;
		}

		//Calculate object size...
		final int size = (def.getSizeX() + def.getSizeY()) - 1;

		//Face object..
		player.setPositionToFace(position);
		
		if(player.getRights() == PlayerRights.ADMINISTRATOR)
			player.getPacketSender().sendMessage("Third click object [objectId] [position]: ["+gameObject.getId()+"] ["+gameObject.getPosition());

		player.setWalkToTask(new WalkToTask(player, position, size, new FinalizedMovementTask() {
			public void execute() {
				switch(id) {
				case MAGICAL_ALTAR:
					player.getPacketSender().sendInterfaceRemoval();
					MagicSpellbook.changeSpellbook(player, MagicSpellbook.ANCIENT);
					break;
				}
			}
		}));
	}

	/**
	 * Handles the fourth click option on an object.
	 * @param player		The player that clicked on the object.
	 * @param packet		The packet containing the object's information.
	 */
	private static void fourthClick(Player player, Packet packet) {
		final int x = packet.readLEShortA();
		final int id = packet.readUnsignedShortA();
		final int y = packet.readLEShortA();
		final Position position = new Position(x, y, player.getPosition().getZ());
		final GameObject gameObject = new GameObject(id, position);

		//Make sure object exists...
		if(!RegionClipping.objectExists(id, position)) {
			GameLoader.getLogger().info("Object with id "+id+" does not exist in region.");
			return;
		}

		//Get object definition
		final ObjectDefinition def = ObjectDefinition.forId(id);
		if(def == null) {
			GameLoader.getLogger().info("ObjectDefinition for object "+id+" is null.");
			return;
		}

		//Calculate object size...
		final int size = (def.getSizeX() + def.getSizeY()) - 1;

		//Face object..
		player.setPositionToFace(position);
		
		if(player.getRights() == PlayerRights.ADMINISTRATOR)
			player.getPacketSender().sendMessage("Fourth click object [objectId] [position]: ["+gameObject.getId()+"] ["+gameObject.getPosition());

		player.setWalkToTask(new WalkToTask(player, position, size, new FinalizedMovementTask() {
			public void execute() {
				switch(id) {
				case MAGICAL_ALTAR:
					player.getPacketSender().sendInterfaceRemoval();
					MagicSpellbook.changeSpellbook(player, MagicSpellbook.LUNAR);
					break;
				}
			}
		}));
	}

	/**
	 * Handles the fifth click option on an object.
	 * @param player		The player that clicked on the object.
	 * @param packet		The packet containing the object's information.
	 */
	private static void fifthClick(final Player player, Packet packet) {

	}

	@Override
	public void handleMessage(Player player, Packet packet) {
		
		//Make sure we aren't doing something else..
		if(player.busy()) {
			return;
		}
		
		switch (packet.getOpcode()) {
		case PacketConstants.OBJECT_FIRST_CLICK_OPCODE:
			firstClick(player, packet);
			break;
		case PacketConstants.OBJECT_SECOND_CLICK_OPCODE:
			secondClick(player, packet);
			break;
		case PacketConstants.OBJECT_THIRD_CLICK_OPCODE:
			thirdClick(player, packet);
			break;
		case PacketConstants.OBJECT_FOURTH_CLICK_OPCODE:
			fourthClick(player, packet);
			break;
		case PacketConstants.OBJECT_FIFTH_CLICK_OPCODE:
			fifthClick(player, packet);
			break;
		}
	}
	
	public static boolean handlers(Player player, GameObject gameObject) {
		if(Woodcutting.attemptWoodcutting(player, gameObject)) {
			return true;
		}
		if(Mining.attemptMining(player, gameObject)) {
			return true;
		}
		if(Thieving.attemptThieving(player,  gameObject)) {
			return true;
		}
		if(Smelting.attemptSmelting(player, gameObject)) {
			return true;
		}
		if(Runecrafting.attemptRunecrafting(player, gameObject)) {
			return true;
		}
		if(AbyssHandler.clickObject(player, gameObject.getId())) {
			return true;
		}
		if(Spinning.attemptSpinning(player, gameObject)) {
			return true;
		}
		if(Jewelry.attemptJewelryMaking(player, gameObject)) {
			return true;
		}
		return false;
	}

	private static final int MAGICAL_ALTAR = 29150;
	private static final int PRAYER_ALTAR = 409;
	private static final int EDGEVILLE_BANK = 6943;
	private static final int COOKS_GUILD_BANK = 7409;
	private static final int WILDERNESS_DITCH = 23271;
}
