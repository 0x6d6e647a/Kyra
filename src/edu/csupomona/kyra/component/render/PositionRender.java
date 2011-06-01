package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class PositionRender extends RenderComponent {

	public PositionRender(String id) {
		super(id);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f position = owner.getPosition();
		String msg = String.format("[%f, %f]", position.x, position.y);
		gr.setColor(Color.blue);
		gr.drawString(msg, position.x - 95.0f, position.y + 65.0f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {

	}

}
