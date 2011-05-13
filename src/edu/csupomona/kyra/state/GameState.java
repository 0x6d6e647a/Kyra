/**************************************************************
 *	file:		GameState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the game state when the main 
 *	game is being played.
**************************************************************/

package edu.csupomona.kyra.state;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.component.physics.PlayerPhysics;
import edu.csupomona.kyra.component.render.ImageRenderComponent;
import edu.csupomona.kyra.component.input.PlayerInput;
import edu.csupomona.kyra.component.render.Level;

public class GameState extends BasicGameState {
	Entity map = null;
	Entity player = null;
	Animation[] animations = null;
	private int stateID = 4;
	//private boolean vs;
	
	public GameState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		TiledMap tiledMap = new TiledMap("lvl/level1map.tmx");
//		Image[] rightmovement = {new Image("img/player1-move-right_001.png"), new Image("img/player1-move-right_004.png"),
//				new Image("img/player1-move-right_003.png"),new Image("img/player1-move-right_001.png"),new Image("img/player1-move-right_005.png"),
//				new Image("img/player1-move-right_002.png")};
//		Image[] leftmovement = {new Image("img/player1-move-left_001.png"), new Image("img/player1-move-left_004.png"),
//				new Image("img/player1-move-left_003.png"),new Image("img/player1-move-left_001.png"),new Image("img/player1-move-left_005.png"),
//				new Image("img/player1-move-left_002.png")};
//		Image[] jumpleftmovement = {new Image("img/player1-jump-left.png"),new Image("img/player1-move-left_001.png")};
//		Image[] jumprightmovement = {new Image("img/player1-jump-right.png"),new Image("img/player1-move-right_001.png")};	
//        int[] duration = {175,175,175,175,175,175};
//        int[] duration2 = {500,175};
//        animations = new Animation[4];
//        animations[0] = new Animation(rightmovement,duration,false);
//        animations[1] = new Animation(leftmovement,duration,false);
//        animations[2] = new Animation(jumpleftmovement,duration2,false);
//        animations[3] = new Animation(jumprightmovement,duration2,false);
		Image playerImage = new Image("img/glow0.png");
        
		player = new Entity("player");
		player.addComponent(new PlayerInput("p1input"));
		player.addComponent(new PlayerPhysics("p1physics", 31, 31, tiledMap));
		//player.addComponent(new ImageRenderComponent("p1Sprite", animations));
		player.addComponent(new ImageRenderComponent("p1Sprite", playerImage));
		
		map = new Entity("map");
		map.addComponent(new Level("lvl1", tiledMap, player));

    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
    	
    	map.render(gc, sbg, gr);
    	player.render(gc, sbg, gr);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	
    	
    	player.update(gc, sbg, delta);
    	map.update(gc, sbg, delta);
    	
    	if(input.isKeyPressed(Input.KEY_ENTER)) {
    		input.clearKeyPressedRecord();
    		sbg.getCurrentState().leave(gc, sbg);
    		sbg.getState(Kyra.CREDITSSTATE).init(gc, sbg);
    		sbg.getState(Kyra.CREDITSSTATE).enter(gc, sbg);
    		sbg.enterState(Kyra.CREDITSSTATE);
    	}
    }
}
