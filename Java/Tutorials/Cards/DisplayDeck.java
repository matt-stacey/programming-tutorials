import java.util.*;

public class DisplayDeck {

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
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		for (int suit = SPADES; suit <= DIAMONDS; suit++) {
			for (int value = ACE; value <= KING; value++) {
				Card card = deck.getCard(suit, value);
				System.out.format("%s of %s%n",
					card.valueToString(card.getValue()),
					card.suitToString(card.getSuit()));
			}
		}
	}
}