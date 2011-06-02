/**************************************************************
 *	file:		GunComponent.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for projectile's
**************************************************************/
package edu.csupomona.kyra.component.gun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.physics.BulletPhysics;
import edu.csupomona.kyra.entity.Entity;

public abstract class GunComponent extends Component {
	
	ArrayList<Entity> bullets;
	TiledMap map;
	Timer coolDown;
	boolean canFire;
	final int COOLDOWN;

	protected abstract Entity makeBullet(int delta) throws SlickException;
	
	//Returns the list of bullets
	public ArrayList<Entity> getBullets() {
		return bullets;
	}
	
	//Shoots the bullet
	public void fireBullet(int delta) {
		try {
			bullets.add(makeBullet(delta));
			canFire = false;
			coolDown = new Timer();
			TimerTask cooling = new TimerTask() {
				
				@Override
				public void run() {
					canFire = true;
				}
			};
			coolDown.schedule(cooling, COOLDOWN);
		}
		catch (SlickException e) {
			System.out.println("Unable to load bullet sprite!");
			System.exit(1);
		}
	}
	
	public GunComponent(String id, int cooldown, TiledMap map) {
		super(id);
		this.map = map;
		COOLDOWN = cooldown;
		bullets = new ArrayList<Entity>();
		canFire = true;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
		//Remove bullets that have hit walls
		for (Iterator<Entity> iter = bullets.iterator(); iter.hasNext();) {
			Entity bullet = iter.next();
			bullet.update(gc, sb, delta);
			BulletPhysics bulletPhysics = (BulletPhysics)bullet.getPhysicsComponent();
			if (bulletPhysics.hitWall())
				iter.remove();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for (Entity bullet : bullets) {
			bullet.render(gc, sb, gr);
		}
	}
}
