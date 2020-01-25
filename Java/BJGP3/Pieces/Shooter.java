// simple visual applet for shooting at shit
// based on Roids from BJGP3

package Pieces;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

// Primary class for game
public class Shooter extends Applet implements Runnable, MouseListener, MouseMotionListener {
	
	// main thread becomes game loop
	Thread gameloop;
	
	// use this as double buffer (reduces flicker)
	BufferedImage backbuffer;
	
	// the main drawing object for the buffer
	Graphics2D g2d;
	
	// mouse position
	int xpos = 0;
	int ypos = 0;
	
	// set sizes
	int appletWidth = 640;
	int appletHeight = 480;
	
	// toggle for drawing hitboxes
	boolean showBounds = true;
	
	// create debris array
	int DEBRIS = 60;
	Debris[] debris = new Debris[DEBRIS];
	int currentDebris = 0;
	
	// create bullet array
	int BULLETS = 100;
	Bullet[] bullet = new Bullet[BULLETS];
	int currentBullet = 0;
	
	// create gun
	Barrel barrel = new Barrel();
	
	// create target array
	int ROWS = 10;
	int COLUMNS = 64;
	Target[][] target = new Target[ROWS][COLUMNS];
	
	// create identity transform
	AffineTransform identity = new AffineTransform();
	
	// create random number generator
	Random rand = new Random();
	
	// applet init event - creates double buffer/initializes barrel and target/listeners start
	public void init() {
		
		this.setMaximumSize(new Dimension(appletWidth, appletHeight)); // resize applet
		this.setMinimumSize(new Dimension(appletWidth, appletHeight));
		this.resize(this.appletWidth, this.appletHeight);
		
		// create the back buffer
		backbuffer = new BufferedImage(appletWidth, appletHeight, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		// set up gun
		barrel.setX(appletWidth / 2 + 3); // dead center of bottom
		barrel.setY(appletHeight);
		barrel.setFaceAngle(90);
		
		// set up target array
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				target[r][c] = new Target();
				target[r][c].setX(c * 10);
				target[r][c].setY((r * 10) + 50);
				target[r][c].setAlive(true);
			}
		}
		
		// set up bullets
		for (int b = 0; b < BULLETS; b++) {
			bullet[b] = new Bullet();
		}
		
		// set up debris
		for (int d = 0; d < DEBRIS; d++) {
			debris[d] = new Debris();
		}

		// start listeners
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
		//g2d.drawString("Gun angle: " + barrel.getFaceAngle(), 5, 10);
		//g2d.drawString("Gun position: " + barrel.getX() + ", " + barrel.getY(), 5, 25);
		//g2d.drawString("Mouse Position: " + xpos + ", " + ypos, 5, 40);
		//g2d.drawString("Target Position: " + target.getX() + ", " + target.getY(), 5, 55);
		//g2d.drawString("Target dimensions: " + target.getWidth() + " x " + target.getHeight(), 5, 70);
		//g2d.drawString("Current debris piece: " + currentDebris, 5, 85);
		
		// draw game graphics to buffer, bottom to top
		drawTarget();
		drawDebris();
		drawBullets();
		drawBarrel();
		
