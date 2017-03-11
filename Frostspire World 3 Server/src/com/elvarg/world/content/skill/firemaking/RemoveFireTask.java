package com.elvarg.world.content.skill.firemaking;

import com.elvarg.engine.task.Task;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.object.ObjectHandler;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.grounditems.GroundItemManager;
import com.elvarg.world.model.GroundItem;
import com.elvarg.world.model.Item;
import com.elvarg.world.model.SecondsTimer;

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
