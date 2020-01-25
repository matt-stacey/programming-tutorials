import java.util.*;

public class EDeck {
	
	private static ECard[] deck = new ECard[52];
	
	public EDeck() { //Constructor
		int i = 0;
		for (ESuit suit : ESuit.values()) { //interates thru enum listing
			for (EValue value : EValue.values()) {
				deck[i++] = new ECard(value, suit); //deck is a 1x52 array of ECards (which have value and suit)
			}
		}
	}
	
	public ECard getCard(int i){
		return deck[i];
	}
}