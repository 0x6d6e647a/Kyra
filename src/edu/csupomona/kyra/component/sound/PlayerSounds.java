package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;

public class PlayerSounds extends SoundComponent {
	
	Sound jumpFx;
	Sound attackFx;
	Sound hitFx;
	Sound pauseFx;
	
	public PlayerSounds(String id) throws SlickException {
		super(id);
		jumpFx = new Sound("audio/player_jump.ogg");
		attackFx = new Sound("audio/player_attack.ogg");
		hitFx = new Sound("audio/player_hit.ogg");
		pauseFx = new Sound("audio/player_pause.ogg");
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) { 
		InputComponent inputComponent = owner.getInputComponent();
		
		if (inputComponent.isPressed("jump"))
			if (jumpFx != null)
				if (!jumpFx.playing())
					jumpFx.play();
		if (inputComponent.isPressed("attack"))
			if (attackFx != null)
				if (!attackFx.playing())
					attackFx.play();
		if (inputComponent.isPressed("pause"))
			if (pauseFx != null)
				if (!pauseFx.playing())
					pauseFx.play();
	}

	public void stopAll() {
		if (jumpFx != null)
			jumpFx.stop();
		if (attackFx != null)
			attackFx.stop();
		if (hitFx != null)
			hitFx.stop();
		if (pauseFx != null)
			pauseFx.stop();
	}
}
