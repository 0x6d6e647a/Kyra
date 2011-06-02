/**************************************************************
 *	file:		PlayerBulletRender.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Renders the players bullets
**************************************************************/
package edu.csupomona.kyra.component.render.bullet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.render.SpriteRender;

public class PlayerBulletRender extends SpriteRender {

	public PlayerBulletRender(String id) throws SlickException {
		super(id);
		
		int[] bulletTween = {50, 50};
		Image[] bullet = {
				new Image("img/bullet-1.png"),
				new Image("img/bullet-2.png")
		};
		setSprite(new Animation(bullet, bulletTween, false));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	}
}
