import java.util.Scanner;
public class Game {
    // instance variables
    private Player player;
    private Player computer;
    private Deck deck;
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
        String name = scan.nextLine();
        // initialize players
        g.Player(name);
        for(int i = 0; i < 26; i++) {
            g.addCard();
        }
    }

    public static void printInstructions() {
        System.out.println("The deck is divided in half with 26 cards given to each person, face down.\nThe players flip their top cards over and the player with the higher of the two cards will get both cards. If both cards have the same rank, each player will draw 3 cards face down. Each player chooses one card to flip over and the player with the higher card gets all the cards.")
    }

    public void playGame() {

    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
