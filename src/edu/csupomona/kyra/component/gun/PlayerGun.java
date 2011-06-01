package edu.csupomona.kyra.component.gun;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.physics.objects.ForceVector;
import edu.csupomona.kyra.entity.Entity;

public class PlayerGun extends GunComponent {

	public PlayerGun(String id) {
		super(id, 5);
	}

	@Override
	public Entity makeBullet() {
		String name = owner.getId() + "bullet";
		Entity bullet = new Entity(name);
		bullet.setPosition(owner.getPosition());
//		bullet.addComponent(new BulletPhysics());
//		bullet.addComponent(new BulletRender());
		return bullet;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		super.update(gc, sb, delta);
	}

}
