package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.SpriteRender;

public abstract class AIRender extends SpriteRender {
	
	Animation moveRight, moveLeft, deathRight, deathLeft;
	
	public AIRender(String id) {
		super(id);
	}
	
	protected void setMoveRight(Animation moveRight) {
		this.moveRight = moveRight;
	}
	
	protected void setMoveLeft(Animation moveLeft) {
		this.moveLeft = moveLeft;
	}
	
	protected void setDeathRight(Animation deathRight) {
		this.deathRight = deathRight;
	}
	
	protected void setDeathLeft(Animation deathLeft) {
		this.deathLeft = deathLeft;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (String action : owner.getAIComponent().getActions()) {
			if (action.equals("left")) {
				setSprite(moveLeft);
				sprite.update(delta);	
			}
			else if (action.equals("right")) {
				setSprite(moveRight);
				sprite.update(delta);
			}
			else {
				Direction direction = owner.getXDirection();
				if (direction.equals(Direction.RIGHT))
					setSprite(moveRight);
				else
					setSprite(moveLeft);
				sprite.update(delta);			
			}
		}
	}
}
