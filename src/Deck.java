import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;
    private GameViewer gv;

    // Constructor initializes cards, creates the specified cards and adds them to the list, and sets cardsLeft to the number of cards in
    // the deck
    public Deck(String[] ranks, String[] suits, int[] points) {
        this.cards = new ArrayList<Card>();
        this.cardsLeft = 0;
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                cardsLeft++;
                Image image = new ImageIcon("Resources/" + cardsLeft + ".png").getImage();
                cards.add(new Card(ranks[i], suits[j], points[i], image, gv));

            }
        }
    }
    // Returns true when there are no cards left in the deck
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }
    // Returns the number of cards left to be dealt
    public int getCardsLeft() {
        return this.cardsLeft;
    }
    // When there are still cards left in the deck, return the last card
    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }
    // Reorders the cards in the ArrayList to create a shuffled deck
    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int r = (int)(Math.random()*i);
            Card copy = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, copy);
        }
    }
}
