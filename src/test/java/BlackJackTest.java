import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlackJackTest {

    private Player player1;
    private Player player2;
    private Dealer dealer;
    private Deck deck;
    private Blackjack blackjack;
    private Card highCard;
    private Card lowCard;

    @Before
    public void setup(){
        player1 = new Player("Edwin");
        player2 = new Player("Sid");
        dealer = new Dealer("Jo");
        deck = new Deck();
        blackjack = new Blackjack(deck, dealer);
        blackjack.addPlayer(player1);
        blackjack.addPlayer(player2);

        highCard = new Card(Suit.SPADES, Rank.KING);
        lowCard = new Card(Suit.SPADES, Rank.TWO);
    }
    @Test
    public void gameHasPlayer(){
        assertEquals( 2, blackjack.playerCount());
    }


    @Test
    public void canRemovePlayer(){
        blackjack.removePlayer(player1);
        assertEquals( 1, blackjack.playerCount());
    }

    @Test
    public void getDealerName(){

        assertEquals( "Jo", blackjack.getDealerName());
    }

    @Test
    public void startGame(){
        blackjack.startGame();
        assertEquals(2, player1.handCount());
        assertEquals(2, player2.handCount());
        assertEquals( 2, dealer.handCount());
        assertEquals( 46, deck.cardCount());
    }

    @Test
    public void playerCanGoBust(){
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        assertTrue(blackjack.checkPlayerBust());
    }

    @Test
    public void canCheckPlayerIsBust(){
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        assertTrue(blackjack.bustCheck(player1));
    }

    @Test
    public void dealerCanGoBust(){
        dealer.busted();
        assertTrue(blackjack.dealerBustCheck());
    }

    @Test
    public void canPlayerTwist(){
        blackjack.twistPlayer(player1);
        assertEquals( 1, player1.handCount());
        assertEquals( 51, deck.cardCount());
    }


    @Test
    public void checkDraw(){
        player1.takeCard(highCard);
        player2.takeCard(highCard);
        dealer.takeCard(highCard);
        assertTrue(blackjack.checkDraw());
    }

    @Test
    public void checkPlayerWinner(){
        player1.takeCard(highCard);
        player2.takeCard(lowCard);
        assertEquals( player1, blackjack.playerWinner());
    }

    @Test
    public void everyOneLoses(){
        player1.busted();
        dealer.busted();
        assertEquals( "Players Lose", blackjack.checkWinner( player1 ));
    }

    @Test
    public void canplayerWinWithBustedDealer(){
        player1.takeCard(highCard);
        player2.takeCard(lowCard);
        dealer.takeCard(highCard);
        dealer.takeCard(highCard);
        dealer.takeCard(highCard);
        dealer.busted();
        Player winner = blackjack.playerWinner();
        assertEquals( "Player Edwin is the Winner!", blackjack.checkWinner( winner ));
    }

    @Test
    public void canDealerWins(){
        player1.takeCard(lowCard);
        player2.takeCard(lowCard);
        dealer.takeCard(highCard);
        dealer.takeCard(highCard);
        Player winner = blackjack.playerWinner();
        assertEquals( "Dealer Jo is the Winner!", blackjack.checkWinner( winner ));
    }
    @Test
    public void canDraw(){
        player1.takeCard(highCard);
        player2.takeCard(highCard);
        dealer.takeCard(highCard);
        Player winner = blackjack.playerWinner();
        assertEquals( "Player Edwin and Dealer Jo draw", blackjack.checkWinner( winner ));
    }

    @Test
    public void canPlayerWin(){
        player1.takeCard(highCard);
        player2.takeCard(highCard);
        dealer.takeCard(lowCard);
        Player winner = blackjack.playerWinner();
        assertEquals( "Player Edwin is the Winner!", blackjack.checkWinner( winner ));
    }

}
