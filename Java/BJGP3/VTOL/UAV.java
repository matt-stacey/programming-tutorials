// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

import java.awt.*;

public class UAV {
	
	/* NOTES:
	 * 
	 * coordinate system
	 * datum: 
	 * x: forward [in m = ft / 3.28084 = in / 39.3701]
	 * y: right
	 * z: down
	 * theta: pitch (up +) [in deg = rad / 0.0174532925] {use Math.toRadians(deg)}
	 * phi: roll (rwd +)
	 * psi: yaw (r +)
	 * 
	 * measurements
	 * mass: kg (= lb-m / 2.20462)
	 * force: N (= lb-F / 4.44975)
	 * 
	 * types:
	 *   aileron
	 *   elevator
	 *   rudder
	 *   engine (1:lift+cruise, 2:lift)
	 *   nozzle ("share" thrust with engine: simply a way to express downward thrust)
	 *   fan (for hovering control system)
	 *   
	 * 
	 * naming conventions:
	 *   A/E/R for ailerons/elevators/rudders, -1 for id on L aileron (deflection is opposite moment)
	 *   C/L for nozzles/engines
	 *   WASDX for fans
	 *   
	 * 
	 * control surface deflections (will jive with coordinate system)
	 *   -ailerons: right wing +up, left wing +down
	 *   -elevators: +up
	 *   -rudder: +right
	 *   -fans- (which way they move aka opposite thrust)
	 *     -nose: +up -dn (pitch)
	 *     -tail: +dn -up
	 *     -right wing: +dn -up (roll)
	 *     -left wing: +up -dn
	 *     -tail: +rt -lt (yaw)
	 *   -nozzles: +deflected (effect: thrust force vector "pitch"ing up)
	 *   
	 *   
	 *   
	 */
	
	// constants
	public static int AILERONS = 2;
	public static int ELEVATORS = 2;
	public static int RUDDERS = 2;
	public static int FANS = 5;
	public static int ENGINES = 2;
	public static int NOZZLES = 2;
		
	// variables
	private Shape shape;
	private double mass;
	private double X, Y, Z; // position
	private double x, y, z; // CG location
	private double velX, velY, velZ; // velocity
	private double accX, accY, accZ; // acceleration
	private double theta, phi, psi; // pitch, roll, yaw
	private double thetaD, phiD, psiD; // pitch/roll/yaw rates
	private double thetaDd, phiDd, psiDd; // pitch/roll/yaw accelerations
	protected double F, Fx, Fy, Fz; // forces
	protected double M, Mx, My, Mz; // moments (about each axis)
	private double alpha, beta; // AOA, sideslip
	private double[][] rotation = Matrix.IDENTITY(3); // rotation matrix, initialized at identity
	private boolean hover, flight; // control states
	
	// measurements
	public double measAccX() { return this.accX; } // measured by 3-axis accelerometer
	public double measAccY() { return this.accY; }
	public double measAccZ() { return this.accZ; }
	public double measThetaD() { return this.thetaD; } // measured by 3-axis gyro
	public double measPhiD() { return this.phiD; }
	public double measPsiD() { return this.psiD; }
	
	// accessors (external read)
	public Shape getShape() { return this.shape; }
	public double getMass() { return this.mass; }
	public double getX() { return this.X; }
	public double getY() { return this.Y; }
	public double getZ() { return this.Z; }
	public double getCGx() { return this.x; }
	public double getCGy() { return this.y; }
	public double getCGz() { return this.z; }
	public double getVelX() { return this.velX; }
	public double getVelY() { return this.velY; }
	public double getVelZ() { return this.velZ; }
	public double getAccX() { return this.accX; }
	public double getAccY() { return this.accY; }
	public double getAccZ() { return this.accZ; }
	public double getTheta() { return this.theta; }
	public double getPhi() { return this.phi; }
	public double getPsi() { return this.psi; }
	public double getThetaD() { return this.thetaD; }
	public double getPhiD() { return this.phiD; }
	public double getPsiD() { return this.psiD; }
	public double getThetaDd() { return this.thetaDd; }
	public double getPhiDd() { return this.phiDd; }
	public double getPsiDd() { return this.psiDd; }
	public double getF() { return this.F; }
	public double getFx() { return this.Fx; }
	public double getFy() { return this.Fy; }
	public double getFz() { return this.Fz; }
	public double getM() { return this.M; }
	public double getMx() { return this.Mx; }
	public double getMy() { return this.My; }
	public double getMz() { return this.Mz; }
	public double getAlpha() { return this.alpha; }
	public double getBeta() { return this.beta; }
	public double[][] getRotation() { return this.rotation; }
	public boolean getHover() { return this.hover; }
	public boolean getFlight() {return this.flight; }
	
