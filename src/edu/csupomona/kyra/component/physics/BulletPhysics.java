/**************************************************************
 *	file:		BulletPhysics.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Projectile physics
**************************************************************/
package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class BulletPhysics extends PhysicsComponent {
	
	boolean hitWall;

	public BulletPhysics(String id, float height, float width, TiledMap map, ForceVector forceVector) {
		super(id, height, width, map);
		this.forceVector = forceVector;
		hitWall = false;
	}
	
	//Returns if the bullet hits the wall
	public boolean hitWall() {
		return hitWall;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		
		if (!testCollisions(forceVector))
			setLocation(forceVector.shiftPosition(owner.getPosition()));
		else
			hitWall = true;
	}

}
