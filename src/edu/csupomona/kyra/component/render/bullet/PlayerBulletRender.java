package edu.csupomona.kyra.component.render.bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.csupomona.kyra.component.render.ImageRender;

public class PlayerBulletRender extends ImageRender {

	public PlayerBulletRender(String id) throws SlickException {
		super(id);
		
		Image bullet = new Image("img/fireball.png");
		
		super.setImage(bullet);
	}
}
