package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class HeartRender extends ImageRender {

	public HeartRender(String id) throws SlickException {
		super(id);
		
		Image heart = new Image("img/heart.png");
		
		super.setImage(heart);
	}
}
