package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class HealthRender extends RenderComponent {

	int xPos, yPos;
	
	public HealthRender(String id) {
		super(id);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		gr.setColor(Color.black);
		gr.drawString("HP " + owner.getHealthComponent().getCurrHealth(), xPos, yPos);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		xPos = (int)owner.getPosition().x - 20;
		yPos = (int)owner.getPosition().y - 20;
	}

}
