package edu.csupomona.kyra.component.health;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.entity.Entity;

public class EnemyHealth extends HealthComponent{

	Entity player1, player2;
	Entity[] p1Bullets, p2Bullets;
	
	public EnemyHealth(String id, int health, Entity player1, Entity player2) {
		super(id, health);
		this.player1 = player1;
		this.player2 = player2;
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		p1Bullets = player1.getGunComponent().getBullets();
		if(player2 != null)
			p2Bullets = player2.getGunComponent().getBullets();
		
		Polygon polygon = owner.getPhysicsComponent().getPolygon();
		for (Entity bullets : p1Bullets) {
			Polygon bulletsPoly = bullets.getPhysicsComponent().getPolygon();
			if ((bulletsPoly != null) && (bulletsPoly.intersects(polygon))) {
				addHealth(-1);
				break;
			}
		}
		if(player2 != null) {
			for (Entity bullets : p2Bullets) {
				Polygon bulletsPoly = bullets.getPhysicsComponent().getPolygon();
				if ((bulletsPoly != null) && (bulletsPoly.intersects(polygon))) {
					addHealth(-1);
					break;
				}
			}
		}
	}

}
