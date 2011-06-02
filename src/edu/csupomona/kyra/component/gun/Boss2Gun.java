/**************************************************************
 *	file:		Boss1Gun.java
 *	author:		Andrew King, Anthony Mendez,  Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: Projectiles for level 1 boss
**************************************************************/
package edu.csupomona.kyra.component.gun;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.ai.Boss2AI;
import edu.csupomona.kyra.component.physics.BulletPhysics;
import edu.csupomona.kyra.component.physics.objects.ForceVector;
import edu.csupomona.kyra.component.render.bullet.Boss2BulletRender;
import edu.csupomona.kyra.entity.Entity;
import edu.csupomona.kyra.entity.EntityType;

public class Boss2Gun extends GunComponent {
	

	public Boss2Gun(String id, TiledMap map) {
		super(id, 2000, map);
	}

	@Override
	protected Entity makeBullet(int delta) throws SlickException {
		Boss2AI bossAI = (Boss2AI)owner.getAIComponent();
		String name = owner.getId()+"bullet";
		ForceVector bulletPath = bossAI.getAim();
		Entity bullet = new Entity(name, EntityType.BULLET);
		bullet.setPosition(owner.getPosition());
		bullet.addComponent(new BulletPhysics(name+"physics", 32.0f, 32.0f, map, bulletPath));
		bullet.addComponent(new Boss2BulletRender(name+"bulletRender"));
		return bullet;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Boss2AI bossAI = (Boss2AI)owner.getAIComponent();
		if ((bossAI.getAim() != null) && canFire)
			fireBullet(delta);
		
		super.update(gc, sb, delta);
	}

}
