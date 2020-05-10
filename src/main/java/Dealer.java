import java.util.ArrayList;

public class Dealer {

    private String name;
    private ArrayList<Card> hand;
    private boolean bust;



    public Dealer(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.bust = false;
    }

    public String getName(){
        return this.name;
    }

    public int handCount() {
        return this.hand.size();
    }

    public void takeCard(Card card) {
        this.hand.add(card);
    }

    public String showCard(int i) {
        return this.hand.get(i).cardName();
    }

    public int handTotal() {
        int total = 0;
        for(Card card : this.hand){
            total += card.getValueFromCard();
        }
        return total;
    }

    public boolean getBust() {
        return this.bust;
    }

    public void busted() {
        this.bust = true;

    }

    public int showFirstCardValue() {
        return this.hand.get(0).getValueFromCard();
    }

    public String showFirstCard() {
        return this.hand.get(0).cardName();
    }
}
