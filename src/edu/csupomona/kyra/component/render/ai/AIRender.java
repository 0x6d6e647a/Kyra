package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.render.SpriteRender;

public abstract class AIRender extends SpriteRender {
	public AIRender(String id) {
		super(id);
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (String action : owner.getAIComponent().getActions()) {
			if (action.equals("left")) {
				sprite = animations[0];
				direction = true;
			}
			else if (action.equals("right")) {
				sprite = animations[1];
				direction = false;
			}
			sprite.update(delta);
		}
	}
}
