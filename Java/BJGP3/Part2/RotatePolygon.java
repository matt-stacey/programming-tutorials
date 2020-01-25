// BJGP3
// RotatePolygon, p104

package Part2;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.awt.geom.*;

public class RotatePolygon extends Applet implements KeyListener, MouseListener, MouseMotionListener {
	
	private int[] xpoints = {  0, -10, -7,  7, 10};
	private int[] ypoints = {-10,  -2, 10, 10, -2};
	
	private int[] starx = {-7,   0,  7, -10, 10};
	private int[] stary = {10, -10, 10,  -2, -2};
	
	// polygon shape for drawing
	private Polygon poly, star;
	
	// rotation variable
	int rotation = 0;
	int xpos = 0;
	int ypos = 0;
	
	// applet init
	public void init() {
		poly = new Polygon(xpoints, ypoints, xpoints.length);
		star = new Polygon(starx, stary, starx.length);
		
		// initialize listeners
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	// applet painting
	public void paint(Graphics g) {
		// force g2d
		Graphics2D g2d = (Graphics2D) g;
		
		// save identity
		AffineTransform identity = new AffineTransform();
		
		// create random #s
		Random rand = new Random();
		
		// save window width/height
		int width = getSize().width;
		int height = getSize().height;
		
		// black background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		// show data
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Courier New", Font.PLAIN, 16));
		g2d.drawString("Rotation angle: " + rotation, 20, 20);
		
		// move/rotate/scale
		g2d.translate(width / 2, height / 2);
		g2d.scale(20, 20);
		g2d.rotate(Math.toRadians(rotation));
		
		// draw shape with border
		g2d.setColor(Color.RED);
		g2d.fill(poly);
		g2d.setColor(Color.BLUE);
		g2d.draw(poly);
		
		// draw star shape
		g2d.setColor(Color.WHITE);
		g2d.fill(star);
	}
	
	// handle keyboard event
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k) {}
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			rotation -= 5;
			if (rotation < 0)
				rotation += 360;
			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			rotation += 5;
			if (rotation >= 360)
				rotation -= 360;
			repaint();
			break;
		default:
			repaint();
		}
	}
	
	// handle mouse button events
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mouseReleased(MouseEvent m) {}
	public void mouseClicked(MouseEvent m) {}
	public void mousePressed(MouseEvent m) {
		switch(m.getButton()) {
		case MouseEvent.BUTTON1:
			rotation -= 5;
			if (rotation < 0)
				rotation += 360;
			repaint();
			break;
		case MouseEvent.BUTTON3:
			rotation += 5;
			if (rotation >= 360)
				rotation -= 360;
			repaint();
			break;
		default:
			repaint();
		}
	}
	
	// handle mouse motion events
	public void mouseDragged(MouseEvent m) {}
	public void mouseMoved(MouseEvent m) {
		xpos = m.getX(); // get mouse location
        ypos = m.getY();
        
        double x = xpos - (getSize().width / 2); // to calculate angular position
        double y = ypos - (getSize().height / 2);
        
        rotation = (int) Math.toDegrees(Math.atan2(y, x)) + 90; // take arc tangent; add 90 and make degrees
        
        repaint();
	}
}
