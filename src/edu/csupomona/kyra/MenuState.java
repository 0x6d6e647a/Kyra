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
	Image options = null;
	Image exit = null;
	int stateID = 0;
	private static int menuX = 410;
	private static int menuY = 160;
	
	public MenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		 background = new Image("img/menu_background.png");
		 Image menuOptions = new Image("img/menu_options.png");
		 start = menuOptions.getSubImage(0, 0, 400, 100);
	     options = menuOptions.getSubImage(0, 0, 400, 200);
	     exit = menuOptions.getSubImage(0, 0, 400, 300);

    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    	start.draw(menuX, menuY);
    	options.draw(menuX, menuY+10);
    	exit.draw(menuX, menuY+20);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	
    	Input input = gc.getInput();
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();
    	boolean insideStart = false;
    	boolean insideOptions = false;
    	boolean insideExit = false;
    	
    	if( ( mouseX >= menuX && mouseX <= menuX + start.getWidth()) &&
                ( mouseY >= menuY && mouseY <= menuY + start.getHeight()) )
            {
                insideStart = true;
            }else if( ( mouseX >= menuX && mouseX <= menuX+ options.getWidth()) &&
                ( mouseY >= menuY+10 && mouseY <= menuY+10 + options.getHeight()) )
            {
                insideOptions = true;
            }else if( ( mouseX >= menuX && mouseX <= menuX+ exit.getWidth()) &&
                ( mouseY >= menuY+20 && mouseY <= menuY+20 + exit.getHeight()) )
            {
                insideExit = true;
            }

    	if(insideStart)
        {
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                sbg.enterState(Kyra.GAMESTATE);
            }
        }
    	if(insideOptions)
        {
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                System.out.println("Entered Options");
            }
        }
    	if(insideExit)
        {
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                System.exit(1);
            }
        }
    }


}
