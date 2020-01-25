// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class ForceModel {
	
	// data points (for individual surfaces; averaged based off of experimental values)
	private double[] deflectionDATA = {-15.0, -12.0, -09.0, -06.0, -03.0, 000.0, 003.0, 006.0, 009.0, 012.0, 015.0}; // in degrees
	private double[] momentDATAelev = {  0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}; // pitch moment: deflection +up, moment +up
	private double[] momentDATAail  = {  0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}; // roll moment: deflection +up (L has -1 multiplier), moment +rwd
	private double[] momentDATArud  = {  0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}; // yaw moment: deflection +right, moment +right
	
	// data points (for individual fans; averaged based off of experimental values)
	private double[] speedDATA  = {  0.0,  10.0,  20.0,  30.0,  40.0,  50.0,  60.0,  70.0,  80.0,  90.0, 100.0}; // speed in percent
	private double[] momentDATA = {  0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}; // W, A, S, D, X moment from thrust
	
	// data points (for engines; averaged based off of experimental values)
	private double[] powerDATA  = {  0.0,  10.0,  20.0,  30.0,  40.0,  50.0,  60.0,  70.0,  80.0,  90.0, 100.0}; // power in percent
	private double[] thrustDATA = {  0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}; // engine thrust
	
	// data points (for nozzles; averaged based off of experimental values)
	private double[] angleDATA = {  0.0,  10.0,  20.0,  30.0,  40.0,  50.0,  60.0,  70.0,  80.0,  90.0}; // angle in degrees
	private double[] forceDATA = {  0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}; // force: deflection +up, force +up
	
	
	public ForceModel() {
		
	}
	
	public double surface() {
		double force = 0.0;
		
		return force;
	}
	
	public double duct() {
		double force = 0.0;
		
		return force;
	}
	
	public double nozzle() {
		double force = 0.0;
		
		return force;
	}
	
	public double engine() {
		double force = 0.0;
		
		return force;
	}

}
