import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    private Deck deck;
    private Card card;

    @Before
    public void before() {
        deck = new Deck();
    }

    @Test
    public void addCardToDeckPile(){
        assertEquals(52, deck.cardCount());
    }

    @Test
    public void canDealOne(){
        deck.dealOne();
        assertEquals( 51, deck.cardCount() );
    }
}
