// BJGP3
// Exercise1, p68

package Part1;

import java.lang.*;
import javax.swing.*;
import java.awt.*;

public class Exercise1 extends JFrame {
	table table1, table2;
	desk desk1, desk2;
	
	public Exercise1()
	{
		super("Exercise 1");
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//init table 1 + 2
		table1 = new table();
		table1.setLength(6);
		table1.setLegs(4);
		table2 = new table(2, 3, 6);
		
		//init desk 1 + 2
		desk1 = new desk();
		desk1.setWidth(3);
		desk1.setLegs(6);
		desk1.setDrawers(1);
		desk2 = new desk(2, 3, 6, 2);
	}
	
	public void paint (Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640, 480);
		
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		g.setColor(Color.BLUE);
		//display table info
		g.drawString("Table 1 dimensions: " + table1.getLength() + " by " + table1.getWidth(), 20, 50);
		g.drawString("Table 1 legs: " + table1.getLegs(), 20, 70);
		g.drawString("Table 2 dimensions: " + table2.getLength() + " by " + table2.getWidth(), 20, 90);
		g.drawString("Table 2 legs: " + table2.getLegs(), 20, 110);
		
		g.setColor(Color.ORANGE);
		//display desk info
		g.drawString("Desk 1 dimensions: " + desk1.getLength() + " by " + desk1.getWidth(), 20, 150);
		g.drawString("Desk 1 legs: " + desk1.getLegs(), 20, 170);
		g.drawString("Desk 1 drawers: " + desk1.getDrawers(), 20, 190);
		g.drawString("Desk 2 dimensions: " + desk2.getLength() + " by " + desk2.getWidth(), 20, 210);
		g.drawString("Desk 2 legs: " + desk2.getLegs(), 20, 230);
		g.drawString("Desk 2 drawers: " + desk2.getDrawers(), 20, 250);
	}
	
	public static void main(String[] args)
	{
		new Exercise1();
	}
}
