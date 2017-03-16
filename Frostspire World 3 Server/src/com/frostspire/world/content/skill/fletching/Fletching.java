package com.frostspire.world.content.skill.fletching;

import java.util.HashMap;

import com.frostspire.cache.impl.definitions.ItemDefinition;
import com.frostspire.util.Misc;
import com.frostspire.world.content.skill.Skill;
import com.frostspire.world.content.skill.firemaking.LogData;
import com.frostspire.world.content.skill.fletching.fletchable.Fletchable;
import com.frostspire.world.content.skill.fletching.fletchable.FletchableItem;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Item;

public enum Fletching {
	SINGLETON;

	public static final String FLETCHABLE_KEY = "FLETCHABLE_KEY";

	private final HashMap<Integer, Fletchable> FLETCHABLES = new HashMap<>();
	
	public boolean fletch(Player player, int index, int amount) {

		Fletchable fletchable = player.getFletchingItem();//(Fletchable) player.getAttributes().get(FLETCHABLE_KEY);

		if (fletchable == null) {
			return false;
		}

		if (start(player, fletchable, index, amount)) {
			return true;
		}

		return false;
	}

	public boolean itemOnItem(Player player, Item use, Item with) {
		
		if (LogData.getLog(use.getId()) == null && LogData.getLog(with.getId()) == null) {
			return false;
		}

		final Fletchable fletchable = getFletchable(use.getId(), with.getId());

		if (fletchable == null || use.getId() == 590 || with.getId() == 590) {
			return false;
		}

		String prefix = fletchable.getWith().getDefinition().getName().split(" ")[0];

		switch (fletchable.getFletchableItems().length) {
		case 1:
			player.setFletchingItem(fletchable);
			player.getPacketSender().sendString(2799, "\\n \\n \\n \\n \\n" + ItemDefinition.forId(fletchable.getFletchableItems()[0].getProduct().getId()).getName());
			player.getPacketSender().sendInterfaceModel(1746, fletchable.getFletchableItems()[0].getProduct().getId(), 170);
			player.getPacketSender().sendChatboxInterface(4429);
			return true;
		case 2:
			player.setFletchingItem(fletchable);
			player.getPacketSender().sendInterfaceModel(8869, fletchable.getFletchableItems()[0].getProduct().getId(), 170);
			player.getPacketSender().sendInterfaceModel(8870, fletchable.getFletchableItems()[1].getProduct().getId(), 170);

			player.getPacketSender().sendString(8874, "\\n \\n \\n \\n \\n ".concat(prefix + " Short Bow"));
			player.getPacketSender().sendString(8878, "\\n \\n \\n \\n \\n ".concat(prefix + " Long Bow"));

			player.getPacketSender().sendChatboxInterface(8866);
			return true;
		case 3:
			player.setFletchingItem(fletchable);
			player.getPacketSender().sendInterfaceModel(8883, fletchable.getFletchableItems()[0].getProduct().getId(), 170);
			player.getPacketSender().sendInterfaceModel(8884, fletchable.getFletchableItems()[1].getProduct().getId(), 170);
			player.getPacketSender().sendInterfaceModel(8885, fletchable.getFletchableItems()[2].getProduct().getId(), 170);
			player.getPacketSender().sendString(8889, "\\n \\n \\n \\n \\n".concat(prefix + " Short Bow"));
			player.getPacketSender().sendString(8893, "\\n \\n \\n \\n \\n".concat(prefix + " Long Bow"));
			player.getPacketSender().sendString(8897, "\\n \\n \\n \\n \\n".concat("Crossbow Stock"));
			player.getPacketSender().sendChatboxInterface(8880);
			return true;
		case 4:
			player.setFletchingItem(fletchable);
			player.getPacketSender().sendInterfaceModel(8902, fletchable.getFletchableItems()[0].getProduct().getId(), 170);
			player.getPacketSender().sendInterfaceModel(8903, fletchable.getFletchableItems()[1].getProduct().getId(), 170);
			player.getPacketSender().sendInterfaceModel(8904, fletchable.getFletchableItems()[2].getProduct().getId(), 170);
			player.getPacketSender().sendInterfaceModel(8905, fletchable.getFletchableItems()[3].getProduct().getId(), 170);
			player.getPacketSender().sendString(8909, "\\n \\n \\n \\n \\n".concat("15 Arrow Shafts"));
			player.getPacketSender().sendString(8913, "\\n \\n \\n \\n \\n".concat("Short Bow"));
			player.getPacketSender().sendString(8917, "\\n \\n \\n \\n \\n".concat("Long Bow"));
			player.getPacketSender().sendString(8921, "\\n \\n \\n \\n \\n".concat("Crossbow Stock"));
			player.getPacketSender().sendChatboxInterface(8899);
			return true;
		default:
			return false;
		}
	}

	public void addFletchable(Fletchable fletchable) {
		if (FLETCHABLES.put(fletchable.getWith().getId(), fletchable) != null) {
			System.out.println("[Fletching] Conflicting item values: " + fletchable.getWith().getId() + " Type: " + fletchable.getClass().getSimpleName());
		}
	}

