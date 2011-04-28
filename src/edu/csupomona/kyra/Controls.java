/**************************************************************
 *	file:		Controls.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class will handle player input and pass button
 *	presses to classes that need it.
**************************************************************/

package edu.csupomona.kyra;

import org.newdawn.slick.Input;

public class Controls {
	
	private int P1UP;
	private int P1DOWN;
	private int P1LEFT;
	private int P1RIGHT;
	private int P1JUMP;
	private int P1ACTION;
	private int P1PAUSE;
	
	private int P2UP;
	private int P2DOWN;
	private int P2LEFT;
	private int P2RIGHT;
	private int P2JUMP;
	private int P2ACTION;
	private int P2PAUSE;
	
	public Controls() {
		P1UP = Input.KEY_W;
		P1DOWN = Input.KEY_S;
		P1LEFT = Input.KEY_A;
		P1RIGHT = Input.KEY_D;
		P1JUMP = Input.KEY_G;
		P1ACTION = Input.KEY_H;
		P1PAUSE = Input.KEY_T;
		
		P2UP = Input.KEY_UP;
		P2DOWN = Input.KEY_DOWN;
		P2LEFT = Input.KEY_LEFT;
		P2RIGHT = Input.KEY_RIGHT;
		P2JUMP = Input.KEY_NUMPAD1;
		P2ACTION = Input.KEY_NUMPAD2;
		P2PAUSE = Input.KEY_NUMPAD4;
	}
	public void setP1UP(int P1UP) {
		this.P1UP = P1UP;
	}
	public int getP1UP() {
		return P1UP;
	}
	public void setP1DOWN(int P1DOWN) {
		this.P1DOWN = P1DOWN;
	}
	public int getP1DOWN() {
		return P1DOWN;
	}
	public void setP1LEFT(int P1LEFT) {
		this.P1LEFT = P1LEFT;
	}
	public int getP1LEFT() {
		return P1LEFT;
	}
	public void setP1RIGHT(int P1RIGHT) {
		this.P1RIGHT = P1RIGHT;
	}
	public int getP1RIGHT() {
		return P1RIGHT;
	}
	public void setP1JUMP(int P1JUMP) {
		this.P1JUMP =  P1JUMP;
	}
	public int getP1JUMP() {
		return P1JUMP;
	}
	public void setP1ACTION(int P1ACTION) {
		this.P1ACTION = P1ACTION;
	}
	public int getP1ACTION() {
		return P1ACTION;
	}
	public void setP1PAUSE(int P1PAUSE) {
		this.P1PAUSE = P1PAUSE;
	}
	public int getP1PAUSE() {
		return P1PAUSE;
	}
	public void setP2UP(int P2UP) {
		this.P2UP = P2UP;
	}
	public int getP2UP() {
		return P2UP;
	}
	public void setP2DOWN(int P2DOWN) {
		this.P2DOWN = P2DOWN;
	}
	public int getP2DOWN() {
		return P2DOWN;
	}
	public void setP2LEFT(int P2LEFT) {
		this.P2LEFT = P2LEFT;
	}
	public int getP2LEFT() {
		return P2LEFT;
	}
	public void setP2RIGHT(int P2RIGHT) {
		this.P2RIGHT = P2RIGHT;
	}
	public int getP2RIGHT() {
		return P2RIGHT;
	}
	public void setP2JUMP(int P2JUMP) {
		this.P2JUMP =  P2JUMP;
	}
	public int getP2JUMP() {
		return P2JUMP;
	}
	public void setP2ACTION(int P2ACTION) {
		this.P2ACTION = P2ACTION;
	}
	public int getP2ACTION() {
		return P2ACTION;
	}
	public void setP2PAUSE(int P2PAUSE) {
		this.P2PAUSE = P2PAUSE;
	}
	public int getP2PAUSE() {
		return P2PAUSE;
	}
}
