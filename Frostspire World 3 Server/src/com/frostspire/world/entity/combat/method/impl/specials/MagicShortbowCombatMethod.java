package com.frostspire.world.entity.combat.method.impl.specials;

import com.frostspire.engine.task.Task;
import com.frostspire.engine.task.TaskManager;
import com.frostspire.world.entity.combat.CombatSpecial;
import com.frostspire.world.entity.combat.CombatType;
import com.frostspire.world.entity.combat.hit.QueueableHit;
import com.frostspire.world.entity.combat.method.CombatMethod;
import com.frostspire.world.entity.combat.ranged.RangedData;
import com.frostspire.world.entity.combat.ranged.RangedData.RangedWeaponData;
import com.frostspire.world.entity.impl.Character;
import com.frostspire.world.entity.impl.player.Player;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Graphic;
import com.frostspire.world.model.GraphicHeight;
import com.frostspire.world.model.Priority;
import com.frostspire.world.model.Projectile;

public class MagicShortbowCombatMethod implements CombatMethod {

	private static final Animation ANIMATION = new Animation(1074, Priority.HIGH);
	private static final Graphic GRAPHIC = new Graphic(250, GraphicHeight.HIGH, Priority.HIGH);

	@Override
	public CombatType getCombatType() {
		return CombatType.RANGED;
	}

	@Override
	public QueueableHit[] fetchDamage(Character character, Character target) {
		return new QueueableHit[]{new QueueableHit(character, target, this, true, 3), new QueueableHit(character, target, this, true, 2)};
	}

	@Override
	public boolean canAttack(Character character, Character target) {
		Player player = character.getAsPlayer();

		//Check if current player's ranged weapon data is magic shortbow.
		if(!(player.getCombat().getRangedWeaponData() != null 
				&& player.getCombat().getRangedWeaponData() == RangedWeaponData.MAGIC_SHORTBOW)) {
			return false;
		}

		//Check if player has enough ammunition to fire.
		if(!RangedData.checkAmmo(player)) {
			return false;
		}

		return true;
	}

	@Override
	public void onQueueAdd(Character character, Character target) {
		final Player player = character.getAsPlayer();
		
		CombatSpecial.drain(player, CombatSpecial.MAGIC_SHORTBOW.getDrainAmount());
		
		new Projectile(player, target, 249, 70, 30, 43, 31, 0).sendProjectile();
		RangedData.decrementAmmo(player, target.getPosition());
		
		TaskManager.submit(new Task(1, player, false) {
			@Override
			protected void execute() {
				new Projectile(player, target, 249, 70, 30, 43, 31, 0).sendProjectile();
				RangedData.decrementAmmo(player, target.getPosition());
				stop();
			}
		});
	}

	@Override
	public int getAttackSpeed(Character character) {
		return character.getBaseAttackSpeed() + 1;
	}

	@Override
	public int getAttackDistance(Character character) {
		return 6;
	}

	@Override
	public void startAnimation(Character character) {
		character.performAnimation(ANIMATION);
		character.performGraphic(GRAPHIC);
	}

	@Override
	public void finished(Character character) {

	}

	@Override
	public void handleAfterHitEffects(QueueableHit hit) {

	}
}