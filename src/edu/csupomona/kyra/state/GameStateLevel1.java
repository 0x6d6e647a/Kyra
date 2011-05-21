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
import edu.csupomona.kyra.component.sound.SoundComponent;
import edu.csupomona.kyra.component.sound.SoundEffects;

public class GameStateLevel1 extends BasicGameState {
	Entity map = null;
	Entity player1 = null;
	Entity player2 = null;
	
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
		Sound[] fx = {new Sound("audio/player_jump.ogg"), new Sound("audio/player_attack.ogg"), new Sound("audio/player_hit.ogg"), null,new Sound("audio/player_pause.ogg")};
		
		Vector2f p1Position = new Vector2f(33, 1535);
		player1 = new Entity("player");
		player1.setPosition(p1Position);
		player1.addComponent(new PlayerInput("p1input"));
		player1.addComponent(new PlayerPhysics("p1physics", 31, 31, tiledMap));
		player1.addComponent(new ImageRenderComponent("p1Sprite", new Image("img/glow0.png")));
		player1.addComponent(new SoundEffects("p1Fx", fx));
		
		
		if(Kyra.vs) {
			Vector2f p2Position = new Vector2f(83, 1535);
			player2 = new Entity("player2");
			player2.setPosition(p2Position);
			player2.addComponent(new PlayerInput("p2input"));
			player2.addComponent(new PlayerPhysics("p2physics", 31, 31, tiledMap));
			player2.addComponent(new ImageRenderComponent("p2Sprite", new Image("img/glow4.png")));
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
    	if(Kyra.vs)
    		player2.render(gc, sbg, gr);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	
    	
    	player1.update(gc, sbg, delta);
    	if(Kyra.vs)
    		player2.update(gc, sbg, delta);
    	map.update(gc, sbg, delta);
    	
    	if(input.isKeyPressed(Input.KEY_ENTER)) {
    		input.clearKeyPressedRecord();
    		sbg.getCurrentState().leave(gc, sbg);
    		sbg.getState(Kyra.GAMESTATETWO).init(gc, sbg);
    		sbg.getState(Kyra.GAMESTATETWO).enter(gc, sbg);
    		sbg.enterState(Kyra.GAMESTATETWO);
    	}
    }
}
