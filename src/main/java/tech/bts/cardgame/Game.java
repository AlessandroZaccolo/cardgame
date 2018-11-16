package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private final Deck deck;
    private String state;
    private List<String> usernames;

    public Game(Deck deck) {
        this.deck = deck;
        this.state = "open";
        this.usernames = new ArrayList<>();
    }

    public String getState() {
        return state;
    }


    public void join(String username){
        usernames.add(username);

    }

    public List<String> getPlayerNames(){
        return usernames;
    }









    // Returns a negative integer, sero, or a positive integer
    // as the first hand is less than, equal to,
    // or grater than the second hand.



    /*

    private static int compare(Hand hand1, Hand hand2){

        int points1 = 0;
        int points2 = 0;

        Card total1 = hand1.calculate();
        Card total2 = hand1.calculate();

        if (total1.getMagic() > total2.getMagic()){
            points1++;
        } else if (total1.getMagic() < total2.getMagic()){
            points2++;
        }

        if (total1.getStrength() > total2.getStrength()){
            points1++;
        } else if (total1.getStrength() < total2.getStrength()){
            points2++;
        }

        if (total1.getIntelligence() > total2.getIntelligence()){
            points1++;
        } else if (total1.getIntelligence() < total2.getIntelligence()){
            points2++;
        }

        return points1 - points2;

    }
    */
}
