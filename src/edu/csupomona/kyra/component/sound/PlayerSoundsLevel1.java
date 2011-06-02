/**************************************************************
 *	file:		PlayerSoundsLevel1.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Player sounds for level 1
**************************************************************/
package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class PlayerSoundsLevel1 extends SoundEffects {
	public PlayerSoundsLevel1(String id) throws SlickException {
		super(id);
		Sound[] fx = {
				new Sound("audio/player_jump.ogg"),
				new Sound("audio/player_attack.ogg"),
				new Sound("audio/player_hit.ogg"),
				null,
				new Sound("audio/player_pause.ogg")
		};
		super.setSoundFx(fx);
	}
}
