package edu.csupomona.kyra.component.health;

import java.util.ArrayList;

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
		ArrayList<Entity> bullets = new ArrayList<Entity>();
		bullets.addAll(player1.getGunComponent().getBullets());
		if (Kyra.vs)
			bullets.addAll(player2.getGunComponent().getBullets());
		
		if (isVulnerable()) {
			Polygon polygon = owner.getPhysicsComponent().getPolygon();
			for (Entity bullet : bullets) {
				Polygon bulletPoly = bullet.getPhysicsComponent().getPolygon();
				if ((bullet != null) && (bulletPoly.intersects(polygon))) {
					setBadHit();
					addHealth(-1);
					makeTempInvulnerable(75);
					break;
				}
			}
		}
		
	}

}
