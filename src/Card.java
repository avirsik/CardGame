import javax.swing.*;
import java.awt.*;

public class Card {
    // Instance variables
    private String rank;
    private String suit;
    private int point;
    private Image image;
    private GameViewer gv;
    // Constructor
    public Card(String rank, String suit, int point, Image image, GameViewer gv) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        this.image = image;
        this.gv = gv;
    }
    // Getter and setter methods
    public String getRank() {
        return this.rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getSuit() {
        return this.suit;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public int getPoint() {
        return this.point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public String toString() {
        return rank + " of " + suit;
    }
    // Draws itself
    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, 75, 125, gv);
    }
}
