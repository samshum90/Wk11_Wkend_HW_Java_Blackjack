import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private Deck deck;
    private Card card;

    @Before
    public void before() {
       player = new Player("bob");
       deck = new Deck();
       card = new Card( Suit.DIAMONDS, Rank.KING);
    }

    @Test
    public void startsOutEmpty(){
        assertEquals( 0, player.handCount());
    }

    @Test
    public void canTakeCard(){
        player.takeCard(card);
        assertEquals( 1, player.handCount());
    }

    @Test
    public void canShowCard(){
        player.takeCard(card);
        assertEquals( "KING of DIAMONDS", player.showCard(0));
    }

    @Test
    public void playerHandTotal(){
        player.takeCard(card);
        assertEquals(10, player.handTotal());
    }

    @Test
    public void hasStick(){
        assertFalse(player.getStatus());
    }
    @Test
    public void hasStuck(){
        player.stuck();
        assertTrue(player.getStatus());
    }

    @Test
    public void hasBust(){
        assertFalse(player.getBust());
    }
    @Test
    public void hasBusted(){
        player.busted();
        assertTrue(player.getBust());
    }

}
