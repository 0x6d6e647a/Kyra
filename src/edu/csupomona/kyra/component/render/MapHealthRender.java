/**************************************************************
 *	file:		MapHealthRender.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Renders the players health at the top of the screen
**************************************************************/
package edu.csupomona.kyra.component.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.health.HealthComponent;
import edu.csupomona.kyra.entity.Entity;

public class MapHealthRender extends RenderComponent {

	float xPos, yPos;
	Entity player1, player2;
	HealthComponent hc;
	
	public MapHealthRender(String id, Entity player1, Entity player2) {
		super(id);
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {	
		gr.setColor(Color.darkGray);
		gr.fillRect(xPos, yPos, gc.getWidth(), 25);
		gr.setColor(Color.white);
		hc = player1.getHealthComponent();
		gr.drawString("Player 1 - HEALTH: " + hc.getCurrHealth() + "/" + hc.getMaxHealth(), xPos+50, yPos+5);
		//gr.drawString("POINTS: ", xPos+275, yPos+5);
		if(player2 != null) {
			hc = player2.getHealthComponent();
			gr.drawString("Player 2 - HEALTH: " + hc.getCurrHealth() + "/" + hc.getMaxHealth(), xPos+gc.getWidth()-275, yPos+5);
			//gr.drawString("Player 2 - HEALTH: " + hc.getCurrHealth() + "/" + hc.getMaxHealth(), xPos+gc.getWidth()-400, yPos+5);
			//gr.drawString("POINTS: ", xPos+gc.getWidth()-175, yPos+5);
		}
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(!player1.getHealthComponent().isDead()) {
			xPos = player1.getPosition().x - gc.getWidth()/2;
			yPos = player1.getPosition().y - gc.getHeight()/2;
		} else {
			if(Kyra.vs) {
				if(!player2.getHealthComponent().isDead()) {
					xPos = player2.getPosition().x - gc.getWidth()/2;
					yPos = player2.getPosition().y - gc.getHeight()/2;
				}
			}
		}
	}

}
