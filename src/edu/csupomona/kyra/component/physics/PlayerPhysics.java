package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.objects.Block;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class PlayerPhysics extends PhysicsComponent{

	public PlayerPhysics(String id, float height, float width, TiledMap map) {
		super(id, height, width, map);
	}
	
	@Override
	protected boolean testCeilingCollision(ForceVector jump) {
		float playerTop = top.getCenterY();
		Polygon testPoly = new Polygon(polygon.getPoints());
		testPoly.setLocation(jump.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockBottom = block.getBottom().getCenterY();
			if ((playerTop > blockBottom) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	@Override
	protected boolean testFloorCollision(ForceVector gravity) {
		float playerBottom = bottom.getCenterY();
		Polygon testPoly = new Polygon(polygon.getPoints());
		testPoly.setLocation(gravity.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockTop = block.getTop().getCenterY();
			if ((playerBottom < blockTop) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}

	@Override
	protected boolean testLeftWallCollision(ForceVector right) {
		float playerRight = this.right.getCenterX();
		Polygon testPoly = new Polygon(polygon.getPoints());
		testPoly.setLocation(right.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockLeft = block.getLeft().getCenterX();
			if ((playerRight < blockLeft) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}

	@Override
	protected boolean testRightWallCollision(ForceVector left) {
		float playerLeft = this.left.getCenterX();
		Polygon testPoly = new Polygon(polygon.getPoints());
		testPoly.setLocation(left.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockRight = block.getRight().getCenterX();
			if ((playerLeft > blockRight) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
				
		InputComponent inputComponent = owner.getInputComponent();
		
		ForceVector gravity = new ForceVector(delta * 0.3f, Math.toRadians(90));
		boolean floorCollision = testFloorCollision(gravity);
		
		
		if (floorCollision) {
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * 0.5f, Math.toRadians(180));
				if (!testRightWallCollision(left))
					setLocation(left.shiftPosition(owner.getPosition()));
			}
			if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * 0.5f, 0);
				if (!testLeftWallCollision(right))
					setLocation(right.shiftPosition(owner.getPosition()));
			}
			if (inputComponent.isPressed("jump")) {
				ForceVector jump = new ForceVector(delta * 100.0f, Math.toRadians(270));
				if (!testCeilingCollision(jump))
					setLocation(jump.shiftPosition(owner.getPosition()));
			}
		}
		else {
			setLocation(gravity.shiftPosition(owner.getPosition()));
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * 0.2f, Math.toRadians(180));
				if (!testRightWallCollision(left))
					setLocation(left.shiftPosition(owner.getPosition()));
			}
			if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * 0.2f, 0);
				if (!testLeftWallCollision(right))
					setLocation(right.shiftPosition(owner.getPosition()));
			}
		}

		if (inputComponent.isPressed("up")) {
			ForceVector up = new ForceVector(delta * 1.5f, Math.toRadians(270));
			setLocation(up.shiftPosition(owner.getPosition()));
		}
	}
}
