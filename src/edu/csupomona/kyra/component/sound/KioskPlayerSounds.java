package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.ai.KioskPlayerAI;
import edu.csupomona.kyra.component.physics.KioskPlayerPhysics;

public class KioskPlayerSounds extends PlayerSoundsLevel2 {

	public KioskPlayerSounds(String id) throws SlickException {
		super(id);
		playHit = true;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		KioskPlayerAI ai = (KioskPlayerAI)owner.getAIComponent();
		KioskPlayerPhysics physics = (KioskPlayerPhysics)owner.getPhysicsComponent();
		
		for (String action : ai.getActions()) {
			if(action.equals("jump") &&	(jumpFx != null) && physics.onFloor &&	!jumpFx.playing())
				jumpFx.play();
			else if(action.equals("attack") && attackFx != null)
				attackFx.play();
			if(periodicFx != null && randomPlay() && !periodicFx.playing())
				periodicFx.play();
		}
	}
}
