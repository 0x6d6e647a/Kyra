/**************************************************************
 *	file:		EnemyHealth.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Enemy Health
**************************************************************/
package edu.csupomona.kyra.component.health;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.physics.PhysicsComponent;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.entity.EntityType;
import edu.csupomona.kyra.state.level.ScoreComponent;

public class EnemyHealth extends HealthComponent{

	Entity player1, player2;
	
	public EnemyHealth(String id, int health, Entity player1, Entity player2) {
		super(id, health);
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		PhysicsComponent physics = owner.getPhysicsComponent();
		ScoreComponent p1Score = player1.getScoreComponent();
		EntityType type = owner.getType();
		if (physics != null) {
			Polygon polygon = owner.getPhysicsComponent().getPolygon();
			if (isVulnerable()) {
				//Check player1's bullets
				ArrayList<Entity> p1Bullets = player1.getGunComponent().getBullets();
				for (Iterator<Entity> iter = p1Bullets.iterator(); iter.hasNext();) {
					Entity bullet = iter.next();
					Polygon bulletPoly = bullet.getPhysicsComponent().getPolygon();
					if ((bulletPoly != null) && (bulletPoly.intersects(polygon))) {
						setBadHit();
						addHealth(-1);
						makeTempInvulnerable(75);
						iter.remove();
						
						break;
					}
				}
				if (isDead()) {
					long points = 0;
					if (type.equals(EntityType.ZOMBIE))
						points = 1000;
					else if (type.equals(EntityType.BOSS))
						points = 50000;
					if (p1Score != null)
						p1Score.addToScore(points);
					return;
				}
				//Check player2's bullets
				if (Kyra.vs) {
					ScoreComponent p2Score = player2.getScoreComponent();
					ArrayList<Entity> p2Bullets = player2.getGunComponent().getBullets();
					for (Iterator<Entity> iter = p2Bullets.iterator(); iter.hasNext();) {
						Entity bullet = iter.next();
						Polygon bulletPoly = bullet.getPhysicsComponent().getPolygon();
						if ((bulletPoly != null) && (bulletPoly.intersects(polygon))) {
							setBadHit();
							addHealth(-1);
							makeTempInvulnerable(75);
							iter.remove();
							break;
						}
					}
					if (isDead()) {
						long points = 0;
						if (type.equals(EntityType.ZOMBIE))
							points = 1000;
						else if (type.equals(EntityType.BOSS))
							points = 50000;
						if (p2Score != null)
							p2Score.addToScore(points);
					}
				}
			}
		}
	}
}
