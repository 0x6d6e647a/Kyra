package edu.csupomona.kyra;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionState extends BasicGameState {

	Image background = null;
	Image fullscreen = null;
	Image fullscreenSelect = null;
	Image back = null;
	Image backSelect = null;
	int stateID = 4;
	Controls con;
	boolean insideFullscreen = false;
	boolean insideBack = true;
	private static int menuX = 312;
	private static int menuY = 334;
	
	public OptionState(int stateID, Controls con) {
		this.stateID = stateID;
		this.con = con;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
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
    		if(input.isKeyPressed(con.getP1UP())) {
    			insideFullscreen = false;
    			insideBack = true;
    		} else if(input.isKeyPressed(con.getP1DOWN())) {
    			insideFullscreen = false;
    			insideBack = true;
    		} else if(input.isKeyPressed(con.getP1ACTION())) {
    			if(gc.isFullscreen()) {
        			gc.setFullscreen(false);
        		} else {
        			gc.setFullscreen(true);
        		}
    		}	
    	}
    	if(insideBack) {
    		if(input.isKeyPressed(con.getP1UP())) {
    			insideFullscreen = true;
    			insideBack = false;
    		} else if(input.isKeyPressed(con.getP1DOWN())) {
    			insideFullscreen = true;
    			insideBack = false;
    		} else if(input.isKeyPressed(con.getP1ACTION())) {
    			sbg.enterState(Kyra.MENUSTATE);
    		}	
    	}
    }
}
