package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class SpriteRender extends RenderComponent{
	protected Animation[] animations;
	protected Animation sprite;
	protected boolean direction;
	
	public SpriteRender(String id) {
		super(id);
		direction = true;
	}
	
	public SpriteRender(String id, Animation[] animations) {
		super(id);
		this.animations = animations;
		sprite = animations[0];
		direction = true;
	}
	
	protected void setAnimations(Animation[] animations) {
		this.animations = animations;
		sprite = this.animations[0];
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		sprite.draw(pos.x, pos.y);
	}
}
