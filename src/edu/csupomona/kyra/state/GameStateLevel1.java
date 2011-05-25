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

import java.util.ArrayList;

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
import edu.csupomona.kyra.component.render.ai.ZombieRender;
import edu.csupomona.kyra.component.render.player.Player1Render;
import edu.csupomona.kyra.component.render.player.Player2Render;
import edu.csupomona.kyra.component.ai.ZombieAI;
import edu.csupomona.kyra.component.input.Player1Input;
import edu.csupomona.kyra.component.input.Player2Input;
import edu.csupomona.kyra.component.render.LevelRender;
import edu.csupomona.kyra.component.sound.PlayerSounds;
import edu.csupomona.kyra.component.sound.SoundComponent;

public class GameStateLevel1 extends BasicGameState {
	Entity map;
	Entity player1, player2;
	ArrayList<Entity> enemies;
	Image intro = null;
	Image pause = null;
	boolean displayIntro = true;
	boolean displayPause = false;
	
	private int stateID = 4;
	
	public GameStateLevel1(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	private void addZombie(Vector2f position, TiledMap map) throws SlickException {
		String name = "zombie" + enemies.size();
		Entity zombie = new Entity(name);
		zombie.setPosition(position);
		zombie.addComponent(new ZombieAI("ai_" + name, player1, player2, map));
		zombie.addComponent(new ZombiePhysics("physics_" + name, 60, 31, map));
		zombie.addComponent(new ZombieRender("render_" + name));
		enemies.add(zombie);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		TiledMap tiledMap = new TiledMap("lvl/level1map.tmx");
		intro = new Image("img/intro.png");
		pause = new Image("img/pause.png");
		
		Vector2f p1Position = new Vector2f(33, 1503);
		player1 = new Entity("player");
		player1.setPosition(p1Position);
		player1.addComponent(new Player1Input("p1Input"));
		player1.addComponent(new PlayerPhysics("p1physics", 60, 31, tiledMap));
		player1.addComponent(new Player1Render("p1Sprite"));
		player1.addComponent(new PlayerSounds("p1Fx"));
        
		if(Kyra.vs) {
			Vector2f p2Position = new Vector2f(83, 1503);
			player2 = new Entity("player2");
			player2.setPosition(p2Position);
			player2.addComponent(new Player2Input("p2Input"));
			player2.addComponent(new PlayerPhysics("p2physics", 60, 31, tiledMap));
			player2.addComponent(new Player2Render("p2Sprite"));
			player2.addComponent(new PlayerSounds("p2Fx"));
		}
		
		map = new Entity("map");
		map.addComponent(new LevelRender("lvl1", tiledMap, player1));
		
		enemies = new ArrayList<Entity>();
		addZombie(new Vector2f(33, 1503), tiledMap);
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
    	if(displayIntro)
    		intro.drawCentered(512, 384);
    	if(displayPause)
    		pause.drawCentered(512, 384);
    	if(!gc.isPaused()) {
    		map.render(gc, sbg, gr);
    		player1.render(gc, sbg, gr);
    		for (Entity enemy : enemies)
    			enemy.render(gc, sbg, gr);
    		if(Kyra.vs)
    			player2.render(gc, sbg, gr);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(!displayIntro){
    		if(!gc.isPaused()) {
    			player1.update(gc, sbg, delta);
    			if(Kyra.vs)
    				player2.update(gc, sbg, delta);
    			for (Entity enemy : enemies)
    				enemy.update(gc, sbg, delta);
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
    	}

    	if(input.isKeyPressed(Input.KEY_SPACE) && displayIntro) {
    			displayIntro = false;
    			input.clearKeyPressedRecord();
    	}
    }
}
