package edu.csupomona.kyra.component.health;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.gun.GunComponent;
import edu.csupomona.kyra.component.physics.PhysicsComponent;
import edu.csupomona.kyra.entity.Entity;

public class PlayerHealth extends HealthComponent {
	ArrayList<Entity> enemies;
	ArrayList<Entity> hearts;
	Entity boss;
	boolean vulnerable;
	
	final long INVUL_TIME = 1200;
	final int ENEMY_BODY_DMG = -1,
		BOSS_BULLET_DMG = -1,
		BOSS_BODY_DMG = -1,
		HEART_HEAL = 1;
		
	
	public PlayerHealth(String id, int health, ArrayList<Entity> enemies, ArrayList<Entity> hearts, Entity boss) {
		super(id, health);
		this.enemies = enemies;
		this.hearts = hearts;
		this.boss = boss;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if (isVulnerable()) {
			Polygon polygon = owner.getPhysicsComponent().getPolygon();
			// Take damage from enemies
			for (Entity enemy : enemies) {
				Polygon enemyPoly = enemy.getPhysicsComponent().getPolygon();
				if ((enemyPoly != null) && (enemyPoly.intersects(polygon))) {
					setBadHit();
					addHealth(ENEMY_BODY_DMG);
					makeTempInvulnerable(INVUL_TIME);
					break;
				}
			}
			//Take damage from boss bullets
			GunComponent bossGun = boss.getGunComponent();
			for (Iterator<Entity> iter = bossGun.getBullets().iterator(); iter.hasNext();) {
				Polygon bulletPoly = iter.next().getPhysicsComponent().getPolygon();
				if ((bulletPoly != null) && (bulletPoly.intersects(polygon))) {
					setBadHit();
					addHealth(BOSS_BULLET_DMG);
					makeTempInvulnerable(INVUL_TIME);
					iter.remove();
					break;
				}
			}
			//Take damage from boss body
			PhysicsComponent bossPhysics = boss.getPhysicsComponent();
			if (bossPhysics != null) {
				Polygon bossPoly = boss.getPhysicsComponent().getPolygon();
				if ((bossPoly != null) && (bossPoly.intersects(polygon))) {
					setBadHit();
					addHealth(BOSS_BODY_DMG);
					makeTempInvulnerable(INVUL_TIME);
				}
			}
			//Give player health if they get a heart
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
