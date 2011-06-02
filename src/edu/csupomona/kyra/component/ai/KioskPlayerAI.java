package edu.csupomona.kyra.component.ai;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.PhysicsComponent;
import edu.csupomona.kyra.component.physics.objects.ForceVector;
import edu.csupomona.kyra.entity.Entity;

public class KioskPlayerAI extends AIComponent {
	
	final float ONGROUND_X = 0.005f,
		BULLET_SPEED = 3.5f;

	ArrayList<Entity> entities;
	
	public KioskPlayerAI(String id, ArrayList<Entity> entities, TiledMap map) {
		super(id, null, null, map);
		this.entities = entities;
		actions = new ArrayList<String>();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		actions.clear();
		PhysicsComponent physics = owner.getPhysicsComponent();
		if (physics.onFloor) {
			//Determine if we should jump
			ForceVector right = new ForceVector(delta * ONGROUND_X, 0.0f);
			if (physics.testCollision_LeftWall(right)) {
				actions.add("jump");
			}
			//Determine if we should attack
			Vector2f position = owner.getPosition();
			Line shotLine = new Line(position.x, position.y+16, position.x+400, position.y+16);
			for (Entity entity : entities) {
				PhysicsComponent entityPhysics = entity.getPhysicsComponent(); 
				if (entityPhysics != null) {
					Polygon entityPoly = entityPhysics.getPolygon();
					if ((entityPoly != null) && shotLine.intersects(entityPoly)) {
						actions.add("attack");
						return;
					}
				}
			}
			actions.add("right");
		}
		else {
			actions.add("right");
		}
	}

}
