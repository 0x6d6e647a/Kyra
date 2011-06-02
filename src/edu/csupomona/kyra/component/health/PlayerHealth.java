package edu.csupomona.kyra.component.health;

import java.util.ArrayList;
import java.util.Timer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.entity.Entity;

public class PlayerHealth extends HealthComponent {
	ArrayList<Entity> enemies;
	ArrayList<Entity> hearts;
	Timer timer;
	boolean vulnerable;
	
	public PlayerHealth(String id, int health, ArrayList<Entity> enemies, ArrayList<Entity> hearts) {
		super(id, health);
		this.enemies = enemies;
		this.hearts = hearts;
		timer = new Timer();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if (isVulnerable()) {
			Polygon polygon = owner.getPhysicsComponent().getPolygon();
			for (Entity enemy : enemies) {
				Polygon enemyPoly = enemy.getPhysicsComponent().getPolygon();
				if ((enemyPoly != null) && (enemyPoly.intersects(polygon))) {
					setBadHit();
					addHealth(-1);
					makeTempInvulnerable(1200);
					break;
				}
			}
			for (Entity heart : hearts) {
				Polygon heartPoly = heart.getPhysicsComponent().getPolygon();
				ItemHealth heartHealth = (ItemHealth)heart.getHealthComponent();
				if ((heartPoly != null) && (heartPoly.intersects(polygon)) && !isHealthFull()) {
					setGoodHit();
					addHealth(1);
					heartHealth.useItem();
				}
			}
		}
	}
}
