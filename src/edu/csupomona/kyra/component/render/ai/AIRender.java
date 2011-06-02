/**************************************************************
 *	file:		AIRender.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Renders the enemies using the AI
**************************************************************/
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
	
	//Sets the movement animation right
	protected void setMoveRight(Animation moveRight) {
		this.moveRight = moveRight;
	}
	
	//Sets the movement animation left
	protected void setMoveLeft(Animation moveLeft) {
		this.moveLeft = moveLeft;
	}
	
	//Sets the death animation right
	protected void setDeathRight(Animation deathRight) {
		this.deathRight = deathRight;
	}
	
	//Sets the death animation left
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
