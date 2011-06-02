/**************************************************************
 *	file:		PlayerHealth.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Player health
**************************************************************/
package edu.csupomona.kyra.component.health;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.entity.EntityType;

public class PlayerHealth extends HealthComponent {
	ArrayList<Entity> entities;
	boolean vulnerable;
	
	final long INVUL_TIME = 1200;
	final int ENEMY_BODY_DMG = -1,
		BOSS_BULLET_DMG = -1,
		BOSS_BODY_DMG = -1,
		HEART_HEAL = 1;
		
	
	public PlayerHealth(String id, int health, ArrayList<Entity> entities) {
		super(id, health);
		this.entities = entities;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if (isVulnerable()) {
			for (Iterator<Entity> iter = entities.iterator(); iter.hasNext();) {
				Entity entity = iter.next();
				if (entity.getPhysicsComponent() == null)
					continue;
				Polygon playerPoly = owner.getPhysicsComponent().getPolygon();
				EntityType type = entity.getType();
				if (type.equals(EntityType.ZOMBIE)) {
					//Damage from Zombie hits
					Polygon zombiePoly = entity.getPhysicsComponent().getPolygon();
					if ((zombiePoly != null) && (zombiePoly.intersects(playerPoly))) {
						setBadHit();
						addHealth(ENEMY_BODY_DMG);
						makeTempInvulnerable(INVUL_TIME);
						break;
					}
				}
				else if (type.equals(EntityType.BOSS)) {
					//Damage from boss body
					Polygon bossPoly = entity.getPhysicsComponent().getPolygon();
					if ((bossPoly != null) && (bossPoly.intersects(playerPoly))) {
						setBadHit();
						addHealth(BOSS_BODY_DMG);
						makeTempInvulnerable(INVUL_TIME);
						break;
					}
					//Damage from boss bullets
					boolean wasHit = false;
					for (Iterator<Entity> bulletIter = entity.getGunComponent().getBullets().iterator(); bulletIter.hasNext();) {
						Polygon bulletPoly = bulletIter.next().getPhysicsComponent().getPolygon();
						if ((bulletPoly != null) && (bulletPoly.intersects(playerPoly))) {
							wasHit = true;
							setBadHit();
							addHealth(BOSS_BULLET_DMG);
							makeTempInvulnerable(INVUL_TIME);
							bulletIter.remove();
							break;
						}
					}
					if (wasHit)
						break;
				}
				else if (type.equals(EntityType.HEART)) {
					Polygon heartPoly = entity.getPhysicsComponent().getPolygon();
					ItemHealth heartHealth = (ItemHealth)entity.getHealthComponent();
					if ((heartPoly != null) && (heartPoly.intersects(playerPoly))) {
						setGoodHit();
						addHealth(1);
						heartHealth.useItem();
					}
				}
				
			}
		}
	}
}
