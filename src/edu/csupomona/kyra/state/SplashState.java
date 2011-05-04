/**************************************************************
 *	file:		SplashState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the splash screen game state.
**************************************************************/

package edu.csupomona.kyra.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Controls;
import edu.csupomona.kyra.Kyra;


public class SplashState extends BasicGameState {

	private Image background = null;
	private int stateID = 0;
	private Controls con;
	
	public SplashState(int stateID, Controls con) {
		this.stateID = stateID;
		this.con = con;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("img/splash_background.png");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(input.isKeyPressed(con.getP1PAUSE())) {
    		input.clearKeyPressedRecord();
    		sbg.enterState(Kyra.MENUSTATE);
    	}
    }
}

