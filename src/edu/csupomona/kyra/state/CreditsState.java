/**************************************************************
 *	file:		CreditsState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the state where the credits are
 *	being played.
**************************************************************/

package edu.csupomona.kyra.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;

public class CreditsState extends BasicGameState {

	private Image background = null;
	private int stateID = 7;
	
	public CreditsState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("img/credits_background.png");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(input.isKeyPressed(Input.KEY_SPACE)) {
    		input.clearKeyPressedRecord();
    		sbg.getCurrentState().leave(gc, sbg);
    		sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
    		sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
    		sbg.enterState(Kyra.GAMEOVERSTATE);
    	}
    }
}
