package com.frostspire.net.packet.impl;

import com.frostspire.GameConstants;
import com.frostspire.cache.impl.definitions.WeaponInterfaces;
import com.frostspire.net.packet.Packet;
import com.frostspire.net.packet.PacketListener;
import com.frostspire.util.Misc;
import com.frostspire.world.content.Emotes;
import com.frostspire.world.content.ItemsKeptOnDeath;
import com.frostspire.world.content.TeleportsInterface;
import com.frostspire.world.content.clan.ClanChatManager;
import com.frostspire.world.content.skill.SkillTeleportHandler;
import com.frostspire.world.content.skill.crafting.spinning.Spinning;
import com.frostspire.world.content.skill.fletching.Fletching;
import com.frostspire.world.content.skill.prayer.PrayerHandler;
import com.frostspire.world.content.skill.smithing.Smelting;
import com.frostspire.world.entity.combat.magic.Autocasting;
import com.frostspire.world.entity.combat.magic.MagicClickSpells;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.PlayerRights;
import com.frostspire.world.model.container.impl.Bank;
import com.frostspire.world.model.equipment.BonusManager;
import com.frostspire.world.model.teleportation.TeleportHandler;
import com.frostspire.world.model.teleportation.TeleportType;

/**
 * This packet listener manages a button that the player has clicked upon.
 * @author Gabriel Hannason
 */

