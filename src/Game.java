import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    // Can write a class containing instance variables, constructors, and methods, using access modifiers (private vs public) appropriately.
    // instance variables
    private Player player;
    private Player computer;
    private Deck deck;
    private String name;
    private Scanner scan = new Scanner(System.in);
    private boolean isOver = false;
    private GameViewer gv;
    // State of the game
    private String state;
    private Card pCard;
    private Card cCard;

    // constructor
    public Game() {
        gv = new GameViewer(this);
        state = "instructions";
        // prints instructions
        printInstructions();
        // creates a deck with cards that have ranks, suits, and points
        // Can declare and initialize Arrays, ArrayLists, and 2D Arrays.
        String[] ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] point = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        // initialize deck class
        this.deck = new Deck (ranks, suits, point);
        // shuffles deck
        deck.shuffle();
        // asks for user's name
        // Read in user input and save it to an appropriate variable using Scanners and .nextLine/Int/Double
        System.out.println("What is your name?");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        state = "draw";
        // initialize players + computer
        this.player = new Player(name);
        this.computer = new Player("computer");
        // adds cards to deck
        // Can use if, while, and for.
        // Can write algorithms to traverse and modify Arrays and ArrayLists.
        // Can use ArrayList methods.
        for(int i = 0; i < 26; i++) {
            this.player.addCard(this.deck.deal());
            this.computer.addCard(this.deck.deal());
        }
        gv.repaint();
    }

    // could add # 1 or 2 depending on if it was computer or player we needed to know
    public Player getPlayer() {
        return player;
    }

    public String getState() {
        return this.state;
    }

    public Card getPCard() {
        return this.pCard;
    }

    public Card getCCard() {
        return this.cCard;
    }

    // Understands how the keyword static affects methods and variables.
    public static void printInstructions() {
        System.out.println("INSTRUCTIONS FOR THE GAME OF WAR: \nHalf the deck is given to the player, the other half is given to the computer and just as the cards would be faced down in a normal\n" +
                                                                "game of war, you will not be able to know the rank or suit of your cards. Flip your top card over and the computer will do the same.\n" +
                                                                "The player with the the higher rank of the two cards will keep both. Ace counts as 1. The goal is to have all the cards by the end of\n" +
                                                                "the game. However, if the rank of yours and the computer's cards is the same, you and the computer will randomly choose 1 out of 3\n" +
                                                                "cards with unknown rank and suit. Whoever has the higher card gets to keep all 8 cards.\n\n");
    }

    public void playGame() {
        // while there are still cards left in the player and computer's hand
        // Can use if, while, and for.
        // Can use ArrayList methods.
        while (!this.player.getHand().isEmpty() && !this.computer.getHand().isEmpty()) {
            compareCards();
        }
        // if there is nothing left in the hands
        checkWon();
        // Can use if, while, and for.
        if (isOver) {
            return;
        }
        return;
    }

    // compares the user's and computer's cards
    public void compareCards() {
        System.out.println(name + ", draw a card. (type 'd')");
        // when the user draws
        // Can use if, while, and for.
        // Can use ArrayList methods.
        if (!scan.nextLine().isEmpty()) {
            state = "play";
            // reads player's draw
            pCard = this.player.getHand().remove(0);
            System.out.println("You drew: " + pCard);
            // reads computer's draw
            cCard = this.computer.getHand().remove(0);
            System.out.println("The computer drew: " + cCard);
            // prints out the greatest card
            System.out.println(greatestCard(pCard, cCard));
            // read player + computer's points
            System.out.println(name + "'s score: " + player.getPoints());
            System.out.println("computer's score: " + computer.getPoints() + "\n");
        }
    }

    // returns the card with the greatest value
    public String greatestCard(Card p, Card c) {
        // Can use if, while, and for.
        // Can write algorithms to traverse and modify Arrays and ArrayLists.
        if (p.getPoint() > c.getPoint()) {
            player.addPoints(2);
            return "YOUR CARD WINS!";
        }
        else if (c.getPoint() > p.getPoint()) {
            computer.addPoints(2);
            return "THE COMPUTER'S CARD WINS!";
        }
        // if it's a tie
        else {
            cardTie();
        }
        return"";
    }

    // deals with ties between cards
    public void cardTie() {
        // tells the user to type 1, 2, or 3 and only accepts those replies
        System.out.println("IT's A TIE! Pick one card out of the three: (type 1, 2, or 3)\n\nMystery Card 1\nMystery Card 2\nMystery Card 3");
        Card p = this.player.getHand().remove(0);
        Card c = this.computer.getHand().remove(0);
        // if the user picks one of the cards
        // Understand how to use nesting to embed loops and conditionals inside of other loops and conditionals.
        // Can use if, while, and for.
        if (!scan.nextLine().isEmpty()) {
            // Check to see if it is a tie in a tie
            if (p.getPoint() == c.getPoint()) {
                player.addPoints(4);
                computer.addPoints(4);
                System.out.println("IT'S A TIE IN A TIE! YOU AND THE COMPUTER BOTH GET 4 POINTS!");
                return;
            }
            // reads the player's card
            System.out.println("You drew: " + p);
            // reads the computer's card
            System.out.println("The computer drew: " + c);
        }
        else {
            System.out.println("Pick one card out of the three: (type '1', '2', or '3')\n\nMystery Card 1\nMystery Card 2\nMystery Card 3");
        }
        // adds correct amount of points
        if (p.getPoint() > c.getPoint()) {
            player.addPoints(8);
            System.out.println("YOUR CARD WINS!");
        }
        else if (c.getPoint() > p.getPoint()) {
            computer.addPoints(8);
            System.out.println("THE COMPUTER'S CARD WINS!");
        }

        // Understand how to use nesting to embed loops and conditionals inside of other loops and conditionals.
        for (int i = 0; i < 3; i++) {
            // while there are cards left in the hand
            if (!this.player.getHand().isEmpty() && !this.computer.getHand().isEmpty()) {
                // remove 3 more cards for a total of 8 cards removed
                this.player.getHand().remove(0);
                this.computer.getHand().remove(0);
            }
            // if there are no cards left in the hand
            else {
                checkWon();
            }
        }
    }

    // checks to see if someone has won the entire game
    public void checkWon() {
        // if player won announce they won
        if (player.getPoints() > computer.getPoints()) {
            System.out.println("YOU WON! CONGRATS!!!");
            isOver = true;
            return;
        }
        // if computer won, announce they won
        else if (computer.getPoints() > player.getPoints()) {
            System.out.println("THE COMPUTER WON! YOU LOST!\nGAME OVER");
            isOver = true;
            return;
        }
        // if it was a tie, announce the tie and play another round
        System.out.println("It was a TIE!");
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
