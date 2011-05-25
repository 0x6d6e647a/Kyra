package edu.csupomona.kyra.component.ai;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.entity.Entity;

public class ZombieAI extends AIComponent {
	final float SIGHT_RANGE = 450.0f;
	
	public ZombieAI(String id, Entity player1, Entity player2, TiledMap map) {
		super(id, player1, player2, map);
		actions = new ArrayList<String>();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		actions.clear();
		Line attackPath = getLineToTarget();
		if ((attackPath != null) && (attackPath.length() < SIGHT_RANGE)) {
			float targetX = attackPath.getEnd().getX();
			float zombieX = owner.getPosition().getX();
			if (targetX < zombieX)
				actions.add("left");
			else
				actions.add("right");
		}
	}

}
