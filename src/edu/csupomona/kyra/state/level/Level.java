/**************************************************************
 *	file:		Level.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Creates players and enemies for levels
**************************************************************/
package edu.csupomona.kyra.state.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.ai.ZombieAI;
import edu.csupomona.kyra.component.gun.PlayerGun;
import edu.csupomona.kyra.component.health.EnemyHealth;
import edu.csupomona.kyra.component.health.HealthComponent;
import edu.csupomona.kyra.component.health.ItemHealth;
import edu.csupomona.kyra.component.health.PlayerHealth;
import edu.csupomona.kyra.component.input.Player1Input;
import edu.csupomona.kyra.component.input.Player2Input;
import edu.csupomona.kyra.component.physics.AntiZombiePhysics;
import edu.csupomona.kyra.component.physics.HeartPhysics;
import edu.csupomona.kyra.component.physics.PlayerPhysics;
import edu.csupomona.kyra.component.physics.ZombiePhysics;
import edu.csupomona.kyra.component.render.HealthRender;
import edu.csupomona.kyra.component.render.HeartRender;
import edu.csupomona.kyra.component.render.LevelRender;
import edu.csupomona.kyra.component.render.PositionRender;
import edu.csupomona.kyra.component.render.MapHealthRender;
import edu.csupomona.kyra.component.render.ai.AntiZombieRender;
import edu.csupomona.kyra.component.render.ai.ZombieRender;
import edu.csupomona.kyra.component.render.player.Player1Render;
import edu.csupomona.kyra.component.render.player.Player2Render;
import edu.csupomona.kyra.component.sound.ZombieFx;
import edu.csupomona.kyra.entity.Entity;

public abstract class Level extends BasicGameState {
	int stateID;
	String path;
	TiledMap tiledMap;
	Entity map, player1, player2;
	ArrayList<Entity> entities, enemies, hearts;
	Entity boss;
	Vector2f p1Pos, p2Pos;
	Image intro, pause;
	boolean drawIntro, levelWon;
	
	public Level(int stateID, String path, Vector2f p1Pos, Vector2f p2Pos, boolean drawIntro) {
		this.stateID = stateID;
		this.path = path;
		this.p1Pos = p1Pos;
		this.p2Pos = p2Pos;
		this.drawIntro = drawIntro;
		levelWon = false;
	}
	
	protected abstract void setBoss();
	
	protected void addZombie(Vector2f position, boolean anti) throws SlickException {
		String name = "zombie" + entities.size();
		Entity zombie = new Entity(name);
		zombie.setPosition(position);
		zombie.addComponent(new ZombieAI("ai_"+name, player1, player2, tiledMap));
		if(!anti) {
			zombie.addComponent(new ZombiePhysics("physics"+name, 60, 31, tiledMap));
			zombie.addComponent(new ZombieRender("render"+name));
		} else {
			zombie.addComponent(new AntiZombiePhysics("physics"+name, 60, 31, tiledMap));
			zombie.addComponent(new AntiZombieRender("render"+name));
		}
		zombie.addComponent(new ZombieFx("fx"+name));
		if(!Kyra.vs)
			zombie.addComponent(new EnemyHealth("health"+name, 1, player1, player2));
		else
			zombie.addComponent(new EnemyHealth("health"+name, 10, player1, player2));
		//zombie.addComponent(new HealthRender("drawHealth"+name));
		enemies.add(zombie);
	}
	
	protected void addHeart(Vector2f position) throws SlickException {
		String name = "heart" + entities.size();
		Entity heart = new Entity(name);
		heart.setPosition(position);
		heart.addComponent(new HeartPhysics("physics"+name, 16, 16, tiledMap));
		heart.addComponent(new HeartRender("render"+name));
		heart.addComponent(new ItemHealth("item"+name));
		hearts.add(heart);
	}
	
	protected void nextLevel(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.getInput().clearKeyPressedRecord();
		gc.resume();
		player1.getSoundComponent().stopAll();
		if (Kyra.vs)
			player2.getSoundComponent().stopAll();
		for (Entity entity : entities)
			if (entity.getSoundComponent() != null)
				entity.getSoundComponent().stopAll();
		sbg.getCurrentState().leave(gc, sbg);
		sbg.getState(stateID+1).init(gc, sbg);
		sbg.getState(stateID+1).enter(gc, sbg);
		sbg.enterState(stateID+1);
	}
	
	protected void gameOver(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.getInput().clearKeyPressedRecord();
		player1.getSoundComponent().stopAll();
		for (Entity enemy: enemies)
			if (enemy.getSoundComponent() != null)
				enemy.getSoundComponent().stopAll();
		gc.resume();
		if(sbg.getCurrentStateID() == 4)
			drawIntro = true;
		sbg.getCurrentState().leave(gc, sbg);
		sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
		sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
		sbg.enterState(Kyra.GAMEOVERSTATE);
	}
	
	protected boolean inRange(Entity thing, Entity player1, Entity player2) {
		float eXPos = thing.getPosition().x,
	          eYPos = thing.getPosition().y,
	          pXPos = 0, pYPos = 0;
		if(!player1.getHealthComponent().isDead())  {
			pXPos = player1.getPosition().x;
			pYPos = player1.getPosition().y;
		} else {
			if(Kyra.vs) {
				if(!player2.getHealthComponent().isDead()) {
					pXPos = player2.getPosition().x;
					pYPos = player2.getPosition().y;
				}
			}
		}
		if(Math.abs(eXPos-pXPos) < 512 && Math.abs(eYPos-pYPos) < 384)
			return true;
		return false;
	}
	
