// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Surface extends Part {

	/* valid types (char):
	 * R: rudder
	 * A: aileron
	 * E: elevator
	 * valid ids (int):
	 * 1: elevator, rudder, R aileron
	 * -1: L aileron
	*/
	
	// variables
	private double deflection; // degrees, trailing edge up (ID sets proper rotation for ailerons)
	
	// accessors
	public double getDeflection() { return this.deflection; }
	
	// mutators/helpers
	public void setDeflection(double deflection) { this.deflection = deflection; } // this impacts forces and moments; needs to be recalculated as soon as a new deflection is set
	
	// constructors
	public Surface() {
		setType('n');
		setID(0);
		setDeflection(0.0);
		System.out.println("Uninitialized Surface!");
	}
	public Surface(char type, int id) {
		setType(type);
		setID(id);
		setDeflection(0.0);
	}
}