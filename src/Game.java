import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    // instance variables
    private Player player;
    private Player computer;
    // creates player's deck
    private Deck deck;
//    // player's name
    private String name;

    Scanner scan = new Scanner(System.in);

    // constructor
    public Game() {
        printInstructions();
        // creates a deck with cards that have ranks, suits, and points
        String[] ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] point = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        this.deck = new Deck (ranks, suits, point);
        deck.shuffle();
        // asks for user's name
        System.out.println("What is your name?");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        // initialize players + computer
        this.player = new Player(name);
        this.computer = new Player("computer");
        // adds cards to deck
        for(int i = 0; i < 26; i++) {
            this.player.addCard(this.deck.deal());
            this.computer.addCard(this.deck.deal());
        }
    }

    public static void printInstructions() {
        System.out.println("WAR: The deck is divided in half with 26 cards given to each person, face down. The players flip their top cards over and the player with \nthe higher of the two cards will get both cards. If both cards have the same rank, each player will draw 3 cards face down. Each player\nchooses one card to flip over and the player with the higher card gets all the cards.\n\n");
    }

    public void playGame() {
        Scanner scan = new Scanner(System.in);
        // while there are things left in the deck
        while (!this.player.getHand().isEmpty() && !this.computer.getHand().isEmpty()) {
            System.out.println(name + ", draw a card. (type 'd')");
            if (!scan.nextLine().isEmpty()) {
                // reads player's draw
                Card pCard = this.player.getHand().remove(0);
                System.out.println("You drew: " + pCard);
                // reads computer's draw
                Card cCard = this.computer.getHand().remove(0);
                System.out.println("The computer drew: " + cCard+"\n");
                System.out.println(greatestCard(pCard, cCard));
                // read player + computer's points
                System.out.println(name + "'s score: " + player.getPoints());
                System.out.println("computer's score: " + computer.getPoints() + "\n");
            }
            else {
                checkWon();
            }
        }
    }

    // returns the card with the greatest value
    public String greatestCard(Card p, Card c) {
        if (p.getPoint() > c.getPoint()) {
            player.addPoints(2);
            return "Your Card Wins!\n";
        }
        else if (c.getPoint() > p.getPoint()) {
            computer.addPoints(2);
            return "The Computer's Card Wins!\n";
        }
        // if it's a tie
        else {
            cardTie();
        }
        return "";
    }

    public void checkWon() {
        // if player won announce they won
        if (player.getPoints() > computer.getPoints()) {
            System.out.println("YOU WON! CONGRATS!!!");
            return;
        }
        // if computer won, announce they won
        else if (computer.getPoints() > player.getPoints()) {
            System.out.println("The Computer Won! You Lost!");
            return;
        }
        // if it was a tie, announce the tie and play another round
        System.out.println("It was a TIE!");
        playGame();
    }

    // deals with ties between cards
    public void cardTie() {
        Scanner scan = new Scanner(System.in);
        Card p = this.player.getHand().remove(0);
        Card c = this.computer.getHand().remove(0);
        System.out.println("IT's A TIE! Pick one card out of the three: (type 1, 2, or 3)\n\nMystery Card 1\nMystery Card 2\nMystery Card 3");
        String w = scan.nextLine();
        if (w.equals("1") || w.equals("2") || w.equals("3")) {
            System.out.println("You drew: " + p);
        }
        System.out.println("The computer drew: " + c);
        System.out.println(greatestCard(p, c));

        playGame();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
