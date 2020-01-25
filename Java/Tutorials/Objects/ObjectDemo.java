class ObjectDemo {
	public static void main(String[] args) {
		
		Table whitey = new Table(0,-4, 4, 4,2); //pos (x,y); facing; LxW
		Desk newDesk = new Desk(3,0, 1, 3,2, 6); //pos (x,y); facing; LxW; legs
		Desk oldDesk = new Desk(10,-2, 2, 3,2, 6);
		
		Door hallway = new Door();
		Door closet = new Door();
		
		
		whitey.printStates();
		newDesk.printStates();
		oldDesk.printStates();
		hallway.printState();
		closet.printState();
		
		System.out.println(" ");
		
		whitey.slide(3,2); // S 2
		newDesk.slide(3,1); // S 1
		newDesk.turn(-1,1); // CCW 90
		newDesk.openDrawer(false); // keep closed
		oldDesk.slide(4,1); // W 1
		oldDesk.turn(1,8); // CW 720
		oldDesk.openDrawer(true); // open
		
		hallway.closeDoor(); // close
		hallway.closeDoor(); // already closed
		closet.openDoor(); // already open
		hallway.openDoor(); // open
		
		System.out.println(" ");
		
		whitey.printStates();
		newDesk.printStates();
		oldDesk.printStates();
		hallway.printState();
		closet.printState();
	}
}

class Table {
	protected int posX; // door = (0,0)
	protected int posY; // N = positive Y
	protected int facing; // N:1 E:2 S:3 W:4
	protected int length;
	protected int width;
	protected int legs;
	protected int ID; // numerical label
	
	private static int numberOfTables = 0;
	
	public Table(int X, int Y, int F, int L, int W) {
		posX = X;
		posY = Y;
		facing = F;
		length = L;
		width = W;
		legs = 4;
		
		ID = ++numberOfTables;
	}
	
	void slide(int direction, int distance) {
		// direction: same as above
		// distance: obvious
		
		if (direction == 1)
			posY += distance;
		if (direction == 2)
			posX += distance;
		if (direction == 3)
			posY -= distance;
		if (direction == 4)
			posX -= distance;
	}
	
	void turn(int direction, int distance) {
		// direction: +1:CW -1:CCW
		// distance: # of 90 deg increments
		
		if (direction == -1)
			facing -= distance;
		if (direction == 1)
			facing += distance;
		
		while (facing < 1) //normalizing
			facing += 4;
		while (facing > 4)
			facing -= 4;
	}
	
	void printStates() {
		System.out.println("Table ID: " + ID);
        System.out.println("Length:" + length + "\tWidth:" + width + "\t\tLegs:" + legs);
		System.out.println("Position:(" + posX + "," + posY + ")\tFacing:" + facing + "\n");
    }
}

class Desk extends Table {
	protected boolean drawerOpen = false; // T = open
	
	public Desk(int X, int Y, int F, int L, int W, int Ls) {
		super(X, Y, F, L, W);
		legs = Ls;
	}
	
	void openDrawer(boolean newValue) { // set open/closed
		drawerOpen = newValue;
	}
	
	@Override //overrides the superclass printStates() method
	void printStates() {
		System.out.println("Desk ID: " + ID);
        System.out.println("Length:" + length + "\tWidth:" + width + "\t\tLegs:" + legs);
		System.out.println("Position:(" + posX + "," + posY + ")\tFacing:" + facing);
		System.out.print("Drawer is ");
		if (!drawerOpen)
			System.out.println("closed.\n");
		if (drawerOpen)
			System.out.println("open.\n");
	}
}

class Door {
	protected boolean doorOpen = true; // T = open
	protected int ID; // numerical label
	
	private static int numberOfDoors = 0;
	
	public Door(){
	ID = ++numberOfDoors;
	}
	
	void closeDoor() { // close door if open
		if (!doorOpen)
			System.out.println("Door is already closed!");
		
		if (doorOpen) {
			doorOpen = false;
			System.out.println("SLAM!");
		}
	}
	
	void openDoor() { // open door if closed
		if (doorOpen)
			System.out.println("Door is already open!");
		if (!doorOpen) {
			doorOpen = true;
			System.out.println("Creeeeaaaaaaaak");
		}
	}
	
	void printState() {
		System.out.println("Door ID: " + ID);
		System.out.print("Door is ");
		if (!doorOpen)
			System.out.println("closed.\n");
		if (doorOpen)
			System.out.println("open.\n");
	}
}