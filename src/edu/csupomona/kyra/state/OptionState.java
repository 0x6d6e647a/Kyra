/**************************************************************
 *	file:		OptionState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the game state where you select
 *	various game options.
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


public class OptionState extends BasicGameState {

	private Image background = null;
	private Image fullscreen = null;
	private Image fullscreenSelect = null;
	private Image back = null;
	private Image backSelect = null;
	private Sound buttonSelect = null;
	private Sound buttonAccept = null;
	private int stateID = 3;
	boolean insideFullscreen = false;
	boolean insideBack = true;
	private static int menuX = 312;
	private static int menuY = 334;
	
	public OptionState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttonSelect = new Sound("audio/menu_select.ogg");
		buttonAccept = new Sound("audio/menu_accept.ogg");
		Image fullscreenLook = new Image("img/options_fullscreen.png");
		Image backLook = new Image("img/options_back.png");
		background = new Image("img/menu_background.png");
		fullscreen = fullscreenLook.getSubImage(0, 0, 400, 100);
		fullscreenSelect = fullscreenLook.getSubImage(0, 100, 400, 100);
		back = backLook.getSubImage(0, 0, 400, 100);
		backSelect = backLook.getSubImage(0, 100, 400, 100);
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    	if(insideFullscreen) {
    		fullscreenSelect.draw(menuX, menuY);
    	} else {
    		fullscreen.draw(menuX, menuY);
    	}
    	if(insideBack) {
    		backSelect.draw(menuX, menuY+100);
    	} else {
    		back.draw(menuX, menuY+100);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(insideFullscreen) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideFullscreen = false;
    			insideBack = true;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideFullscreen = false;
    			insideBack = true;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			if(gc.isFullscreen()) {
        			gc.setFullscreen(false);
        		} else {
        			gc.setFullscreen(true);
        		}
    		}	
    	}
    	if(insideBack) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideFullscreen = true;
    			insideBack = false;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideFullscreen = true;
    			insideBack = false;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			input.clearKeyPressedRecord();
    			sbg.getCurrentState().leave(gc, sbg);
        		sbg.getState(Kyra.MENUSTATE).enter(gc, sbg);
    			sbg.enterState(Kyra.MENUSTATE);
    		}	
    	}
    }
}
