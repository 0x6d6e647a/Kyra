package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.input.InputComponent;

public class PlayerPhysics extends PhysicsComponent{
	TiledMap map = null;
	boolean[][] blocked = null;
	Rectangle boundingBox = null;

	public PlayerPhysics(String id, float height, float width, TiledMap map) {
		super(id);
		this.map = map;
		blocked = new boolean[map.getWidth()][map.getHeight()];
		//Vector2f position = owner.getPosition();
		//boundingBox = new Rectangle(position.x, position.y, width, height);
		int collisionLayerIndex = map.getLayerIndex("collision");
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, collisionLayerIndex);
				String value = map.getTileProperty(tileID, "blocked", "false");
				if ("true".equals(value)) {
					blocked[x][y] = true;
				}
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f position = owner.getPosition();
		InputComponent inputComponent = owner.getInputComponent();
		if (inputComponent.isPressed("up"))
			position.y -= delta * 0.1f;
		if (inputComponent.isPressed("down"))
			position.y += delta * 0.1f;
		if (inputComponent.isPressed("left"))
			position.x -= delta * 0.1f;
		if (inputComponent.isPressed("right"))
			position.x += delta * 0.1f;
		
		owner.setPosition(position);
	}
	
}
