package edu.csupomona.kyra.component.gun;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.entity.Entity;

public abstract class GunComponent extends Component {
	
	ArrayList<Entity> bullets;
	final int MAX_BULLETS;

	public abstract Entity makeBullet();
	
	public Entity[] getBullets() {
		return (Entity[]) bullets.toArray();
	}
	
	public void fireBullet() {
		if (bullets.size() < MAX_BULLETS)
			bullets.add(makeBullet());
	}
	
	public GunComponent(String id, int maxBullets) {
		super(id);
		MAX_BULLETS = maxBullets;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (Entity bullet : bullets) {
			bullet.update(gc, sb, delta);
		}
	}

}
