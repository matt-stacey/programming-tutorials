// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Engine extends Part {

	/* valid types (char):
	 * C: lift/cruise
	 * L: lift only
	 * valid ids (int):
	 * 1
	*/
	
	// variables
	private double power; // percentage
	
	// accessors
	public double getPower() { return this.power; }
	
	// mutators/helpers
	public void setPower(double power) { this.power = power; } // this impacts forces and moments; needs to be recalculated as soon as a new power is set
	
	// constructor
	public Engine() {
		setType('n');
		setID(0);
		setPower(0.0);
		System.out.println("Uninitialized Engine!");
	}
	public Engine(char type, int id) {
		setType(type);
		setID(id);
		setPower(0.0);
	}
}