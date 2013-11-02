package com.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.enums.BatteryLevel;
import com.enums.Color;
import com.shaver.Battery;
import com.shaver.Shaver;
import com.shaver.ShaverHead;

// comment from branch2
public class ShaverWindow extends JFrame {

	private static final long serialVersionUID = 7930087728275607544L;

	private static final String GREEN_BUTTON = "green_button.png";
	private static final String RED_BUTTON = "red_button.png";
	private static final String HEAD = "head.png";
	private static final String POWER_BUTTON = "powerButton.png";
	private static final String SHAVER = "shaver.png";
	private static final String WIRE = "wire.png";
	private ImagePanel imagePanel;
	private ImagePanel imageRedPanel;
	private ImagePanel imageGreenPanel;
	private ImagePanel imagePowerPanel;
	private ImagePanel imageWirePanel;
	private ImagePanel imageHeadPanel;
	private Timer timer;
	private Shaver shaver;

	public ShaverWindow(final Shaver shaver) {
		super("Java Shaver");
		
		this.shaver = shaver;
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(new FrameMouseListener());

		initData();
		constructPanels();
	}
	
	private void initData(){
		timer = new Timer(100, new ShaverHeaderMoveTimeListener());
		ClassLoader classLoader = getClass().getClassLoader();

		imagePanel = new ImagePanel(classLoader.getResource(SHAVER));
		imagePanel.setLayout(null);
		this.setSize(imagePanel.getSize());
		
		imageRedPanel = new ImagePanel(classLoader.getResource(RED_BUTTON));
		imageRedPanel.setBounds(261, 205, 18, 18);
		imageRedPanel.setVisible(false);
	
		imageGreenPanel = new ImagePanel(classLoader.getResource(GREEN_BUTTON));
		imageGreenPanel.setBounds(261, 205, 18, 18);
		imageGreenPanel.setVisible(false);

		imagePowerPanel = new ImagePanel(classLoader.getResource(POWER_BUTTON));
		imagePowerPanel.setBounds(138, 189, 78, 52);
		imagePowerPanel.setVisible(false);
		
		imageWirePanel = new ImagePanel(classLoader.getResource(WIRE));
		imageWirePanel.setBounds(351, 44, 231, 188);
		imageWirePanel.setVisible(false);
		
		imageHeadPanel = new ImagePanel(classLoader.getResource(HEAD));
		imageHeadPanel.setBounds(22, 157, 70, 116);
		imageHeadPanel.setVisible(true);
	}
	
	private void constructPanels(){
		timer.start();
		add(imagePanel);
		imagePanel.add(imageRedPanel);
		imagePanel.add(imageWirePanel);
		imagePanel.add(imagePowerPanel);
		imagePanel.add(imageGreenPanel);
		imagePanel.add(imageHeadPanel);
	}
	
	private class FrameMouseListener extends MouseAdapter { 

		public void mouseClicked(MouseEvent e) {
			
			// power button clicked
			if (e.getX() > 138 && e.getX() < 215 && e.getY() > 215 && e.getY() < 265) {
				imagePowerPanel.setVisible(!imagePowerPanel.isVisible());
				if (!shaver.getPowerButton().isOn()) {
					shaver.turnOn();
				} else {
					shaver.turnOff();
				}
			}
			
			// socket clicked
			if (e.getX() > 485 && e.getX() < 556 && e.getY() > 70 && e.getY() < 140) {
				if (!shaver.isCharging()) {
					shaver.getBattery().setBatteryLevel(BatteryLevel.MAX.getValue());
					shaver.startCharging();
					imageWirePanel.setVisible(true);
				}else{
					shaver.stopCharging();
					imageWirePanel.setVisible(false);
				}
			}
		}
	}

	private class ShaverHeaderMoveTimeListener implements ActionListener {
		private boolean isTopHeadPosition = false;

		public void actionPerformed(ActionEvent e) {
			if (shaver.isWork()) {
				int y = isTopHeadPosition ? 158 : 159;
				isTopHeadPosition = !isTopHeadPosition;
				imageHeadPanel.setLocation(22, y);
				imageGreenPanel.setVisible(true);
				
				if (shaver.getDiode().getColor() == Color.RED) {
					imageGreenPanel.setVisible(false);
					imageRedPanel.setVisible(true);
				} else if (shaver.getDiode().getColor() == Color.GREEN) {
					imageGreenPanel.setVisible(true);
					imageRedPanel.setVisible(false);
				}
			} else {
				imageHeadPanel.setLocation(22, 157);
				imageHeadPanel.setVisible(true);
				imageGreenPanel.setVisible(false);
				imageRedPanel.setVisible(false);
			}
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ShaverWindow(new Shaver(new Battery(), new ShaverHead()));
			}
		});
	}
}
