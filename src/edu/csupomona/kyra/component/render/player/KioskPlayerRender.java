package edu.csupomona.kyra.component.render.player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.Direction;

public class KioskPlayerRender extends Player1Render {
	
	public KioskPlayerRender(String id) throws SlickException {
		super(id);
	}
	
	@Override
	public void update (GameContainer gc, StateBasedGame sbg, int delta) {
		if (owner.getPhysicsComponent().onFloor) {
			if (owner.getXDirection().equals(Direction.RIGHT))
				setSprite(moveRight);
			else
				setSprite(moveLeft);
			sprite.update(delta);
		}
		else {
			if (owner.getXDirection().equals(Direction.RIGHT))
				setSprite(jumpRight);
			else
				setSprite(jumpLeft);
			sprite.update(delta);
		}
	}
}
		
