package com.shaver;

import com.enums.BatteryLevel;

public class Battery {

	private int batteryLevel = BatteryLevel.MAX.getValue();

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
	public boolean isBatteryNotEmpty() {
		return this.batteryLevel >= BatteryLevel.OFF.getValue() ? true : false;
	}
	
	public void decreaseLevel(int decreaseValue){
		this.batteryLevel -= decreaseValue;
	}

}
