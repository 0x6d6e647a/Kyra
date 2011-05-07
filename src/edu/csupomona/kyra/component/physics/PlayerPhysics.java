package edu.csupomona.kyra.component.physics;

import java.util.ArrayList;

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
	float height;
	float width;

	public PlayerPhysics(String id, float height, float width, TiledMap map) throws SlickException {
		super(id);
		this.height = height;
		this.width = width;
		this.blockMap = new BlockMap(map);
		forceVector = new ForceVector(0, 0);
	}
	
	public boolean entityCollisionWith() {
		ArrayList<Block> blocks = blockMap.getBlocks();
		for (int i = 0; i < blocks.size(); i++) {
			Block entity1 = blocks.get(i);
			if (playerPolygon.intersects(entity1.getPolygon())) {
				return true;
			}       
		}       
		return false;
	}
	
	public boolean collisionWith(Vector2f position, ForceVector force) {
		ArrayList<Block> blocks = blockMap.getBlocks();
		for (int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			PlayerPolygon testPlayerPolygon = new PlayerPolygon(playerPolygon.getPolygon().getPoints());
			if(testPlayerPolygon.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	public boolean collisionWithFloor(Vector2f position, ForceVector gravity) {
		ArrayList<Block> blocks = blockMap.getBlocks();
		Line playerBottom = playerPolygon.getBottom();
		for (int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			Line blockTop = block.getTop();
			if (playerBottom.getCenterY() < blockTop.getCenterY()) {
				PlayerPolygon testPlayerPolygon = new PlayerPolygon(playerPolygon.getPolygon().getPoints());
				testPlayerPolygon.setLocation(gravity.shiftPosition(position));
				if (testPlayerPolygon.intersects(block.getPolygon()))
					return true;
			}
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
		boolean floorCollision = collisionWithFloor(position, gravity);
		
		if (!floorCollision) { //In-Air Actions
			position = gravity.shiftPosition(position);
			playerPolygon.setLocation(position);
			if (inputComponent.isPressed("left")) {
				ForceVector left = new ForceVector(delta * 0.2f, Math.toRadians(180));
				if (!collisionWith(position, left)) {
					position = left.shiftPosition(position);
					playerPolygon.setLocation(position);
				}
			}
			if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * 0.2f, 0);
				if (!collisionWith(position, right)) {
					position = right.shiftPosition(position);
					playerPolygon.setLocation(position);
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
				if (!collisionWith(position, left)) {
					position = left.shiftPosition(position);
					playerPolygon.setLocation(position);
				}
			}
			if (inputComponent.isPressed("right")) {
				ForceVector right = new ForceVector(delta * 0.5f, 0);
				if (!collisionWith(position, right)) {
					position = right.shiftPosition(position);
					playerPolygon.setLocation(position);
				}
			}
		}
		
		if (inputComponent.isPressed("up")) {
			position.y -= delta * 0.2f;
			playerPolygon.setY(position.y);
			if (entityCollisionWith()){
				position.y += delta * 0.1f;;
				playerPolygon.setY(position.y);
			}
		}
		owner.setPosition(position);
	}
}
