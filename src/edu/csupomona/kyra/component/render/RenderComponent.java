package edu.csupomona.kyra.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.Component;

public abstract class RenderComponent extends Component {
	
	public RenderComponent(String id) {
		super(id);
	}
	

	public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);

}
