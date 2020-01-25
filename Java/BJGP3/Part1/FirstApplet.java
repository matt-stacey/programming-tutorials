// BJGP3
// FirstApplet, p21

package Part1;

import java.awt.*;
import java.applet.*;

public class FirstApplet extends Applet
{
	public void paint(Graphics g)
	{
		g.drawString("This is my first Java Applet!", 20, 30);
		g.drawString("And now I've changed it!", 10, 100);
	}
}