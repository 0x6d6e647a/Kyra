/**************************************************************
 *	file:		Boss1BulletRender.java
 *	author:		Andrew King, Anthony Mendez,  Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: Renders level 1 boss projectile's
**************************************************************/
package edu.csupomona.kyra.component.render.bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.csupomona.kyra.component.render.ImageRender;

public class Boss2BulletRender extends ImageRender {

	public Boss2BulletRender(String id) throws SlickException {
		super(id);
		
		Image boss1bullet = new Image("img/fireball.png");
		
		super.setImage(boss1bullet);
	}

}
