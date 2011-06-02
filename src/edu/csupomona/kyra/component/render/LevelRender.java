/**************************************************************
 *	file:		LevelRender.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Renders level
**************************************************************/
package edu.csupomona.kyra.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.render.RenderComponent;
import edu.csupomona.kyra.entity.Entity;


public class LevelRender extends RenderComponent {
	TiledMap map = null;
	Entity player1, player2;
	float xPos = 0;
	float yPos = 0;
	
	public LevelRender(String id, TiledMap map, Entity player1, Entity player2) throws SlickException{
		super(id);
		this.map = map;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public LevelRender(String id, TiledMap map) {
		super(id);
		this.map = map;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		gr.translate(xPos, yPos);
		map.render(0, 0, map.getLayerIndex("tiles"));
	}
	
	//Returns the map
	public TiledMap getTiledMap() {
		return map;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(!player1.getHealthComponent().isDead()) {
			xPos = -player1.getPosition().x+(gc.getWidth()/2);
			yPos = -player1.getPosition().y+(gc.getHeight()/2);
		} else {
			if(Kyra.vs) {
				if(!player2.getHealthComponent().isDead()) {
					xPos = -player2.getPosition().x+(gc.getWidth()/2);
					yPos = -player2.getPosition().y+(gc.getHeight()/2);
				}
			}
		}
	}
}