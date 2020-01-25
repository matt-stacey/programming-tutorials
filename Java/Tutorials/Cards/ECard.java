public class ECard {

	private final EValue value; //enum types
	private final ESuit suit; //aka simple listing
	
	public ECard (EValue value, ESuit suit) { //Constructor
		this.value = value; //stores the passed value/suit
		this.suit = suit; //to this instance
	}
	
	public String toString() {
		return value + " of " + suit;
	}
}