		// repaint applet window
		paint(g);		
	}
	
	// drawTarget called by applet event
	public void drawTarget() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				// is target block active
				if (target[r][c].isAlive()) {
					g2d.setTransform(identity);
					g2d.translate(target[r][c].getX(), target[r][c].getY());
					g2d.setColor(Color.DARK_GRAY);
					g2d.fill(target[r][c].getShape());
					if (showBounds) {
						g2d.setTransform(identity);
						g2d.setColor(Color.GREEN); // bounding box
						g2d.draw(target[r][c].getBounds());
					}
				}
			}
		}
	}
	
	// drawDebris called by applet event
	public void drawDebris() {
		// iterate through debris array
		for (int d = 0; d < DEBRIS; d++) {
			// is debris in use?
			if (debris[d].isAlive()) {
				g2d.setTransform(identity);
				g2d.translate(debris[d].getX(), debris[d].getY());
				g2d.rotate(Math.toRadians(debris[d].getFaceAngle()));
				g2d.scale(debris[d].getXScale(), debris[d].getYScale());
				g2d.setColor(debris[d].getColor());
				g2d.fill(debris[d].getShape());
			}
		}
	}
	
	// drawBullets called by applet event
	public void drawBullets() {
		// iterate through bullet array
		for (int b = 0; b < BULLETS; b++) {
			// is bullet in use?
			if (bullet[b].isAlive()) {
				g2d.setTransform(identity);
				g2d.translate(bullet[b].getX(), bullet[b].getY());
				g2d.rotate(Math.toRadians(bullet[b].getMoveAngle()));
				g2d.setColor(Color.RED);
				g2d.draw(bullet[b].getShape());
			}
		}
	}
		
	// drawBarrel called by applet event
	public void drawBarrel() {
		g2d.setTransform(identity); // uses gun's local coordinate system
		g2d.translate(barrel.getX(), barrel.getY());
		g2d.rotate(Math.toRadians(270 - barrel.getFaceAngle()));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(barrel.getShape());
		
		g2d.setTransform(identity); // box to hide bottom of gun
		g2d.translate(310, 473);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Rectangle(0, 0, 20, 7));
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
				
				// target FPS is 100
				Thread.sleep(10); //milliseconds
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
		updateDebris();
		updateBullets();
		updateBarrel();
		checkCollisions();
	}

	// update debris based on vel/rotVel
	public void updateDebris() {
		// move/rotate
		for (int d = 0; d < DEBRIS; d++) {
			// is debris in use?
			if (debris[d].isAlive()) {
				// update X/Y positions
				debris[d].incX(debris[d].getVelX());
				debris[d].incY(debris[d].getVelY());
				
				// disappear at edges
				if (debris[d].getX() < 0 || debris[d].getX() > getSize().width)
					debris[d].setAlive(false);
				if (debris[d].getY() < 0 || debris[d].getY() > getSize().height)
					debris[d].setAlive(false);
					
				// update rotation
				debris[d].incFaceAngle(debris[d].getRotationVelocity());
				
				// keep facing between 0-359 degrees
				if (debris[d].getFaceAngle() > 360)
					debris[d].setFaceAngle(debris[d].getFaceAngle() - 360);
				if (debris[d].getFaceAngle() < 0)
					debris[d].setFaceAngle(debris[d].getFaceAngle() + 360);
					
				// kill after lifeCounts expires
				debris[d].decLife();
				if (debris[d].getLife() <= 0)
					debris[d].setAlive(false);
			}
		}
	}
		
	// update bullets based on vel
	public void updateBullets() {
		// for each bullet
		for (int b = 0; b < BULLETS; b++) {
			// is bullet in use?
			if (bullet[b].isAlive()) {
				// update X/Y positions
				bullet[b].incX(bullet[b].getVelX());
				bullet[b].incY(bullet[b].getVelY());
				
				// reflect at edges
				if (bullet[b].getX() < 0) { bullet[b].setVelX(-1 * bullet[b].getVelX()); }
				if (bullet[b].getX() > getSize().width) { bullet[b].setVelX(-1 * bullet[b].getVelX()); }
				if (bullet[b].getY() < 0) { bullet[b].setVelY(-1 * bullet[b].getVelY()); }
				if (bullet[b].getY() > getSize().height) { bullet[b].setVelY(-1 * bullet[b].getVelY()); }
			}
		}
	}
	
	// update barrel
	public void updateBarrel() {
		barrel.setFaceAngle(Math.toDegrees(Math.atan2(480 - ypos, xpos - 320)));
		if (barrel.getFaceAngle() > 150)
			barrel.setFaceAngle(150);
		if (barrel.getFaceAngle() < 30)
			barrel.setFaceAngle(30);
		barrel.setX(appletWidth / 2 + 3 * Math.sin(Math.toRadians(barrel.getFaceAngle())));
		barrel.setY(appletHeight + 3 * Math.cos(Math.toRadians(barrel.getFaceAngle())));
	}
	
	// test for collisions
	public void checkCollisions() {
		// check for each bullet hitting target
		for (int b = 0; b < BULLETS; b++) {
			// is bullet in use?
			if (bullet[b].isAlive()) {
				// against each target
				for (int r = 0; r < ROWS; r++) {
					for (int c = 0; c < COLUMNS; c++) {
						// is target alive?
						if (target[r][c].isAlive()) {
							// is bullet impacting target?
							if (target[r][c].getBounds().contains(bullet[b].getX(), bullet[b].getY())) {
								bullet[b].setAlive(false);
								target[r][c].setAlive(false);
							
								// create debris
								int pieces = rand.nextInt(2) + 2; // select random #, 2-4
								for (int d = 0; d <= pieces; d++) {
									currentDebris++;
									if (currentDebris > DEBRIS - 1)
									currentDebris = 0;
									// turn on
									debris[currentDebris].setAlive(true);
									debris[currentDebris].setLife();
									// position at impact point
									debris[currentDebris].setX(bullet[b].getX());
									debris[currentDebris].setY(bullet[b].getY());
									// impart velocity
									int ang = rand.nextInt(360);
									debris[currentDebris].setMoveAngle(ang);
									debris[currentDebris].setVelX(calcAngleMoveX(ang) * 5 + bullet[currentBullet].getVelX());
									debris[currentDebris].setVelY(calcAngleMoveY(ang) * 5 + bullet[currentBullet].getVelY());
									// set random color/scale
									debris[currentDebris].newColor(rand.nextInt());
									debris[currentDebris].setScales(rand.nextDouble() * 5, rand.nextDouble() * 5);
									// set rotation velocity
									debris[currentDebris].setRotationVelocity(rand.nextDouble() * 20 - 10);
									debris[currentDebris].setFaceAngle(0);
								}
							}
						}
					}
				}
			}
		}
	}
	
	// handle mouse button events
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mouseReleased(MouseEvent m) {}
	public void mouseClicked(MouseEvent m) {}
	public void mousePressed(MouseEvent m) {
		switch(m.getButton()) {
		case MouseEvent.BUTTON1:
		case MouseEvent.BUTTON3:
			// fire a bullet
			currentBullet++;
			if (currentBullet > BULLETS - 1)
				currentBullet = 0;
			bullet[currentBullet].setAlive(true);
			
			// put bullet at gun
			bullet[currentBullet].setX(320 + 30 * Math.cos(Math.toRadians(barrel.getFaceAngle())));
			bullet[currentBullet].setY(480 - 30 * Math.sin(Math.toRadians(barrel.getFaceAngle())));
			bullet[currentBullet].setMoveAngle(360 - barrel.getFaceAngle());
			
			// fire bullet at angle of gun
			double angle = bullet[currentBullet].getMoveAngle();
			bullet[currentBullet].setVelX(calcAngleMoveX(angle) * 5);
			bullet[currentBullet].setVelY(calcAngleMoveY(angle) * 5);
			break;
		case MouseEvent.BUTTON2:
			// resize applet to full dimensions
			this.resize(this.appletWidth, this.appletHeight);
			// kill bullets
			for (int b = 0; b < BULLETS; b++) {
				bullet[b].setAlive(false);
			}
			// kill debris
			for (int d = 0; d < DEBRIS; d++) {
				debris[d].setAlive(false);
			}
			// reset targets
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLUMNS; c++) {
					target[r][c].setAlive(true);
				}
			}
			break;
		default:
			repaint();
		}
	}
		
	// handle mouse motion events
	public void mouseDragged(MouseEvent m) {}
	public void mouseMoved(MouseEvent m) {
		xpos = m.getX(); // get mouse location
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
