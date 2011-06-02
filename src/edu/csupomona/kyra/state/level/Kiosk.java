package edu.csupomona.kyra.state.level;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.ai.Boss1AI;
import edu.csupomona.kyra.component.gun.Boss1Gun;
import edu.csupomona.kyra.component.health.EnemyHealth;
import edu.csupomona.kyra.component.health.HealthComponent;
import edu.csupomona.kyra.component.physics.Boss1Physics;
import edu.csupomona.kyra.component.render.HealthRender;
import edu.csupomona.kyra.component.render.LevelRender;
import edu.csupomona.kyra.component.render.ai.Boss1Render;
import edu.csupomona.kyra.component.render.player.Player1Render;
import edu.csupomona.kyra.component.sound.PlayerSoundsLevel2;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.entity.EntityType;

public class Kiosk extends Level {

	
	final static Vector2f P1_POS = new Vector2f(0.0f, 0.0f);
	final static Vector2f P2_POS = new Vector2f(0.0f, 0.0f);
	
	public Kiosk(int stateID) throws SlickException {
		super(stateID, "lvl/kiosk.tmx", P1_POS, P2_POS, true);
	}
	
	@Override
	protected void setBoss() {
		boss.setPosition(new Vector2f(0.0f, 0.0f));
		boss.addComponent(new Boss1Render("kioskBossRender"));
		boss.addComponent(new Boss1AI("boss1AI", player1, player2, tiledMap));
		boss.addComponent(new Boss1Physics("boss1Physics", 96.0f, 64.0f, tiledMap));
		boss.addComponent(new Boss1Gun("boss1Gun", tiledMap));
		boss.addComponent(new EnemyHealth("bossHealth", 20, player1, player2));
		boss.addComponent(new HealthRender("bossHealthRender"));
	}
	
	@Override
	protected void nextLevel(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.getInput().clearKeyPressedRecord();
		gc.resume();
		player1.getSoundComponent().stopAll();
		for (Entity entity : entities)
			if (entity.getSoundComponent() != null)
				entity.getSoundComponent().stopAll();
		sbg.getCurrentState().leave(gc, sbg);
		sbg.getState(Kyra.MENUSTATE).init(gc, sbg);
		sbg.getState(Kyra.MENUSTATE).enter(gc, sbg);
		sbg.enterState(Kyra.MENUSTATE);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tiledMap = new TiledMap(path);
		
		boss = new Entity("boss", EntityType.BOSS);
		entities = new ArrayList<Entity>();
		
		player1 = new Entity("player1", EntityType.PLAYER1);
		player1.setPosition(p1Pos);
		player1.addComponent(new Player1Render("p1Sprite"));
		player1.addComponent(new PlayerSoundsLevel2("p1_SFX_lvl2"));
		
		map = new Entity("map", EntityType.MAP);
		map.addComponent(new LevelRender("level", tiledMap, player1, player2));
		
		setBoss();
		entities.add(boss);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		map.render(gc, sbg, gr);
		if(!player1.getHealthComponent().isDead())
			player1.render(gc, sbg, gr);
		for (Entity entity : entities)
			if (isInRange(player1, player2, entity))
				entity.render(gc, sbg, gr);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ENTER))
			System.out.println("EXIT KIOSK");
		player1.update(gc, sbg, delta);
		for(Iterator<Entity> iter = entities.iterator(); iter.hasNext();) {
			Entity entity = iter.next();
			if (isInRange(player1, player2, entity))
				entity.update(gc, sbg, delta);
			HealthComponent healthCmpt = entity.getHealthComponent();
			if ((healthCmpt != null) && healthCmpt.isDead())
				iter.remove();
			map.update(gc, sbg, delta);
		}
	}
}