	@Override
	public final int getID() {
		return stateID; 
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tiledMap = new TiledMap(path);
		intro = new Image("img/intro.png");
		pause = new Image("img/pause.png");
		
		boss = new Entity("boss");
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		hearts = new ArrayList<Entity>();
		
		player1 = new Entity("player1");
		player1.setPosition(p1Pos);
		player1.addComponent(new Player1Input("p1Input"));
		player1.addComponent(new PlayerPhysics("p1Physics", 60, 31, tiledMap));
		player1.addComponent(new Player1Render("p1Sprite"));
		player1.addComponent(new PlayerHealth("p1Health", 10, enemies, hearts, boss));
		player1.addComponent(new PlayerGun("p1Gun", tiledMap));
		//player1.addComponent(new PositionRender("p1Pos"));
		
		if (Kyra.vs) {
			player2 = new Entity("player2");
			player2.setPosition(p2Pos);
			player2.addComponent(new Player2Input("p2Input"));
			player2.addComponent(new PlayerPhysics("p1Physics", 60, 31, tiledMap));
			player2.addComponent(new Player2Render("p2Sprite"));
			player2.addComponent(new PlayerHealth("p2Health", 10, enemies, hearts, boss));
			player2.addComponent(new PlayerGun("p2Gun", tiledMap));
		}
		
		map = new Entity("map");
		map.addComponent(new LevelRender("level", tiledMap, player1, player2));
		map.addComponent(new MapHealthRender("playerHealth", player1, player2));
		
		setBoss();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		if (drawIntro)
			intro.drawCentered(512, 384);
		if (gc.isPaused())
			pause.drawCentered(512, 384);
		if (levelWon)
			gr.drawString("You Win!", gc.getWidth()/2, gc.getHeight()/2);
		else {
			map.render(gc, sbg, gr);
			if(!player1.getHealthComponent().isDead())
				player1.render(gc, sbg, gr);
			if (Kyra.vs)
				if(!player2.getHealthComponent().isDead())
					player2.render(gc, sbg, gr);
			for (Entity enemy: enemies)
				if(inRange(enemy, player1, player2))
					enemy.render(gc, sbg, gr);
			for (Entity heart : hearts)
				if(inRange(heart, player1, player2))
					heart.render(gc, sbg, gr);
			boss.render(gc, sbg, gr);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(!drawIntro) {
			if (!gc.isPaused()) {
					if(!player1.getHealthComponent().isDead())
						player1.update(gc, sbg, delta);
					if (Kyra.vs)
						if(!player2.getHealthComponent().isDead())
							player2.update(gc, sbg, delta);
					for (Entity enemy: enemies)
						if(inRange(enemy, player1, player2))
							enemy.update(gc, sbg, delta);
					boss.update(gc, sbg, delta);
					HealthComponent bossHealth = boss.getHealthComponent();
					if ((bossHealth != null) && (bossHealth.isDead())) {
						levelWon = true;
					for (Entity heart : hearts)
						if(inRange(heart, player1, player2))
							heart.update(gc, sbg, delta);
					map.update(gc, sbg, delta);
					//Remove hearts from the map that have been used
					for (Iterator<Entity> iter = hearts.iterator(); iter.hasNext();) {
						Entity heart = iter.next();
						if (heart.getHealthComponent().isDead())
							iter.remove();
					}
					//Remove zombies from that map that have died
					for (Iterator<Entity> iter = enemies.iterator(); iter.hasNext();) {
						Entity enemy = iter.next();
						if (enemy.getHealthComponent().isDead()) {
							enemy.getSoundComponent().stopAll();
							iter.remove();
						}
					}
					/*//Remove the boss if he died
					HealthComponent bossHealth = boss.getHealthComponent();
					if ((bossHealth != null) && (bossHealth.isDead())) {
								if(input.isKeyPressed(Input.KEY_SPACE))
									nextLevel(gc, sbg);	
					}*/
					//Pause if pause key is pressed
					if (input.isKeyPressed(Input.KEY_ENTER)) {
						gc.pause();
						input.clearKeyPressedRecord();
					}
				}
				else {
					player1.getSoundComponent().stopAll();
					if (Kyra.vs)
						player2.getSoundComponent().stopAll();
					for (Entity entity : entities)
						if (entity.getSoundComponent() != null)
							entity.getSoundComponent().stopAll();
					if (input.isKeyPressed(Input.KEY_ENTER)) {
						gc.resume();
						input.clearKeyPressedRecord();
					}
					if(input.isKeyPressed(Input.KEY_Q)) {
						File f = new File("save.txt");
						try {
							PrintWriter pw = new PrintWriter(f);
							pw.println(Kyra.vs);
							pw.println(sbg.getCurrentStateID());
							pw.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}		
				}
			}
			if(!Kyra.vs)
				if(player1.getHealthComponent().isDead())
					gameOver(gc, sbg);
			else
				if(player1.getHealthComponent().isDead() && player2.getHealthComponent().isDead())
					gameOver(gc, sbg);
		} else {
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				drawIntro = false;
				input.clearKeyPressedRecord();
			}
		}
	}

}
