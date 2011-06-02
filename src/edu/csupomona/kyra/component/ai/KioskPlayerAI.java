package edu.csupomona.kyra.component.ai;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.PhysicsComponent;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class KioskPlayerAI extends AIComponent {
	
	final float ONGROUND_X = 0.005f;

	public KioskPlayerAI(String id, TiledMap map) {
		super(id, null, null, map);
		actions = new ArrayList<String>();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		actions.clear();
		PhysicsComponent physics = owner.getPhysicsComponent();
		if (physics.onFloor) {
			ForceVector left = new ForceVector(delta * -ONGROUND_X, 0.0f);
			if (physics.testCollision_RightWall(left))
				actions.add("jump");
		}
		actions.add("left");
	}

}
