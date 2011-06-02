package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.health.HealthComponent;

public class HealthRender extends RenderComponent {

	public HealthRender(String id) {
		super(id);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f position = owner.getPosition();
		HealthComponent health = owner.getHealthComponent();
		String msg = String.format("HP: %d / %d", health.getCurrHealth(), health.getMaxHealth());
		gr.setColor(Color.red);
		gr.drawString(msg, position.x - 30.0f, position.y - 20.0f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub

	}

}
