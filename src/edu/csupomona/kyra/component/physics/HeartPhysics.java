package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class HeartPhysics extends PhysicsComponent {
	
	final float GRAVITY = 0.009f,
		MIN_Y = -5.0f,
		MAX_Y = 1.0f;

	public HeartPhysics(String id, float height, float width, TiledMap map) {
		super(id, height, width, map);
		forceVector = new ForceVector();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		
		ForceVector gravity = new ForceVector(0, delta * GRAVITY);
		
		if(testFloorCollision(forceVector.clone().add(gravity))) {
			onFloor = true;
			forceVector.setYComponent(0.0f);
		}
		else {
			onFloor = false;
			forceVector.add(gravity);
		}
		forceVector.clampY(MIN_Y, MAX_Y);
		setLocation(forceVector.shiftPosition(owner.getPosition()));
	}

}
