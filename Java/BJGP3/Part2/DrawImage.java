// BJGP3
// DrawImage, p 110

package Part2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.*;
import javax.swing.*;
import java.net.*;

public class DrawImage extends JFrame implements KeyListener {
	private Image image;
	
	public static void main(String args[]) {
		new DrawImage();
	}
	
	// identity transform
	AffineTransform identity = new AffineTransform();
	AffineTransform trans = new AffineTransform();
	
	public DrawImage() {
		super("DrawImage");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		image = tk.getImage(getURL("castle.png"));
		addKeyListener(this);
	}

	private URL getURL(String filename) {
		URL url = null;
		try {
			url = this.getClass().getResource(filename);
		}
		catch (Exception e) {}
		return url;
	}
	
	public void paint(Graphics g) {
		// create g2d instance
		Graphics2D g2d = (Graphics2D) g;
		
		// black background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		
		// draw
		g2d.drawImage(image, trans, this);
	}
	
	// key listener events
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k) {}
	public void keyPressed(KeyEvent k) {
		int keyCode = k.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_EQUALS:
			trans.scale(1/0.9, 1/0.9);
			repaint();
			break;
		case KeyEvent.VK_MINUS:
			trans.scale(0.9, 0.9);
			repaint();
			break;
		}
	}
}