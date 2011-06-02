package edu.csupomona.kyra.component.health;

import java.util.ArrayList;

import edu.csupomona.kyra.entity.Entity;

public class KioskPlayerHealth extends PlayerHealth {
	
	final int ENEMY_BODY_DMG = 0,
		BOSS_BULLET_DMG = 0,
		BOSS_BODY_DMG = 0,
		HEART_HEAL = 1;
	
	public KioskPlayerHealth(String id, int health, ArrayList<Entity> entities) {
		super(id, health, entities);
	}
	

}
