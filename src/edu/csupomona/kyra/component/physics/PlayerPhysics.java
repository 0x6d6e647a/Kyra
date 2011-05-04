package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.input.InputComponent;

public class PlayerPhysics extends PhysicsComponent{
	TiledMap map = null;
	Polygon playerPoly;
	BlockMap bmap;

	public PlayerPhysics(String id, float height, float width, TiledMap map) throws SlickException {
		super(id);
		this.map = map;
		bmap = new BlockMap(map);
	}
	
	public boolean entityCollisionWith() {
		for (int i = 0; i < BlockMap.entities.size(); i++) {
			Block entity1 = (Block) BlockMap.entities.get(i);
			if (playerPoly.intersects(entity1.poly)) {
				return true;
			}       
		}       
		return false;
	}


	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f position = owner.getPosition();
		playerPoly = new Polygon(new float[]{
				position.x,position.y,
				position.x+32,position.y,
				position.x+32,position.y+32,
				position.x,position.y+32
		});
		InputComponent inputComponent = owner.getInputComponent();
		if (inputComponent.isPressed("up")) {
			position.y -= delta * 0.1f;
			playerPoly.setY(position.y);
			if (entityCollisionWith()){
				position.y += delta * 0.1f;;
				playerPoly.setY(position.y);
			}
		}
		if (inputComponent.isPressed("down")) {
			position.y += delta * 0.1f;
			playerPoly.setY(position.y);
			if (entityCollisionWith()) {
				position.y -= delta * 0.1f;
				playerPoly.setY(position.y);
			}
		}
		if (inputComponent.isPressed("left")) {
			position.x -= delta * 0.1f;
			playerPoly.setX(position.x);
			if (entityCollisionWith()) {
				position.x += delta * 0.1f;
				playerPoly.setX(position.x);
			}
		}
		if (inputComponent.isPressed("right")) {
			position.x += delta * 0.1f;
			playerPoly.setX(position.x);
			if (entityCollisionWith()) {
				position.x -= delta * 0.1f;
				playerPoly.setX(position.x);
			}
		}
		owner.setPosition(position);
	}
	
}
