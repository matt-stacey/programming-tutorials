// BJGP3
// Bullet, p76

package Roids;

import java.awt.*;

// Bullet class - polygonal shape of a bullet
public class Bullet extends BaseVectorShape {
	
	private int lifecounts;
	
	// bounding rectangle
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), 1, 1);
		return r;
	}
	
	public int getLifecounts() { return this.lifecounts; }
	public void decLifecounts() { this.lifecounts--; }
	public void setLifecounts(int counts) { this.lifecounts = counts; }
	
	// default constructor
	public Bullet() {
		// create bullet shape
		setShape(new Rectangle(0, 0, 1, 1));
		setAlive(false);
	}
}
