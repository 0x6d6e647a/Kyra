package edu.csupomona.kyra.component.health;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.entity.Entity;

public class PlayerHealth extends HealthComponent{
	
	Entity[] enemies = null;
	Timer timer;
	boolean timerCreated;

	
	public PlayerHealth(String id, int health, Entity[] enemies) {
		super(id, health);
		this.enemies = enemies;
		timer = new Timer();
		timerCreated = false;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(!recentHit) {
			for(int i = 0; i < enemies.length; i++)
				intersect(enemies[i].getPhysicsComponent().getPolygon(), true);
		} else if(recentHit){
			if(!timerCreated) {
				timerCreated = true;
				timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						recentHit = false;
						timerCreated = false;
					}
				}, 3000);
			}
		}
	}
}
