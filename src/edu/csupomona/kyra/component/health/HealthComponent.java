/**************************************************************
 *	file:		HealthComponent.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for health
**************************************************************/
package edu.csupomona.kyra.component.health;

import java.util.Timer;
import java.util.TimerTask;

import edu.csupomona.kyra.component.Component;

public abstract class HealthComponent extends Component {
	
	int currHealth;
	final int maxHealth;
	boolean vulnerable, badHit, goodHit;
	Timer timer;
	
	public HealthComponent(String id, int health) {
		super(id);
		this.currHealth = health;
		this.maxHealth = health;
		vulnerable = true;
		badHit = false;
		timer = new Timer();
	}
	
	//Returns current health
	public int getCurrHealth() {
		return currHealth;
	}
	
	//Returns max health
	public int getMaxHealth() {
		return maxHealth;
	}
	
	//Returns whether the hit was bad
	public boolean getBadHit() {
		if (badHit) {
			badHit = false;
			return true;
		}
		return false;
	}
	
	//Returns whether the hit was good
	public boolean getGoodHit() {
		if (goodHit) {
			goodHit = false;
			return true;
		}
		return false;
	}
	
	//Adds health
	protected void addHealth(int health) {
		if ((currHealth + health) > maxHealth)
			currHealth = maxHealth;
		else
			currHealth += health;
	}
	
	//Returns whether entity is vulnerable
	public boolean isVulnerable() {
		return vulnerable;
	}
	
	//Makes the entity invulnerable
	protected void makeInvulnerable() {
		vulnerable = false;
	}
	
	//Makes the entity vulnerable
	protected void makeVulnerable() {
		vulnerable = true;
	}
	
	//Temporary makes invulnerable
	protected void makeTempInvulnerable(long delay) {
		makeInvulnerable();
		timer = new Timer();
		TimerTask end = new TimerTask() {
			public void run() {
				makeVulnerable();
			}
		};
		timer.schedule(end, delay);
	}
	
	//Sets a bad hit
	protected void setBadHit() {
		badHit = true;
	}
	
	//Sets a good hit
	protected void setGoodHit() {
		goodHit = true;
	}
	
	//Returns if entity is dead
	public boolean isDead() {
		return currHealth <= 0;
	}
	
	//Returns if entity is at full health
	public boolean isHealthFull() {
		return currHealth == maxHealth;
	}
}
