import java.util.Scanner;
import java.util.ArrayList;
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
        for(int i = 0; i < 3; i++) {
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
        //
        while (!this.player.getHand().isEmpty() && !this.computer.getHand().isEmpty() && isOver == false) {
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

    // Returns the card with the greatest value
    public String greatestCard(Card p, Card c) {
//        if (p.getPoint() > c.getPoint()) {
//            player.addPoints(2);
//            return "YOUR CARD WINS!";
//        }
//        else if (c.getPoint() > p.getPoint()) {
//            computer.addPoints(2);
//            return "THE COMPUTER'S CARD WINS!";
//        }
        // If it's a tie
//        else {
////             DELETE THIS IS JUST TO TEST FOR A TIE PROBLEM 2/16 9:00
//            c.setPoint(10);
//            p.setPoint(10);
            cardTie();
//        }
        return "";
    }

    // Deals with ties between cards
    public void cardTie() {
        // Tells the user to type 1, 2, or 3 and only accepts those replies
        System.out.println("IT's A TIE! You have picked one card out of three.\n");
        // If the user types something, checks to see if it is a tie in a tie - adds the correct number of points, writes statement
//        if (!scan.nextLine().isEmpty()) {
            // If there are still cards left in the deck
            // START HERE FIGURE OUT WHY I WAS LOOPING THREE TIMES AND TRY TO FIX THIS. MAYBE CREATE IF STATEMENT FOR IF IT RUNS OUT OF CARDS
            //           for (int i = 0; i < 3; i++) {
            // While there are cards left in the hand
            if (!this.player.getHand().isEmpty() && !this.computer.getHand().isEmpty()) {
                // Remove the next card in each deck to compare them
                Card pCard = this.player.getHand().remove(0);
                Card cCard = this.computer.getHand().remove(0);

                // If the cards are equal announce tie-in-tie
                if (pCard.getPoint() == cCard.getPoint()) {
                    player.addPoints(4);
                    computer.addPoints(4);
                    System.out.println("IT'S A TIE IN A TIE! YOU AND THE COMPUTER BOTH GET 4 POINTS!");
                    return;
                }
                else {
                    // If it is not a tie in a tie, reads the player and computer's cards
                    System.out.println("You drew: " + pCard);
                    System.out.println("The computer drew: " + cCard);

                    // Adds correct amount of points
                    if (pCard.getPoint() > cCard.getPoint()) {
                        player.addPoints(8);
                        System.out.println("YOUR CARD WINS!");
                    }
                    else { //if (cCard.getPoint() > pCard.getPoint())
                        computer.addPoints(8);
                        System.out.println("THE COMPUTER'S CARD WINS!");
                    }
                    return;
                }


                // Remove 3 more cards for a total of 8 cards removed
//            this.player.getHand().remove(0);
//            this.computer.getHand().remove(0);
//          }
            }
        // taking two more
//        this.player.getHand().remove(0);
//        this.computer.getHand().remove(0);


        // If there are no cards left in the hand
        else {
            checkWon();
        }
//            }


//        else {
//            System.out.println("Pick one card out of the three: (type '1', '2', or '3')\n\nMystery Card 1\nMystery Card 2\nMystery Card 3");
//        }

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
