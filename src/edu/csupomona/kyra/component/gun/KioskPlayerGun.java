package edu.csupomona.kyra.component.gun;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.ai.KioskPlayerAI;
import edu.csupomona.kyra.component.physics.BulletPhysics;
import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.physics.objects.ForceVector;
import edu.csupomona.kyra.component.render.bullet.PlayerBulletRender;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.entity.EntityType;

public class KioskPlayerGun extends GunComponent {
	
	final float SPEED = 3.5f,
		SIZE = 30.0f;
	
	public KioskPlayerGun(String id, TiledMap map) {
		super(id, 200, map);
	}
	
	@Override
	protected Entity makeBullet(int delta) throws SlickException {
		String name = owner.getId()+"bullet";
		ForceVector forceVector;
		if (owner.getXDirection().equals(Direction.LEFT))
			forceVector = new ForceVector(delta * -SPEED, 0.0f);
		else
			forceVector = new ForceVector(delta * SPEED, 0.0f);
		Entity bullet = new Entity(name, EntityType.BULLET);
		bullet.setPosition(owner.getPosition());
		bullet.addComponent(new BulletPhysics(name+"physics", SIZE, SIZE, map, forceVector));
		bullet.addComponent(new PlayerBulletRender(name+"bullet"));
		return bullet;
	}
	
	public void update (GameContainer gc, StateBasedGame sb, int delta) {
		KioskPlayerAI ai = (KioskPlayerAI)owner.getAIComponent();
		for (String action : ai.getActions())
			if (action.equals("attack") && canFire)
				fireBullet(delta);
		
		super.update(gc, sb, delta);
	}


}
