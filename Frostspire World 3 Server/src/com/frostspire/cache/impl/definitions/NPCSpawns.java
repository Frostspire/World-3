package com.frostspire.cache.impl.definitions;

import com.frostspire.GameConstants;
import com.frostspire.util.JsonLoader;
import com.frostspire.world.World;
import com.frostspire.world.entity.impl.npc.NPC;
import com.frostspire.world.model.Position;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class NPCSpawns {

	public static JsonLoader NPCSpawnsLoader() {
		return new JsonLoader() {
			@Override
			public void load(JsonObject reader, Gson builder) {
				
				int npcId = reader.get("npcId").getAsInt();
				int x = reader.get("x").getAsInt();
				int y = reader.get("y").getAsInt();
				int z = 0;
				int walkRadius = 0;
				
				if(reader.has("z")) {
					z = reader.get("z").getAsInt();
				}
				
				if(reader.has("walk-radius")) {
					walkRadius = reader.get("walk-radius").getAsInt();
				}
				
				NPC npc = new NPC(npcId, new Position(x, y, z));
				
				if(walkRadius > 0) {
					npc.setWalkRadius(walkRadius);
				}
			
				World.getNpcAddQueue().add(npc);
			}
			@Override
			public String filePath() {
				return GameConstants.DEFINITIONS_DIRECTORY + "npc_spawns.json";
			}
		};
	}
	
}
