package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ZombieRender extends AIRender {
	public ZombieRender(String id) throws SlickException {
		super(id);
		Image[] bERightmovement = {
				new Image("img/basic-enemy-move-right_001.png"),
				new Image("img/basic-enemy-move-right_002.png"),
				new Image("img/basic-enemy-move-right_001.png"),
				new Image("img/basic-enemy-move-right_003.png")
		};
		Image[] bELefttmovement = {
				new Image("img/basic-enemy-move-left_001.png"),
				new Image("img/basic-enemy-move-left_002.png"),
				new Image("img/basic-enemy-move-left_001.png"),
				new Image("img/basic-enemy-move-left_003.png")
		};
		int[] movementDuration = {500, 500, 500, 500};
		
		animations = new Animation[2];
		animations[0] = new Animation(bERightmovement, movementDuration, false);
		animations[1] = new Animation(bELefttmovement, movementDuration, false);
		
		super.setAnimations(animations);
	}
}
