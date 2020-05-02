import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean stick;
    private boolean bust;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.stick = false;
        this.bust = false;
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

    public Object getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.stick;
    }

    public void stuck() {
        this.stick = true;
    }
    public boolean getBust() {
        return this.bust;
    }

    public void busted() {
        this.bust = true;

    }
}
