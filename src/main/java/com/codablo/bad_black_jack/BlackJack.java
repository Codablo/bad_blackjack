package com.codablo.bad_black_jack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BlackJack {

    private Stack<Card> deck;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;

    public BlackJack() {
        createDeck();
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
    }

    private void createDeck() {
        deck = new Stack<>();
        var list = new ArrayList<Card>();
        for (var suite : Suite.values()) {
            for (var rank : Rank.values()) {
                list.add(new Card(rank, suite));
            }
        }
        Collections.shuffle(list);
        deck.addAll(list);
    }

    public void play() {
        System.out.println("Welcome to Black Jack.");
        System.out.println("Dealing cards.\n");

        playerHand.add(deck.pop());
        dealerHand.add(deck.pop());
        playerHand.add(deck.pop());
        dealerHand.add(deck.pop());

        displayHand();
        displayDealerHand(false);

        var action = "";
        while(!action.equals("s")) {

            action = promptForAction();
            if("h".equals(action)) {
                playerHand.add(deck.pop());
            }
            displayHand();
            System.out.println("");

            var score = getScore(playerHand);
            if(score > 21) {
                System.out.println("\nYou've busted!");
                return;
            }

        }

        var playerScore = getScore(playerHand);
        var dealerScore = getScore(dealerHand);
        while(dealerScore < 17) {
            System.out.println("Dealer hits.");
            dealerHand.add(deck.pop());
            dealerScore = getScore(dealerHand);
        }

        displayHand();
        displayDealerHand(true);

        System.out.println("\nYour score is " + getScore(playerHand));
        System.out.println("Dealer score is " + dealerScore);

        if(dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("You Win!!!!!!");
        } else if(dealerScore == 21 && playerScore == 21) {
            System.out.println("It's a tie.");
        } else {
            System.out.println("Sorry, you lost...");
        }

    }

    private void displayHand() {
        System.out.println("You have the following cards in your hand:");
        for (var card : playerHand) {
            System.out.println(String.format("The %s of %s", card.getRank(), card.getSuite()));
        }
    }

    private void displayDealerHand(boolean showHidden) {
        System.out.println("\nDealer has the following cards:");
        for (var i = 0; i < dealerHand.size(); i++) {
            if(!showHidden && i == 0) {
                System.out.println("Hidden Card");
            } else {
                var card = dealerHand.get(i);
                System.out.println(String.format("The %s of %s", card.getRank(), card.getSuite()));
            }

        }
    }

    private String promptForAction() {
        try{
            System.out.println("\nWhat action would you like to take? [h]it, [s]tay");

            //Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Reading data using readLine
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private int getScore(List<Card> hand) {
        var score = 0;
        var numberOfAces = 0;
        for (var card : hand) {
            var code = card.getRank().code;
            score += code;
            if(code == 11) {
                numberOfAces++;
            }
        }
        while(score > 21 && numberOfAces > 0) {
            score -= 10;
            numberOfAces--;
        }
        return score;
    }
}
