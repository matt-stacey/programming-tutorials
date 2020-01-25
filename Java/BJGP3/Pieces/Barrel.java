// simple visual applet for shooting at shit
// based on Roids from BJGP3

package Pieces;

import java.awt.Rectangle;

// Bullet class - polygonal shape of the barrel
public class Barrel extends BaseShape {
	
	// default constructor
	public Barrel() {
		// create bullet shape
		setShape(new Rectangle(0, 0, 6, 30)); // 5 wide, 30 high
		setAlive(true);
	}
}