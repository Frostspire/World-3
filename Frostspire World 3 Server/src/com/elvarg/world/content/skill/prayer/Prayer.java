package com.elvarg.world.content.skill.prayer;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Animation;
import com.elvarg.world.model.Item;

public class Prayer {
	
	public static boolean attemptBoneOnAltar(Player player, int itemId, GameObject object) {
		BoneData bone = BoneData.getBone(itemId);
		if(bone == null) {
			return false;
		} else {
			player.setPositionToFace(object.getPosition());
			player.setCurrentSkillingTask(new BoneOnAltarTask(player, bone));
			return true;
		}
	}
	
	public static boolean attemptBuryBone(Player player, int itemId) {
		BoneData bone = BoneData.getBone(itemId);
		if(bone == null) {
			return false;
		} else {
			buryBone(player, bone);
			return true;
		}
	}
	
	public static void buryBone(Player player, BoneData bone) {
		if(!player.getClickDelay().elapsed(2000))
			return;
		
		player.getSkillManager().stopSkilling();
		player.getPacketSender().sendInterfaceRemoval();
		player.performAnimation(new Animation(827));
		player.getPacketSender().sendMessage("You dig a hole in the ground..");
		player.getInventory().delete(new Item(bone.getId()));
		player.getSkillManager().addExperience(Skill.PRAYER, (int) (bone.getExperience()));
		player.getPacketSender().sendMessage("and bury the "+ItemDefinition.forId(bone.getId()).getName());
		player.getClickDelay().reset();
	}

}
