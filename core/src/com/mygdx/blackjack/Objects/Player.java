package com.mygdx.blackjack.Objects;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    private final List<Card> deck;

    public Player(List<List<Integer>> picked){
        deck = new ArrayList<>();
        for (int i = 0; i<2; i++){
            addCard(picked);
        }
    }

    public void addCard(List<List <Integer>> picked){
        Random rand = new Random();
        int suit = rand.nextInt(3);
        int number = rand.nextInt(12);
        // int[] item  = new int[] {suit, number};
        List <Integer> item = Arrays.asList(suit, number);

        while (picked.contains(item))
        {
            suit = rand.nextInt(3);
            number = rand.nextInt(12);
            // item  = new int[] {suit, number};
            item = Arrays.asList(suit, number);
        }

        Card card = new Card(suit, number);
        picked.add(item);

        this.deck.add(card);


    }
    public List<Card> getDeck(){
        return this.deck;
    }

    public int getTotal(){
        boolean ace = false;
        int total = 0;
        for (Card card : getDeck()){
            total += card.getValue();
        }
        if (total > 21){
            for (Card card : getDeck()){
                if (card.getValue() == 11){
                    card.setValue(1);
                    ace = true;
                    break;
                }
            }
            if (ace){
                return getTotal();
            }

        }
        return total;
    }


}
