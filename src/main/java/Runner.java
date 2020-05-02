import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Dealer dealer = new Dealer("Jo");
        Deck deck = new Deck();
        Blackjack blackjack = new Blackjack(deck, dealer);


// Adds players
        blackjack.createPlayers();

// Deals out cards
        blackjack.startGame();
//play Game
        blackjack.playGame();
//dealer Play
        blackjack.dealerPlay();


        Player winningPlayer = blackjack.playerWinner();
        String result = blackjack.checkWinner(winningPlayer);
        System.out.println(result);

    }
}