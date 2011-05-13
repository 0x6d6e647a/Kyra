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
		double x = this.xComponent + f.xComponent;
		double y = this.yComponent + f.yComponent;
		return new ForceVector(calcMagnitude(x, y), calcDirection(x, y));
	}
	
	public ForceVector addVectors(ForceVector[] vectors) {
		ForceVector result = new ForceVector(0, 0);
		for (int i = 0; i < vectors.length; i++) {
			result = result.add(vectors[i]);
		}
		return result;
	}
	
	public ForceVector mult(float factor) {
		double x = xComponent * factor;
		double y = yComponent * factor;
		return new ForceVector(calcMagnitude(x, y), calcDirection(x, y));
	}
	
	private double calcMagnitude(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	private double calcDirection(double x, double y) {
		return Math.atan(y / x);
	}
	
	public Vector2f shiftPosition(Vector2f position) {
		return new Vector2f(position.x + (float)xComponent, position.y + (float)yComponent);
	}
	
	public Line getLine(Vector2f currPos) {
		return new Line(currPos, shiftPosition(currPos));
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
		xComponent = magnitude * Math.cos(direction);
		yComponent = magnitude * Math.sin(direction);
	}
	
	public double getDirection() {
		return direction;
	}
	
	public void setDirection(double direction) {
		this.direction = direction;
		xComponent = magnitude * Math.cos(direction);
		yComponent = magnitude * Math.sin(direction);
	}
	
	public double getXComponent() {
		return xComponent;
	}
	
	public void setXComponent(double xComponent) {
		this.xComponent = xComponent;
		this.magnitude = calcMagnitude(this.xComponent, this.yComponent);
		this.direction = calcDirection(this.xComponent, this.yComponent);
	}
	
	public double getYComponent() {
		return yComponent;
	}
	
	public void setYComponent(double yComponent) {
		this.yComponent = yComponent;
		this.magnitude = calcMagnitude(this.xComponent, this.yComponent);
		this.direction = calcDirection(this.xComponent, this.yComponent);
	}
}
