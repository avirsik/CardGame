import java.util.ArrayList;
public class Deck {
    // instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;
    // constructor
    public Deck(String[] ranks, String[] suits, int[] points) {
        this.cards = new ArrayList<Card>();
        this.cardsLeft = 0;
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                cards.add(new Card(ranks[i], suits[j], points[i]));
                cardsLeft++;
            }
        }
    }
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return this.cardsLeft;
    }

    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int r = (int)(Math.random()*i);
            Card copy = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, copy);
        }
    }
}
