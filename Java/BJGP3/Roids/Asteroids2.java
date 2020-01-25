// BJGP3
// Asteroids, p78

package Roids;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

// Primary class for game
public class Asteroids2 extends Applet implements Runnable, MouseListener, MouseMotionListener {
	
	// main thread becomes game loop
	Thread gameloop;
	
	// use this as double buffer (reduces flicker)
	BufferedImage backbuffer;
	
	// the main drawing object for the buffer
	Graphics2D g2d;
	
	// toggle for drawing hitboxes
	boolean showBounds = true;
	
	// mouse position
	int xpos, ypos;
	
	// create asteroid array
	int ASTEROIDS = 20;
	Asteroid[] ast = new Asteroid[ASTEROIDS];
	
	// create bullet array
	int BULLETS = 20;
	public static int BULLET_SPEED = 3;
	public static int LIFECOUNTS = 100;
	Bullet[] bullet = new Bullet[BULLETS];
	int currentBullet = 0;
	
	// create player ship
	Ship ship = new Ship();
	int thrust = 0;
	public static double SHIP_ACCELERATION = 0.1;
	
	// create identity transform (0, 0)
	AffineTransform identity = new AffineTransform();
	
	// create random number generator
	Random rand = new Random();
	
	// applet init event - creates double buffer/initializes ship and asteroids/key listener starts
	public void init() {
		// create the back buffer
		backbuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		// set up ship
		ship.setX(320); //dead center, facing north
		ship.setY(240);
		
		// set up bullets
		for (int n = 0; n < BULLETS; n++) {
			bullet[n] = new Bullet();
		}
		
		// create asteroids
		for (int n = 0; n < ASTEROIDS; n++) {
			ast[n] = new Asteroid();
			ast[n].setRotationVelocity(rand.nextInt(3)+1);
			ast[n].setX((double)rand.nextInt(600)+20);
			ast[n].setY((double)rand.nextInt(440)+20);
			ast[n].setMoveAngle(rand.nextInt(360));
			double ang = ast[n].getMoveAngle() - 90;
			ast[n].setVelX(calcAngleMoveX(ang));
			ast[n].setVelY(calcAngleMoveY(ang));
		}
		
		// start key listener
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	// applet update to redraw screen (updates written to buffer, then buffer written to screen)
	public void update(Graphics g) {
		// start off transforms at identity
		g2d.setTransform(identity);
		
		// black background
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		
		// print some status info
		g2d.setColor(Color.WHITE);
		g2d.drawString("Ship position: " + Math.round(ship.getX()) + ", " + Math.round(ship.getY()), 5, 10);
		g2d.drawString("Ship velocity: " + Math.round(Math.pow(Math.pow(ship.getVelX(), 2) + Math.pow(ship.getVelY(),2), 0.5)), 5, 25);
		g2d.drawString("Face angle: " + Math.round(ship.getFaceAngle()), 5, 40);
		g2d.drawString("Move angle: " + Math.round(ship.getMoveAngle() + 90), 5, 55);
		
		// draw game graphics
		drawShip();
		drawBullets();
		drawAsteroids();
		
		// repaint applet window
		paint(g);		
	}
	
	// drawShip called by applet event
	public void drawShip() {
		g2d.setTransform(identity); // uses ships local coordinate system
		g2d.translate(ship.getX(), ship.getY());
		g2d.rotate(Math.toRadians(ship.getFaceAngle()));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(ship.getShape());
	}
	
	// drawBullets called by applet event
	public void drawBullets() {
		// iterate through bullet array
		for (int n = 0; n < BULLETS; n++) {
			// is bullet in use?
			if (bullet[n].isAlive()) {
				g2d.setTransform(identity);
				g2d.translate(bullet[n].getX(), bullet[n].getY());
				g2d.setColor(Color.RED);
				g2d.draw(bullet[n].getShape());
			}
		}
	}
	
	// drawAsteroids called by applet event
	public void drawAsteroids() {
		// iterate through asteroid array
		for (int n = 0; n < ASTEROIDS; n++) {
			// is asteroid in use?
			if (ast[n].isAlive()) {
				g2d.setTransform(identity);
				g2d.translate(ast[n].getX(), ast[n].getY());
				g2d.rotate(Math.toRadians(ast[n].getFaceAngle()));
				g2d.setColor(Color.DARK_GRAY);
				g2d.fill(ast[n].getShape());
			}
		}
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
				
				// target FPS is 50
				Thread.sleep(20); //milliseconds
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
		updateShip();
		updateBullets();
		updateAsteroids();
		checkCollisions();
		this.resize(640, 480);
	}
	
	// update ship pos based on vel
	public void updateShip() {
		// update X/Y positions
		ship.incX(ship.getVelX());
		ship.incY(ship.getVelY());
		
		// update facing
		ship.setFaceAngle(Math.toDegrees(Math.atan2(ship.getY() - ypos, ship.getX() - xpos)) - 90);
		ship.setMoveAngle(ship.getFaceAngle() - 90);
		
		// update velocities
		ship.incVelX(calcAngleMoveX(ship.getMoveAngle()) * SHIP_ACCELERATION * thrust);
		ship.incVelY(calcAngleMoveY(ship.getMoveAngle()) * SHIP_ACCELERATION * thrust);
		
		// wrap L/R
		if (ship.getX() < -10)
			ship.setX(getSize().width + 10);
		else if (ship.getX() > getSize().width + 10)
			ship.setX(-10);
		
		// wrap top/bottom
		if (ship.getY() < -10)
			ship.setY(getSize().height + 10);
		else if (ship.getY() > getSize().height + 10)
			ship.setY(-10);
	}
	
	// update bullets based on vel
	public void updateBullets() {
		// for each bullet
		for (int n = 0; n < BULLETS; n++) {
			// is bullet in use?
			if (bullet[n].isAlive()) {
				// update X/Y positions
				bullet[n].incX(bullet[n].getVelX());
				bullet[n].incY(bullet[n].getVelY());
				
				// wrap at edges
				if (bullet[n].getX() < 0) { bullet[n].setX(bullet[n].getX() + getSize().width); }
				if (bullet[n].getX() > getSize().width) { bullet[n].setX(bullet[n].getX() - getSize().width); }
				if (bullet[n].getY() < 0) { bullet[n].setY(bullet[n].getY() + getSize().height); }
				if (bullet[n].getY() > getSize().height) { bullet[n].setY(bullet[n].getY() - getSize().height); }
				
				// age bullets
				bullet[n].decLifecounts();
				
				// disappear old bullets
				if (bullet[n].getLifecounts() <= 0) { bullet[n].setAlive(false); }
			}
		}
	}
	
	// update asteroids based on vel
	public void updateAsteroids() {
		// move/rotate
		for (int n = 0; n < ASTEROIDS; n++) {
			// is asteroid in use?
			if (ast[n].isAlive()) {
				// update X/Y positions
				ast[n].incX(ast[n].getVelX());
				ast[n].incY(ast[n].getVelY());
				
				// wrap L/R
				if (ast[n].getX() < -20)
					ast[n].setX(getSize().width + 20);
				else if (ast[n].getX() > getSize().width + 20)
					ast[n].setX(-20);
				
				// wrap top/bottom
				if (ast[n].getY() < -20)
					ast[n].setY(getSize().height + 20);
				else if (ast[n].getY() > getSize().height + 20)
					ast[n].setY(-20);
				
				// update rotation
				ast[n].incFaceAngle(ast[n].getRotationVelocity());
				
				// keep facing between 0-359 degrees
				if (ast[n].getFaceAngle() > 360)
					ast[n].setFaceAngle(ast[n].getFaceAngle() - 360);
				if (ast[n].getFaceAngle() < 0)
					ast[n].setFaceAngle(ast[n].getFaceAngle() + 360);
			}
		}
	}
	
	// test for collisions
	public void checkCollisions() {
		// check for each asteroid
		for (int m = 0; m < ASTEROIDS; m++) {
			// is asteroid in use?
			if (ast[m].isAlive()) {
				// check each bullet
				for (int n = 0; n < BULLETS; n++) {
					// is bullet in use?
					if (bullet[n].isAlive()) {
						// collision check
						if (ast[m].getBounds().contains(bullet[n].getX(), bullet[n].getY())) {
							bullet[n].setAlive(false);
							ast[m].setAlive(false);
							continue;
						}
					}
				}
				
				// check ship
				if (ast[m].getBounds().intersects(ship.getBounds())) {
					ast[m].setAlive(false);
					ship.setX(320);
					ship.setY(240);
					ship.setFaceAngle(0);
					ship.setVelX(0);
					ship.setVelY(0);
					continue;
				}
			}
		}
	}
	
	// MouseListener
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mouseReleased(MouseEvent m) {
		switch (m.getButton()) {
		case MouseEvent.BUTTON3:
			thrust = 0;
			break;
			
		default:
			repaint();
		}
	}
	public void mouseClicked(MouseEvent m) {}
	public void mousePressed(MouseEvent m) {
		switch(m.getButton()) {
		case MouseEvent.BUTTON1: // L - fire a bullet
			currentBullet++;
			if (currentBullet > BULLETS - 1)
				currentBullet = 0;
			bullet[currentBullet].setAlive(true);
			bullet[currentBullet].setLifecounts(LIFECOUNTS);
			
			// put bullet at ship
			bullet[currentBullet].setX(ship.getX());
			bullet[currentBullet].setY(ship.getY());
			bullet[currentBullet].setMoveAngle(ship.getFaceAngle() - 90);
			
			// fire bullet at angle of ship
			double angle = bullet[currentBullet].getMoveAngle();
			double svx = ship.getVelX();
			double svy = ship.getVelY();
			bullet[currentBullet].setVelX(svx + calcAngleMoveX(angle) * BULLET_SPEED);
			bullet[currentBullet].setVelY(svy + calcAngleMoveY(angle) * BULLET_SPEED);
			break;
			
		case MouseEvent.BUTTON3: // R - adds thrust to ship
			thrust = 1;
			break;
			
		case MouseEvent.BUTTON2: // wheel
			for (int n = 0; n < ASTEROIDS; n++) {
				ast[n] = new Asteroid();
				ast[n].setRotationVelocity(rand.nextInt(3)+1);
				ast[n].setX((double)rand.nextInt(600)+20);
				ast[n].setY((double)rand.nextInt(440)+20);
				ast[n].setMoveAngle(rand.nextInt(360));
				double ang = ast[n].getMoveAngle() - 90;
				ast[n].setVelX(calcAngleMoveX(ang));
				ast[n].setVelY(calcAngleMoveY(ang));
			}
			break;
			
		default:
			repaint();
		}
	}
	
	// MouseMotionListener
	public void mouseDragged(MouseEvent m) {
		xpos = m.getX();
		ypos = m.getY();
	}
	public void mouseMoved(MouseEvent m) {
		xpos = m.getX();
		ypos = m.getY();
	}
	
	// calculate X movement based on angle
	public double calcAngleMoveX(double angle) {
		return (double) (Math.cos(angle * Math.PI / 180));
	}
	
	// calculate Y movement based on angle
	public double calcAngleMoveY(double angle) {
		return (double) (Math.sin(angle * Math.PI / 180));
	}
}