package edu.csupomona.kyra.state.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

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
import edu.csupomona.kyra.component.gun.HeartRender;
import edu.csupomona.kyra.component.gun.PlayerGun;
import edu.csupomona.kyra.component.health.ItemHealth;
import edu.csupomona.kyra.component.health.PlayerHealth;
import edu.csupomona.kyra.component.input.Player1Input;
import edu.csupomona.kyra.component.input.Player2Input;
import edu.csupomona.kyra.component.physics.HeartPhysics;
import edu.csupomona.kyra.component.physics.PlayerPhysics;
import edu.csupomona.kyra.component.physics.ZombiePhysics;
import edu.csupomona.kyra.component.render.LevelRender;
import edu.csupomona.kyra.component.render.PositionRender;
import edu.csupomona.kyra.component.render.MapHealthRender;
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
	ArrayList<Entity> entities, enemies, hearts, playerBullets, enemyBullets;
	Vector2f p1Pos, p2Pos;
	Image intro, pause;
	boolean drawIntro;
	
	public Level(int stateID, String path, Vector2f p1Pos, Vector2f p2Pos, boolean drawIntro) {
		this.stateID = stateID;
		this.path = path;
		this.p1Pos = p1Pos;
		this.p2Pos = p2Pos;
		this.drawIntro = drawIntro;
	}
	
	protected void addZombie(Vector2f position) throws SlickException {
		String name = "zombie" + entities.size();
		Entity zombie = new Entity(name);
		zombie.setPosition(position);
		zombie.addComponent(new ZombieAI("ai_"+name, player1, player2, tiledMap));
		zombie.addComponent(new ZombiePhysics("physics"+name, 60, 31, tiledMap));
		zombie.addComponent(new ZombieRender("render"+name));
		zombie.addComponent(new ZombieFx("fx"+name));
		//zombie.addComponent(new EnemyHealth("hp"+name, 5, player1, player2));
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
		sbg.getCurrentState().leave(gc, sbg);
		sbg.getState(stateID+1).init(gc, sbg);
		sbg.getState(stateID+1).enter(gc, sbg);
		sbg.enterState(stateID+1);
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
		
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		hearts = new ArrayList<Entity>();
		playerBullets = new ArrayList<Entity>();
		enemyBullets = new ArrayList<Entity>();
		
		player1 = new Entity("player1");
		player1.setPosition(p1Pos);
		player1.addComponent(new Player1Input("p1Input"));
		player1.addComponent(new PlayerPhysics("p1Physics", 60, 31, tiledMap));
		player1.addComponent(new Player1Render("p1Sprite"));
		player1.addComponent(new PlayerHealth("p1Health", 3, enemies, hearts));
		player1.addComponent(new PlayerGun("p1Gun", tiledMap));
		player1.addComponent(new PositionRender("p1Pos"));
		
		if (Kyra.vs) {
			player2 = new Entity("player2");
			player2.setPosition(p2Pos);
			player2.addComponent(new Player2Input("p2Input"));
			player2.addComponent(new PlayerPhysics("p1Physics", 60, 31, tiledMap));
			player2.addComponent(new Player2Render("p2Sprite"));
			player2.addComponent(new PlayerHealth("p2Health", 3, enemies, hearts));
			player2.addComponent(new PlayerGun("p2Gun", tiledMap));
		}
		
		map = new Entity("map");
		map.addComponent(new LevelRender("level", tiledMap, player1));
		map.addComponent(new MapHealthRender("playerHealth", player1, player2));
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		if (drawIntro)
			intro.drawCentered(512, 384);
		if (gc.isPaused())
			pause.drawCentered(512, 384);
		else {
			map.render(gc, sbg, gr);
			player1.render(gc, sbg, gr);
			if (Kyra.vs)
				player2.render(gc, sbg, gr);
			for (Entity enemy: enemies) {
				float eXPos = enemy.getPosition().x;
				float pXPos = player1.getPosition().x;
				if(Math.abs(eXPos-pXPos) < 1000)
					enemy.render(gc, sbg, gr);
			}
			for (Entity heart : hearts)
				heart.render(gc, sbg, gr);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(!drawIntro) {
			if (!gc.isPaused()) {
				player1.update(gc, sbg, delta);
				if (Kyra.vs)
					player2.update(gc, sbg, delta);
				for (Entity enemy: enemies) {
					float eXPos = enemy.getPosition().x;
					float pXPos = player1.getPosition().x;
					if(Math.abs(eXPos-pXPos) < 1000)
						enemy.update(gc, sbg, delta);
				}
				for (Entity heart : hearts)
					heart.update(gc, sbg, delta);
				map.update(gc, sbg, delta);
				//Remove hearts from the map that have been used
				for (Iterator<Entity> iter = hearts.iterator(); iter.hasNext();) {
					Entity heart = iter.next();
					if (heart.getHealthComponent().zeroHealth())
						iter.remove();
				}
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
			if(!Kyra.vs) {
				if(player1.getHealthComponent().zeroHealth()) {
					input.clearKeyPressedRecord();
        			player1.getSoundComponent().stopAll();
        			for (Entity enemy: enemies)
    					if (enemy.getSoundComponent() != null)
    						enemy.getSoundComponent().stopAll();
        			gc.resume();
        			sbg.getCurrentState().leave(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
        			drawIntro = true;
        			sbg.enterState(Kyra.GAMEOVERSTATE);
				}
			} else {
				if(player1.getHealthComponent().zeroHealth() || player2.getHealthComponent().zeroHealth()) {
					input.clearKeyPressedRecord();
        			player1.getSoundComponent().stopAll();
            		player2.getSoundComponent().stopAll();
            		for (Entity enemy: enemies)
    					if (enemy.getSoundComponent() != null)
    						enemy.getSoundComponent().stopAll();
        			gc.resume();
        			sbg.getCurrentState().leave(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
        			sbg.getState(Kyra.GAMEOVERSTATE).enter(gc, sbg);
        			drawIntro = true;
        			sbg.enterState(Kyra.GAMEOVERSTATE);
				}
			}
		} else {
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				drawIntro = false;
				input.clearKeyPressedRecord();
			}
		}
	}

}
