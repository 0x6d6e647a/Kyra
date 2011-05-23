package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class PlayerPhysics extends PhysicsComponent{
	final float GRAVITY = 0.009f,
		ONGROUND_X = 0.005f,
		ONGROUND_Y = -1.5f,		
		INAIR_X = 0.001f,
		FRICTION = 0.97f,
		MIN_X = -1.7f,
		MAX_X = 1.7f,
		MIN_Y = -20.0f,
		MAX_Y = 2.5f;

	public PlayerPhysics(String id, float height, float width, TiledMap map) {
		super(id, height, width, map);
		forceVector = new ForceVector();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		InputComponent inputComponent = owner.getInputComponent();
		
		ForceVector gravity = new ForceVector(0, delta * GRAVITY);
		
		if (testFloorCollision(forceVector.clone().add(gravity))) {
			forceVector.setYComponent(0.0f);
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * -ONGROUND_X, 0.0f);
				forceVector.add(left);
			}
			else if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * ONGROUND_X, 0.0f);
				forceVector.add(right);
			}
			else {
				forceVector.setXComponent(forceVector.getXComponent() * FRICTION);
			}
			if (inputComponent.isPressed("jump")) {
				ForceVector jump = new ForceVector(0, delta * ONGROUND_Y);
				forceVector.add(jump);
			}
		}
		else {
			forceVector.add(gravity);
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * -INAIR_X, 0.0f);
				forceVector.add(left);
			}
			else if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * INAIR_X, 0.0f);
				forceVector.add(right);
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
