import java.util.*;

public class Deck {
	public static int numSuits = 4;
	public static int numVals  = 13;
	public static int numCards = numSuits * numVals;
	
	private Card[][] cards;
	
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
	
	public Deck() { //Constructor
		cards = new Card[numSuits][numVals];
		for (int suit = SPADES; suit <= DIAMONDS; suit++) {
			for (int value = ACE; value <= KING; value++) {
				cards[suit-1][value-1] = new Card(value, suit);
			}
		}
	}
	
	public Card getCard(int suit, int value){
		return cards[suit-1][value-1];
	}
}