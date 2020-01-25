// simple visual applet for shooting at shit
// based on Roids from BJGP3

package Pieces;

import java.awt.Rectangle;

// Target class - for target/bullet collision detection
public class Target extends BaseShape {
	
	private int width, height;
	
	// bounding rectangle
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight());
		return r;
	}
	
	// accessors/mutators
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }

	public Target() {
		this.setWidth(10);
		this.setHeight(10);
		setShape(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
		setAlive(true);
	}
}
