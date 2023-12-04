public class Game {
    public static void main(String[] args) {
        String[] ranks = {"A", "2", "3"};
        String[] suits = {"Hearts", "Clubs"};
        int[] points = {1,2,3};

        Deck d = new Deck(ranks, suits, points);
    }
}
