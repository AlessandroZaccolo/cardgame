package tech.bts.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void add(Card card){ this.cards.add(card);}

    public void generate(){

        for(int m = 1; m <= 8; m++){
            for (int s = 1; s <= (9 - m); s++){
                int i = (10 - (m + s));
                this.add(new Card(m, s, i));
            }
        }
    }

    public void shuffle(){

        System.out.println("Shuffling " + this.cards.size() + " cards");



        Random random = new Random();


        for (int i = 0; i < this.cards.size() - 1; i++){

            // pick a random index and swap it with card at index "i"
            int randomIndex = random.nextInt(this.cards.size());


            System.out.println("Swapping cards at indexes "+ i + " to " + randomIndex);

            Card card1 = this.cards.get(i);
            Card card2 = this.cards.get(randomIndex);

            this.cards.set(randomIndex, card1);
            this.cards.set(i, card2);
            // don't move cards you have already moved (indexes below i)
        }
    }

    public Card pickCard(){
        return cards.remove(cards.size() - 1);
    }

}
