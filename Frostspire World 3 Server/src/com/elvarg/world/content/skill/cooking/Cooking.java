package com.elvarg.world.content.skill.cooking;

import java.security.SecureRandom;

import com.elvarg.engine.task.TaskManager;
import com.elvarg.world.content.skill.Skill;
import com.elvarg.world.entity.impl.object.GameObject;
import com.elvarg.world.entity.impl.player.Player;

public class Cooking {
	
	private static SecureRandom cookingRandom = new SecureRandom();
	
	public static boolean canCookingHandle(Player player, int itemId, GameObject gameObject) {
		CookingData data = CookingData.getItem(itemId);
		if(data == null) {
			return false;
		} else {
			if(CookingData.isRange(gameObject.getId())) {
				//selectionInterface(player, data);
				player.setPositionToFace(gameObject.getPosition());
				cookFood(player, data);
				return true;
			} else {
				return false;
			}
		}
	}

	/*public static void selectionInterface(Player player, CookingData data) {
		player.setSelectedSkillingItem(cookingData.getRawItem());
		player.setInputHandling(new EnterAmountToCook());
		player.getPacketSender().sendString(2799, ItemDefinition.forId(cookingData.getCookedItem()).getName()).sendInterfaceModel(1746, cookingData.getCookedItem(), 150).sendChatboxInterface(4429);
		player.getPacketSender().sendString(2800, "How many would you like to cook?");
	}*/
	
	public static void cookFood(Player player, CookingData data) {
		if(!canCook(player, data))
			return;
		
		player.setCurrentSkillingTask(new CookingTask(player, data));
		//TaskManager.submit(new CookingTask(player, data));
		
	}
	
	public static boolean canCook(Player player, CookingData data) {
		if(player.getSkillManager().getMaxLevel(Skill.COOKING) < data.getLevelRequired()) {
			player.getPacketSender().sendMessage("You need a Cooking level of atleast "+data.getLevelRequired()+" to cook this.");
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean success(Player player, int burnBonus, int levelReq, int stopBurn) {
		if (player.getSkillManager().getCurrentLevel(Skill.COOKING) >= stopBurn) {
			return true;
		}
		double burn_chance = (45.0 - burnBonus);
		double cook_level = player.getSkillManager().getCurrentLevel(Skill.COOKING);
		double lev_needed = (double) levelReq;
		double burn_stop = (double) stopBurn;
		double multi_a = (burn_stop - lev_needed);
		double burn_dec = (burn_chance / multi_a);
		double multi_b = (cook_level - lev_needed);
		burn_chance -= (multi_b * burn_dec);
		double randNum = cookingRandom.nextDouble() * 100.0;
		return burn_chance <= randNum;
	}
	
	
}
