package edu.csupomona.kyra;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Kyra extends StateBasedGame {
	
	public static final int MENUSTATE = 0;
	public static final int OPTIONSTATE = 1;
	public static final int GAMESTATE = 2;
	
	
	public Kyra() {
		super("Kyra");
		
		this.addState(new MenuState(MENUSTATE));
		this.addState(new OptionState(OPTIONSTATE));
		this.addState(new GameState(GAMESTATE));
		
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
 
        this.getState(MENUSTATE).init(gameContainer, this);
        this.getState(OPTIONSTATE).init(gameContainer, this);
        this.getState(GAMESTATE).init(gameContainer, this);
        this.enterState(MENUSTATE);
    }

}
