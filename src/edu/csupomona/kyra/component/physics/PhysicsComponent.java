package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public abstract class PhysicsComponent extends Component {
	Polygon polygon;
	float[] points;
	float height, width;
	Line top, bottom, left, right;
	BlockMap map;
	ForceVector forceVector;
	
	public PhysicsComponent(String id, float height, float width, TiledMap map) throws SlickException{
		if (points.length != 8)
			throw new SlickException("Physics polygons consist of 4 points!");
		super.id = id;
		this.height = height;
		this.width = width;
		this.map = new BlockMap(map);
		setPolygon();
		setLines();
	}
	
	private void setPolygon() {
		Vector2f position = owner.getPosition();
		points = new float[] {
				position.x, position.y,
				position.x+width, position.y,
				position.x+width, position.y+height,
				position.x, position.y+height
		};
		polygon = new Polygon(points);
	}
	
	private void setLines() {
		top = new Line(points[0], points[1], points[2], points[3]);
		bottom = new Line(points[4], points[5], points[6], points[7]);
		left = new Line(points[0], points[1], points[6], points[7]);
		right = new Line(points[2], points[3], points[4], points[5]);
	}
	
	public boolean intersections(Shape shape) {
		return polygon.intersects(shape);
	}
	
	public abstract PhysicsComponent clone();
	
	/*
	 * -- Setters --
	 */
	
	public void setLocation(Vector2f position) {
		owner.setPosition(position);
		polygon.setLocation(position);
		setLines();
	}
	
	public void setX(float x) {
		setLocation(new Vector2f(x, polygon.getY()));
	}
	
	public void setY(float y) {
		setLocation(new Vector2f(polygon.getX(), y));
	}
	
	/*
	 * -- Getters --
	 */
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public Line getTop() {
		return top;
	}
	
	public Line getBottom() {
		return bottom;
	}
	
	public Line getLeft() {
		return left;
	}
	
	public Line getRight() {
		return right;
	}
	
	public ForceVector getForceVector() {
		return forceVector;
	}
}