package edu.csupomona.kyra.component.render.bullet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.render.ImageRender;
import edu.csupomona.kyra.component.render.SpriteRender;

public class PlayerBulletRender extends SpriteRender {

	public PlayerBulletRender(String id) throws SlickException {
		super(id);
		
		Image[] bulletAnimation = {
				new Image("img/bullet-1.png"),
				new Image("img/bullet-2.png")
		};
		
		int[] frameDuration = {50, 50};
		
		animations = new Animation[1];
		animations[0] = new Animation(bulletAnimation, frameDuration, true);
		super.setAnimations(animations);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	}
}
