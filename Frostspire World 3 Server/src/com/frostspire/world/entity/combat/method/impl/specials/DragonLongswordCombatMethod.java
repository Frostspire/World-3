package com.frostspire.world.entity.combat.method.impl.specials;

import com.frostspire.world.entity.combat.CombatSpecial;
import com.frostspire.world.entity.combat.CombatType;
import com.frostspire.world.entity.combat.hit.QueueableHit;
import com.frostspire.world.entity.combat.method.CombatMethod;
import com.frostspire.world.entity.impl.Character;
import com.frostspire.world.model.Animation;
import com.frostspire.world.model.Graphic;
import com.frostspire.world.model.GraphicHeight;
import com.frostspire.world.model.Priority;

public class DragonLongswordCombatMethod implements CombatMethod {

	private static final Animation ANIMATION = new Animation(1058, Priority.HIGH);
	private static final Graphic GRAPHIC = new Graphic(248, GraphicHeight.HIGH, Priority.HIGH);
	
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
		CombatSpecial.drain(character.getAsPlayer(), CombatSpecial.DRAGON_LONGSWORD.getDrainAmount());
	}

	@Override
	public int getAttackSpeed(Character character) {
		return character.getBaseAttackSpeed();
	}

	@Override
	public int getAttackDistance(Character character) {
		return 1;
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