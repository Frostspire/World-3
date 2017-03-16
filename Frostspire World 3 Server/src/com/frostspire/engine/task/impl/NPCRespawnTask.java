package com.frostspire.engine.task.impl;

import com.frostspire.engine.task.Task;
import com.frostspire.world.World;
import com.frostspire.world.entity.impl.npc.NPC;

/**
 * A {@link Task} implementation which handles the respawn
 * of an npc.
 * 
 * @author Professor Oak
 */
public class NPCRespawnTask extends Task {

	public NPCRespawnTask(NPC npc, int respawn) {
		super(respawn);
		this.npc = npc;
	}

	private final NPC npc;

	@Override
	public void execute() {
		
		NPC npc_ = new NPC(npc.getId(), npc.getSpawnPosition());
		npc_.setHitpoints(npc.getDefinition().getHitpoints());
		World.getNpcAddQueue().add(npc_);
		
		stop();
	}

}
