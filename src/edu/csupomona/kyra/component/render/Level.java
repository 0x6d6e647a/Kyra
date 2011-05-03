package edu.csupomona.kyra.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.render.RenderComponent;


public class Level extends RenderComponent {
	TiledMap map = null;
	
	public Level(String id, String filename) throws SlickException{
		super(id);
		map = new TiledMap(filename);
	}
	
	public Level(String id, TiledMap map) {
		super(id);
		this.map = map;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		float scale = owner.getScale();
		map.render(0, 0, map.getLayerIndex("tiles"));
	}
	
	public TiledMap getTiledMap() {
		return map;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	}
}