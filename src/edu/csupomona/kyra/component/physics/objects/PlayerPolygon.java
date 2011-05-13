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
	Line left;
	Line right;
	
	public PlayerPolygon(float[] points) {
		this.points = points;
		polygon = new Polygon(points);
		setLines();
	}
	
	private void setLines() {
		top = new Line(points[0], points[1], points[2], points[3]);
		bottom = new Line(points[4], points[5], points[6], points[7]);
		left = new Line(points[0], points[1], points[6], points[7]);
		right = new Line(points[2], points[3], points[4], points[5]);
	}
	
	public void setLocation(Vector2f position) {
		polygon.setLocation(position);
		points = polygon.getPoints();
		setLines();
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
	
	public Line getLeft() {
		return left;
	}
	
	public Line getRight() {
		return right;
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
