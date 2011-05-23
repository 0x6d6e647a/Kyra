package edu.csupomona.kyra.component.render.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player2Render extends PlayerRender {	
	public Player2Render(String id) throws SlickException {
        super(id);
        Image[] p2Rightmovement = {
				new Image("img/player2-move-right_001.png"),
				new Image("img/player2-move-right_004.png"),
				new Image("img/player2-move-right_003.png"),
				new Image("img/player2-move-right_001.png"),
				new Image("img/player2-move-right_005.png"),
				new Image("img/player2-move-right_002.png")
		};
		Image[] p2Leftmovement = {
				new Image("img/player2-move-left_001.png"),
				new Image("img/player2-move-left_004.png"),
				new Image("img/player2-move-left_003.png"),
				new Image("img/player2-move-left_001.png"),
				new Image("img/player2-move-left_005.png"),
				new Image("img/player2-move-left_002.png")
		};
		Image[] p2Jumpleftmovement = {
				new Image("img/player2-jump-left.png"),
				new Image("img/player2-move-left_001.png")
		};
		Image[] p2Jumprightmovement = {
				new Image("img/player2-jump-right.png"),
				new Image("img/player2-move-right_001.png")
		};	
		int[] movementDuration = {175,175,175,175,175,175};
        int[] jumpDuration = {500,175};
        
        animations = new Animation[4];
        animations[0] = new Animation(p2Rightmovement, movementDuration, false);
        animations[1] = new Animation(p2Leftmovement, movementDuration, false);
        animations[2] = new Animation(p2Jumpleftmovement, jumpDuration, false);
        animations[3] = new Animation(p2Jumprightmovement, jumpDuration, false);
        
        super.setAnimations(animations);
	}
}