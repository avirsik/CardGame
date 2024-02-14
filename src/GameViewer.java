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
    private static final int INSTRUCT_Y_START = 280;
    private Image backCard;

    // Constructor that sets up the window and creates the images
    public GameViewer(Game game) {
        this.game = game;

        this.cards = new Image[52];
        for (int i = 0; i < 52; i++)
        {
            cards[i] = new ImageIcon("Resources/1.png").getImage();
        }

        this.background = new ImageIcon("Resources/background.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Game Viewer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        // Prints window background
        g.drawImage(background, 0, 0, this);

        // Writes instructions
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 50));
        g.drawString("Welcome to WAR!", 300, 120);
        g.drawString("Rules of the Game: ", 75, 225);

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
        g.drawString("WHAT IS YOUR NAME", 325, 670);
        // If the user typed in a name repaint window background
        // could also create a state over, in game, instructions...i
        if (game.getState().equals("draw")) {
            gameScreen(g);
        }
    }

    public void gameScreen(Graphics g) {
        // Redraw background
        g.drawImage(background, 0, 0, this);
        g.drawString("Draw a card (press d)", 300, 120);
        if (game.getState().equals("play")) {
            // Redraw background
            g.drawImage(background, 0, 0, this);
            // Create back of card image
            backCard = new ImageIcon("Resources/back.png").getImage();
            // Prints back of cards
            g.drawImage(backCard, 460, 50, 75, 125, this);
            g.drawImage(backCard, 460, 600, 75, 125, this);
            // HOW DO I PRINT OUT THE SAME CARD THAT MY MATH.RANDOM GAVE ME?
            // Prints the front of both cards
            g.drawImage(cards[0], 460, 175, 75, 125, this);
            g.drawImage(cards[1], 460, 475, 75, 125, this);
//          game.getPCard();
        }
    }
}