	// controls (for sim use)
	public void setShape(Shape shape) { this.shape =  shape; }
	public void setMass(double mass) { this.mass = mass; }
	public void setX(double X) { this.X = X; }
	public void setY(double Y) { this.Y = Y; }
	public void setZ(double Z) { this.Z = Z; }
	public void setCGx(double x) { this.x = x; }
	public void setCGy(double y) { this.y = y; }
	public void setCGz(double z) { this.z = z; }
	public void setVelX(double velX) { this.velX = velX; }
	public void setVelY(double velY) { this.velY = velY; }
	public void setVelZ(double velZ) { this.velZ = velZ; }
	public void setAccX(double accX) { this.accX = accX; }
	public void setAccY(double accY) { this.accY = accY; }
	public void setAccZ(double accZ) { this.accZ = accZ; }
	public void setTheta(double theta) {
		this.theta = theta;
		while (this.theta > 180.0) { this.theta -= 360.0; } // bound to -179.9 <=> 180.0
		while (this.theta <= -180.0) { this.theta += 360.0; }
		this.theta = Math.round(this.theta * 10.0) / 10.0; // round to 1 place
	}
	public void setPhi(double phi) {
		this.phi = phi;
		while (this.phi > 180.0) { this.phi -= 360.0; } // bound to -179.9 <=> 180.0
		while (this.phi <= -180.0) { this.phi += 360.0; }
		this.phi = Math.round(this.phi * 10.0) / 10.0; // round to 1 place
	}
	public void setPsi(double psi) {
		this.psi = psi;
		while (this.psi > 180.0) { this.psi -= 360.0; } // bound to -179.9 <=> 180.0
		while (this.psi <= -180.0) { this.psi += 360.0; }
		this.psi = Math.round(this.psi * 10.0) / 10.0; // round to 1 place
	}
	public void setAngles(double[] angles) {
		this.theta = angles[0];
		while (this.theta > 180.0) { this.theta -= 360.0; } // bound to -179.9 <=> 180.0
		while (this.theta <= -180.0) { this.theta += 360.0; }
		this.theta = Math.round(this.theta * 10.0) / 10.0; // round to 1 place
		
		this.phi = angles[1];
		while (this.phi > 180.0) { this.phi -= 360.0; } // bound to -179.9 <=> 180.0
		while (this.phi <= -180.0) { this.phi += 360.0; }
		this.phi = Math.round(this.phi * 10.0) / 10.0; // round to 1 place
		
		this.psi = angles[2];
		while (this.psi > 180.0) { this.psi -= 360.0; } // bound to -179.9 <=> 180.0
		while (this.psi <= -180.0) { this.psi += 360.0; }
		this.psi = Math.round(this.psi * 10.0) / 10.0; // round to 1 place
	}
	public void setThetaD(double thetaD) { this.thetaD = thetaD; }
	public void setPhiD(double phiD) { this.phiD = phiD; }
	public void setPsiD(double psiD) { this.psiD = psiD; }
	public void setThetaDd(double thetaDd) { this.thetaDd = thetaDd; }
	public void setPhiDd(double phiDd) { this.phiDd = phiDd; }
	public void setPsiDd(double psiDd) { this.psiDd = psiDd; }
	public void setF(double F) { this.F = F; }
	public void setFx(double Fx) { this.Fx = Fx; }
	public void setFy(double Fy) { this.Fy = Fy; }
	public void setFz(double Fz) { this.Fz = Fz; }
	public void setM(double M) { this.M = M; }
	public void setMx(double Mx) { this.Mx = Mx; }
	public void setMy(double My) { this.My = My; }
	public void setMz(double Mz) { this.Mz = Mz; }
	public void setAlpha() { this.alpha = 0.0; }
	public void setBeta() { this.beta = 0.0; }
	public void setHover(boolean hover) { this.hover = hover; }
	public void setFlight(boolean flight) { this.flight = flight; }
	
