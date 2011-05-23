package edu.csupomona.kyra.component.render.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player1Render extends PlayerRender {	
	public Player1Render(String id) throws SlickException {
        super(id);
		Image[] p1Rightmovement = {
				new Image("img/player1-move-right_001.png"),
				new Image("img/player1-move-right_004.png"),
				new Image("img/player1-move-right_003.png"),
				new Image("img/player1-move-right_001.png"),
				new Image("img/player1-move-right_005.png"),
				new Image("img/player1-move-right_002.png")
		};
		Image[] p1Leftmovement = {
				new Image("img/player1-move-left_001.png"),
				new Image("img/player1-move-left_004.png"),
				new Image("img/player1-move-left_003.png"),
				new Image("img/player1-move-left_001.png"),
				new Image("img/player1-move-left_005.png"),
				new Image("img/player1-move-left_002.png")};
		Image[] p1Jumpleftmovement = {
				new Image("img/player1-jump-left.png"),
				new Image("img/player1-move-left_001.png")
		};
		Image[] p1Jumprightmovement = {
				new Image("img/player1-jump-right.png"),
				new Image("img/player1-move-right_001.png")
		};	
		int[] movementDuration = {175,175,175,175,175,175};
        int[] jumpDuration = {500,175};
        
        animations = new Animation[4];
        animations[0] = new Animation(p1Rightmovement, movementDuration, false);
        animations[1] = new Animation(p1Leftmovement, movementDuration, false);
        animations[2] = new Animation(p1Jumpleftmovement, jumpDuration, false);
        animations[3] = new Animation(p1Jumprightmovement, jumpDuration, false);
        
        super.setAnimations(animations);
	}
}
