package edu.csupomona.kyra.component.physics.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
 
public class Block  {
	float[] points;
	Polygon polygon;
	Line top;
	Line bottom;
	
	public Block(int x, int y, int test[], String type) {
		points = new float[]{
				x+test[0], y+test[1],
				x+test[2], y+test[3],
				x+test[4], y+test[5],
				x+test[6], y+test[7],
		};
        polygon = new Polygon(points);
        top = new Line(points[0], points[1], points[2], points[3]);
        bottom = new Line(points[4], points[5], points[6], points[7]);   
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	public Line getTop() {
		return top;
	}
	
	public Line getBottom() {
		return bottom;
	}
	
	public void update(int delta) {
	}
 
	public void draw(Graphics g) {
		g.draw(polygon);
	}
}
