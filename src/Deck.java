import java.util.ArrayList;
public class Deck {
    // instance variables
    // Can declare and initialize Arrays, ArrayLists, and 2D Arrays
    private ArrayList<Card> cards;
    private int cardsLeft;
    // constructor
    // Can declare and initialize Arrays, ArrayLists, and 2D Arrays.

    public Deck(String[] ranks, String[] suits, int[] points) {
        this.cards = new ArrayList<Card>();
        this.cardsLeft = 0;
        // Understand how to use nesting to embed loops and conditionals inside of other loops and conditionals
        // Can use if, while, and for.
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                cards.add(new Card(ranks[i], suits[j], points[i]));
                cardsLeft++;
            }
        }
    }
    public boolean isEmpty() {
        // Can use if, while, and for.
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return this.cardsLeft;
    }

    public Card deal() {
        // Can use if, while, and for.
        if (cards.isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        // Can use if, while, and for.
        for (int i = cards.size() - 1; i > 0; i--) {
            // Can use Math class, especially Math.random().
            int r = (int)(Math.random()*i);
            Card copy = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, copy);
        }
    }
}
