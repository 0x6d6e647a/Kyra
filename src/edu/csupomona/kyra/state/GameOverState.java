/**************************************************************
 *	file:		GameOverState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the state where the "Game Over"
 *	screen is displayed upon player death with 0 lives.
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


public class GameOverState extends BasicGameState {

	private Image background = null;
	private int stateID = 5;
	
	public GameOverState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("img/gameover_background.png");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if(input.isKeyPressed(Input.KEY_ENTER)) {
    		input.clearKeyPressedRecord();
    		sbg.getCurrentState().leave(gc, sbg);
    		sbg.getState(Kyra.SPLASHSTATE).init(gc, sbg);
    		sbg.getState(Kyra.SPLASHSTATE).enter(gc, sbg);
    		sbg.enterState(Kyra.SPLASHSTATE);
    	}
    }
}
