import java.util.Scanner;
public class Game {
    // Instance variables
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
    private int roundNum = -1;
    private String winner;

    // Constructor
    public Game() {
        gv = new GameViewer(this);
        state = "instructions";
        // Prints instructions
        printInstructions();
        // Creates a deck with cards that have ranks, suits, and points
        String[] ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Spades", "Hearts",  "Diamonds", "Clubs"};
        int[] point = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        // Initialize deck class
        this.deck = new Deck (ranks, suits, point);
        // Shuffles deck
        deck.shuffle();
        // Asks for user's name
        // Read in user input and save it to an appropriate variable using Scanners and .nextLine/Int/Double
        System.out.println("What is your name?");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        state = "draw";
        // Initialize players + computer
        this.player = new Player(name);
        this.computer = new Player("computer");
        // Adds cards to deck
        for(int i = 0; i < 26; i++) {
            this.player.addCard(this.deck.deal());
            this.computer.addCard(this.deck.deal());
        }
        gv.repaint();
    }
    // Getter methods
    public String getState() {
        return this.state;
    }

    public Card getPCard() {
        return this.pCard;
    }

    public Card getCCard() {
        return this.cCard;
    }
    public String getRoundNum() {
        return "" + roundNum;
    }
    public String getWinner() {
        return winner;
    }
    public Player getPlayer() {
        return this.player;
    }
    public boolean getIsOver() {
        return isOver;
    }
    // Prints instructions
    public static void printInstructions() {
        System.out.println("INSTRUCTIONS FOR THE GAME OF WAR: \nHalf the deck is given to the player, the other half is given to the computer and just as the cards would be faced down in a normal\n" +
                                                                "game of war, you will not be able to know the rank or suit of your cards. Flip your top card over and the computer will do the same.\n" +
                                                                "The player with the the higher rank of the two cards will keep both. Ace counts as 1. The goal is to have all the cards by the end of\n" +
                                                                "the game. However, if the rank of yours and the computer's cards is the same, you and the computer will randomly choose 1 out of 3\n" +
                                                                "cards with unknown rank and suit. Whoever has the higher card gets to keep all 8 cards.\n\n");
    }

    public void playGame() {
        while (!this.player.getHand().isEmpty() && !this.computer.getHand().isEmpty() && !isOver) {
            compareCards();
        }
        // Check if the game has been won by checking if there are no cards left in the hands
        checkWon();
        if (isOver) {
            return;
        }
        return;
    }

    // Compares the user's and computer's cards
    public void compareCards() {
        System.out.println(name + ", draw a card. (type 'd')");
        roundNum++;
        // When the user draws a card
        if (!scan.nextLine().isEmpty()) {
            state = "play";
            gv.repaint();
            // Reads player's draw
            pCard = this.player.getHand().remove(0);
            System.out.println("You drew: " + pCard);
            // Reads computer's draw
            cCard = this.computer.getHand().remove(0);
            System.out.println("The computer drew: " + cCard);
            // Prints out the greatest card
            System.out.println(greatestCard(pCard, cCard));
            // Read player + computer's points
            System.out.println(name + "'s score: " + player.getPoints());
            System.out.println("computer's score: " + computer.getPoints() + "\n");
        }
    }

    // Returns the card that wins
    public String greatestCard(Card p, Card c) {
        if (p.getPoint() > c.getPoint()) {
            player.addPoints(2);
            return "YOUR CARD WINS!";
        }
        else if (c.getPoint() > p.getPoint()) {
            computer.addPoints(2);
            return "THE COMPUTER'S CARD WINS!";
        }
//         If the points are equal, it is a tie
        else {
            cardTie();
        }
        return "";
    }

    // Deals with ties between cards, adding 4 points to both players
    public void cardTie() {
        System.out.println("IT's A TIE! You and the computer both get 4 points!\n");
        player.addPoints(4);
        computer.addPoints(4);
    }

    // Checks to see if someone has won the entire game
    public void checkWon() {
        // If player won announce they won
        if (player.getPoints() > computer.getPoints()) {
            System.out.println("YOU WON! CONGRATS!!!");
            isOver = true;
            winner = "YOU WON! CONGRATS!!!";
            return;
        }
        // If computer won, announce they won
        else if (computer.getPoints() > player.getPoints()) {
            System.out.println("THE COMPUTER WON! YOU LOST!\nGAME OVER");
            isOver = true;
            winner = "THE COMPUTER WON! YOU LOST! GAME OVER";
            return;
        }
        // If it was a tie, announce the tie
        winner = "IT WAS A TIE!";
        System.out.println("It was a TIE!");
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
