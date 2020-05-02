import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Blackjack {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<Player> players;
    private Dealer dealer;
    private Deck deck;

    public Blackjack(Deck deck, Dealer dealer) {
        this.players = new ArrayList<>();
        this.dealer = dealer;
        this.deck = deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }


    public int playerCount() {
        return this.players.size();
    }

    public void startGame() {
        for (int j = 0; j < 2; j++) {
            Card card = deck.dealOne();
            this.dealer.takeCard(card);
        }
        for (Player player : this.players) {
            for (int i = 0; i < 2; i++) {
                Card card = deck.dealOne();
                player.takeCard(card);
            }
        }
    }


    public boolean checkDraw() {
        boolean drawgame = true;
        int dealerTotal = this.dealer.handTotal();
        for (Player player : this.players) {
            if (player.handTotal() != dealerTotal && !player.getBust() && player.getStatus()) {
                drawgame = false;
            }
        }
        return drawgame;
    }

    public String dealerBustWinner() {
        int highest = 0;
        Player playerWinner = null;
        for (Player player : this.players) {
            if (player.handTotal() > highest && !player.getBust() && player.getStatus()) {
                highest = player.handTotal();
                playerWinner = player;
            }
        }
        return String.format("Player %s is the Winner!", playerWinner.getName());
    }

    public Player playerWinner() {
        int highest = 0;
        Player winner = null;
        for (Player player : this.players) {
            if (player.handTotal() > highest && !player.getBust()) {
                highest = player.handTotal();
                winner = player;
            }
        }
        return winner;
    }


    public String checkWinner( Player player){
        String result = null;
        if (!player.getBust() && !dealer.getBust()){
            if(!dealer.getBust()){
                if (dealer.handTotal() > player.handTotal()){
                    result = String.format("Dealer %s is the Winner!", dealer.getName());
                } else if (dealer.handTotal() == player.handTotal()){
                    result = String.format("Player %s and Dealer %s draw", player.getName(), dealer.getName());
                } else {
                    result = String.format("Player %s is the Winner!", player.getName());
                }
            } else  {
                result = String.format("Player %s is the Winner!", player.getName());
            }
        } else  {
            result = "Everyone Loses";
        }
        return result;
    }

    public boolean checkPlayerBust() {
        boolean bust = false;
        for (Player player : this.players) {
            if (player.handTotal() > 21) {
                bust = true;
            }
        }
        return bust;
    }

    public boolean bustCheck(Player player) {
        boolean bust = false;
        if (player.handTotal() > 21) {
            bust = true;
        }
        return bust;
    }

    public boolean dealerBustCheck() {
        boolean bust = false;
        if (dealer.getBust()) {
            bust = true;
        }
        return bust;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void bustPlayer( Player player) {
        removePlayer(player);
        System.out.println("You went BUST!");
    }

    public void twistPlayer(Player player) {
        Card card = deck.dealOne();
        player.takeCard(card);
    }

    public void createPlayers() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players would you like to play?");

        String input = scanner.next();
        int players = parseInt(input);
        for (int i = 0; i < players; i++) {
            String prompt = String.format("Player %s, enter your name: ", (i + 1));
            System.out.println(prompt);
            String playerName = scanner.next();
            Player player = new Player(playerName);
            addPlayer(player);
        }
    }

    public void playGame() {
        for (Player player : this.players) {
            if (!player.getStatus() && !player.getBust()) {
                cardInfo(player, this.dealer);

                playGameResponse( player);

            }
        }
    }

    public void cardInfo(Player player, Dealer dealer){
        String dealerOutput = String.format("Dealer %s has:", dealer.getName());
        System.out.println(dealerOutput);
        for (int i = 0; i < 1; i++) {
            System.out.println(dealer.showCard(i));
            System.out.println(String.format("Dealer Hand total: %s", dealer.showFirstCardValue()));
        }

        String playerOutput = String.format("%s has:", player.getName());
        System.out.println(playerOutput);
        for (int i = 0; i < player.handCount(); i++) {
            System.out.println(player.showCard(i));
        }
        System.out.println(String.format("Hand total: %s", player.handTotal()));
        String quesOutput = String.format("%s Would you like to twist or stick?", player.getName());
        System.out.println(quesOutput);
    }

    public void playGameResponse( Player player) {
        String response = scanner.next();

        if(response.toLowerCase().equals("stick")) {
            player.stuck();
            playGame();
        } else if(response.toLowerCase().equals("twist")) {
            twistPlayer(player);
            if( bustCheck(player) ){
                player.busted();
                System.out.println("You went BUST!");
            }
            playGame();
        } else {
            System.out.println(String.format("Input was not regconised please try again"));
            playGameResponse( player);
        }
    }

    public String getDealerName() {
        return this.dealer.getName();
    }

    public void dealerPlay() {
        dealerCardInfo();
            if ( this.dealer.handTotal() < 19 ) {
                String dealerAction = String.format("Dealer %s drew a card", dealer.getName());
                System.out.println(dealerAction);
                Card card = deck.dealOne();
                dealer.takeCard(card);
                String showCard = String.format("Dealer drew a %s", card.cardName());
                System.out.println(showCard);
                if( this.dealer.handTotal() > 21 ){
                    this.dealer.busted();
                    String dealerBusted = String.format("Dealer %s went BUST!", dealer.getName());
                    System.out.println(dealerBusted);
                }
                dealerPlay();
        }
    }

    public void dealerCardInfo() {
        String dealerOutput = String.format("Dealer %s has:", dealer.getName());
        System.out.println(dealerOutput);
        for (int i = 0; i < dealer.handCount(); i++) {
            System.out.println(dealer.showCard(i));
        }
        System.out.println(String.format("Dealer hand total: %s", dealer.handTotal()));
    }

}
