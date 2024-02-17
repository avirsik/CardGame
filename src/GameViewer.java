import javax.swing.*;
import java.awt.*;

/**
 * GameViewer - The front end of a game of War
 * Built by Annie Virsik on January 9, 2024
 * This program runs the card game War.
 */

public class GameViewer extends JFrame {
    private Game game;
    private Image[] cards;
    private Image background;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private static final int INSTRUCT_Y_START = 375;
    private Image backCard;
    private Image instructionsBackground;
    private Image computerWonBackground;
    private Image playerWonBackground;
    private Image winningBackground;

    // Constructor that sets up the window and creates the images
    public GameViewer(Game game) {
        this.game = game;

        this.cards = new Image[52];
        for (int i = 0; i < 52; i++)
        {
            cards[i] = new ImageIcon("Resources/" + i + ".png").getImage();
        }

        this.background = new ImageIcon("Resources/background.png").getImage();
        this.instructionsBackground = new ImageIcon("Resources/instructionsBackground.png").getImage();
        this.computerWonBackground = new ImageIcon("Resources/computerWonBackground.png").getImage();
        this.playerWonBackground = new ImageIcon("Resources/playerWonBackground.png").getImage();
        this.winningBackground = new ImageIcon("Resources/winningBackground.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Game Viewer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        if (game.getState().equals("instructions")) {
            // Prints window background
            g.drawImage(instructionsBackground, 0, 0, this);

            // Writes instructions
            g.setColor(Color.black);
            g.setFont(new Font("Serif", Font.BOLD, 60));
            g.drawString("WELCOME TO WAR!", 225, 120);
            g.setColor(Color.black);
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString("Rules of the Game: ", 75, 320);
            // Prints instructions
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.drawString("Half the deck is given to the player, the other half is given to the", 75, INSTRUCT_Y_START);
            g.drawString("computer and just as the cards would be faced down in a normal", 75, INSTRUCT_Y_START+30);
            g.drawString("game of war, you will not be able to know the rank or suit of your", 75, INSTRUCT_Y_START+60);
            g.drawString("cards. Flip your top card over and the computer will do the same.", 75, INSTRUCT_Y_START+90);
            g.drawString("The player with the higher rank of the two cards will keep both. Ace", 75, INSTRUCT_Y_START+120);
            g.drawString("counts as 1. The goal is to have all the cards by the end of the game.", 75, INSTRUCT_Y_START+150);
            g.drawString("However, if the rank of yours and the computer's cards is the same,", 75, INSTRUCT_Y_START+180);
            g.drawString("you and the computer will randomly choose 1 out of 3 cards with", 75, INSTRUCT_Y_START+210);
            g.drawString("unknown rank and suit. Whoever has the higher card gets to keep all", 75, INSTRUCT_Y_START+240);
            g.drawString("8 cards.", 75, INSTRUCT_Y_START+270);
            // Tells user to type in their name
            g.setColor(Color.blue);
            g.drawString("What is your name?", 350, 690);
        }
        // If the user typed in a name repaint window background
        else {
            gameScreen(g);
//            if (game.getWinner().equals("player")) {
//                g.drawString("YOU WON!!!", 325, 670);
//            }
//            else if (game.getWinner().equals("computer")) {
//                g.drawString("THE COMPUTER WON THE GAME!!!", 325, 670);
//            }
//            else {
//                g.drawString("IT'S A TIE!", 325, 670);
//            }
        }

    }

    public void gameScreen(Graphics g) {
        // Redraw background
        g.drawImage(background, 0, 0, this);
        // Tells the user to draw a card
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.drawString("DRAW A CARD (press d)", 325, 400);
        if (game.getState().equals("play")) {
            // Redraw background
            g.drawImage(background, 0, 0, this);
            // Create back of card image
            backCard = new ImageIcon("Resources/back.png").getImage();
            // Prints back of cards
            g.drawImage(backCard, 460, 50, 75, 125, this);
            g.drawImage(backCard, 460, 600, 75, 125, this);
            // Prints the front of both cards
            game.getCCard().draw(g, 460, 225);
            game.getPCard().draw(g, 460, 425);
            // Prints out the message that says whose card it is
            g.drawString("Computer's Card:", 150, 300);
            g.drawString("Your Card:", 150, 500);
            // Says who won
            g.drawString(game.greatestCard(game.getPCard(), game.getCCard()), 500, 400);
            // Displays round number
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString("Round: " + game.getRoundNum(), 825, 75);
            // Displays each player's points
            g.setFont(new Font("Serif", Font.BOLD, 20));
            g.drawString(("Score: " + game.getPlayer().getPoints()), 170, 325);
            g.drawString(("Score: " + game.getPlayer().getPoints()), 170, 525);
        }
        // If the game has been won, prints out winning screen for whoever won
        if (game.getIsOver() == true) {
            if (game.getWinner().equals("YOU WON! CONGRATS!!!")) {
                g.drawImage(playerWonBackground, 0, 0, this);
            }
            else if (game.getWinner().equals("THE COMPUTER WON! YOU LOST! GAME OVER")) {
                g.drawImage(computerWonBackground, 0, 0, this);
            }
            else {
                g.drawImage(winningBackground, 0, 0, this);
            }
//            g.setFont(new Font("Serif", Font.BOLD, 40));
//            g.drawString(game.getWinner(), 200, 200);
        }
    }
}
