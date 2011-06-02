package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class BossFx extends SoundComponent{

	Sound gun;
	
	public BossFx(String id) {
		super(id);
		try {
			gun = new Sound("audio/player_pause.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(owner.getGunComponent())
			gun.play();	
	}

}
