public class Card {

	private final int value;
	private final int suit;
	
	//Suit List
	public final static int SPADES   = 1;
	public final static int HEARTS   = 2;
	public final static int CLUBS    = 3;
	public final static int DIAMONDS = 4;
	
	//Card List
	public final static int ACE   = 1;
	public final static int DEUCE = 2;
	//3-10 are duh
	public final static int JACK  = 11;
	public final static int QUEEN = 12;
	public final static int KING  = 13;
	
	public Card (int value, int suit) { //Constructor
		assert isValidValue(value);
		assert isValidSuit(suit);
		this.value = value;
		this.suit = suit;
	}
	
	public int getSuit() {
		return suit;
	}
	public int getValue() {
		return value;
	}
	
	public static boolean isValidValue(int value) {
		return ((ACE <= value) && (value <= KING));
	}
	public static boolean isValidSuit(int suit) {
		return ((SPADES <= suit) && (suit <= DIAMONDS));
	}
	
	public static String valueToString(int value) {
		switch (value) {
			case ACE:
				return "Ace";
			case DEUCE:
				return "Deuce";
			case 3:
				return "Three";
			case 4:
				return "Four";
			case 5:
				return "Five";
			case 6:
				return "Six";
			case 7:
				return "Seven";
			case 8:
				return "Eight";
			case 9:
				return "Nine";
			case 10:
				return "Ten";
			case JACK:
				return "Jack";
			case QUEEN:
				return "Queen";
			case KING:
				return "King";
			default:
				return null;
		}
	}
	public static String suitToString(int suit) {
		switch (suit) {
			case SPADES:
				return "Spades";
			case HEARTS:
				return "Hearts";
			case CLUBS:
				return "Clubs";
			case DIAMONDS:
				return "Diamonds";
			default:
				return null;
		}
	}
	
	public static void main(String[] args) {
		assert (valueToString(ACE) == "Ace");
		assert (valueToString(DEUCE) == "Deuce");
		assert (valueToString(3) == "Three");
		assert (valueToString(4) == "Four");
		assert (valueToString(5) == "Five");
		assert (valueToString(6) == "Six");
		assert (valueToString(7) == "Seven");
		assert (valueToString(8) == "Eight");
		assert (valueToString(9) == "Nine");
		assert (valueToString(10) == "Ten");
		assert (valueToString(JACK) == "Jack");
		assert (valueToString(QUEEN) == "Queen");
		assert (valueToString(KING) == "King");
		
		assert (suitToString(SPADES) == "Spades");
		assert (suitToString(HEARTS) == "Hearts");
		assert (suitToString(CLUBS) == "Clubs");
		assert (suitToString(DIAMONDS) == "Diamonds");
	}
}