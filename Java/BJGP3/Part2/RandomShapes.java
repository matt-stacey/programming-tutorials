// BJGP3
// RandomShapes, p98

package Part2;

import java.awt.*;
import java.applet.*;
import java.awt.geom.*;
import java.util.*;

public class RandomShapes extends Applet {
	private Shape shape;
	
	// init applet
	public void init() {
		shape = new Rectangle2D.Double(-1.0, -1.0, 1.0, 1.0);
	}
	
	// applet paint
	public void paint(Graphics g) {
		// use Graphics2D
		Graphics2D g2d = (Graphics2D)g;
		
		// save identity transform
		AffineTransform identity = new AffineTransform();
		
		// rando
		Random rand = new Random();
		
		// save window width/height
		int width = getSize().width;
		int height = getSize().height;
		
		// fill with black
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		for (int n = 0; n < 300; n++) {
			// reset to identity transform
			g2d.setTransform(identity);
			
			// move/rotate/scale shape randomly
			g2d.translate(rand.nextInt() % width, rand.nextInt() % height);
			g2d.rotate(Math.toRadians(360 * rand.nextDouble()));
			g2d.scale(60 * rand.nextDouble(), 60 * rand.nextDouble());
			
			// draw random shape
			g2d.setColor(new Color(rand.nextInt()));
			g2d.fill(shape);
		}
	}
}
