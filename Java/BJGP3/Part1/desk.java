// BJGP3
// Exercise1, p68

package Part1;

public class desk extends table {
	protected int drawers;

	public desk()
	{
		this.drawers = 1;
	}

	public desk(int length, int width, int legs, int drawers)
	{
		if (length > width)
		{
			setLength(length);
			setWidth(width);
		} else {
			setLength(width);
			setWidth(length);
		}
		
		if (legs > 0)
			setLegs(legs);
		else
			setLegs(4);
		
		if (drawers > 0)
			setDrawers(drawers);
		else
			setDrawers(1);
	}
	
	public int getDrawers()
	{
		return drawers;
	}
	
	public boolean setDrawers(int drawers)
	{
		if (drawers > 0)
		{
			this.drawers = drawers;
			return true;
		} else {
			return false;
		}
	}
}
