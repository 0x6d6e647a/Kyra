/**************************************************************
 *	file:		RenderComponent.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for rendering
**************************************************************/
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
