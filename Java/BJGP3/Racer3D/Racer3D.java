// Racer3D
// Matt Stacey
// 29 Nov 2012

package Racer3D;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class Racer3D extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	// flags
	public static boolean showBounds = false; // toggle for drawing hitboxes
	public static boolean statusPrint = false; // toggle for status printout
	public static boolean proximityFuse = true; // prox fuse for missiles
	public static boolean invincible = false; // invincible cities and launchers

	// constants
	public static int APPLET_WIDTH = 800; // applet size
	public static int APPLET_HEIGHT = 600;
	public static double FPS = 100.0; // target framerate
	public static Color OUTLINE = Color.YELLOW; // outline color
	
	// main thread becomes game loop
	Thread gameloop;
	
	// use this as double buffer (reduces flicker)
	BufferedImage backbuffer;
	
	// the main drawing object for the buffer
	Graphics2D g2d;
	
	// mouse position
	int xpos = 0;
	int ypos = 0;
	
	// create identity transform
	AffineTransform identity = new AffineTransform();
	
	// create random number generator
	Random rand = new Random();
	
	// track stats
	

	// create arrays
	
	
	// applet init event - creates double buffer/initializes objects/listeners start
	public void init() {
		
		this.setMaximumSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT)); // resize applet
		this.setMinimumSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT));
		this.resize(APPLET_WIDTH, APPLET_HEIGHT);
		
		// create the back buffer
		backbuffer = new BufferedImage(APPLET_WIDTH, APPLET_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		// set up arrays
		
		
		// start listeners
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	// applet update to redraw screen (updates written to buffer, then buffer written to screen)
	public void update(Graphics g) {
		// start off transforms at identity
		g2d.setTransform(identity);
		
		// black background
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, APPLET_WIDTH, APPLET_HEIGHT);
		
		// draw game graphics to buffer, bottom to top
		
		
		// print some status info
		if (statusPrint) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			
			
		}
		
		// print stats if status info is off
		if (!statusPrint) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			
			
		}
		
		// repaint applet window
		paint(g);		
	}
	
	// drawXXX called by applet
	
	
	// draw backbuffer to applet
	public void paint(Graphics g) {
		// draw backbuffer on applet window
		g.drawImage(backbuffer, 0, 0, this);
	}
	
	// thread start event - start game loop running
	public void start() {
		// create game loop for real-time updates
		gameloop = new Thread(this);
		gameloop.start();
	}
	
	// thread run event - game loop
	public void run() {
		// acquire current thread
		Thread t = Thread.currentThread();
		
		// keep going while thread is alive
		while (t == gameloop) {
			try {
				// update game loop
				gameUpdate();
				
				Thread.sleep((long) (1000.0 / FPS)); //milliseconds
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	// thread stop event
	public void stop() {
		// kill game loop
		gameloop = null;
	}
	
	// move/animate objects
	private void gameUpdate() {
		
		
		// resize applet to full dimensions
		this.resize(APPLET_WIDTH, APPLET_HEIGHT);
	}
	
	// updateXXX
	
	
	// handle key press events
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k) {}
	public void keyPressed(KeyEvent k) {}
	
	// handle mouse button events
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mouseReleased(MouseEvent m) {}
	public void mouseClicked(MouseEvent m) {}
	public void mousePressed(MouseEvent m) {}
		
	// handle mouse motion events
	public void mouseDragged(MouseEvent m) {}
	public void mouseMoved(MouseEvent m) {}
	
	// angular transforms
	
} // feed me a cat