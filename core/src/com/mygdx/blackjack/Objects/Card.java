package com.mygdx.blackjack.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Card {



    enum Suit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }

    enum Number {
        ace,
        two,
        three,
        four,
        five,
        six,
        seven,
        eight,
        nine,
        ten,
        jack,
        queen,
        king
    }

    private Suit suit;
    private Number number;
    private int value;
    private Texture cardTexture;
    private Image cardImage;



    public Card(){
        this.suit = Suit.CLUBS;
        this.number = Number.ace;
    }

    public Card(int suit, int number){
        this.suit = Suit.values()[suit];
        this.number = Number.values()[number];
        setValue(this.number);
        setCardTexture(this.suit, this.number);
        this.cardImage = new Image(this.cardTexture);
    }

    private void setValue(Number number) {
        switch (number){
            case ace:
                this.value = 11;
                break;
            case two:
                this.value = 2;
                break;
            case three:
                this.value = 3;
                break;
            case four:
                this.value = 4;
                break;
            case five:
                this.value = 5;
                break;
            case six:
                this.value = 6;
                break;
            case seven:
                this.value = 7;
                break;
            case eight:
                this.value = 8;
                break;
            case nine:
                this.value = 9;
                break;
            default:
                this.value = 10;
                break;
        }
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value){
        // for use when changing an ace from 11 to 1
        this.value = value;
    }

    private void setCardTexture(Suit suit, Number number){
        String suitName = "spades";
        String numName = "ace";
        String filename;


        switch (suit){
            case CLUBS:
                suitName = "clubs";
                break;
            case DIAMONDS:
                suitName = "diamonds";
                break;
            case HEARTS:
                suitName = "hearts";
                break;
            case SPADES:
                suitName = "spades";
                break;
        }

        switch (number){
            case ace:
                numName = "ace";
                break;
            case two:
                numName = "2";
                break;
            case three:
                numName = "3";
                break;
            case four:
                numName = "4";
                break;
            case five:
                numName = "5";
                break;
            case six:
                numName = "6";
                break;
            case seven:
                numName = "7";
                break;
            case eight:
                numName = "8";
                break;
            case nine:
                numName = "9";
                break;
            case ten:
                numName = "10";
                break;
            case jack:
                numName = "jack";
                break;
            case queen:
                numName = "queen";
                break;
            case king:
                numName = "king";
                break;

        }

        filename = numName + "_of_" + suitName + ".png";
        this.cardTexture = new Texture(filename);


    }

    public Image getCardImage(){
        return this.cardImage;
    }
}
