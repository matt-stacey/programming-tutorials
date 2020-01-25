import java.util.*;

public class EDisp {
	
	public static void main(String[] args) {
		EDeck deck = new EDeck();	//creates a new deck, "deck"
									//which is a 1x52 array
		for (int i = 0; i <= 51; i++) { //so we need to step thru the deck
			ECard card = deck.getCard(i); //this is our new card, that is made to be the card in the deck that we are at
							
			
			System.out.format("%s%n",
					card.toString());
		}
	}
}