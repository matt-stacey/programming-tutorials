// BJGP3
// RandomPolygons, p 102

package Part2;

import java.awt.*;
import java.applet.*;
import java.util.*;
import java.awt.geom.*;

public class RandomPolygons extends Applet {
	
	private int[] xpoints = {  0, -10, -7,  7, 10};
	private int[] ypoints = {-10,  -2, 10, 10, -2};
	
	// polygon shape for drawing
	private Polygon poly;
	
	// applet init
	public void init() {
		poly = new Polygon(xpoints, ypoints, xpoints.length);
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
		
		for (int n = 0; n < 300; n++) {
			// reset to identity
			g2d.setTransform(identity);
			
			// move/rotate/scale randomly
			g2d.translate(rand.nextInt() % width, rand.nextInt() % height);
			g2d.rotate(Math.toRadians(360 * rand.nextDouble()));
			g2d.scale(5 * rand.nextDouble(), 5 * rand.nextDouble());
			
			// draw shape with random color
			g2d.setColor(new Color(rand.nextInt()));
			g2d.fillPolygon(poly);
		}
	}
}
