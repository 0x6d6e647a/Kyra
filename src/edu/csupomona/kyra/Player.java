/**************************************************************
 *	file:		Player.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the player and handles input
 *	and state changes.
**************************************************************/

package edu.csupomona.kyra;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Player {
	private Animation sprite, glowing;
	private float x, y;
	private boolean[][] blocked;
	private static final int SIZE=34;
	private TiledMap map;


	public Player() {}
	
	public void init(GameContainer container, TiledMap currentMap) throws SlickException {
		map = currentMap;
		
		//Create the glowing face animation
		Image [] glow = {
				new Image("img/glow0.png"),
				new Image("img/glow1.png"),
				new Image("img/glow2.png"),
				new Image("img/glow3.png"),
				new Image("img/glow4.png")
		};
		int [] duration = {300, 300};
		glowing = new Animation(glow, duration, true);
		
		//Set the default sprite to the glowing animation
		sprite = glowing;
		
		blocked = new boolean[map.getWidth()][map.getHeight()];
		
		for (int xAxis=0; xAxis < map.getWidth(); xAxis++)
		{
			for (int yAxis=0; yAxis < map.getHeight(); yAxis++)
			{
				int tileID = map.getTileId(xAxis, yAxis, 0);
				String value = currentMap.getTileProperty(tileID, "blocked", "false");
				if ("true".equals(value))
				{
					blocked[xAxis][yAxis] = true;
				}
			}
		}
		
		
	}
	public void update(GameContainer container, int delta) throws SlickException
	{
		Input input = container.getInput();
//		if (input.isKeyDown(Input.KEY_UP))
//		{
//			if (!isBlocked(x, y - delta * 0.1f))
//			{
//				sprite.update(delta);
//				y -= delta * 0.1f;
//			}
//		}
//		else if (input.isKeyDown(Input.KEY_DOWN))
//		{
//			if(!isBlocked(x, y + SIZE + delta))
//		}
	}
	public void render(GameContainer container, Graphics g) throws SlickException {}

	private boolean isBlocked(float x, float y) {
		return true;
	}
}
