/**************************************************************
 *	file:		Player1Input.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Player 1 controls
**************************************************************/
package edu.csupomona.kyra.component.input;

import org.newdawn.slick.Input;

public class Player1Input extends PlayerInput {
	public Player1Input(String id) {
		super(id);
		buttonMappings.put("up", Input.KEY_UP);
		buttonMappings.put("left", Input.KEY_A);
		buttonMappings.put("right", Input.KEY_S);
		buttonMappings.put("jump", Input.KEY_D);
		buttonMappings.put("pause", Input.KEY_ENTER);
		buttonMappings.put("attack", Input.KEY_F);
	}
}
