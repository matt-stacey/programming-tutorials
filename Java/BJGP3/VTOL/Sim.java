// VTOL UAV Project
// Motion/Control Simulation Applet
// Matt Stacey
// 15 Dec 2012

package VTOL;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

// Primary class for game
public class Sim extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	
	// flags
	public static boolean statusPrint = true; // toggle for status printout

	// constants
	public static int APPLET_WIDTH = 1000; // applet size
	public static int APPLET_HEIGHT = 800;
	public static double FPS = 100.0; // target framerate
	
	// main thread becomes game loop
	Thread gameloop;
	
	// use this as double buffer (reduces flicker)
	BufferedImage backbuffer;
	
	// the main drawing object for the buffer
	Graphics2D g2d;
	
	// mouse position
	int m_xpos = 0;
	int m_ypos = 0;
	
	// create identity transform
	AffineTransform identity = new AffineTransform();
	
	// create random number generator
	Random rand = new Random();
	
	// create UAV
	UAV uav = new UAV();
	
	// point array to test
	int POINTS = 10;
	int[][] points = new int[POINTS][2];
	
	// applet init event - creates double buffer/initializes objects/listeners start
	public void init() {
		
		this.setMaximumSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT)); // resize applet
		this.setMinimumSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT));
		this.resize(APPLET_WIDTH, APPLET_HEIGHT);
		
		// create the back buffer
		backbuffer = new BufferedImage(APPLET_WIDTH, APPLET_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		// start listeners
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		// points
		for (int n = 0; n < POINTS; n++) {
			points[n][0] = rand.nextInt(500);
			points[n][1] = rand.nextInt(500);
		}
	}
	
	// applet update to redraw screen (updates written to buffer, then buffer written to screen)
	public void update(Graphics g) {
		// start off transforms at identity
		g2d.setTransform(identity);
		
		// black background
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		
		// draw graphics to buffer, bottom to top
		drawPosition(); //upper left
		drawPositionRates(); //lower left
		drawAngles(); //need to think how to
		drawAngleRates(); //lower right
		drawPower(); //bottom left
		drawFlightControls(); //bottom middle
		drawHCS(); //bottom right
		drawAxes(0); //upper
		drawAxes(1); //lower
		drawControls(); //bottom
		drawFrames();
		
		// print some status info
		if (statusPrint) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			
			// random points
			g2d.setColor(Color.BLUE);
			g2d.drawString("Points", 5, 515);
			for (int n=0; n<POINTS; n++){
				g2d.drawString(n + ": " + points[n][0] + ", " + points[n][1], 5, 530 + (15*n));
				g2d.drawRect(points[n][0], points[n][1], 1, 1);
				g2d.drawString(points[n][0] + ", " + points[n][1], points[n][0] + 5, points[n][1] + 5);
			}
			
			// mouse follower
			g2d.setColor(Color.MAGENTA);
			g2d.drawString(m_xpos + ", " + m_ypos, m_xpos +10, m_ypos -20);
			g2d.drawString(m_xpos + ", " + m_ypos, m_xpos +10, m_ypos +20);
			g2d.drawString(m_xpos + ", " + m_ypos, m_xpos -50, m_ypos -20);
			g2d.drawString(m_xpos + ", " + m_ypos, m_xpos -50, m_ypos +20);
			
		}
		
		// repaint applet window
		paint(g);		
	}
	
	// draw angular positions (upper/left)
	public void drawAngles() {
		double theta = uav.getTheta();
		double psi = uav.getPsi();
		double phi = uav.getPhi();
	}
	
	// draw angular rates (lower/left)
	public void drawAngleRates() {
		double thetaD = uav.getThetaD();
		double psiD = uav.getPsiD();
		double phiD = uav.getPhiD();
	}
	
	// draw positions (upper/right)
	public void drawPosition() {
		double X = uav.getX();
		double Y = uav.getY();
		double Z = uav.getZ();
	}
	
	// draw velocities (lower/right)
	public void drawPositionRates() {
		double velX = uav.getVelX();
		double velY = uav.getVelY();
		double velZ = uav.getVelZ();
	}
	
	// draw power settings (bottom left)
	public void drawPower() {
//		double[] power = uav.getPower();
		
	}
	
	// draw flight control positions (bottom middle)
	public void drawFlightControls() {
//		double[] fcs = uav.getFlightControls();
		
	}
	
	// draw rcs power (bottom right)
	public void drawHCS() {
//		double[] hcs = uav.getHoverControls();
//		double[] noz = uav.getNozzles();
	}
	
	// draw axes (upper and lower)
	public void drawAxes(int array) {
		g2d.setTransform(identity);
		g2d.setColor(Color.WHITE);
		
		int shift = 285*array;
		
		// frame
		g2d.drawLine(500, shift, 999, shift);
		
		// linear variables on left (649, 142+shift)_
		g2d.drawRect(643, 142+shift, 12, 101); //z+: down
		g2d.drawRect(643, 41+shift, 12, 101); //z-: up
		g2d.drawRect(649, 136+shift, 101, 12); //y+: right
		g2d.drawRect(548, 136+shift, 101, 12); //y-: left
		
		g2d.translate(649, 142+shift); //rotate to get x axis
		g2d.rotate(Math.toRadians(-45.0)); //pointing "forward"
		g2d.translate(0, -6); //and draw
		g2d.drawRect(0, 0, 101, 12); //x+: forward
		g2d.drawRect(-101, 0, 101, 12); //x-: back
		g2d.setTransform(identity); //and reset transform
		
		// angular variables on right (851, 107+shift)
		g2d.drawLine(774, 184+shift, 774, 284+shift); //z axis: down
		g2d.drawLine(774, 184+shift, 963, 184+shift); //y axis: right
		g2d.drawLine(774, 184+shift, 851, 107+shift); //x axis: forward
		g2d.drawRect(750, 101+shift, 101, 12); //yaw left
		g2d.drawRect(851, 101+shift, 101, 12); //yaw right
		g2d.drawRect(845, 6+shift, 12, 101); //pitch up
		g2d.drawRect(845, 107+shift, 12, 101); //pitch down
		g2d.drawRect(952, 83+shift, 12, 101); //roll left (up)
		g2d.drawRect(952, 184+shift, 12, 101); //roll right (down)
		
		// labels!
		g2d.setColor(Color.ORANGE);
		g2d.drawString("x+", 713, 58+shift);
		g2d.drawString("x-", 573, 238+shift);
		g2d.drawString("y+", 728, 132+shift);
		g2d.drawString("y-", 545, 132+shift);
		g2d.drawString("z+", 642, 263+shift);
		g2d.drawString("z-", 642, 32+shift);
		g2d.drawString("P+", 865, 21+shift);
		g2d.drawString("P-", 865, 209+shift);
		g2d.drawString("R+", 969, 260+shift);
		g2d.drawString("R-", 969, 100+shift);
		g2d.drawString("Y+", 911, 92+shift);
		g2d.drawString("Y-", 751, 92+shift);
		switch(array) {
		case 0: //upper
			g2d.drawString("Positions", 505, 15+shift);
			g2d.drawString("m", 580, 100+shift);
			g2d.drawString("deg", 880, 160+shift);
			break;
		case 1: //lower
			g2d.drawString("Rates", 505, 15+shift);
			g2d.drawString("m/s", 580, 100+shift);
			g2d.drawString("deg/s", 880, 160+shift);
			break;
		default:
			break;
		}
	}
	
	// draw control axes (bottom)
	public void drawControls() {
		g2d.setTransform(identity);
		g2d.setColor(Color.WHITE);
		
		// frame
		g2d.drawLine(500, 570, 999, 570);
		
		// power boxes 799-570:230  ([500/548], 799-%*2.3)
		g2d.drawRect(500, 570, 48, 230); //left: lift/cruise
		g2d.drawRect(548, 570, 47, 230); //right: lift
		
		// ailerons (595, 671) and (746, 671)
		g2d.drawRect(595, 570, 51, 101); //left up
		g2d.drawRect(595, 671, 51, 101); //left down
		g2d.drawRect(746, 570, 51, 101); //right up
		g2d.drawRect(746, 671, 51, 101); //right down
		
		// elevators (646, 671)
		g2d.drawRect(646, 570, 100, 101); //up
		g2d.drawRect(646, 671, 100, 101); //down
		
		// rudders (696, 772)
		g2d.drawRect(595, 772, 101, 27); //left
		g2d.drawRect(696, 772, 101, 27); //right
		
		// hcs (898, 685)
		g2d.drawRect(797, 679, 101, 12); //left
		g2d.drawRect(898, 679, 101, 12); //right
		g2d.drawRect(892, 584, 12, 101); //forward
		g2d.drawRect(892, 685, 12, 101); //aft
		
		// labels!
		g2d.setColor(Color.ORANGE);
		g2d.drawString("L/C", 509, 595); //powers
		g2d.drawString("L", 557, 595);
		g2d.drawString("R left", 600, 790); //rudders
		g2d.drawString("R right", 750, 790);
		g2d.drawString("E up", 681, 595); //elevators
		g2d.drawString("E down", 681, 761);
		g2d.drawString("L up", 604, 595); //ailerons
		g2d.drawString("L dn", 604, 761);
		g2d.drawString("R up", 755, 595);
		g2d.drawString("R dn", 755, 761);
		g2d.drawString("Fwd", 915, 597); //RCS
		g2d.drawString("Aft", 915, 771);
		g2d.drawString("Left", 810, 670);
		g2d.drawString("Right", 955, 670);
	}
	
	// draw frame panels
	public void drawFrames() {
		g2d.setTransform(identity);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(500, 0, 500, 799);
		g2d.drawLine(0, 500, 500, 500);
	}
	
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
	
	// key listener events
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k) {}
	public void keyPressed(KeyEvent k) {
		int keyCode = k.getKeyCode();
	}
		
	// handle mouse button events
	public void mouseEntered(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
	public void mouseExited(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
	public void mouseReleased(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
	public void mouseClicked(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
	public void mousePressed(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
		
	// handle mouse motion events
	public void mouseDragged(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
	public void mouseMoved(MouseEvent m) {
		m_xpos = m.getX(); // get mouse location
		m_ypos = m.getY();
	}
	
	// calculate X movement based on angle
	public double calcAngleMoveX(double angle) {
		return (double) (Math.cos(angle * Math.PI / 180.0));
	}
	
	// calculate Y movement based on angle
	public double calcAngleMoveY(double angle) {
		return (double) (Math.sin(angle * Math.PI / 180.0));
	}
} // feed me a cat