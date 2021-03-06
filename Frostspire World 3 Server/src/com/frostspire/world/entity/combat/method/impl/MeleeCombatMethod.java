package com.frostspire.world.entity.combat.method.impl;

import com.frostspire.cache.impl.definitions.WeaponInterfaces.WeaponInterface;
import com.frostspire.world.entity.combat.CombatType;
import com.frostspire.world.entity.combat.hit.QueueableHit;
import com.frostspire.world.entity.combat.method.CombatMethod;
import com.frostspire.world.entity.impl.Character;
import com.frostspire.world.model.Animation;
/**
 * The melee combat method.
 * @author Gabriel Hannason
 */
public class MeleeCombatMethod implements CombatMethod {

	@Override
	public CombatType getCombatType() {
		return CombatType.MELEE;
	}

	@Override
	public QueueableHit[] fetchDamage(Character character, Character target) {
		return new QueueableHit[]{new QueueableHit(character, target, this, true, 0)};
	}

	@Override
	public boolean canAttack(Character character, Character target) {
		return true;
	}

	@Override
	public void onQueueAdd(Character character, Character target) {

	}

	@Override
	public int getAttackSpeed(Character character) {
		return character.getBaseAttackSpeed();
	}

	@Override
	public int getAttackDistance(Character character) {
		if(character.isPlayer()) {
			if(character.getAsPlayer().getCombat().getWeapon() == WeaponInterface.HALBERD) {
				return 2;
			}
		}
		return 1;
	}

	@Override
	public void startAnimation(Character character) {
		int animation = character.getAttackAnim();
		
		if(animation != -1) {
			character.performAnimation(new Animation(animation));
		}
	}

	@Override
	public void finished(Character character) {

	}

	@Override
	public void handleAfterHitEffects(QueueableHit hit) {

	}

}
