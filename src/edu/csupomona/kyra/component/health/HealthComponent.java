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
	
	public int getCurrHealth() {
		return currHealth;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public boolean getBadHit() {
		if (badHit) {
			badHit = false;
			return true;
		}
		return false;
	}
	
	public boolean getGoodHit() {
		if (goodHit) {
			goodHit = false;
			return true;
		}
		return false;
	}
	
	protected void addHealth(int health) {
		if ((currHealth + health) > maxHealth)
			currHealth = maxHealth;
		else
			currHealth += health;
	}
	
	public boolean isVulnerable() {
		return vulnerable;
	}
	
	protected void makeInvulnerable() {
		vulnerable = false;
	}
	
	protected void makeVulnerable() {
		vulnerable = true;
	}
	
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
	
	protected void setBadHit() {
		badHit = true;
	}
	
	protected void setGoodHit() {
		goodHit = true;
	}
	
	public boolean isDead() {
		return currHealth <= 0;
	}
	
	public boolean isHealthFull() {
		return currHealth == maxHealth;
	}
}
