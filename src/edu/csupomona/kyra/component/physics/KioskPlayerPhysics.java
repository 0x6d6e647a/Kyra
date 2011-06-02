package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class KioskPlayerPhysics extends PlayerPhysics {
	
	public KioskPlayerPhysics(String id, float height, float width, TiledMap map) {
		super(id, height, width, map);
		forceVector = new ForceVector();
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		
		ForceVector gravity = new ForceVector(0, delta * GRAVITY);
		
		if (testFloorCollision(forceVector.clone().add(gravity))) {
			onFloor = true;
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
				else if (action.equals("jump")) {
					ForceVector jump = new ForceVector(0, delta * ONGROUND_Y);
					forceVector.add(jump);
				}
			}
		}
		else {
			onFloor = false;
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
		if (testCeilingCollision(forceVector))
			forceVector.setYComponent(0.0f);
		setLocation(forceVector.shiftPosition(owner.getPosition()));
	}

}
