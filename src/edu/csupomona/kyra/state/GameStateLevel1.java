/**************************************************************
 *	file:		GameStateLevel1.java
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
import edu.csupomona.kyra.component.health.PlayerHealth;
import edu.csupomona.kyra.component.input.PlayerInput;
import edu.csupomona.kyra.component.render.Level;
import edu.csupomona.kyra.component.sound.SoundComponent;
import edu.csupomona.kyra.component.sound.SoundEffects;

public class GameStateLevel1 extends BasicGameState {
	Entity map = null;
	Entity player1 = null;
	Entity player2 = null;
	Entity enemy1 = null;
	Entity enemy2 = null;
	Entity enemy3 = null;
	Entity enemy4 = null;
	Entity enemy5 = null;
	Entity enemy6 = null;
	Entity enemy7 = null;
	Entity enemy8 = null;
	Entity enemy9 = null;
	Entity enemy10 = null;
	Image intro = null;
	Image pause = null;
	boolean displayIntro = true;
	boolean displayPause = false;
	Animation[] animationsP1 = null;
	Animation[] animationsP2 = null;
	Animation[] animationsEnemy = null;	
	
	private int stateID = 4;
	
	public GameStateLevel1(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		TiledMap tiledMap = new TiledMap("lvl/level1map.tmx");
		intro = new Image("img/intro.png");
		pause = new Image("img/pause.png");
		Sound[] fx = {new Sound("audio/player_jump.ogg"), new Sound("audio/player_attack.ogg"), new Sound("audio/player_hit.ogg"), null,new Sound("audio/player_pause.ogg")};
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
		//boss 1, 10-11
		Image[] boss1Leftmovement = {new Image("img/boss-1-left.png")};
		Image[] boss1Rightmovement = {new Image("img/boss-1-right.png")};
		int[] duration = {175,175,175,175,175,175}; //player 1 and 2 movement
        int[] duration2 = {500,175}; //player 1 and 2 jump
        int[] duration3 = {500,500,500,500}; //basic enemy movement
        int[] duration4 = {}; //boss 1
        
        animationsEnemy = new Animation[2];
        animationsEnemy[0] = new Animation(bERightmovement,duration3,false);
        animationsEnemy[1] = new Animation(bELefttmovement,duration3,false);
        
        Vector2f e1Position = new Vector2f( 123, 1503);
        enemy1 = new Entity("enemy");
        enemy1.setPosition(e1Position);
        enemy1.addComponent(new PlayerInput("blah"));
        enemy1.addComponent(new PlayerPhysics("e1Physics",60,31,tiledMap));
        enemy1.addComponent(new ImageRenderComponent("e1Physics",animationsEnemy));
        enemy1.getInputComponent().changebutton("left", Input.KEY_U);
		enemy1.getInputComponent().changebutton("right", Input.KEY_I);
		enemy1.getInputComponent().changebutton("jump", Input.KEY_O);
		enemy1.getInputComponent().changebutton("attack", Input.KEY_P);
        
        animationsP1 = new Animation[4];
        //player 1 frames
        animationsP1[0] = new Animation(p1Rightmovement,duration,false);
        animationsP1[1] = new Animation(p1Leftmovement,duration,false);
        animationsP1[2] = new Animation(p1Jumpleftmovement,duration2,false);
        animationsP1[3] = new Animation(p1Jumprightmovement,duration2,false);
		
		Vector2f p1Position = new Vector2f(33, 1503);
		player1 = new Entity("player");
		player1.setPosition(p1Position);
		player1.addComponent(new PlayerInput("p1input"));
		player1.addComponent(new PlayerPhysics("p1physics", 60, 31, tiledMap));
		player1.addComponent(new ImageRenderComponent("p1Sprite", animationsP1));
		player1.addComponent(new SoundEffects("p1Fx", fx));
		Entity[] enemies = {enemy1};
		player1.addComponent(new PlayerHealth("p1health", 4, enemies));
		
        
        
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
			Vector2f p2Position = new Vector2f(83, 1503);
			player2 = new Entity("player2");
			player2.setPosition(p2Position);
			player2.addComponent(new PlayerInput("p2input"));
			player2.addComponent(new PlayerPhysics("p2physics", 60, 31, tiledMap));
			player2.addComponent(new ImageRenderComponent("p2Sprite", animationsP2));
			player2.addComponent(new SoundEffects("p2Fx", fx));
			player2.addComponent(new PlayerHealth("p2health", 4, enemies));
			player2.getInputComponent().changebutton("left", Input.KEY_K);
			player2.getInputComponent().changebutton("right", Input.KEY_L);
			player2.getInputComponent().changebutton("jump", Input.KEY_SEMICOLON);
			player2.getInputComponent().changebutton("attack", Input.KEY_APOSTROPHE);
		}
		
		map = new Entity("map");
		map.addComponent(new Level("lvl1", tiledMap, player1));
		
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
    	if(displayIntro)
    		intro.drawCentered(512, 384);
    	if(displayPause)
    		pause.drawCentered(512, 384);
    	if(!gc.isPaused()) {
	    map.render(gc, sbg, gr);
	    enemy1.render(gc, sbg, gr);
	    player1.render(gc, sbg, gr);
	    if(Kyra.vs)
	    	player2.render(gc, sbg, gr);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	
    	if(!displayIntro){
    		if(!gc.isPaused()) {
    			enemy1.update(gc, sbg, delta);
    			player1.update(gc, sbg, delta);
    			if(Kyra.vs)
    				player2.update(gc, sbg, delta);
    			map.update(gc, sbg, delta);
    		}
        	if(input.isKeyPressed(Input.KEY_ENTER)) {
        		if(gc.isPaused()) {
        			input.clearKeyPressedRecord();
        			gc.resume();
        			displayPause = false;
        		} else if(!gc.isPaused()) {
        			input.clearKeyPressedRecord();
        			gc.pause();
        			displayPause = true;
        		}
        	}
        	if(gc.isPaused()) {
        		SoundComponent sounds = player1.getSoundComponent();
        		sounds.stopAll();
        		if(Kyra.vs) {
        			sounds = player2.getSoundComponent();
        			sounds.stopAll();
        		}
        		if(input.isKeyPressed(Input.KEY_P)){
        			input.clearKeyPressedRecord();
        			gc.resume();
            		displayIntro = true;
            		displayPause = false;
            		sbg.getCurrentState().leave(gc, sbg);
            		sbg.getState(Kyra.GAMESTATETWO).init(gc, sbg);
            		sbg.getState(Kyra.GAMESTATETWO).enter(gc, sbg);
            		sbg.enterState(Kyra.GAMESTATETWO);
        		}
        	}
        	if(Kyra.vs) {
        		if(player1.getHealthComponent().zeroHealth() || player2.getHealthComponent().zeroHealth()) {
        			input.clearKeyPressedRecord();
        			gc.resume();
        			displayIntro = true;
        			displayPause = false;
        			sbg.getCurrentState().leave(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
        			sbg.enterState(Kyra.GAMEOVERSTATE);
        		}
        	} else {
        		if(player1.getHealthComponent().zeroHealth()) {
        			input.clearKeyPressedRecord();
        			gc.resume();
        			displayIntro = true;
        			displayPause = false;
        			sbg.getCurrentState().leave(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
        			sbg.enterState(Kyra.GAMEOVERSTATE);
        		}
        	}
    	}

    	if(input.isKeyPressed(Input.KEY_SPACE) && displayIntro) {
    			displayIntro = false;
    			input.clearKeyPressedRecord();
    	}
    }
}
