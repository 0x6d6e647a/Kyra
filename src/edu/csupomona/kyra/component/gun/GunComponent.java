package edu.csupomona.kyra.component.gun;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.entity.Entity;

public abstract class GunComponent extends Component {
	
	ArrayList<Entity> bullets;
	TiledMap map;
	final int MAX_BULLETS;

	protected abstract Entity makeBullet(int delta);
	
	public Entity[] getBullets() {
		return (Entity[]) bullets.toArray();
	}
	
	public void fireBullet(int delta) {
		if (bullets.size() < MAX_BULLETS)
			bullets.add(makeBullet(delta));
	}
	
	public GunComponent(String id, int maxBullets, TiledMap map) {
		super(id);
		this.map = map;
		MAX_BULLETS = maxBullets;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		InputComponent input = owner.getInputComponent();
		
		if (input.isPressed("attack"))
			fireBullet(delta);
		
		for (Entity bullet : bullets) {
			bullet.update(gc, sb, delta);
		}
	}

}
