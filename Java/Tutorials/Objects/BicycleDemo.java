class BicycleDemo {
    public static void main(String[] args) {

        // Create two different 
        // Bicycle objects
        Bicycle bike1 = new Bicycle();
        Bicycle bike2 = new Bicycle();
		MountainBike mbike = new MountainBike();

        // Invoke methods on 
        // those objects
        bike1.changeCadence(50);
        bike1.speedUp(10);
        bike1.changeGear(2);
        bike1.printStates();

        bike2.changeCadence(50);
        bike2.speedUp(10);
        bike2.changeGear(2);
        bike2.changeCadence(40);
        bike2.speedUp(10);
        bike2.changeGear(3);
        bike2.printStates();
		
		mbike.changeCadence(40);
        mbike.speedUp(8);
        mbike.changeGear(3);
		mbike.changeIncline(3);
        mbike.printMStates();
    }
}

class Bicycle {

    int cadence = 0;
    int speed = 0;
    int gear = 1;

    void changeCadence(int newValue) {
         cadence = newValue;
    }

    void changeGear(int newValue) {
         gear = newValue;
    }

    void speedUp(int increment) {
         speed = speed + increment;   
    }

    void applyBrakes(int decrement) {
         speed = speed - decrement;
    }

    void printStates() {
         System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}

class MountainBike extends Bicycle {

    // new fields and methods defining 
    // a mountain bike would go here
	int incline = 20;
	
	void changeIncline(int newValue) {
		incline = newValue;
	}
	
	void printMStates() {
		System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear + 
			 " incline:" + incline);
	}

}

/*
interface Bicycle {

    //  wheel revolutions per minute
    void changeCadence(int newValue);

    void changeGear(int newValue);

    void speedUp(int increment);

    void applyBrakes(int decrement);
}
class ACMEBicycle implements Bicycle {

    // remainder of this class 
    // implemented as before
}
*/