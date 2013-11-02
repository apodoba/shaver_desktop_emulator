package com.shaver;

public class Button {
	
	// button on - state=true
	// button off - state=false
	private boolean state = false;

	public boolean isOn() {
		return state;
	}

	public void setOn() {
		this.state = true;
	}
	
	public void setOff() {
		this.state = false;
	}

}
