/**************************************************************
 *	file:		ForceVector.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Creates force vectors
**************************************************************/
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
	
	//Returns string of force vector
	public String toString() {
		return "xComp: " + xComponent + ", yComp: " + yComponent;
	}
	
	//Clones force vector
	public ForceVector clone() {
		return new ForceVector(xComponent, yComponent);
	}
	
	//Adds force vectors
	public ForceVector add(ForceVector f) {
		this.xComponent += f.xComponent;
		this.yComponent += f.yComponent;
		return this;
	}
	
	//Multiplies force vectors by a factor
	public ForceVector mult(float factor) {
		this.xComponent *= factor;
		this.yComponent *= factor;
		return this;
	}
	
	//Multiplies x part force vectors by a factor
	public ForceVector multX(float factor) {
		this.xComponent *= factor;
		return this;
	}
	
	//Multiplies y part force vectors by a factor
	public ForceVector multY(float factor) {
		this.yComponent *= factor;
		return this;
	}
	
	//Shifts the vector
	public Vector2f shiftPosition(Vector2f position) {
		return new Vector2f(position.x + xComponent, position.y + yComponent);
	}
	
	//Returns a line
	public Line getLine(Vector2f currPos) {
		return new Line(currPos, shiftPosition(currPos));
	}
	
	//Gets x component
	public float getXComponent() {
		return xComponent;
	}
	
	//Sets x component
	public ForceVector setXComponent(float xComponent) {
		this.xComponent = xComponent;
		return this;
	}
	
	//Gets y component
	public float getYComponent() {
		return yComponent;
	}
	
	//Sets y component
	public ForceVector setYComponent(float yComponent) {
		this.yComponent = yComponent;
		return this;
	}
	
	//Returns the direction
	public Direction getXDirection() {
		if (xComponent < 0)
			return Direction.LEFT;
		else if (xComponent > 0)
			return Direction.RIGHT;
		return Direction.NONE;
	}
	
	//Returns the y direction
	public Direction getYDirection() {
		if (yComponent < 0)
			return Direction.UP;
		else if (yComponent > 0)
			return Direction.DOWN;
		return Direction.NONE;
	}
	
	//Restricts the x component
	public void clampX(float minX, float maxX) {
		if (xComponent > maxX)
			xComponent = maxX;
		else if (xComponent < minX)
			xComponent = minX;
	}
	
	//Restricts the y component
	public void clampY(float minY, float maxY) {
		if (yComponent > maxY)
			yComponent = maxY;
		else if (yComponent < minY)
			yComponent = minY;
	}
}
