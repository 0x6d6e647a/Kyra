/**************************************************************
 *	file:		PlayerSelectState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the game state where you select
 *	your player.
**************************************************************/

package edu.csupomona.kyra.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;

public class PlayerSelectState extends BasicGameState {

	private Image background = null;
	private Image onePlayer = null;
	private Image onePlayerSelect = null;
	private Image twoPlayer = null;
	private Image twoPlayerSelect = null;
	private Sound buttonSelect = null;
	private Sound buttonAccept = null;
	private int stateID = 2;
	private boolean insideOnePlayer = true;
	private boolean insideTwoPlayer = false;
	private static int menuX = 312;
	private static int menuY = 334;
	
	public PlayerSelectState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttonSelect = new Sound("audio/menu_select.ogg");
		buttonAccept = new Sound("audio/menu_accept.ogg");
		Image onePlayerLook = new Image("img/player_one.png");
		Image twoPlayerLook = new Image("img/player_two.png");
		background = new Image("img/menu_background.png");
		onePlayer = onePlayerLook.getSubImage(0, 0, 400, 100);
		onePlayerSelect = onePlayerLook.getSubImage(0, 100, 400, 100);
		twoPlayer = twoPlayerLook.getSubImage(0, 0, 400, 100);
		twoPlayerSelect = twoPlayerLook.getSubImage(0, 100, 400, 100);
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    	if(insideOnePlayer) {
    		onePlayerSelect.draw(menuX, menuY);
    	} else {
    		onePlayer.draw(menuX, menuY);
    	}
    	if(insideTwoPlayer) {
    		twoPlayerSelect.draw(menuX, menuY+100);
    	} else {
    		twoPlayer.draw(menuX, menuY+100);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(insideOnePlayer) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideOnePlayer = false;
    			insideTwoPlayer = true;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideOnePlayer = false;
    			insideTwoPlayer = true;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
	    		input.clearKeyPressedRecord();
	    		sbg.getCurrentState().leave(gc, sbg);
	    		sbg.getState(Kyra.GAMESTATE).init(gc, sbg);
	        	sbg.getState(Kyra.GAMESTATE).enter(gc, sbg);
	        	buttonAccept.stop();
	        	MenuState.backSound.stop();
	    		sbg.enterState(Kyra.GAMESTATE);
    		}	
    	}
    	if(insideTwoPlayer) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideOnePlayer = true;
    			insideTwoPlayer = false;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideOnePlayer = true;
    			insideTwoPlayer = false;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			sbg.getCurrentState().leave(gc, sbg);
	    		sbg.getState(Kyra.GAMESTATE).init(gc, sbg);
	        	sbg.getState(Kyra.GAMESTATE).enter(gc, sbg);
	        	buttonAccept.stop();
	        	MenuState.backSound.stop();
	    		sbg.enterState(Kyra.GAMESTATE);
    		}	
    	}
    }
}
