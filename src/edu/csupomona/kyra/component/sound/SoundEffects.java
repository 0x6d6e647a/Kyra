package edu.csupomona.kyra.component.sound;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.health.HealthComponent;
import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.PhysicsComponent;

public class SoundEffects extends SoundComponent {
	
	Sound jumpFx, attackFx, hitFx, periodicFx, pauseFx;
	boolean playHit;
	
	public SoundEffects(String id) {
		super(id);
		playHit = true;
	}	
	
	public void setSoundFx(Sound[] fx) {
		jumpFx = fx[0];
		attackFx = fx[1];
		hitFx = fx[2];
		periodicFx = fx[3];
		pauseFx = fx[4];
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) { 
		InputComponent inputComponent = owner.getInputComponent();
		PhysicsComponent physicsComponent = owner.getPhysicsComponent();
		HealthComponent healthComponent = owner.getHealthComponent();

		if(inputComponent.isPressed("jump"))
			if(jumpFx != null)
				if(physicsComponent.onFloor)
					if(!jumpFx.playing())
						jumpFx.play();
		if(inputComponent.isPressed("attack"))
			if(attackFx != null)
				if(!attackFx.playing())
					attackFx.play();
		if(healthComponent.getRecentHit() && playHit) {
			if(hitFx != null) {
				if(!hitFx.playing()) {
					hitFx.play();
					playHit = false;
				}
			}
		} else if(!healthComponent.getRecentHit()) {
			playHit = true;
		}
		if(periodicFx != null)
			if(randomPlay())
				if(!periodicFx.playing())
					periodicFx.play();
		if(inputComponent.isPressed("pause"))
			if(pauseFx != null)
				if(!pauseFx.playing())
					pauseFx.play();
		
	}
	public void stopAll() {
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
