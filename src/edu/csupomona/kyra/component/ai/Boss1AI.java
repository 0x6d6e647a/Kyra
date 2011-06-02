package edu.csupomona.kyra.component.ai;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.ForceVector;
import edu.csupomona.kyra.entity.Entity;

public class Boss1AI extends AIComponent {
	
	ForceVector aim, move;
	final float SIGHT_RANGE = 520.0f,
		BULLET_SPEED = 0.5f,
		MOVE_SPEED = 0.00003f;

	public Boss1AI(String id, Entity player1, Entity player2, TiledMap map) {
		super(id, player1, player2, map);
	}
	
	public ForceVector getAim() {
		return aim;
	}
	
	public ForceVector getMove() {
		return move;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Line attackPath = getLineToTarget();
		if ((attackPath != null) && (attackPath.length() < SIGHT_RANGE)) {
			aim = new ForceVector(attackPath, BULLET_SPEED, delta);
			move = new ForceVector(attackPath, MOVE_SPEED, delta);
			owner.setXDirection(move.getXDirection());
		}
		else {
			aim = null;
			move = null;
		}
	}

}
