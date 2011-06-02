/**************************************************************
 *	file:		ItemHealth.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Item health
**************************************************************/
package edu.csupomona.kyra.component.health;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class ItemHealth extends HealthComponent {
	
	public ItemHealth(String id) {
		super(id, 1);
	}
	
	//Kills the heart when used
	public void useItem() {
		currHealth = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {

	}

}
