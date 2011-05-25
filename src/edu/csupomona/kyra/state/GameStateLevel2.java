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
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.component.physics.PlayerPhysics;
import edu.csupomona.kyra.component.physics.ZombiePhysics;
import edu.csupomona.kyra.component.ai.ZombieAI;
import edu.csupomona.kyra.component.health.PlayerHealth;
import edu.csupomona.kyra.component.input.Player1Input;
import edu.csupomona.kyra.component.input.Player2Input;
import edu.csupomona.kyra.component.render.Level;
import edu.csupomona.kyra.component.render.ai.ZombieRender;
import edu.csupomona.kyra.component.render.player.Player1Render;
import edu.csupomona.kyra.component.render.player.Player2Render;
import edu.csupomona.kyra.component.sound.PlayerSoundsLevel2;
import edu.csupomona.kyra.component.sound.SoundComponent;

public class GameStateLevel2 extends BasicGameState {
	Entity map = null;
	Entity player1 = null;
	Entity player2 = null;
	Entity enemy1 = null;
	Image pause = null;
	boolean displayPause = false;
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
		pause = new Image("img/pause.png");
		
		/*Image[] boss2Rightmovement = {new Image("img/boss-2-move-right_001.png"),new Image("img/boss-2-move-right_002.png"),
				new Image("img/boss-2-move-right_001.png"),new Image("img/boss-2-move-right_003.png")};
		Image[] boss2Leftmovement = {new Image("img/boss-2-move-left_001.png"),new Image("img/boss-2-move-left_002.png"),
				new Image("img/boss-2-move-left_001.png"),new Image("img/boss-2-move-left_003.png")};		
		Image[] boss2Flyright = {new Image("img/boss-2-fly-right.png")};
		Image[] boss2Flyleft = {new Image("img/boss-2-fly-left.png")};*/
		
		Vector2f ePosition = new Vector2f(203, 1216);
		enemy1 = new Entity("enemy1");
		enemy1.setPosition(ePosition);
		enemy1.addComponent(new ZombieAI("e1AI", player1, tiledMap));
		enemy1.addComponent(new ZombiePhysics("e1Physics", 60, 31, tiledMap));
		enemy1.addComponent(new ZombieRender("e1Sprite"));
		
		Entity[] enemies = {enemy1};
        
		Vector2f p1Position = new Vector2f(33, 1216);
		player1 = new Entity("player1");
		player1.setPosition(p1Position);
		player1.addComponent(new Player1Input("p1Input"));
		player1.addComponent(new PlayerPhysics("p1physics", 60, 31, tiledMap));
		player1.addComponent(new Player1Render("p1Sprite"));
		player1.addComponent(new PlayerSoundsLevel2("p1Fx"));
		player1.addComponent(new PlayerHealth("p1health", 4, enemies));
        
		if(Kyra.vs) {
			Vector2f p2Position = new Vector2f(83, 1216);
			player2 = new Entity("player2");
			player2.setPosition(p2Position);
			player2.addComponent(new Player2Input("p2Input"));
			player2.addComponent(new PlayerPhysics("p2physics", 60, 31, tiledMap));
			player2.addComponent(new Player2Render("p2Sprite"));
			player2.addComponent(new PlayerSoundsLevel2("p2Fx"));
			player2.addComponent(new PlayerHealth("p2health", 4, enemies));
		}
		
		map = new Entity("map");
		map.addComponent(new Level("lvl1", tiledMap, player1));	
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
    	if(displayPause)
    		pause.drawCentered(512, 384);
    	else {
    		map.render(gc, sbg, gr);
    		gr.setColor(Color.black);
    		gr.drawString("HP: " + player1.getHealthComponent().getHealth(), player1.getPosition().x-8, player1.getPosition().y-20);
    		if(Kyra.vs)
    			gr.drawString("HP: " + player2.getHealthComponent().getHealth(), player2.getPosition().x-8, player2.getPosition().y-20);
    		enemy1.render(gc, sbg, gr);
		    player1.render(gc, sbg, gr);   
		    if(Kyra.vs)
		    	player2.render(gc, sbg, gr);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(!gc.isPaused()) {
			map.update(gc, sbg, delta);
			enemy1.update(gc, sbg, delta);
			player1.update(gc, sbg, delta);
			if(Kyra.vs)
				player2.update(gc, sbg, delta);	
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
    			SoundComponent sounds = player1.getSoundComponent();
        		sounds.stopAll();
        		if(Kyra.vs) {
        			sounds = player2.getSoundComponent();
        			sounds.stopAll();
        		}
    		}
    	}
    	//--Shortcut--//
    	if(gc.isPaused()) {
    		if(input.isKeyPressed(Input.KEY_P)){
    			input.clearKeyPressedRecord();
    			gc.resume();
        		displayPause = false;
        		sbg.getCurrentState().leave(gc, sbg);
        		sbg.getState(Kyra.MENUSTATE).init(gc, sbg);
        		sbg.getState(Kyra.MENUSTATE).enter(gc, sbg);
        		sbg.enterState(Kyra.MENUSTATE);
    		}
    	}
    	//------------//
    	if(Kyra.vs) {
    		if(player1.getHealthComponent().zeroHealth() || player2.getHealthComponent().zeroHealth()) {
    			input.clearKeyPressedRecord();
    			SoundComponent sounds = player1.getSoundComponent();
        		sounds.stopAll();
        		if(Kyra.vs) {
        			sounds = player2.getSoundComponent();
        			sounds.stopAll();
        		}
    			gc.resume();
    			displayPause = false;
    			sbg.getCurrentState().leave(gc, sbg);
    			sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
    			sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
    			sbg.enterState(Kyra.GAMEOVERSTATE);
    		}
    	} else {
    		if(player1.getHealthComponent().zeroHealth()) {
    			input.clearKeyPressedRecord();
    			SoundComponent sounds = player1.getSoundComponent();
        		sounds.stopAll();
        		if(Kyra.vs) {
        			sounds = player2.getSoundComponent();
        			sounds.stopAll();
        		}
    			gc.resume();
    			displayPause = false;
    			sbg.getCurrentState().leave(gc, sbg);
    			sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
    			sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
    			sbg.enterState(Kyra.GAMEOVERSTATE);
    		}
    	}
    }
}
