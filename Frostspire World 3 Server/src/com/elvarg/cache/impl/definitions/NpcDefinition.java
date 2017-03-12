package com.elvarg.cache.impl.definitions;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import com.elvarg.GameConstants;
import com.google.gson.Gson;

/**
 * 
 * @author Stan
 *
 */

public class NpcDefinition {

	/**
	 * {@link java.util.Logger}
	 */
	private static final Logger LOGGER = Logger.getLogger(NpcDefinition.class.getName());

	/**
	 * Represents the maximum of NPCs that exists in this revision
	 */
	public static final int MAX_NPC_AMOUNT = 14500;

	/**
	 * An array that stores all NPC definitions
	 */
	private static NpcDefinition[] definitions = new NpcDefinition[MAX_NPC_AMOUNT];

	/**
	 * A getter that fetches NPC Definitions for specific NPCs
	 * 
	 * @param id
	 * @return {@code definitions}
	 */
	public static final NpcDefinition get(int id) {
		if (id <= 0 || id >= MAX_NPC_AMOUNT)
			return null;
		return definitions[id];
	}

	/**
	 * Loads NPC definitions from JSON file.
	 */
	public static void loadNPCDefinitions() {
		LOGGER.info("Loading NPC definitions...");
		try {
			NpcDefinition[] in = new Gson().fromJson(new FileReader(GameConstants.DEFINITIONS_DIRECTORY + "NpcDefinitions.json"),
					NpcDefinition[].class);
			for (NpcDefinition defs : in) {
				definitions[defs.id] = defs;
			}
		} catch (IOException e) {
			System.out.println("Error loading NPC Definitions!");
			e.printStackTrace();
		}
		LOGGER.info(definitions.length + " NPC definitions loaded...");
	}

	/**
	 * Represents the NPC id
	 */
	private int id;

	/**
	 * Represents the name of the NPC
	 */
	private String name;

	/**
	 * Represents the description of the NPC
	 */
	private String description;
	
	/**
	 * Represents the NPCs size
	 */
	private int size;

	/**
	 * Field that represents if this NPC is attackable or not;
	 */
	private boolean attackable;
	
	/**
	 * Represents the combat level of the NPC
	 */
	private int combatLevel;

	/**
	 * Field that represents this NPCs aggressiveness;
	 */
	private boolean aggressive;
	
	/**
	 * Field that represents if this NPC is poisonous or not
	 */
	private boolean poisonous;
	
	/**
	 * Field that represents if this NPC is undead or not;
	 */
	private boolean undead;
	
	/**
	 * Field that represents if this NPC is immune to poison or not;
	 */
	private boolean poisonImmunity;
	
	/**
	 * Field that represents if this NPC is immune to poison or not;
	 */
	private boolean venomImmunity;

	/**
	 * Represents the slayer level required to kill this NPC
	 */
	private int slayerLevel;
	
	/**
	 * Represents the amount of hitpoints this NPC has
	 */
	private int hitpoints;
	
	/**
	 * Represents the attack speed for this NPC
	 */
	private int attackSpeed;
	
	/**
	 * Represents this NPC's max hit
	 */
	private int maxHit;
	
	/**
	 * Represents the NPCs skills
	 */
	private int[] skills;

	/**
	 * A list that represents the NPCs bonuses
	 */
	private int[] bonusses;
	
	/**
	 * Represents the radius the NPC will walk around it's spawn
	 */
	private int walkRadius;
	
	/**
	 * Represents the distance until the NPC is no longer aggressive
	 */
	private int aggressionDistance;
	
	/**
	 * Represents the maximum distance the NPC will follow a player in combat
	 */
	private int combatFollowDistance = 7;
	
	/**
	 * Represents the amount of time until the NPC respawns
	 */
	private int respawn;
	
	/**
	 * Represents the id of the NPC's attack animation
	 */
	private int attackAnimation;
	
	/**
	 * Represents the id of the NPC's block animation
	 */
	private int blockAnimation;
	
	/**
	 * Represents the id of the NPC's death animation
	 */
	private int deathAnimation;
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isAttackable() {
		return this.attackable;
	}

	public int getCombatLevel() {
		return this.combatLevel;
	}

	public boolean isAggressive() {
		return this.aggressive;
	}
	
	public boolean isPoisonous() {
		return this.poisonous;
	}
	
	public boolean isUndead() {
		return this.undead;
	}
	
	public boolean isImmuneToPoison() {
		return this.poisonImmunity;
	}
	
	public boolean isImmuneToVenom() {
		return this.venomImmunity;
	}
	
	public int getSlayerLevel() {
		return this.slayerLevel;
	}
	
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	public int getAttackSpeed() {
		return this.attackSpeed;
	}
	
	public int getMaxHit() {
		return this.maxHit;
	}

	public int[] getSkills() {
		return this.skills;
	}

	public int[] getBonuses() {
		return this.bonusses;
	}
	
	public int getWalkRadius() {
		return this.walkRadius;
	}
	
	public int getAggressionDistance() {
		return this.aggressionDistance;
	}
	
	public int getCombatFollowDistance() {
		return this.combatFollowDistance;
	}
	
	public int getRespawn() {
		return this.respawn;
	}
	
	public int getAttackAnimation() {
		return this.attackAnimation;
	}
	
	public int getBlockAnimation() {
		return this.blockAnimation;
	}
	
	public int getDeathAnimation() {
		return this.deathAnimation;
	}
	
	public int getAttackLevel() {
		return this.skills[0];
	}
	
	public int getStrengthLevel() {
		return this.skills[1];
	}
	
	public int getDefenceLevel() {
		return this.skills[2];
	}
	
	public int getRangedLevel() {
		return this.skills[3];
	}
	
	public int getMagicLevel() {
		return this.skills[4];
	}
	
	public int getDefenceMelee() {
		return (int)((this.bonusses[5] + this.bonusses[6] + this.bonusses[7]) / 3);
	}
	
	public int getDefenceMage() {
		return this.bonusses[8];
	}
	
	public int getDefenceRange() {
		return this.bonusses[9];
	}

}