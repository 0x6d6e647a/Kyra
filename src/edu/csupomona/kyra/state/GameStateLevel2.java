/**************************************************************
 *	file:		GameStateLevel2.java
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
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.component.physics.PlayerPhysics;
import edu.csupomona.kyra.component.render.ImageRenderComponent;
import edu.csupomona.kyra.component.input.PlayerInput;
import edu.csupomona.kyra.component.render.Level;
import edu.csupomona.kyra.component.sound.SoundEffects;

public class GameStateLevel2 extends BasicGameState {
	Entity map = null;
	Entity player1 = null;
	Entity player2 = null;
	Entity enemy = null;
	Animation[] animationsP1 = null;
	Animation[] animationsP2 = null;
	Animation[] animationsEnemy = null;
	
	private int stateID = 5;
	
	public GameStateLevel2(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		TiledMap tiledMap = new TiledMap("lvl/level2map.tmx");
		Sound[] fx = {new Sound("audio/player_jump.ogg"), new Sound("audio/player_attack.ogg"), new Sound("audio/player_hit.ogg"), new Sound("audio/player_random.ogg"),new Sound("audio/player_pause.ogg")};
		//player 1 frames, 0-3
		Image[] p1Rightmovement = {new Image("img/player1-move-right_001.png"), new Image("img/player1-move-right_004.png"),
				new Image("img/player1-move-right_003.png"),new Image("img/player1-move-right_001.png"),new Image("img/player1-move-right_005.png"),
				new Image("img/player1-move-right_002.png")};
		Image[] p1Leftmovement = {new Image("img/player1-move-left_001.png"), new Image("img/player1-move-left_004.png"),
				new Image("img/player1-move-left_003.png"),new Image("img/player1-move-left_001.png"),new Image("img/player1-move-left_005.png"),
				new Image("img/player1-move-left_002.png")};
		Image[] p1Jumpleftmovement = {new Image("img/player1-jump-left.png"),new Image("img/player1-move-left_001.png")};
		Image[] p1Jumprightmovement = {new Image("img/player1-jump-right.png"),new Image("img/player1-move-right_001.png")};	
		//basic enemy frames, 8-9
		Image[] bERightmovement = {new Image("img/basic-enemy-move-right_001.png"),new Image("img/basic-enemy-move-right_002.png"),
				new Image("img/basic-enemy-move-right_001.png"),new Image("img/basic-enemy-move-right_003.png")};
		Image[] bELefttmovement = {new Image("img/basic-enemy-move-left_001.png"),new Image("img/basic-enemy-move-left_002.png"),
				new Image("img/basic-enemy-move-left_001.png"),new Image("img/basic-enemy-move-left_003.png")};
		//boss 2 12-15
		Image[] boss2Rightmovement = {new Image("img/boss-2-move-right_001.png"),new Image("img/boss-2-move-right_002.png"),
				new Image("img/boss-2-move-right_001.png"),new Image("img/boss-2-move-right_003.png")};
		Image[] boss2Leftmovement = {new Image("img/boss-2-move-left_001.png"),new Image("img/boss-2-move-left_002.png"),
				new Image("img/boss-2-move-left_001.png"),new Image("img/boss-2-move-left_003.png")};		
		Image[] boss2Flyright = {new Image("img/boss-2-fly-right.png")};
		Image[] boss2Flyleft = {new Image("img/boss-2-fly-left.png")};
		int[] duration = {175,175,175,175,175,175}; //player 1 and 2 movement
        int[] duration2 = {500,175}; //player 1 and 2 jump
        int[] duration3 = {500,500,500,500}; //basic enemy movement
        int[] duration4 = {}; //boss 1
        animationsP1 = new Animation[4];
        //player 1 frames
        animationsP1[0] = new Animation(p1Rightmovement,duration,false);
        animationsP1[1] = new Animation(p1Leftmovement,duration,false);
        animationsP1[2] = new Animation(p1Jumpleftmovement,duration2,false);
        animationsP1[3] = new Animation(p1Jumprightmovement,duration2,false);
        
		Vector2f p1Position = new Vector2f(33, 1248);
		player1 = new Entity("player");
		player1.setPosition(p1Position);
		player1.addComponent(new PlayerInput("p1input"));
		player1.addComponent(new PlayerPhysics("p1physics", 31, 31, tiledMap));
		player1.addComponent(new ImageRenderComponent("p1Sprite", animationsP1));
		player1.addComponent(new SoundEffects("p1Fx", fx));
		
        animationsEnemy = new Animation[2];
        animationsEnemy[0] = new Animation(bERightmovement,duration3,false);
        animationsEnemy[1] = new Animation(bELefttmovement,duration3,false);
        
        Vector2f ePosition = new Vector2f(165, 1248);
        enemy = new Entity("enemy");
        enemy.setPosition(ePosition);
        enemy.addComponent(new PlayerInput("einput"));
		enemy.addComponent(new PlayerPhysics("ephysics", 31, 31, tiledMap));
        enemy.addComponent(new PlayerPhysics("ephysics", 31, 31, tiledMap));
        enemy.addComponent(new ImageRenderComponent("eSprite", animationsEnemy));
        
		if(Kyra.vs) {
			//player 2 frames, 4-7
			Image[] p2Rightmovement = {new Image("img/player2-move-right_001.png"), new Image("img/player2-move-right_004.png"),
					new Image("img/player2-move-right_003.png"),new Image("img/player2-move-right_001.png"),new Image("img/player2-move-right_005.png"),
					new Image("img/player2-move-right_002.png")};
			Image[] p2Leftmovement = {new Image("img/player2-move-left_001.png"), new Image("img/player2-move-left_004.png"),
					new Image("img/player2-move-left_003.png"),new Image("img/player2-move-left_001.png"),new Image("img/player2-move-left_005.png"),
					new Image("img/player2-move-left_002.png")};
			Image[] p2Jumpleftmovement = {new Image("img/player2-jump-left.png"),new Image("img/player2-move-left_001.png")};
			Image[] p2Jumprightmovement = {new Image("img/player2-jump-right.png"),new Image("img/player2-move-right_001.png")};	
			//player 2 frames
			animationsP2 = new Animation[4];
	        animationsP2[0] = new Animation(p2Rightmovement,duration,false);
	        animationsP2[1] = new Animation(p2Leftmovement,duration,false);
	        animationsP2[2] = new Animation(p2Jumpleftmovement,duration2,false);
	        animationsP2[3] = new Animation(p2Jumprightmovement,duration2,false);
			Vector2f p2Position = new Vector2f(83, 1248);
			player2 = new Entity("player2");
			player2.setPosition(p2Position);
			player2.addComponent(new PlayerInput("p2input"));
			player2.addComponent(new PlayerPhysics("p2physics", 31, 31, tiledMap));
			player2.addComponent(new ImageRenderComponent("p2Sprite", animationsP2));
			player2.addComponent(new SoundEffects("p2Fx", fx));
			player2.getInputComponent().changebutton("left", Input.KEY_K);
			player2.getInputComponent().changebutton("right", Input.KEY_L);
			player2.getInputComponent().changebutton("jump", Input.KEY_SEMICOLON);
			player2.getInputComponent().changebutton("attack", Input.KEY_APOSTROPHE);
		}
		
		map = new Entity("map");
		map.addComponent(new Level("lvl1", tiledMap, player1));
		
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
    	
    	map.render(gc, sbg, gr);
    	player1.render(gc, sbg, gr);
    	enemy.render(gc, sbg, gr);
    	if(Kyra.vs)
    		player2.render(gc, sbg, gr);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	player1.update(gc, sbg, delta);
    	enemy.update(gc, sbg, delta);
    	if(Kyra.vs)
    		player2.update(gc, sbg, delta);
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
