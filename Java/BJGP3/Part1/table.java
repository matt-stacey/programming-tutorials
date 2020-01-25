// BJGP3
// Exercise1, p68

package Part1;

public class table {
	protected int length, width, legs; //private doesn't extend to desk

	public table()
	{
		this.length = 4;
		this.width = 2;
		this.legs = 4;
	}
	
	public table(int length, int width, int legs)
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
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getLegs()
	{
		return legs;
	}
	
	public boolean setLength(int length)
	{
		if (length > 0)
		{
			this.length = length;
			return true;
		} else {
			return false;
		}
			
	}
	
	public boolean setWidth(int width)
	{
		if (width > 0)
		{
			this.width = width;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean setLegs(int legs)
	{
		if (legs > 0)
		{
			this.legs = legs;
			return true;
		} else {
			return false;
		}
	}
}