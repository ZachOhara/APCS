package oharaElevens;

/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] suits = {"clubs", "hearts", "diamonds", "spades"};
		int[] points = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		Deck deck = new Deck(ranks, suits, points);

		System.out.println("length is " + deck.size());
		while ( !deck.isEmpty()) {
			System.out.println(deck.deal());
		}
		System.out.println("length is " + deck.size());

		for (int i = 0; i < 10; i++) {
			deck.shuffle();
			System.out.println(deck);
		}
	}
}
