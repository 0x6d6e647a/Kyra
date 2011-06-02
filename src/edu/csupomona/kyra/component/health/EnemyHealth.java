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
import edu.csupomona.kyra.entity.Entity;

public class EnemyHealth extends HealthComponent{

	Entity player1, player2;
	
	public EnemyHealth(String id, int health, Entity player1, Entity player2) {
		super(id, health);
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Polygon polygon = owner.getPhysicsComponent().getPolygon();
		if (isVulnerable()) {
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
			if (Kyra.vs){
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
			}
		}
	}
}
