package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.SpriteRender;

public abstract class AIRender extends SpriteRender {
	public AIRender(String id) {
		super(id);
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (String action : owner.getAIComponent().getActions()) {
			if (action.equals("left")) {
				sprite = animations[0];
				sprite.update(delta);

			}
			else if (action.equals("right")) {
				sprite = animations[1];
				sprite.update(delta);
			}
			else {
				Direction direction = owner.getXDirection();
				if (direction.equals(Direction.RIGHT))
					sprite = animations[0];
				else
					sprite = animations[1];
				sprite.update(delta);
				
			}
		}
	}
}
