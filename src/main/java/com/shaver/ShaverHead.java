package com.shaver;

import com.enums.Speed;

public class ShaverHead {
	
	// movement speed
	private int speed;
	// shaver head type
	private String type;

	public double getSpeed() {
		return speed;
	}

	protected void setSpeed(Speed speed) {
		this.speed = speed.getValue();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
