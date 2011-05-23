package edu.csupomona.kyra.component.input;

import org.newdawn.slick.Input;

public class Player2Input extends PlayerInput {
	public Player2Input(String id) {
		super(id);
		buttonMappings.put("up", Input.KEY_UP);
		buttonMappings.put("left", Input.KEY_K);
		buttonMappings.put("right", Input.KEY_L);
		buttonMappings.put("jump", Input.KEY_SEMICOLON);
		buttonMappings.put("pause", Input.KEY_ENTER);
		buttonMappings.put("attack", Input.KEY_APOSTROPHE);
	}
}
