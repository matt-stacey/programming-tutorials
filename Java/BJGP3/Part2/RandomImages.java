// BJGP3
// RandomImages, p 112

package Part2;

import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.awt.geom.*;
import java.net.*;

public class RandomImages extends JFrame {
	private Image[] image = new Image[2];
	
	public static void main(String[] args) {
		new RandomImages();
	}
	
	// applet init
	public RandomImages() {
		super("RandomImages");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		image[0] = tk.getImage(getURL("spaceship.png"));
		image[1] = tk.getImage(getURL("asteroid2.png"));
	}
	
	// identity transform
	AffineTransform identity = new AffineTransform();
	
	private URL getURL(String filename) {
		URL url = null;
		try {
			url = this.getClass().getResource(filename);
		}
		catch (Exception e) {}
		return url;
	}
	
	// applet paint
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// working transform
		AffineTransform trans = new AffineTransform();
		
		Random rand = new Random();
		
		// applet size
		int width = getSize().width;
		int height = getSize().height;
		
		// black background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		
		// draw image
		for (int m = 0; m < 2; m++) {
			for (int n = 0; n < 50; n++) {
				trans.setTransform(identity);
				// move/rotate/scale
				trans.translate(rand.nextInt()%width, rand.nextInt()%height);
				trans.rotate(Math.toRadians(360 * rand.nextDouble()));
				double scale = rand.nextDouble() + 1;
				trans.scale(scale, scale);
			
				g2d.drawImage(image[m], trans, this);
			}
		}
	}
}