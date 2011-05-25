package edu.csupomona.kyra.component.health;

import org.newdawn.slick.geom.Polygon;

import edu.csupomona.kyra.component.Component;

public abstract class HealthComponent extends Component {
	
	int health;
	boolean recentHit;
	
	public HealthComponent(String id, int health) {
		super(id);
		this.health = health;
		recentHit = false;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void intersect(Polygon polygon, boolean enemy) {
		if(owner.getPhysicsComponent().getPolygon().intersects(polygon)) {
			if(enemy) {
				health -= 1;
				recentHit = true;
			}
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean zeroHealth() {
		if(health <= 0)
			return true;
		else
			return false;
	}
}
