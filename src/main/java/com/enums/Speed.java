package com.enums;

public enum Speed {
	MAX(100), MIN(0);

	private int value;

	private Speed(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
