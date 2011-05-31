package edu.csupomona.kyra.component.sound;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.StateBasedGame;

public class ZombieFx  extends SoundComponent {

	Sound random;
	boolean played;
	
	public ZombieFx(String id) throws SlickException {
		super(id);
		random = new Sound("audio/enemy_random.ogg");
		played = false;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
		Line attackPath = owner.getAIComponent().getLineToTarget();
		
		if(attackPath != null && attackPath.length() <= 450.0f && (random != null) && !random.playing() && !played) {
			random.play();
			played = true;
		} else if(attackPath != null && attackPath.length() >= 450.0f)
			played = false;
	}
	public void stopAll() {
		if(random != null)
			random.stop();
	}

}
