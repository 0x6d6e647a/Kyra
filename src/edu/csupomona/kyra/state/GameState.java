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
		TiledMap tiledMap = new TiledMap("lvl/test2.tmx");
		
		
		player = new Entity("player");
		player.addComponent(new PlayerInput("p1input"));
		player.addComponent(new PlayerPhysics("p1physics", 31, 31, tiledMap));
		player.addComponent(new ImageRenderComponent("p1Sprite", new Image("img/glow0.png")));
		
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
