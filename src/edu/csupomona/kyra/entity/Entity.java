/**************************************************************
 *	file:		Entity.java
 *	author:		Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/30/2011
 *
 *	purpose: This interface defines the basic fields that any 
 *	entity in the game engine should have.
**************************************************************/


package edu.csupomona.kyra.entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.ai.AIComponent;
import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.PhysicsComponent;
import edu.csupomona.kyra.component.render.RenderComponent;
import edu.csupomona.kyra.component.sound.SoundComponent;

public class Entity {
	String id;
	
	Vector2f position;
	float scale;
	float rotation;
	
	RenderComponent renderComponent = null;
	PhysicsComponent physicsComponent = null;
	InputComponent inputComponent = null;
	AIComponent aiComponent = null;
	SoundComponent soundComponent = null;
	
	
	protected ArrayList<Component> components = null;
	
	public Entity(String id) {
		this.id = id;
		
		components = new ArrayList<Component>();
		
		position = new Vector2f(0, 0);
		scale = 1;
		rotation = 0;
	}
	
	public void addComponent(Component component) {
		if (RenderComponent.class.isInstance(component))
			renderComponent = (RenderComponent)component;
		else if (PhysicsComponent.class.isInstance(component))
			physicsComponent = (PhysicsComponent)component;
		else if (InputComponent.class.isInstance(component))
			inputComponent = (InputComponent)component;
		else if (SoundComponent.class.isInstance(component))
			soundComponent = (SoundComponent)component;
		else if (AIComponent.class.isInstance(component))
			aiComponent = (AIComponent)component;
		
		component.setOwnerEntity(this);
		components.add(component);
	}
	
	public Component getComponent(String id) {
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				return comp;
		}
		return null;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public float getScale() {
		return scale;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public void setRotation(float rotate) {
		rotation = rotate;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public PhysicsComponent getPhysicsComponent() {
		return physicsComponent;
	}
	
	public InputComponent getInputComponent() {
		return inputComponent;
	}
	
	public SoundComponent getSoundComponent() {
		return soundComponent;
	}
	
	public AIComponent getAIComponent() {
		return aiComponent;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (Component component : components) {
			component.update(gc, sb, delta);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		if (renderComponent != null)
			renderComponent.render(gc, sb, gr);
	}
}