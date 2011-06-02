/**************************************************************
 *	file:		Component.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for adding items to entity's
**************************************************************/
package edu.csupomona.kyra.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.entity.Entity;

public abstract class Component {
	protected String id;
	protected Entity owner;
	
	public Component(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setOwnerEntity(Entity owner) {
		this.owner = owner;
	}
	
	public abstract void update(GameContainer gc, StateBasedGame sb, int delta);
}
