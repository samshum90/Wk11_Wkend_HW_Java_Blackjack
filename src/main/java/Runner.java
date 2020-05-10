import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Dealer dealer = new Dealer("Jo");
        Deck deck = new Deck();
        Blackjack blackjack = new Blackjack(deck, dealer);


// Adds players
        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players would you like to play?");

        String numOfPlayers = scanner.next();
        int players = parseInt(numOfPlayers);
        for (int i = 0; i < players; i++) {
            String prompt = String.format("Player %s, enter your name: ", (i + 1));
            System.out.println(prompt);
            String playerName = scanner.next();
            Player player = new Player(playerName);
            blackjack.addPlayer(player);
        }

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