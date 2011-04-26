package edu.csupomona.kyra;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Kyra extends StateBasedGame {
	
	public Controls con = new Controls();
	public static final int SPLASHSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int PLAYERSELECTSTATE = 2;
	public static final int LOADGAMESTATE = 3;
	public static final int OPTIONSTATE = 4;
	public static final int GAMESTATE = 5;
	public static final int PAUSESTATE = 6;
	public static final int GAMEOVERSTATE = 7;
	public static final int CREDITSSTATE = 8;
	
	public Kyra() {
		super("Kyra");
		this.addState(new SplashState(SPLASHSTATE, con));
		this.addState(new MenuState(MENUSTATE, con));
		this.addState(new PlayerSelectState(PLAYERSELECTSTATE, con));
		this.addState(new LoadGameState(LOADGAMESTATE, con));
		this.addState(new OptionState(OPTIONSTATE, con));
		this.addState(new GameState(GAMESTATE, con));
		this.addState(new PauseState(PAUSESTATE, con));
		this.addState(new GameOverState(GAMEOVERSTATE, con));
		this.addState(new CreditsState(CREDITSSTATE, con));
	}
	
	public static void main(String[] arguments) {
		try
		{
			AppGameContainer app = new AppGameContainer(new Kyra());
			app.setDisplayMode(1024, 768, false);
			app.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
 
    	this.getState(SPLASHSTATE).init(gameContainer, this);
    	this.getState(MENUSTATE).init(gameContainer, this);
    	this.getState(PLAYERSELECTSTATE).init(gameContainer, this);
    	this.getState(LOADGAMESTATE).init(gameContainer, this);
    	this.getState(OPTIONSTATE).init(gameContainer, this);
    	this.getState(GAMESTATE).init(gameContainer, this);
        this.getState(PAUSESTATE).init(gameContainer, this);
        this.getState(GAMEOVERSTATE).init(gameContainer, this);
        this.getState(CREDITSSTATE).init(gameContainer, this);
        this.enterState(MENUSTATE);
    }

}
