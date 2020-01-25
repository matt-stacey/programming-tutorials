// BJGP3
// SimpleClass, p 65

package Part1;

import java.lang.*;
import javax.swing.*;
import java.awt.*;

public class SimpleClass extends JFrame {
	vehicle car;
	truck lightning;

	public SimpleClass()
	{
		super("SimpleClass");
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Init vehicle
		car = new vehicle();
		car.setMake("Ford");
		car.setNumWheels(4);
		
		//Init truck w/ constructor
		lightning = new truck("Ford SVT", "F-150 Lightning", "5.4L Triton V8", 7700);
	}
	
	public void paint (Graphics g)
	{
		g.setFont(new Font("Verdana", Font.BOLD, 12));
		
		//display car info
		g.drawString("Car make: " + car.getMake(), 20, 50);
		g.drawString("Number of wheels: " + car.getNumWheels(), 20, 70);
		
		//display truck info
		g.drawString("Truck make: " + lightning.getMake(), 20, 90);
		g.drawString("Truck model: " + lightning.getModel(), 20, 110);
		g.drawString("Truck engine: " + lightning.getEngine(), 20, 130);
		g.drawString("Truck towing capacity: " + lightning.getTowingCapacity(), 20, 150);
	}
	
	public static void main(String[] args) {
		new SimpleClass();
	}

}
