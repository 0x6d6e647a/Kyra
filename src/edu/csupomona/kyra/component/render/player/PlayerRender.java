package edu.csupomona.kyra.component.render.player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.SpriteRender;

public abstract class PlayerRender extends SpriteRender {
	public PlayerRender(String id) {
		super(id);
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta) {		
		InputComponent inputComponent = owner.getInputComponent();
		
		if(inputComponent.isPressed("right")) {
			sprite = animations[0];
			direction = Direction.RIGHT;
			sprite.update(delta);

		}
		if(inputComponent.isPressed("left")) {
			super.sprite = animations[1];
			direction = Direction.LEFT;
			sprite.update(delta);

		}
		if(inputComponent.isPressed("jump")) {
			if(direction.equals(Direction.RIGHT))
				sprite = animations[3];
			else
				sprite = animations[2];
			sprite.update(delta);

		}
	}
}
