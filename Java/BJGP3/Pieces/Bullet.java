// simple visual applet for shooting at shit
// based on Roids from BJGP3

package Pieces;

import java.awt.Rectangle;

// Bullet class - polygonal shape of a bullet
public class Bullet extends BaseShape {
	
	// bounding rectangle
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), 1, 1);
		return r;
	}
	
	// default constructor
	public Bullet() {
		// create bullet shape
		setShape(new Rectangle(0, 0, 1, 1));
		setAlive(false);
	}
}