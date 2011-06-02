package edu.csupomona.kyra.state.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.ai.Boss1AI;
import edu.csupomona.kyra.component.gun.Boss1Gun;
import edu.csupomona.kyra.component.health.EnemyHealth;
import edu.csupomona.kyra.component.physics.Boss1Physics;
import edu.csupomona.kyra.component.render.HealthRender;
import edu.csupomona.kyra.component.render.ai.Boss1Render;
import edu.csupomona.kyra.component.sound.PlayerSoundsLevel1;

public class Level1 extends Level {
	final static Vector2f P1_POS = new Vector2f(33, 1503);
	final static Vector2f P2_POS = new Vector2f(83, 1503);
	
	public Level1() throws SlickException {
		super(Kyra.GAMESTATEONE, "lvl/level1map.tmx", P1_POS, P2_POS, true);
	}
	
	protected void setBoss() {
		boss.setPosition(new Vector2f(5575.0f, 1140.0f));
		boss.addComponent(new Boss1Render("boss1Render"));
		boss.addComponent(new Boss1AI("boss1AI", player1, player2, tiledMap));
		boss.addComponent(new Boss1Physics("boss1Physics", 96.0f, 64.0f, tiledMap));
		boss.addComponent(new Boss1Gun("boss1Gun", tiledMap));
		boss.addComponent(new EnemyHealth("bossHealth", 20, player1, player2));
		boss.addComponent(new HealthRender("boss1Health"));
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		player1.addComponent(new PlayerSoundsLevel1("player1_SFX_lvl1"));
		if (Kyra.vs)
			player2.addComponent(new PlayerSoundsLevel1("player2_SFX_lvl1"));
		addZombie(new Vector2f(1200, 1503));
		addZombie(new Vector2f(1362, 580));
		addZombie(new Vector2f(2057, 196));
		addZombie(new Vector2f(2359, 196));
		addZombie(new Vector2f(2596, 580));
		addZombie(new Vector2f(1028, 2855));
		addZombie(new Vector2f(3414, 1028));
		addZombie(new Vector2f(3844, 1508));
		addZombie(new Vector2f(4062, 1508));
		addZombie(new Vector2f(4502, 1508));
		addHeart(new Vector2f(1200, 1503));
		addHeart(new Vector2f(2591, 196));
		addHeart(new Vector2f(3649, 836));
		addHeart(new Vector2f(4982, 1508));
		addHeart(new Vector2f(6048, 932));
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (gc.isPaused() && gc.getInput().isKeyPressed(Input.KEY_P)) {
			drawIntro = true;
			nextLevel(gc, sbg);
		}
	}
}
