package com.shaver;

import com.enums.BatteryLevel;
import com.enums.Color;
import com.enums.Speed;

public class Shaver {
	
	private final static int DECREASE = 1; 

	private Diode diode = new Diode();

	private Button powerButton = new Button();

	private ShaverHead shaverHead;
	private Battery battery;
	private BatteryIteration batteryIteration;
	private boolean isCharging;

	public Shaver(Battery battery, ShaverHead nozzle) {
		this.battery = battery;
		this.shaverHead = nozzle;
	}

	// if the battery is low and shaver doesn't start working
	public void turnOn() {
		powerButton.setOn();
		shaverHead.setSpeed(Speed.MAX);
		if (battery.isBatteryNotEmpty()) {
			batteryIteration = new BatteryIteration();
		} 
	}

	public void turnOff() {
		powerButton.setOff();
		diode.setColor(Color.NONE);
		if(batteryIteration != null){
			batteryIteration.thread.interrupt();
		}
	}
	
	public void startCharging(){
		battery.setBatteryLevel(BatteryLevel.MAX.getValue());
		isCharging = true;
		if (powerButton.isOn() && batteryIteration != null && !batteryIteration.thread.isAlive())
			batteryIteration = new BatteryIteration();
		
	}
	
	public void stopCharging(){
		isCharging = false;
	}

	public boolean isWork() {
		return battery.isBatteryNotEmpty() && powerButton.isOn();
	}
	
	public Battery getBattery() {
		return battery;
	}
	
	public Button getPowerButton() {
		return powerButton;
	}

	public boolean isCharging() {
		return isCharging;
	}
	
	public Diode getDiode() {
		return diode;
	}

	private class BatteryIteration implements Runnable {
		Thread thread;

		private BatteryIteration() {
			thread = new Thread(this);
			thread.start();
		}

		public void run() {
			while (battery.isBatteryNotEmpty()) {
				if(!isCharging){
					battery.decreaseLevel(DECREASE);
				}
				if (battery.getBatteryLevel() > BatteryLevel.MIN.getValue()) {
					diode.setColor(Color.GREEN);
				} else {
					diode.setColor(Color.RED);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// thread was interrupted, exit thread
					break;
				}
			}
			diode.setColor(Color.NONE);
		}
	}
}
