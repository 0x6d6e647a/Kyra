/**************************************************************
 *	file:		SoundEffects.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Plays player sound effects
**************************************************************/
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
	
	//Sets the sounds
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

		if(inputComponent.isPressed("jump") &&	jumpFx != null &&
				physicsComponent.onFloor &&	!jumpFx.playing())
			jumpFx.play();
		if(inputComponent.isPressed("attack") && attackFx != null /*&& !attackFx.playing()*/)
			attackFx.play();
		if(healthComponent.getBadHit() && (hitFx != null) && !hitFx.playing())
			hitFx.play();
		if(periodicFx != null && randomPlay() && !periodicFx.playing())
			periodicFx.play();
		if(inputComponent.isPressed("pause") && (pauseFx != null) && !pauseFx.playing())
			pauseFx.play();
	}
	
	//Stops all sounds
	public void stopAll() {
		if (jumpFx != null)
			jumpFx.stop();
		if (attackFx != null)
			attackFx.stop();
		if (hitFx != null)
			hitFx.stop();
		if (periodicFx != null)
			periodicFx.stop();
		if (pauseFx != null)
			pauseFx.stop();
	}
	
	//Randomly chooses whether to play something
	protected boolean randomPlay(){
		Random rand = new Random();
		int num = rand.nextInt(1000);
		return num >= 20 && num <= 25;
	}
}
