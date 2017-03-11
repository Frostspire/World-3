package com.elvarg.world.content.skill.firemaking;

import com.elvarg.engine.task.TaskManager;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.object.ObjectHandler;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.grounditems.GroundItemManager;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.GroundItem;
import com.elvarg.world.model.Item;

public class Firemaking {

	public static boolean attemptFiremaking(Player player, Item itemUsed, Item usedWith) {
		
		if(LogData.getLog(itemUsed.getId()) == null && LogData.getLog(usedWith.getId()) == null) {
			return false;
		}
		
		Item log = usedWith.getId() != 590 ? usedWith : itemUsed.getId() != 590 ? itemUsed : null;
		
		if(log == null || itemUsed.getId() == 946 || usedWith.getId() == 946) {
			return false;
		}
		
		LogData logData = LogData.getLog(log.getId());
		
		if(logData == null) {
			return false;
		} else {
			lightFire(player, logData);
			return true;
		}
	}
	
	private static boolean meetsRequirements(Player player, LogData log) {
		
		if (!player.getClickDelay().elapsed(2000))
			return false;
		
		if(!player.getLocation().isFiremakingAllowed()) {
			player.getPacketSender().sendMessage("You can not light a fire in this area.");
			return false;
		}

		if (player.getSkillManager().getMaxLevel(Skill.FIREMAKING) < log.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a firemaking level of " + log.getLevelRequired() + " to light this log.");
			return false;
		}

		if (ObjectHandler.objectExists(player.getPosition())) {
			player.getPacketSender().sendMessage("You cannot light a fire here.");
			return false;
		}

		if (!player.getInventory().contains(590)) {
			player.getPacketSender().sendMessage("You need a tinderbox to light this log.");
			return false;
		}

		return true;
	}
	
	public static void lightFire(Player player, LogData log) {

		if (!meetsRequirements(player, log)) {
			return;
		}
		
		player.getPacketSender().sendInterfaceRemoval();
		player.performAnimation(new Animation(733));
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(log.getLogId()), player.getPosition(), player.getUsername(), false, 150, false, -1));
		player.getInventory().delete(new Item(log.getLogId()));
		
		player.setCurrentSkillingTask(new FiremakingTask(player, log));
	}
	
	public static void successfulFire(Player player, LogData log) {
		GroundItemManager.remove(GroundItemManager.getGroundItem(player, new Item(log.getLogId()), player.getPosition()), true);

		player.performAnimation(new Animation(65535));

		GameObject fire = new GameObject(5249, player.getPosition());
		ObjectHandler.spawnGlobalObject(fire);

		TaskManager.submit(new RemoveFireTask(player, fire, log.getBurnTime()));

		player.getSkillManager().addExperience(Skill.FIREMAKING, log.getXp());
		player.getPacketSender().sendMessage("You successfully light a fire.");
		player.getMovementQueue().stepAway(player);
	}
	
}
