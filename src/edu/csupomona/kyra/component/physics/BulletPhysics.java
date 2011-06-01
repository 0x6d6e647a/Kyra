package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.ForceVector;

public class BulletPhysics extends PhysicsComponent {

	public BulletPhysics(String id, float height, float width, TiledMap map, ForceVector forceVector) {
		super(id, height, width, map);
		this.forceVector = forceVector;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		setPolygon();
		
		if (!testCollisions(forceVector)) {
			setLocation(forceVector.shiftPosition(owner.getPosition()));
		}
	}

}
