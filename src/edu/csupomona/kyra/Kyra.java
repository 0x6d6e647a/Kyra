package edu.csupomona.kyra;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Kyra extends BasicGame {
	private TiledMap testroom00;
	public Kyra() {
		super("Kyra");
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
	public void init(GameContainer container) throws SlickException
	{
		testroom00 = new TiledMap("lvl/testroom00.tmx");
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		testroom00.render(0, 0);
	}
}
