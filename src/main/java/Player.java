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


//  We want a method to return a integer with the total value of the cards in my hand
    public int handTotal() {
//  Initialize total to zero
        int total = 0;
//  for every card in the hand Array list
        for(Card card : this.hand){
//  add the value from the card and add it to the total
            total += card.getValueFromCard();
        }
//  return the total
        return total;
    }

    public String getName() {
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
