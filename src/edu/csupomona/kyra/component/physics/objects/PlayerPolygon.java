package edu.csupomona.kyra.component.physics.objects;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class PlayerPolygon {
	float[] points;
	Polygon polygon;
	Line top;
	Line bottom;
	
	public PlayerPolygon(float[] points) {
		this.points = points;
		polygon = new Polygon(points);
		top = new Line(points[0], points[1], points[2], points[3]);
		bottom = new Line(points[4], points[5], points[6], points[7]);
	}
	
	public void setLocation(Vector2f position) {
		polygon.setLocation(position);
		points = polygon.getPoints();
		top = new Line(points[0], points[1], points[2], points[3]);
		bottom = new Line(points[4], points[5], points[6], points[7]);
	}
	
	public void setLocation(float x, float y) {
		setLocation(new Vector2f(x, y));
	}
	
	public boolean intersects(Shape shape) {
		return polygon.intersects(shape);
	}
	
	public Line getTop() {
		return top;
	}
	
	public Line getBottom() {
		return bottom;
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	public void setX(float x) {
		setLocation(x, polygon.getY());
	}
	
	public void setY(float y) {
		setLocation(polygon.getX(), y);
	}
}
