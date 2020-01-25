// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Fan extends Part{
	
	/* valid types (char):
	 * W: nose
	 * A: left wing
	 * S: tail
	 * D: right wing
	 * X: rudder
	 * valid ids (int):
	 * 1: W, A // positive thrust (downward) aids moment
	 * -1: S, D // positive thrust opposes moment
	 * -1/1: X // depends on installation: 1 if thrust goes R/moment goes L, -1 if thrust goes L/moment goes R
	*/
	
	// variables
	private double speed; // percentage
	
	// accessors
	public double getSpeed() { return this.speed; }
	
	// mutators/helpers
	public void setSpeed(double speed) { this.speed = speed; } // this impacts forces and moments; needs to be recalculated as soon as a new speed is set

	// constructor
	public Fan() {
		setType('n');
		setID(0);
		setSpeed(0.0);
		System.out.println("Uninitialized Fan!");
	}
	public Fan(char type, int id) {
		setType(type);
		setID(id);
		setSpeed(0.0);
	}
}