	public void update() {
		// positions based on velocity
		setX(getX() + getVelX());
		setY(getY() + getVelY());
		setZ(getZ() + getVelZ());
		
		// velocities based on acceleration
		setVelX(getVelX() + getAccX());
		setVelY(getVelY() + getAccY());
		setVelZ(getVelZ() + getAccZ());
		
		// accelerations based on forces
		
		
		// angles based on rates
		setTheta(getTheta() + getThetaD());
		setPhi(getPhi() + getPhiD());
		setPsi(getPsi() + getPsiD());
		
		// angle rates based on accelerations
		setThetaD(getThetaD() + getThetaDd());
		setPhiD(getPhiD() + getPhiDd());
		setPsiD(getPsiD() + getPsiDd());
		
		// angular accelerations based on moments
		
		
		//setRotation(this.theta, this.phi, this.psi);
	}

	public UAV() {
		setShape(null);
		setMass(0.0);
		setX(0.0);
		setY(0.0);
		setZ(0.0);
		setCGx(0.0);
		setCGy(0.0);
		setCGz(0.0);
		setVelX(0.0);
		setVelY(0.0);
		setVelZ(0.0);
		setAccX(0.0);
		setAccY(0.0);
		setAccZ(0.0);
		setTheta(0.0);
		setPhi(0.0);
		setPsi(0.0);
		setThetaD(0.0);
		setPhiD(0.0);
		setPsiD(0.0);
		setThetaDd(0.0);
		setPhiDd(0.0);
		setPsiDd(0.0);
		setF(0.0);
		setFx(0.0);
		setFy(0.0);
		setFz(0.0);
		setM(0.0);
		setMx(0.0);
		setMy(0.0);
		setMz(0.0);
		setAlpha();
		setBeta();
		
		// set up control surfaces
		Surface[] surfaces = new Surface[AILERONS+ELEVATORS+RUDDERS]; //6
			surfaces[0] = new Surface('A', -1); //left aileron
			surfaces[1] = new Surface('E', 1); //left elevator
			surfaces[2] = new Surface('R', 1); //left rudder
			surfaces[3] = new Surface('R', 1); //right rudder
			surfaces[4] = new Surface('E', 1); //right elevator
			surfaces[5] = new Surface('A', 1); //right aileron
		
		// set up hover control system (fans)
		Fan[] fans = new Fan[FANS]; //5
			fans[0] = new Fan('W', 1); //nose
			fans[1] = new Fan('A', 1); //left wing
			fans[2] = new Fan('S', -1); //tail
			fans[3] = new Fan('D', -1); //right wing
			fans[4] = new Fan('X', 1); //rudder
		
		// set up engines
		Engine[] engines = new Engine[ENGINES]; //2
			engines[0] = new Engine('C', 1); //lift/cruise
			engines[1] = new Engine('L', 1); //lift
		
		// set up nozzles
		Nozzle[] nozzles = new Nozzle[NOZZLES]; //2
			nozzles[0] = new Nozzle('C', 1); //lift/cruise
			nozzles[1] = new Nozzle('L', 1); //lift
		
		// set up model of forces
		ForceModel forceModel = new ForceModel();
	}
}