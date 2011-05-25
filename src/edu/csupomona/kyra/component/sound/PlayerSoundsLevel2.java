package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class PlayerSoundsLevel2 extends SoundEffects {
	public PlayerSoundsLevel2(String id) throws SlickException {
		super(id);
		Sound[] fx = {
				new Sound("audio/player_jump.ogg"),
				new Sound("audio/player_attack.ogg"),
				new Sound("audio/player_hit.ogg"),
				new Sound("audio/player_random.ogg"),
				new Sound("audio/player_pause.ogg")
		};
		super.setSoundFx(fx);
	}
}