	public Fletchable getFletchable(int use, int with) {
		return FLETCHABLES.get(use) == null ? FLETCHABLES.get(with) : FLETCHABLES.get(use);
	}

	public boolean clickButton(Player player, int button) {
		if(player.getFletchingItem() == null) {
			return false;
		}

		Fletchable fletchable = player.getFletchingItem();

		switch (button) {

		/** Option 1 - Make all */
		case 1747:
			start(player, fletchable, 0, player.getInventory().getAmount(fletchable.getWith().getId()));
			return true;

			/** Option 1 - Make 1 */
		case 8909: //4 options
		case 8889: //3 options
		case 8874: //2 options ?
		case 2799: //1 option ?
			start(player, fletchable, 0, 1);
			return true;

			/** Option 1 - Make 5 */
		case 8908:
		case 8888:
		case 8873:
		case 2798:
			start(player, fletchable, 0, 5);
			return true;

			/** Option 1 - Make 10 */
		case 8907:
		case 8887:
		case 8872:
			start(player, fletchable, 0, 10);
			return true;

			/** Option 1 - Make X */
		case 8906:
		case 8886:
		case 8871:
		case 1748:
			start(player, fletchable, 0, 28);
			return true;

			/** Option 2 - Make 1 */
		case 8913:
		case 8893:
		case 8878:
			start(player, fletchable, 1, 1);
			return true;

			/** Option 2 - Make 5 */
		case 8912:
		case 8892:
		case 8877:
			start(player, fletchable, 1, 5);
			return true;

			/** Option 2 - Make 10 */
		case 8911:
		case 8891:
		case 8876:
			start(player, fletchable, 1, 10);
			return true;

			/** Option 2 - Make X */
		case 8910:
		case 8890:
		case 8875:
			start(player, fletchable, 1, 28);
			return true;

			/** Option 3 - Make 1 */
		case 8917:
		case 8897:
			start(player, fletchable, 2, 1);
			return true;

			/** Option 3 - Make 5 */
		case 8916:
		case 8896:
			start(player, fletchable, 2, 5);
			return true;

			/** Option 3 - Make 10 */
		case 8915:
		case 8895:
			start(player, fletchable, 2, 10);
			return true;

			/** Option 3 - Make X */
		case 8914:
		case 8894:
			start(player, fletchable, 2, 28);
			return true;

			/** Option 4 - Make 1 */
		case 8921:
			start(player, fletchable, 3, 1);
			return true;

			/** Option 4 - Make 5 */
		case 8920:
			start(player, fletchable, 3, 5);
			return true;

			/** Option 4 - Make 10 */
		case 8919:
			start(player, fletchable, 3, 10);
			return true;

			/** Option 4 - Make X */
		case 8918:
			start(player, fletchable, 3, 28);
			return true;

		default:
			return false;
		}
	}

	public boolean start(Player player, Fletchable fletchable, int index, int amount) {
		if (fletchable == null) {
			return false;
		}
		
		if(!player.getClickDelay().elapsed(1000)) {
			return true;
		}
		
		player.getClickDelay().reset();

		player.setFletchingItem(null);

		FletchableItem item = fletchable.getFletchableItems()[index];

		player.getPacketSender().sendInterfaceRemoval();

		if (player.getSkillManager().getMaxLevel(Skill.FLETCHING) < item.getLevel()) {
			player.getPacketSender().sendMessage("<col=369>You need a Fletching level of " + item.getLevel() + " to do that.");
			return true;
		}

		if (!(player.getInventory().contains(fletchable.getIngediants()))) {
			String firstName = fletchable.getUse().getDefinition().getName().toLowerCase();
			String secondName = fletchable.getWith().getDefinition().getName().toLowerCase();

			if (fletchable.getUse().getAmount() > 1 && !firstName.endsWith("s")) {
				firstName = firstName.concat("s");
			}

			if (fletchable.getWith().getAmount() > 1 && !secondName.endsWith("s")) {
				secondName = secondName.concat("s");
			}

			if (fletchable.getUse().getAmount() == 1 && firstName.endsWith("s")) {
				firstName = firstName.substring(0, firstName.length() - 1);
			}

			if (fletchable.getWith().getAmount() == 1 && secondName.endsWith("s")) {
				secondName = secondName.substring(0, secondName.length() - 1);
			}

			final String firstAmount;

			if (fletchable.getUse().getAmount() == 1) {
				firstAmount = Misc.anOrA(fletchable.getUse().getDefinition().getName());
			} else {
				firstAmount = String.valueOf(fletchable.getUse().getAmount());
			}

			final String secondAmount;

			if (fletchable.getWith().getAmount() == 1) {
				secondAmount = Misc.anOrA(fletchable.getWith().getDefinition().getName());
			} else {
				secondAmount = String.valueOf(fletchable.getWith().getAmount());
			}

			String firstRequirement = firstAmount + " " + firstName;
			String secondRequirement = secondAmount + " " + secondName;
			player.getPacketSender().sendMessage("You need " + firstRequirement + " and " + secondRequirement + " to do that.");
			return true;
		}

		player.setCurrentSkillingTask(new FletchingTask(player, fletchable, item, amount));

		return true;
	}
}