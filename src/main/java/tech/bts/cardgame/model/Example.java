package tech.bts.cardgame.model;

public class Example {

    public static void main(String[] args) {

        Deck deck = new Deck();
        deck.generate();
        deck.shuffle();

        Card card = deck.pickCard();

        // System.out.println("I've picked the card: " + card);

        Player player1 = new Player("john");
        Player player2 = new Player("mary");

        Game game = new Game(deck);

        System.out.println(game.toString());



    }
}