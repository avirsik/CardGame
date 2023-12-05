import java.util.ArrayList;
public class Player {
    // instance variables
    private String name;
    private ArrayList<Card> hand;
    private int points;
    // constructors
    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
    }
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        this.points = 0;
    }
    // getter methods for instance variables
    public String getName() {
        return this.name;
    }
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    public int getPoints() {
        return this.points;
    }
    public void addPoints(int points) {
        this.points += points;
    }
    public void addCard(Card c) {
        hand.add(c);
    }
    public String toString() {
        return this.name + " has " + this.points + " points \n" + this.name + "'s cards: " + this.hand;
    }
}
