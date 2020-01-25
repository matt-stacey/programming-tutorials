// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Nozzle extends Part {

	/* valid types (char):
	 * C: lift/cruise
	 * L: lift only
	 * valid ids (int):
	 * 1
	*/
	
	// variables
	private double angle; // degrees
	
	// accessors
	public double getAngle() { return this.angle; }
	
	// mutators/helpers
	public void setAngle(double angle) { this.angle = angle; } // this impacts forces and moments; needs to be recalculated as soon as a new angle is set
	
	// constructor
	public Nozzle() {
		setType('n');
		setID(0);
		setAngle(0.0);
		System.out.println("Uninitialized Nozzle!");
	}
	public Nozzle(char type, int id) {
		setType(type);
		setID(id);
		setAngle(0.0);
	}
}