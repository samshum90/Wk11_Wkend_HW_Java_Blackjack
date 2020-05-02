import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DealerTest {

    private Dealer dealer;
    private Card card;
    private boolean bust;

    @Before
    public void before(){
        this.dealer = new Dealer("Jo");
        this.card = new Card( Suit.SPADES, Rank.ACE );
        this.bust = false;
    }
    @Test
    public void hasName() {
        assertEquals( "Jo", dealer.getName());
    }

    @Test
    public void startsOutEmpty(){
        assertEquals( 0, dealer.handCount());
    }

    @Test
    public void canTakeCard(){
        dealer.takeCard(card);
        assertEquals( 1, dealer.handCount());
    }

    @Test
    public void canShowCard(){
        dealer.takeCard(card);
        assertEquals( "ACE of SPADES", dealer.showCard(0));
    }

    @Test
    public void dealerHandTotal(){
        dealer.takeCard(card);
        assertEquals(11, dealer.handTotal());
    }

    @Test
    public void hasBust(){
        assertFalse(dealer.getBust());
    }
    @Test
    public void hasBusted(){
        dealer.busted();
        assertTrue(dealer.getBust());
    }

    @Test
    public void getFirstCardValue(){
        dealer.takeCard(card);
        assertEquals(11, dealer.showFirstCardValue());
    }
}
