package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.Direction;

public class HealthRender extends RenderComponent {

	int xPos, yPos;
	
	public HealthRender(String id) {
		super(id, Direction.NONE);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		gr.setColor(Color.black);
		gr.drawString("HP " + owner.getHealthComponent().getCurrHealth(), xPos, yPos);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		xPos = (int)owner.getPosition().x - 7;
		yPos = (int)owner.getPosition().y - 20;
	}

}
