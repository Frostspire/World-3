package com.elvarg.cache.impl.definitions;

import com.elvarg.GameConstants;
import com.elvarg.util.JsonLoader;
import com.elvarg.world.World;
import com.elvarg.world.entity.impl.npc.NPC;
import com.elvarg.world.model.Position;
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
