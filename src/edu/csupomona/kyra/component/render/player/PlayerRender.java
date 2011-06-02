package edu.csupomona.kyra.component.render.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.SpriteRender;

public abstract class PlayerRender extends SpriteRender {
	Animation moveRight, moveLeft, jumpRight, jumpLeft, deathRight, deathLeft;
	boolean isDead;
	
	
	public PlayerRender(String id) {
		super(id);
		isDead = false;
	}
	
	protected void setMoveRight(Animation moveRight) {
		this.moveRight = moveRight;
	}
	
	protected void setMoveLeft(Animation moveLeft) {
		this.moveLeft = moveLeft;
	}
	
	protected void setJumpRight(Animation jumpRight) {
		this.jumpRight = jumpRight;
	}
	
	protected void setJumpLeft(Animation jumpLeft) {
		this.jumpLeft = jumpLeft;
	}
	
	protected void setDeathRight(Animation deathRight) {
		this.deathRight = deathRight;
	}
	
	protected void setDeathLeft(Animation deathLeft) {
		this.deathLeft = deathLeft;
	}
	
	public void kill() {
		isDead = true;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {		
		InputComponent inputComponent = owner.getInputComponent();
		
		if (isDead) {
			Direction direction = owner.getXDirection();
			if (direction.equals(Direction.RIGHT))
				setSprite(deathRight);
			else
				setSprite(deathLeft);
			sprite.update(delta);
		}
		
		if (inputComponent.isPressed("right")) {
			setSprite(moveRight);
			sprite.update(delta);

		}
		if (inputComponent.isPressed("left")) {
			setSprite(moveLeft);
			sprite.update(delta);

		}
		if(inputComponent.isPressed("jump")) {
			Direction direction = owner.getXDirection();
			if(direction.equals(Direction.RIGHT))
				setSprite(jumpRight);
			else
				setSprite(jumpLeft);
			sprite.update(delta);
		}
	}
}