public class ButtonClickPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int button = packet.readInt();

		if(player.getRights() == PlayerRights.DEVELOPER) {
			player.getPacketSender().sendConsoleMessage("Button: "+button);
		}
		
		if(handlers(player, button)) {
			return;
		}

		switch(button) {


		case LOGOUT:
			if(player.canLogout()) {
				player.logout();
			} else {
				player.getPacketSender().sendMessage("You cannot log out at the moment.");
			}
			break;


		case TOGGLE_RUN_ENERGY_ORB:
		case TOGGLE_RUN_ENERGY_SETTINGS:
			if(player.getRunEnergy() > 0 && !player.busy()) {
				player.setRunning(!player.isRunning());
				player.getPacketSender().sendRunStatus();
			} else {
				player.getPacketSender().sendMessage("You cannot do that right now.");
			}
			break;

		case OPEN_EQUIPMENT_SCREEN:
			if(!player.busy()) {
				BonusManager.open(player);
			} else {
				player.getPacketSender().sendMessage("You cannot do that right now.");
			}
			break;

		case OPEN_PRICE_CHECKER:
			if(!player.busy()) {
				player.getPriceChecker().open();
			} else {
				player.getPacketSender().sendMessage("You cannot do that right now.");
			}
			break;
			
		case PRICE_CHECKER_WITHDRAW_ALL:
			player.getPriceChecker().withdrawAll();
			break;
			
		case PRICE_CHECKER_DEPOSIT_ALL:
			player.getPriceChecker().depositAll();
			break;
			
		case OPEN_ITEMS_KEPT_ON_DEATH_SCREEN:
			if(!player.busy()) {
				ItemsKeptOnDeath.open(player);
			} else {
				player.getPacketSender().sendMessage("You cannot do that right now.");
			}
			break;
			
		case TRADE_ACCEPT_BUTTON_1:
		case TRADE_ACCEPT_BUTTON_2:
			player.getTrading().acceptTrade();
			break;

		case TOGGLE_AUTO_RETALIATE:
		case TOGGLE_AUTO_RETALIATE_2:
		case TOGGLE_AUTO_RETALIATE_3:
			player.getCombat().setAutoRetaliate(!player.getCombat().autoRetaliate());
			break;

		case DESTROY_ITEM:
			if(player.getDestroyItem() != -1 && player.getInterfaceId() == 55) {
				player.getInventory().delete(player.getDestroyItem(), player.getInventory().getAmount(player.getDestroyItem()));
			}
			player.getPacketSender().sendInterfaceRemoval();
			break;

		case CANCEL_DESTROY_ITEM:
			player.getPacketSender().sendInterfaceRemoval();
			break;

		case HOME_TELEPORT_BUTTON:
			TeleportHandler.teleport(player, GameConstants.DEFAULT_POSITION.copy().add(Misc.getRandom(4), Misc.getRandom(2)), TeleportType.NORMAL);
			break;
			
		case AUTOCAST_BUTTON_1:
		case AUTOCAST_BUTTON_2:
			player.getPacketSender().sendMessage("A spell can be autocast by simply right-clicking on it in your Magic spellbook and ").sendMessage("selecting the \"Autocast\" option.");
			break;
			
		case CLOSE_BUTTON_1:
			player.getPacketSender().sendInterfaceRemoval();
			break;
			
		case FIRST_DIALOGUE_OPTION_OF_FIVE:
		case FIRST_DIALOGUE_OPTION_OF_FOUR:
		case FIRST_DIALOGUE_OPTION_OF_THREE:
		case FIRST_DIALOGUE_OPTION_OF_TWO:
			if(player.getDialogueOptions() != null) {
				player.getDialogueOptions().handleOption(player, 1);
			}
			break;

		case SECOND_DIALOGUE_OPTION_OF_FIVE:
		case SECOND_DIALOGUE_OPTION_OF_FOUR:
		case SECOND_DIALOGUE_OPTION_OF_THREE:
		case SECOND_DIALOGUE_OPTION_OF_TWO:
			if(player.getDialogueOptions() != null) {
				player.getDialogueOptions().handleOption(player, 2);
			}
			break;

		case THIRD_DIALOGUE_OPTION_OF_FIVE:
		case THIRD_DIALOGUE_OPTION_OF_FOUR:
		case THIRD_DIALOGUE_OPTION_OF_THREE:
			if(player.getDialogueOptions() != null) {
				player.getDialogueOptions().handleOption(player, 3);
			}
			break;

		case FOURTH_DIALOGUE_OPTION_OF_FIVE:
		case FOURTH_DIALOGUE_OPTION_OF_FOUR:
			if(player.getDialogueOptions() != null) {
				player.getDialogueOptions().handleOption(player, 4);
			}
			break;

		case FIFTH_DIALOGUE_OPTION_OF_FIVE:
			if(player.getDialogueOptions() != null) {
				player.getDialogueOptions().handleOption(player, 5);
			}
			break;
		default:
			//	player.getPacketSender().sendMessage("Player "+player.getUsername()+", click button: "+button);
			break;
		}
	}

	public static boolean handlers(Player player, int button) {
		if(PrayerHandler.togglePrayer(player, button)) {
			return true;
		}
		if(Autocasting.toggleAutocast(player, button)) {
			return true;
		}
		if(WeaponInterfaces.changeCombatSettings(player, button)) {
			BonusManager.update(player);
			return true;
		}
		if(MagicClickSpells.handleSpell(player, button)) {
			return true;
		}
		if(Bank.handleButton(player, button, 0)) {
			return true;
		}
		if(TeleportsInterface.handleButton(player, button)) {
			return true;
		}
		if(Emotes.doEmote(player, button)) {
			return true;
		}
		if(ClanChatManager.handleButton(player, button, 0)) {
			return true;
		}
		if(SkillTeleportHandler.handleButton(player, button)) {
			return true;
		}
		if(Smelting.handleButtonClick(player, button)) {
			return true;
		}
		if(Fletching.SINGLETON.clickButton(player, button)) {
			return true;
		}
		if(Spinning.handleButtonClick(player, button)) {
			return true;
		}
		return false;
	}

	private static final int LOGOUT = 2458;
	private static final int TOGGLE_RUN_ENERGY_ORB = 1050;
	private static final int TOGGLE_RUN_ENERGY_SETTINGS = 19158;
	private static final int OPEN_EQUIPMENT_SCREEN = 27653;
	private static final int OPEN_PRICE_CHECKER = 27651;
	private static final int OPEN_ITEMS_KEPT_ON_DEATH_SCREEN = 27654;
	private static final int TOGGLE_AUTO_RETALIATE = 22845;
	private static final int TOGGLE_AUTO_RETALIATE_2 = 24010;
	private static final int TOGGLE_AUTO_RETALIATE_3 = 24115;
	private static final int DESTROY_ITEM = 14175;
	private static final int CANCEL_DESTROY_ITEM = 14176;
	private static final int PRICE_CHECKER_WITHDRAW_ALL = 18255;
	private static final int PRICE_CHECKER_DEPOSIT_ALL = 18252;
	
	//Magic spell buttons
	private static final int HOME_TELEPORT_BUTTON = 39101;

	//Autocast buttons
	private static final int AUTOCAST_BUTTON_1 = 349;
	private static final int AUTOCAST_BUTTON_2 = 24111;
	
	//Trade buttons
	private static final int TRADE_ACCEPT_BUTTON_1 = 3420;
	private static final int TRADE_ACCEPT_BUTTON_2 = 3546;
	
	//Close buttons
	private static final int CLOSE_BUTTON_1 = 18247;

	//Dialogues
	public static final int FIRST_DIALOGUE_OPTION_OF_FIVE = 2494;
	public static final int SECOND_DIALOGUE_OPTION_OF_FIVE = 2495;
	public static final int THIRD_DIALOGUE_OPTION_OF_FIVE = 2496;
	public static final int FOURTH_DIALOGUE_OPTION_OF_FIVE = 2497;
	public static final int FIFTH_DIALOGUE_OPTION_OF_FIVE = 2498;

	public static final int FIRST_DIALOGUE_OPTION_OF_FOUR = 2482;
	public static final int SECOND_DIALOGUE_OPTION_OF_FOUR = 2483;
	public static final int THIRD_DIALOGUE_OPTION_OF_FOUR = 2484;
	public static final int FOURTH_DIALOGUE_OPTION_OF_FOUR = 2485;

	public static final int FIRST_DIALOGUE_OPTION_OF_THREE = 2471;
	public static final int SECOND_DIALOGUE_OPTION_OF_THREE = 2472;
	public static final int THIRD_DIALOGUE_OPTION_OF_THREE = 2473;

	public static final int FIRST_DIALOGUE_OPTION_OF_TWO = 2461;
	public static final int SECOND_DIALOGUE_OPTION_OF_TWO = 2462;
}
