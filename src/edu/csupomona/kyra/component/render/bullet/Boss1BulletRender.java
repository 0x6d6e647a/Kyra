package edu.csupomona.kyra.component.render.bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.csupomona.kyra.component.render.ImageRender;

public class Boss1BulletRender extends ImageRender {

	public Boss1BulletRender(String id) throws SlickException {
		super(id);
		
		Image boss1bullet = new Image("img/projectiles.png");
		
		super.setImage(boss1bullet);
	}

}
	
