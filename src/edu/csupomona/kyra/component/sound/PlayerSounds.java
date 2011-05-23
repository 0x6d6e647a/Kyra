package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class PlayerSounds extends SoundEffects {
	public PlayerSounds(String id) throws SlickException {
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
