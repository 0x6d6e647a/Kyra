package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class SpriteRender extends RenderComponent{
	protected Animation sprite;
	
	public SpriteRender(String id) {
		super(id);
	}
	
	public SpriteRender(String id, Animation sprite) {
		super(id);
		this.sprite = sprite;
	}
	
	protected void setSprite(Animation sprite) {
		this.sprite = sprite;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		sprite.draw(pos.x, pos.y);
	}
}
