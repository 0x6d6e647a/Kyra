package edu.csupomona.kyra.component.physics.objects;

import java.lang.Math;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

public class ForceVector {
	double magnitude;
	double direction;
	double xComponent;
	double yComponent;
	
	public ForceVector(double magnitude, double direction) {
		this.magnitude = magnitude;
		this.direction = direction;
		xComponent = magnitude * Math.cos(direction);
		yComponent = magnitude * Math.sin(direction);
	}
	
	public ForceVector add(ForceVector f) {
		double resultXComponent = this.xComponent + f.xComponent;
		double resultYComponent = this.yComponent + f.yComponent;
		double resultMagnitude = Math.sqrt(Math.pow(resultXComponent, 2) + Math.pow(resultYComponent, 2));
		double resultDirection = Math.atan(resultYComponent / resultXComponent);
		return new ForceVector(resultMagnitude, resultDirection);
	}
	
	public ForceVector addVectors(ForceVector[] vectors) {
		ForceVector result = new ForceVector(0, 0);
		for (int i = 0; i < vectors.length; i++) {
			result = result.add(vectors[i]);
		}
		return result;
	}
	
	public Vector2f shiftPosition(Vector2f position) {
		return new Vector2f(position.x + (float)xComponent, position.y + (float)yComponent);
	}
	
	public Line getLine(Vector2f currPos) {
		return new Line(currPos, shiftPosition(currPos));
	}
	
	public void setYComponent(double yComponent) {
		this.yComponent = yComponent;
		this.magnitude = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
		this.direction = Math.atan(yComponent / xComponent);
	}
	
	public double getXComponent() {
		return xComponent;
	}
	
	public double getYComponent() {
		return yComponent;
	}
}
