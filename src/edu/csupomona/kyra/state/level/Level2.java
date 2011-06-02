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
import edu.csupomona.kyra.component.sound.PlayerSoundsLevel2;

public class Level2 extends Level {
	final static Vector2f P1_POS = new Vector2f(33, 1216);
	final static Vector2f P2_POS = new Vector2f(83, 1216);
	
	public Level2() throws SlickException {
		super(Kyra.GAMESTATETWO, "lvl/level2map.tmx", P1_POS, P2_POS, false);
	}
	
	protected void setBoss() {
		boss.setPosition(new Vector2f(7160.0f, 250.0f));
		boss.addComponent(new Boss1Render("boss1Render"));
		boss.addComponent(new Boss1AI("boss2AI", player1, player2, tiledMap));
		boss.addComponent(new Boss1Physics("bossPhysics", 96.0f, 64.0f, tiledMap));
		boss.addComponent(new Boss1Gun("boss2Gun", tiledMap));
		boss.addComponent(new EnemyHealth("boss2Health", 35, player1, player2));
		boss.addComponent(new HealthRender("boss1Health"));
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		player1.addComponent(new PlayerSoundsLevel2("player1_SFX_lvl2"));
		if (Kyra.vs)
			player2.addComponent(new PlayerSoundsLevel2("player2_SFX_lvl2"));
		addZombie(new Vector2f(684, 1092));
		addZombie(new Vector2f(1225, 1188));
		addZombie(new Vector2f(1690, 1092));
		addZombie(new Vector2f(2177, 1220));
		addZombie(new Vector2f(2784, 1028));
		addZombie(new Vector2f(2525, 388));
		addZombie(new Vector2f(1938, 164));
		addZombie(new Vector2f(2432, 132));
		addZombie(new Vector2f(2883, 132));
		addZombie(new Vector2f(3607, 132));
		addZombie(new Vector2f(4057, 260));
		addZombie(new Vector2f(4244, 804));
		addZombie(new Vector2f(3895, 1284));
		addZombie(new Vector2f(3664, 1508));
		addZombie(new Vector2f(4491, 1284));
		addZombie(new Vector2f(4741, 1156));
		addZombie(new Vector2f(5674, 1284));
		addZombie(new Vector2f(5835, 676));
		addZombie(new Vector2f(6171, 676));
		addZombie(new Vector2f(6343, 676));
		addHeart(new Vector2f(799, 676));
		addHeart(new Vector2f(2849, 1220));
		addHeart(new Vector2f(1602, 356));
		addHeart(new Vector2f(4161, 132));
		addHeart(new Vector2f(3500, 1508));
		addHeart(new Vector2f(5887, 1028));
		addHeart(new Vector2f(5064, 1252));
		addHeart(new Vector2f(4257, 516));
		addHeart(new Vector2f(2822, 132));
		addHeart(new Vector2f(799, 1444));
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (gc.isPaused() && gc.getInput().isKeyPressed(Input.KEY_P)) {
			nextLevel(gc, sbg);
		}
	}
}
