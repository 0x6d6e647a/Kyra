package edu.csupomona.kyra.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class ImageRender extends RenderComponent {
	
	Image image;
	

	public ImageRender(String id) {
		super(id);
	}
	
	protected void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f position = owner.getPosition();
		image.draw(position.x, position.y);
	}


}
