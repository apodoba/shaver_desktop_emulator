package com.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private static final long serialVersionUID = -5801507804352086340L;
	private BufferedImage image;

    public ImagePanel(URL path) {
       try {                
          image = ImageIO.read(path);
          this.setSize(image.getWidth(), image.getHeight());
       } catch (IOException ex) {
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);   
    }

}
