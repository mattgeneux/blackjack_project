package com.mygdx.blackjack.Objects;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import jdk.internal.net.http.common.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private List<Card> deck;

    public Player(List<int[]> picked){
        deck = new ArrayList<>();
        for (int i = 0; i<2; i++){
            addCard(picked);
        }
    }

    public void addCard(List<int[]> picked){
        Random rand = new Random();
        int suit = rand.nextInt(3);
        int number = rand.nextInt(12);
        int[] item  = new int[] {suit, number};

        while (picked.contains(item))
        {
            suit = rand.nextInt(3);
            number = rand.nextInt(12);
            item  = new int[] {suit, number};
        }

        Card card = new Card(suit, number);
        picked.add(item);

        this.deck.add(card);


    }
    public List<Card> getDeck(){
        return this.deck;
    }


}
