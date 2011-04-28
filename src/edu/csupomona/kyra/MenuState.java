/**************************************************************
 *	file:		MenuState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defined the game state with the splash
 *	screen and options.
**************************************************************/

package edu.csupomona.kyra;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {

	Image background = null;
	Image start = null;
	Image startSelect = null;
	Image options = null;
	Image optionsSelect = null;
	Image exit = null;
	Image exitSelect = null;
	int stateID = 1;
	Controls con;
	boolean insideStart = true;
	boolean insideOptions = false;
	boolean insideExit = false;
	private static int menuX = 312;
	private static int menuY = 334;
	
	public MenuState(int stateID, Controls con) {
		this.stateID = stateID;
		this.con = con;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {	
		Image startLook = new Image("img/menu_start.png");
		Image optionsLook = new Image("img/menu_options.png");
		Image exitLook = new Image("img/menu_exit.png");
		background = new Image("img/menu_background.png");
		start = startLook.getSubImage(0, 0, 400, 100);
		startSelect = startLook.getSubImage(0, 100, 400, 100);
	    options = optionsLook.getSubImage(0, 0, 400, 100);
	    optionsSelect = optionsLook.getSubImage(0, 100, 400, 100);
	    exit = exitLook.getSubImage(0, 0, 400, 100);
	    exitSelect = exitLook.getSubImage(0, 100, 400, 100);

    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    	if(insideStart) {
    		startSelect.draw(menuX, menuY);
    	} else {
    		start.draw(menuX, menuY);
    	}
    	if(insideOptions) {
    		optionsSelect.draw(menuX, menuY+100);
    	} else {
    		options.draw(menuX, menuY+100);
    	}
    	if(insideExit) {
    		exitSelect.draw(menuX, menuY+200);
    	} else {
    		exit.draw(menuX, menuY+200);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	
    	Input input = gc.getInput();
    	
    	if(insideStart) {
    		if(input.isKeyPressed(con.getP1UP())) {
    			insideStart = false;
    			insideOptions = false;
    			insideExit = true;
    		} else if(input.isKeyPressed(con.getP1DOWN())) {
    			insideStart = false;
    			insideOptions = true;
    			insideExit = false;
    		} else if(input.isKeyPressed(con.getP1ACTION())) {
    			sbg.enterState(Kyra.GAMESTATE);
    		}	
    	}
    	if(insideOptions) {
    		if(input.isKeyPressed(con.getP1UP())) {
    			insideStart = true;
    			insideOptions = false;
    			insideExit = false;
    		} else if(input.isKeyPressed(con.getP1DOWN())) {
    			insideStart = false;
    			insideOptions = false;
    			insideExit = true;
    		} else if(input.isKeyPressed(con.getP1ACTION())) {
    			sbg.enterState(Kyra.OPTIONSTATE);
    		}	
    	}
    	if(insideExit) {
    		if(input.isKeyPressed(con.getP1UP())) {
    			insideStart = false;
    			insideOptions = true;
    			insideExit = false;
    		} else if(input.isKeyPressed(con.getP1DOWN())) {
    			insideStart = true;
    			insideOptions = false;
    			insideExit = false;
    		} else if(input.isKeyPressed(con.getP1ACTION())) {
    			System.exit(0);
    		}	
    	}
    }


}
