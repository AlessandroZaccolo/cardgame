package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {


    public enum State { OPEN, PLAYING }

    private final Deck deck;
    private State state;
    private List<String> usernames;
    private Map<String, Card> pickedCardByUsername;
    private int countDiscarded;
    private int countPicked;
    private List<Card> keptCard;

    public Game(Deck deck) {
        this.deck = deck;
        this.state = State.OPEN;
        this.usernames = new ArrayList<>();
        this.pickedCardByUsername = new HashMap<>();
        this.countDiscarded = 0;
        this.countPicked = 0;
        this.keptCard = new ArrayList<>();
    }

    public State getState() {
        return state;
    }

    public void join(String username) {

        if (state != State.OPEN) {
            throw new JoiningNotAllowedException();
        }

        usernames.add(username);

        if (usernames.size() == 2) {
            state = State.PLAYING;
        }
    }

    public List<String> getPlayerNames() {
        return usernames;
    }

    public Card pickCard(String username) {

        if (state == State.OPEN){
            throw new CannotPickCardsInStateOpen();
        }

        if (!usernames.contains(username)) {
            throw new PlayerNotInTheGameException();
        }

        Card pickedCard = pickedCardByUsername.get(username);
        if (pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }


        Card newPickedCard = deck.pickCard();

        pickedCardByUsername.put(username, newPickedCard);

        countPicked++;

        if(countPicked > 3){
            throw new NotAllowToPickMoreThan3Cards();
        }

        return newPickedCard;
    }

    public void discard(String username) {

        Card pickedCard = pickedCardByUsername.get(username);
        if (pickedCard == null){
            throw new AllowDiscardOnlyIfCardIsPickedBefore();
        }

        pickedCardByUsername.remove(username);


        countDiscarded++;

        if(countDiscarded > 2){
            throw new AllowDiscardNoMoreThan2Cards();
        }

}

    public List<Card> KeptCardByUser(String username){

        Card pickedCard = pickedCardByUsername.get(username);

        keptCard.add(pickedCard);

        return keptCard;
    }













    /**
     * Returns a negative integer, zero, or a positive integer
     * as the first hand is less than, equal to,
     * or greater than the second hand.
     *
     private int compare(Hand hand1, Hand hand2) {

     int points1 = 0;
     int points2 = 0;

     Card total1 = hand1.calculate();
     Card total2 = hand2.calculate();

     if (total1.getMagic() > total2.getMagic()) {
     points1++;
     } else if (total1.getMagic() < total2.getMagic()) {
     points2++;
     }

     // TODO: do the same with strength and intelligence

     return points1 - points2;
     }
     */
}