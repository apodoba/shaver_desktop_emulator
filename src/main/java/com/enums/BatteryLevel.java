package com.enums;

public enum BatteryLevel {
	MAX(9), MIN(3), OFF(1);

	private int value;

	private BatteryLevel(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
