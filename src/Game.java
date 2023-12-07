import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    // instance variables
    private Player player;
    private Player computer;
    // creates player's deck
    private Deck deck;
    // creates player's arraylist of cards
    private ArrayList<Card> pCard[];
    // creates computer's arraylist of cards
    private ArrayList<Card> cCard[];
    // player's name
    private String name;
    // constructor
    public Game() {
        // creates a deck with cards that have ranks, suits, and points
        String[] ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] point = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        this.deck = new Deck (ranks, suits, point);
        // asks for user's name
        System.out.println("What is your name?");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        // initialize players
        this.player = new Player(name);
        // adds cards to deck
        for(int i = 0; i < 52; i++) {
            if (i <= 26) {
                // gives player a hand of 26 cards
                this.player.addCard(this.deck.deal());
            }
            else {
                this.computer.addCard(this.deck.deal());
            }
        }
    }

    public static void printInstructions() {
        System.out.println("WAR: The deck is divided in half with 26 cards given to each person, face down. The players flip their top cards over and the player with \nthe higher of the two cards will get both cards. If both cards have the same rank, each player will draw 3 cards face down. Each player\nchooses one card to flip over and the player with the higher card gets all the cards.\n\n");
    }

    public void playGame() {
        printInstructions();
        System.out.println("Let's Go! " + name + ", draw a card. (type 'd')");
        Scanner scan = new Scanner(System.in);
        // while there are things left in the deck
        while (!this.deck.isEmpty()) {
            if (!scan.nextLine().isEmpty()) {
                // reads player's draw
                System.out.println(pCard.getRank());
                // remove card from arraylist
                pCard.remove();
                // reads computer's draw
                System.out.println("Computer draws:");
                // remove card from arrayList
                System.out.println();
                // if player's card was higher announce they won and add to their score
                player.addPoints(1);
                // if computer's card was higher announce they won and add to their score
                // if it was a tie
                System.out.println();
            }
            // if all the cards are used up
            else {
                // add up the player's score and the computer's score
                // if player won announce they won
                // if computer won, announce they won
                // if it was a tie, announce the tie and play another round
            }
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
