package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

import java.util.ArrayList;


public class Game {

    private Deck deck;
    private Dealer dealer;
    private ArrayList<Participant> players;

    public Game(Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer("Dealer");
        this.players = new ArrayList<>();


        players.add(new Player("Player 1"));
        players.add(dealer);

    }

    public ArrayList<Participant> getPlayers() {
        return players;
    }

    public void deal(){
        dealer.startDealing(this.players, this.deck);
    }

    public String showUserHand(){
        return this.players.get(0).getHand().describeHand();
    }

    public void play() {

        for (int i = 0; i < players.size()-1; i++) {
            Participant player = players.get(i);
            if (player.getHand().checkIfAce()){
                player.getHand().makeAcesLowIfBust();
            }
            while (!player.getHand().checkBust() && player.getStickOrTwist().equals("t")) {
                player.setStickOrTwist("t");

                if (player.getStickOrTwist().equals("t")) {
                    dealer.dealCard(player, deck);
                }
            }
        }

//      Dealer logic


        while (dealer.checkShouldTwist(dealer.getHandValue())) {
            dealer.dealCard(dealer, deck);
        }

        Participant winner = checkWinner(players);

    }

    public Participant checkWinner(ArrayList<Participant> players) {
        Participant player1 = players.get(0);
        Participant player2 = players.get(1);

        if (!player1.getHand().checkBust() && !player2.getHand().checkBust()) {
            if (player1.getHandValue() == player2.getHandValue()) {
                return player1;
            } else {
                return player1.getHandValue() > player2.getHandValue() ? player1 : player2;
            }

        } else {

            return player1.getHand().checkBust() ? player2 : player1;

        }
    }
}

