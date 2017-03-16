package com.frostspire.world.content.skill.firemaking;

import com.frostspire.engine.task.Task;
import com.frostspire.world.entity.impl.object.GameObject;
import com.frostspire.world.entity.impl.object.ObjectHandler;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.grounditems.GroundItemManager;
import com.frostspire.world.model.GroundItem;
import com.frostspire.world.model.Item;
import com.frostspire.world.model.SecondsTimer;

public class RemoveFireTask extends Task {
	
	private Player player;
	private SecondsTimer timer;
	private GameObject fire;
	
	public RemoveFireTask(Player player, GameObject fire, int seconds) {
		this.player = player;
		this.fire = fire;
		this.timer = new SecondsTimer(seconds);
	}

	@Override
	protected void execute() {
		
		if(timer.finished()) {
			ObjectHandler.despawnGlobalObject(fire);
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(592), fire.getPosition(), "", true, 150, false, -1));
			stop();
		}
		
	}

}
