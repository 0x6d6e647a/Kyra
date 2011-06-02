package edu.csupomona.kyra.component.sound;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class BossFx extends SoundComponent{

	Sound gun, random;
	
	public BossFx(String id) {
		super(id);
		try {
			gun = new Sound("audio/menu_select.ogg");
			random = new Sound("audio/boss_random.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(owner.getGunComponent().getFire())
			gun.play();	
		if(randomPlay() && !random.playing())
			random.play();
	}
	
	//Randomly chooses whether to play something
	private boolean randomPlay() {
		Random rand = new Random();
		int num = rand.nextInt(1000);
		return num >= 20 && num <= 25;
	}

}
