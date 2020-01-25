// simple visual applet for shooting at shit
// based on Roids from BJGP3

package Pieces;

import java.awt.*;

// Debris class - target debris on bullet impact
public class Debris extends BaseShape {
	
	// define the debris polygon - roughly an equlateral triangle
	private int[] debx = {-1, 0, 1};
	private int[] deby = {-1, 1, -1};
			
	// rotation speed
	protected double rotVel;
	public double getRotationVelocity() { return rotVel; }
	public void setRotationVelocity(double v) { rotVel = v; }
	
	// lifetime
	protected int lifeCounts;
	public int getLife() { return lifeCounts; }
	public void setLife() { lifeCounts = 100; }
	public void decLife() { lifeCounts -= 1; }
	
	// scaling for random-looking pieces
	private double xscl, yscl;
	public void setScales(double x, double y) { xscl = x; yscl = y; }
	public double getXScale() { return xscl; }
	public double getYScale() { return yscl; }
	
	// coloring
	private Color color = Color.BLACK;
	public void newColor(int x) { this.color = new Color(x); }
	public Color getColor() { return this.color; }
	
	// default constructor
	public Debris() {
		// create debris shape
		setShape(new Polygon(debx, deby, debx.length));
		setAlive(false);
		setRotationVelocity(0.0);
	}
}