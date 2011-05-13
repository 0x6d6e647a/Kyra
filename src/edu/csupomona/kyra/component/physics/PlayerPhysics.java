package edu.csupomona.kyra.component.physics;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.objects.Block;
import edu.csupomona.kyra.component.physics.objects.BlockMap;
import edu.csupomona.kyra.component.physics.objects.ForceVector;
import edu.csupomona.kyra.component.physics.objects.PlayerPolygon;

public class PlayerPhysics extends PhysicsComponent{
	PlayerPolygon playerPolygon;
	BlockMap blockMap;
	ForceVector forceVector;

	public PlayerPhysics(String id, float height, float width, TiledMap map) throws SlickException {
		super(id);
		this.height = height;
		this.width = width;
		this.blockMap = new BlockMap(map);
		forceVector = new ForceVector(0, 0);
	}
	
	private boolean testCollisionWithFloor(ForceVector gravity) {
		float playerBottom = playerPolygon.getBottom().getCenterY();
		PlayerPolygon testPoly = playerPolygon.clone();
		testPoly.setLocation(gravity.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockTop = block.getTop().getCenterY();
			if ((playerBottom < blockTop) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	private boolean testCollisionWithRightWall(ForceVector left) {
		float playerLeft = playerPolygon.getLeft().getCenterX();
		PlayerPolygon testPoly = playerPolygon.clone();
		testPoly.setLocation(left.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockRight = block.getRight().getCenterX();
			if ((playerLeft > blockRight) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	private boolean testCollisionWithLeftWall(ForceVector right) {
		float playerRight = playerPolygon.getRight().getCenterX();
		PlayerPolygon testPoly = playerPolygon.clone();
		testPoly.setLocation(right.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockLeft = block.getLeft().getCenterX();
			if ((playerRight < blockLeft) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	public boolean collisionWithCeiling(Vector2f position, ForceVector jump) {
		ArrayList<Block> blocks = blockMap.getBlocks();
		Line playerTop = playerPolygon.getTop();
		for (int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			Line blockBottom = block.getBottom();
			if (playerTop.getCenterY() > blockBottom.getCenterY()) {
				PlayerPolygon testPlayerPolygon = new PlayerPolygon(playerPolygon.getPolygon().getPoints());
				testPlayerPolygon.setLocation(jump.shiftPosition(position));
				if (testPlayerPolygon.intersects(block.getPolygon())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void setPositions(Vector2f position) {
		owner.setPosition(position);
		playerPolygon.setLocation(position);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) { 
		Vector2f position = owner.getPosition();
		float[] points = {
				position.x, position.y,
				position.x+width, position.y,
				position.x+width, position.y+height,
				position.x, position.y+height
		};
		playerPolygon = new PlayerPolygon(points);
				
		InputComponent inputComponent = owner.getInputComponent();
		
		ForceVector gravity = new ForceVector(delta * 0.3f, Math.toRadians(90));
		boolean floorCollision = testCollisionWithFloor(gravity);
		
		if (!floorCollision) { //In-Air Actions
			position = gravity.shiftPosition(position);
			setPositions(position);
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * 0.2f, Math.toRadians(180));
				if (!testCollisionWithRightWall(left)) {
					position = left.shiftPosition(position);
					setPositions(position);
				}
			}
			if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * 0.2f, 0);
				if (!testCollisionWithLeftWall(right)) {
					position = right.shiftPosition(position);
					setPositions(position);
				}
			}
			
		}
		else { //On Ground Actions
			forceVector.setYComponent(0);
			if (inputComponent.isPressed("jump")) {
				ForceVector jump = new ForceVector(delta * 100.0f, Math.toRadians(270));
				if (!collisionWithCeiling(position, jump)) {
					//forceVector.add(jump);
					position = jump.shiftPosition(position);
					playerPolygon.setLocation(position);
				}
			}
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * 0.5f, Math.toRadians(180));
				if (!testCollisionWithRightWall(left)) {
					position = left.shiftPosition(position);
					setPositions(position);
				}
			}
			if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * 0.5f, 0);
				if (!testCollisionWithLeftWall(right)) {
					position = right.shiftPosition(position);
					setPositions(position);
				}
			}
		}
		
		if (inputComponent.isPressed("up")) {
			position.y -= delta * 1.5f;
			playerPolygon.setY(position.y);
		}
		owner.setPosition(position);
	}
}
