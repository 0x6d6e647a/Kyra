package edu.csupomona.kyra.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.physics.objects.Direction;

public abstract class RenderComponent extends Component {
	protected Direction direction;
	
	public RenderComponent(String id, Direction direction) {
		super(id);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);

}
