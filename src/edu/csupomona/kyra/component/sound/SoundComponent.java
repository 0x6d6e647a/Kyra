/**************************************************************
 *	file:		SoundComponent.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for sound
**************************************************************/
package edu.csupomona.kyra.component.sound;

import edu.csupomona.kyra.component.Component;

public abstract class SoundComponent extends Component {
	public SoundComponent(String id) {
		super(id);
	}
	
	public void stopAll() {
		
	}
}
