package edu.csupomona.kyra.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.RenderComponent;
import edu.csupomona.kyra.entity.Entity;


public class LevelRender extends RenderComponent {
	TiledMap map = null;
	Entity player = null;
	float xPos = 0;
	float yPos = 0;
	
	public LevelRender(String id, TiledMap map, Entity player) throws SlickException{
		super(id);
		this.map = map;
		this.player = player;
	}
	
	public LevelRender(String id, TiledMap map) {
		super(id);
		this.map = map;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		gr.translate(xPos, yPos);
		map.render(0, 0, map.getLayerIndex("tiles"));
	}
	
	public TiledMap getTiledMap() {
		return map;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		xPos = -player.getPosition().x+(gc.getWidth()/2);
		yPos = -player.getPosition().y+(gc.getHeight()/2);
	}
}