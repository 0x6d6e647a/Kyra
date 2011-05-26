package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.objects.Direction;


public class ImageRenderComponent extends RenderComponent{
	Image image;
	Animation[] animations;
	Animation sprite;
	boolean direction;
	
	public ImageRenderComponent(String id, Animation[] animations) {
		super(id, Direction.RIGHT);
		this.animations = animations;
		sprite = animations[0];
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		
		sprite.draw(pos.x, pos.y);
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		//image.rotate(owner.getRotation() - image.getRotation());
		
		InputComponent inputComponent = owner.getInputComponent();
		
		if(inputComponent.isPressed("right")) {
			sprite = animations[0];
			direction = true;
			sprite.update(delta);
		}
		if(inputComponent.isPressed("left")) {
			sprite = animations[1];
			direction = false;
			sprite.update(delta);
		}
		if(inputComponent.isPressed("jump")) {
			if(direction) {
				if(animations.length > 2)
					sprite = animations[3];
			} else {
				if(animations.length > 2)
					sprite = animations[2];
			}
			sprite.update(delta);
		}
	}
}
