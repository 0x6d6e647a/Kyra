/**************************************************************
 *	file:		Player2Render.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Images for player 2
**************************************************************/
package edu.csupomona.kyra.component.render.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player2Render extends PlayerRender {	
	public Player2Render(String id) throws SlickException {
        super(id);
		int[] moveTween= {175,175,175,175,175,175};
		Image[] moveRight = {
				new Image("img/player2-move-right_001.png"),
				new Image("img/player2-move-right_004.png"),
				new Image("img/player2-move-right_003.png"),
				new Image("img/player2-move-right_001.png"),
				new Image("img/player2-move-right_005.png"),
				new Image("img/player2-move-right_002.png")
		};
		Image[] moveLeft = {
				new Image("img/player2-move-left_001.png"),
				new Image("img/player2-move-left_004.png"),
				new Image("img/player2-move-left_003.png"),
				new Image("img/player2-move-left_001.png"),
				new Image("img/player2-move-left_005.png"),
				new Image("img/player2-move-left_002.png")};
		setMoveRight(new Animation(moveRight, moveTween, false));
		setMoveLeft(new Animation(moveLeft, moveTween, false));
		
		
        int[] jumpTween = {500,175};
		Image[] jumpLeft = {
				new Image("img/player2-jump-left.png"),
				new Image("img/player2-move-left_001.png")
		};
		Image[] jumpRight = {
				new Image("img/player2-jump-right.png"),
				new Image("img/player2-move-right_001.png")
		};	
		setJumpRight(new Animation(jumpRight, jumpTween, false));
		setJumpLeft(new Animation(jumpLeft, jumpTween, false));
		
		int[] deathTween = {500,175};
		Image[] deathRight = {
				new Image("img/player2-death-right-1.png"),
				new Image("img/player2-death-right-2.png")
		};
		Image[] deathLeft = {
				new Image("img/player2-death-left-1.png"),
				new Image("img/player2-death-left-2.png")
		};
		setDeathRight(new Animation(deathRight, deathTween, false));
		setDeathLeft(new Animation(deathLeft, deathTween, false));
		
		setSprite(super.moveRight);
	}
}
