// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

import java.awt.*;

public class Part {
	
	// variables
	protected Shape shape; // to display
	protected char type; // to identify
	protected int id; //for identity
	protected double mass; // kg
	protected double x, y, z; // position on craft, m
	protected double l, w, h; // sizes
	protected double phi, theta, psi; // pitch/roll/yaw, deg
	protected double F, Fx, Fy, Fz; // forces
	protected double M, Mx, My, Mz; // moments (about each axis)
	protected double[][] rotation = Matrix.IDENTITY(3); // rotation matrix, initialized at identity
	
	// accessors
	public Shape getShape() { return this.shape; }
	public char getType() { return this.type; }
	public int getID() { return this.id; }
	public double getMass() { return this.mass; }
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	public double getZ() { return this.z; }
	public double getL() { return this.l; }
	public double getW() { return this.w; }
	public double getH() { return this.h; }
	public double getTheta() { return this.theta; }
	public double getPhi() { return this.phi; }
	public double getPsi() { return this.psi; }
	public double getF() { return this.F; }
	public double getFx() { return this.Fx; }
	public double getFy() { return this.Fy; }
	public double getFz() { return this.Fz; }
	public double getM() { return this.M; }
	public double getMx() { return this.Mx; }
	public double getMy() { return this.My; }
	public double getMz() { return this.Mz; }
	public double[][] getRotation() { return this.rotation; }
	
	// mutators/helpers
	public void setShape(Shape shape) { this.shape =  shape; }
	public void setType(char type) { this.type = type; }
	public void setID(int id) { this.id = id; }
	public void setMass(double mass) { this.mass = mass; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	public void setZ(double z) { this.z = z; }
	public void setL(double l) { this.l = l; }
	public void setW(double w) { this.w = w; }
	public void setH(double h) { this.h = h; }
	public void setTheta(double theta) { this.theta = theta; }
	public void setPhi(double phi) { this.phi = phi; }
	public void setPsi(double psi) { this.psi = psi; }
	public void setF(double F) { this.F = F; }
	public void setFx(double Fx) { this.Fx = Fx; }
	public void setFy(double Fy) { this.Fy = Fy; }
	public void setFz(double Fz) { this.Fz = Fz; }
	public void setM(double M) { this.M = M; }
	public void setMx(double Mx) { this.Mx = Mx; }
	public void setMy(double My) { this.My = My; }
	public void setMz(double Mz) { this.Mz = Mz; }
	public void setRotation(/*find what to use*/) {}
	
	// constructor
	public Part() { // blank part
		setShape(null);
		setType('n');
		setID(0);
		setMass(0.0);
		setX(0.0);
		setY(0.0);
		setZ(0.0);
		setL(0.0);
		setW(0.0);
		setH(0.0);
		setPhi(0.0);
		setTheta(0.0);
		setPsi(0.0);
		setF(0.0);
		setFx(0.0);
		setFy(0.0);
		setFz(0.0);
		setM(0.0);
		setMx(0.0);
		setMy(0.0);
		setMz(0.0);
	}
}