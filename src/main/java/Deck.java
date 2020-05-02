import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deckPile;


    public Deck(){
        this.deckPile = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                this.deckPile.add(card);
            }
        }
        Collections.shuffle(this.deckPile);
    }


    public int cardCount() {
        return this.deckPile.size();
    }


    public Card dealOne() {
        return this.deckPile.remove(0);
    }
}
