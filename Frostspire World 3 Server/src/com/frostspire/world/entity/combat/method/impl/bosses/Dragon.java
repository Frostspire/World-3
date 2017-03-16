package com.frostspire.world.entity.combat.method.impl.bosses;

import com.frostspire.world.entity.combat.CombatType;
import com.frostspire.world.entity.combat.hit.QueueableHit;
import com.frostspire.world.entity.combat.method.CombatMethod;
import com.frostspire.world.entity.impl.Character;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Graphic;
import com.frostspire.world.model.Projectile;

public class Dragon implements CombatMethod {

	@Override
	public boolean canAttack(Character character, Character target) {
		return true;
	}

	@Override
	public void onQueueAdd(Character character, Character target) {
		new Projectile(character, target, 393, 50, 60, 43, 31, 0).sendProjectile();
	}

	@Override
	public int getAttackSpeed(Character character) {
		return character.getBaseAttackSpeed();
	}

	@Override
	public int getAttackDistance(Character character) {
		return 7;
	}

	@Override
	public void startAnimation(Character character) {
		character.performAnimation(new Animation(81));
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.DRAGON_FIRE;
	}

	@Override
	public QueueableHit[] fetchDamage(Character character, Character target) {
		return new QueueableHit[]{new QueueableHit(character, target, this, true, 2)};
	}

	@Override
	public void finished(Character character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleAfterHitEffects(QueueableHit hit) {
		Character target = hit.getTarget();
		target.performGraphic(new Graphic(393));
	}

}
