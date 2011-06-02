package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class AntiZombiePhysics extends PhysicsComponent {
	final float GRAVITY = -0.009f,
		ONGROUND_X = 0.005f,
		ONGROUND_Y = -1.5f,		
		INAIR_X = 0.001f,
		FRICTION = 0.97f,
		MIN_X = -1.1f,
		MAX_X = 2.1f,
		MIN_Y = -20.0f,
		MAX_Y = 2.5f;
	
	
	public AntiZombiePhysics(String id, float height, float width, TiledMap map) {
		super(id, height, width, map);
		forceVector = new ForceVector();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		
		ForceVector gravity = new ForceVector(0, delta * GRAVITY);
		
		if (testCeilingCollision(forceVector.clone().add(gravity))) {
			forceVector.setYComponent(0.0f);
			for (String action : owner.getAIComponent().getActions()) {
				if (action.equals("left")) {
					ForceVector left = new ForceVector(delta * -ONGROUND_X, 0.0f);
					forceVector.add(left);	
					owner.setXDirection(Direction.LEFT);
				}
				else if (action.equals("right")) {
					ForceVector right = new ForceVector(delta * ONGROUND_X, 0.0f);
					forceVector.add(right);
					owner.setXDirection(Direction.RIGHT);
				}
			}
			forceVector.setXComponent(forceVector.getXComponent() * FRICTION);
		}
		else {
			forceVector.add(gravity);
			for (String action : owner.getAIComponent().getActions()) {
				if (action.equals("left")) {
					ForceVector left = new ForceVector(delta * -INAIR_X, 0.0f);
					forceVector.add(left);
					owner.setXDirection(Direction.LEFT);
				}
				else if (action.equals("right")) {
					ForceVector right = new ForceVector(delta * INAIR_X, 0.0f);
					forceVector.add(right);
					owner.setXDirection(Direction.RIGHT);
				}
			}
		}
		forceVector.clampX(MIN_X, MAX_X);
		forceVector.clampY(MIN_Y, MAX_Y);
		if (testWallCollisions(forceVector))
			forceVector.setXComponent(0.0f);
		if (testWallCollisions(forceVector))
			forceVector.setYComponent(0.0f);
		setLocation(forceVector.shiftPosition(owner.getPosition()));
	}

}
