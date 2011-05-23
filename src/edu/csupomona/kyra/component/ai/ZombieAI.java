package edu.csupomona.kyra.component.ai;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.entity.Entity;

public class ZombieAI extends AIComponent {
	final float SIGHT_RANGE = 120.0f;
	
	public ZombieAI(String id, Entity player, TiledMap map) {
		super(id, player, map);
		actions = new ArrayList<String>();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		actions.clear();
		if ((getLineToPlayer().length() < SIGHT_RANGE) && clearPathToPlayer()) {
			float playerX = player.getPosition().getX();
			float zombieX = owner.getPosition().getX();
			if(playerX < zombieX)
				actions.add("left");
			else
				actions.add("right");	
		}
	}

}
