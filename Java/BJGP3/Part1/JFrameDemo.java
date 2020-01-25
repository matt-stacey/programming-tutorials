// BJGP3
// JFrameDemo, p34

package Part1;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo extends JFrame
{
	public JFrameDemo()
	{
		super("JFrameDemo");
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400,400);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Doing graphics with a JFrame...", 55, 200);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		g.drawString("BIATCH!", 160, 250);
	}

	public static void main(String[] args)
	{
		new JFrameDemo();
	}
}