package edu.csupomona.kyra.component.sound;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.input.InputComponent;

public class SoundEffects extends SoundComponent {
	
	Sound jumpFx = null;
	Sound attackFx = null;
	Sound hitFx = null;
	Sound periodicFx = null;
	Sound pauseFx = null;
	
	public SoundEffects(String id, Sound[] fx) {
		super(id);
		jumpFx = fx[0];
		attackFx = fx[1];
		hitFx = fx[2];
		periodicFx = fx[3];
		pauseFx = fx[4];
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) { 
		InputComponent inputComponent = owner.getInputComponent();		
		
		if(inputComponent.isPressed("jump"))
			if(jumpFx != null)
				if(!jumpFx.playing())
					jumpFx.play();
		if(inputComponent.isPressed("attack"))
			if(attackFx != null)
				if(!attackFx.playing())
					attackFx.play();
		//When hit fx
		if(periodicFx != null)
			if(randomPlay())
				if(!periodicFx.playing())
					periodicFx.play();
		if(inputComponent.isPressed("pause"))
			if(pauseFx != null)
				if(!pauseFx.playing())
					pauseFx.play();
		
	}
	public void stopAllFx() {
		if(jumpFx != null)
			jumpFx.stop();
		if(attackFx != null)
			attackFx.stop();
		if(hitFx != null)
			hitFx.stop();
		if(periodicFx != null)
			periodicFx.stop();
		if(pauseFx != null)
			pauseFx.stop();
	}
	private boolean randomPlay(){
		Random rand = new Random();
		int num = rand.nextInt(100);
		if(num >= 20 && num <= 25)
			return true;
		return false;
	}
}
