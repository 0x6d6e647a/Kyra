package edu.csupomona.kyra.state.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;

public class Level1 extends Level {
	final static Vector2f P1_POS = new Vector2f(33, 1503);
	final static Vector2f P2_POS = new Vector2f(83, 1503);
	
	public Level1() throws SlickException {
		super(Kyra.GAMESTATEONE, "lvl/level1map.tmx", P1_POS, P2_POS, true);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		addZombie(new Vector2f(33, 1503));
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (gc.isPaused() && gc.getInput().isKeyPressed(Input.KEY_P)) {
			nextLevel(gc, sbg);
		}
	}
}
