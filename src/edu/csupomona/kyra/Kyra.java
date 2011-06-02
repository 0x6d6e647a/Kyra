/**************************************************************
 *	file:		Kyra.java
 *	author:		Andrew King, Anthony Mendez,
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This main class this is executed to start the 
 *	game program.
**************************************************************/

package edu.csupomona.kyra;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.state.SplashState;

public class Kyra extends StateBasedGame {
	
	public static final int SPLASHSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int PLAYERSELECTSTATE = 2;
	public static final int OPTIONSTATE = 3;
	public static final int GAMESTATEONE = 4;
	public static final int GAMESTATETWO = 5;
	public static final int GAMEOVERSTATE = 6;
	public static final int CREDITSSTATE = 7;
	public static boolean vs = false;
	
	public Kyra() throws SlickException {
		super("Kyra");
		this.addState(new SplashState(SPLASHSTATE));
	}
	
	public static void main(String[] arguments) {
		try
		{
			AppGameContainer app = new AppGameContainer(new Kyra());
			app.setDisplayMode(1024, 768, false);
			app.setShowFPS(false);
			app.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
    	this.getState(SPLASHSTATE).init(gameContainer, this);
        this.enterState(SPLASHSTATE);
    }
}
