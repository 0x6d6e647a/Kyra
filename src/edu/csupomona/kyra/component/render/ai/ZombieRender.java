/**************************************************************
 *	file:		ZombieRender.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Images for normal enemy
**************************************************************/
package edu.csupomona.kyra.component.render.ai;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ZombieRender extends AIRender {
	public ZombieRender(String id) throws SlickException {
		super(id);
		int[] moveTween = {200, 200, 200, 200};
		Image[] moveRight = {
				new Image("img/basic-enemy-move-right_001.png"),
				new Image("img/basic-enemy-move-right_002.png"),
				new Image("img/basic-enemy-move-right_001.png"),
				new Image("img/basic-enemy-move-right_003.png")
		};
		Image[] moveLeft = {
				new Image("img/basic-enemy-move-left_001.png"),
				new Image("img/basic-enemy-move-left_002.png"),
				new Image("img/basic-enemy-move-left_001.png"),
				new Image("img/basic-enemy-move-left_003.png")
		};
		setMoveRight(new Animation(moveRight, moveTween, false));
		setMoveLeft(new Animation(moveLeft, moveTween, false));
		
		int[] deathTween = {500,175};
		Image[] deathRight = {
				new Image("img/basic-enemy-death-right-1.png"),
				new Image("img/basic-enemy-death-right-2.png")
		};
		Image[] deathLeft = {
				new Image("img/basic-enemy-death-left-1.png"),
				new Image("img/basic-enemy-death-left-2.png")
		};
		setDeathRight(new Animation(deathRight, deathTween, false));
		setDeathLeft(new Animation(deathLeft, deathTween, false));
		
		setSprite(super.moveLeft);
	}
}
