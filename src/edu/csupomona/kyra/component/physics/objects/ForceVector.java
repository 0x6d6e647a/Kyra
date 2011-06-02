package edu.csupomona.kyra.component.physics.objects;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

public class ForceVector {
	float xComponent;
	float yComponent;
	
	public ForceVector() {
		xComponent = 0.0f;
		yComponent = 0.0f;
	}
	
	public ForceVector(float xComponent, float yComponent) {
		this.xComponent = xComponent;
		this.yComponent = yComponent;
	}
	
	public ForceVector(Line line, float magnitude, int delta) {
		float lineXComponent = line.getX2() - line.getX1();
		float lineYComponent = line.getY2() - line.getY1();
		double direction = Math.atan2(lineYComponent, lineXComponent);
		this.xComponent = delta * (float)(magnitude * Math.cos(direction));
		this.yComponent = delta * (float)(magnitude * Math.sin(direction));
	}
	
	public String toString() {
		return "xComp: " + xComponent + ", yComp: " + yComponent;
	}
	
	public ForceVector clone() {
		return new ForceVector(xComponent, yComponent);
	}
	
	public ForceVector add(ForceVector f) {
		this.xComponent += f.xComponent;
		this.yComponent += f.yComponent;
		return this;
	}
	
	public ForceVector mult(float factor) {
		this.xComponent *= factor;
		this.yComponent *= factor;
		return this;
	}
	
	public ForceVector multX(float factor) {
		this.xComponent *= factor;
		return this;
	}
	
	public ForceVector multY(float factor) {
		this.yComponent *= factor;
		return this;
	}
	
	public Vector2f shiftPosition(Vector2f position) {
		return new Vector2f(position.x + xComponent, position.y + yComponent);
	}
	
	public Line getLine(Vector2f currPos) {
		return new Line(currPos, shiftPosition(currPos));
	}
	
	public float getXComponent() {
		return xComponent;
	}
	
	public ForceVector setXComponent(float xComponent) {
		this.xComponent = xComponent;
		return this;
	}
	
	public float getYComponent() {
		return yComponent;
	}
	
	public ForceVector setYComponent(float yComponent) {
		this.yComponent = yComponent;
		return this;
	}
	
	public Direction getXDirection() {
		if (xComponent < 0)
			return Direction.LEFT;
		else if (xComponent > 0)
			return Direction.RIGHT;
		return Direction.NONE;
	}
	
	public Direction getYDirection() {
		if (yComponent < 0)
			return Direction.UP;
		else if (yComponent > 0)
			return Direction.DOWN;
		return Direction.NONE;
	}
	
	public void clampX(float minX, float maxX) {
		if (xComponent > maxX)
			xComponent = maxX;
		else if (xComponent < minX)
			xComponent = minX;
	}
	
	public void clampY(float minY, float maxY) {
		if (yComponent > maxY)
			yComponent = maxY;
		else if (yComponent < minY)
			yComponent = minY;
	}
}
