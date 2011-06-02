/**************************************************************
 *	file:		Boss1Physics.java
 *	author:		Andrew King, Anthony Mendez,  Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: Physics for level 1 boss
**************************************************************/
package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.ai.Boss1AI;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class Boss1Physics extends PhysicsComponent {
	
	final float FRICTION = 0.97f,
		MIN_X = -0.2f,
		MAX_X = 0.2f,
		MIN_Y = -0.2f,
		MAX_Y = 0.2f;

	public Boss1Physics(String id, float height, float width, TiledMap map) {
		super(id, height, width, map);
		forceVector = new ForceVector();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		
		Boss1AI bossAI = (Boss1AI)owner.getAIComponent();
		ForceVector move = bossAI.getAim();
		if (move != null) {
			forceVector.add(move);
		}
		else {
			forceVector.setXComponent(forceVector.getXComponent() * FRICTION);
			forceVector.setYComponent(forceVector.getYComponent() * FRICTION);
		}
		forceVector.clampX(MIN_X, MAX_X);
		forceVector.clampY(MIN_Y, MAX_Y);
		if (testWallCollisions(forceVector))
			forceVector.setXComponent(0.0f);
		if (testFloorCollision(forceVector))
			forceVector.setYComponent(0.0f);
		if (testCeilingCollision(forceVector))
			forceVector.setYComponent(0.0f);
		setLocation(forceVector.shiftPosition(owner.getPosition()));
	}

}
