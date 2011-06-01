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

package edu.csupomona.kyra.state;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;


public class MenuState extends BasicGameState {

	private Image background = null;
	private Image start = null;
	private Image startSelect = null;
	private Image load = null;
	private Image loadSelect = null;
	private Image options = null;
	private Image optionsSelect = null;
	private Image exit = null;
	private Image exitSelect = null;
	private Sound buttonSelect = null;
	private Sound buttonAccept = null;
	private int stateID = 1;
	private boolean insideStart = true;
	private boolean insideLoad = false;
	private boolean insideOptions = false;
	private boolean insideExit = false;
	private boolean first = true;
	private static int menuX = 312;
	private static int menuY = 334;
	public static Sound backSound = null;
	
	public MenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttonSelect = new Sound("audio/menu_select.ogg");
		buttonAccept = new Sound("audio/menu_accept.ogg");
		backSound = new Sound("audio/menu_background.ogg");
		Image startLook = new Image("img/menu_start.png");
		Image loadLook = new Image("img/menu_load.png");
		Image optionsLook = new Image("img/menu_options.png");
		Image exitLook = new Image("img/menu_exit.png");
		background = new Image("img/menu_background.png");
		start = startLook.getSubImage(0, 0, 400, 115);
		startSelect = startLook.getSubImage(0, 115, 400, 115);
		load = loadLook.getSubImage(0, 0, 400, 115);
		loadSelect = loadLook.getSubImage(0, 115, 400, 115);
	    options = optionsLook.getSubImage(0, 0, 400, 115);
	    optionsSelect = optionsLook.getSubImage(0, 115, 400, 115);
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
    	if(insideLoad) {
    		loadSelect.draw(menuX, menuY+100);
    	} else {
    		load.draw(menuX, menuY+100);
    	}
    	if(insideOptions) {
    		optionsSelect.draw(menuX, menuY+200);
    	} else {
    		options.draw(menuX, menuY+200);
    	}
    	if(insideExit) {
    		exitSelect.draw(menuX, menuY+300);
    	} else {
    		exit.draw(menuX, menuY+300);
    	}
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
    	Input input = gc.getInput();
    	
    	if(first) {
    		input.clearKeyPressedRecord();
    		first = false;
    	}
    	
    	if(!backSound.playing())
    		backSound.play();
    	
    	if(insideStart) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideStart = false;
    			insideLoad = false;
    			insideOptions = false;
    			insideExit = true;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideStart = false;
    			insideLoad = true;
    			insideOptions = false;
    			insideExit = false;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			input.clearKeyPressedRecord();
    			sbg.getCurrentState().leave(gc, sbg);
        		sbg.getState(Kyra.PLAYERSELECTSTATE).enter(gc, sbg);
    			sbg.enterState(Kyra.PLAYERSELECTSTATE);
    		}	
    	}
    	if(insideLoad) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideStart = true;
    			insideLoad = false;
    			insideOptions = false;
    			insideExit = false;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideStart = false;
    			insideLoad = false;
    			insideOptions = true;
    			insideExit = false;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			input.clearKeyPressedRecord();
    			File f = new File("save.txt");
    			try {
					Scanner s = new Scanner(f);
					Kyra.vs = s.nextBoolean();
					int tempState = s.nextInt();
					s.close();
					sbg.getCurrentState().leave(gc, sbg);
		    		sbg.getState(tempState).init(gc, sbg);
		        	sbg.getState(tempState).enter(gc, sbg);
		        	buttonAccept.stop();
		        	MenuState.backSound.stop();
		    		sbg.enterState(tempState);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}	
    	}
    	if(insideOptions) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideStart = false;
    			insideLoad = true;
    			insideOptions = false;
    			insideExit = false;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideStart = false;
    			insideLoad = false;
    			insideOptions = false;
    			insideExit = true;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			input.clearKeyPressedRecord();
    			sbg.getCurrentState().leave(gc, sbg);
        		sbg.getState(Kyra.OPTIONSTATE).enter(gc, sbg);
    			sbg.enterState(Kyra.OPTIONSTATE);
    		}	
    	}
    	if(insideExit) {
    		if(input.isKeyPressed(Input.KEY_UP)) {
    			buttonSelect.play();
    			insideStart = false;
    			insideLoad = false;
    			insideOptions = true;
    			insideExit = false;
    		} else if(input.isKeyPressed(Input.KEY_DOWN)) {
    			buttonSelect.play();
    			insideStart = true;
    			insideLoad = false;
    			insideOptions = false;
    			insideExit = false;
    		} else if(input.isKeyPressed(Input.KEY_ENTER)) {
    			buttonAccept.play();
    			System.exit(0);
    		}	
    	}
    }


}
