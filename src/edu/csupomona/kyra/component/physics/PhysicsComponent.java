package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.geom.Polygon;

import edu.csupomona.kyra.component.Component;

public abstract class PhysicsComponent extends Component {
	Polygon polygon = null;
	
	
	public PhysicsComponent(String id) {
		super.id = id;
	}
	
	public boolean collidiesWith(Polygon polygon) {
		if(this.polygon.intersects(polygon)) {
			return true;
		}
		return false;
	}
}