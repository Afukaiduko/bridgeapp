package model;

import enums.Suit;

public class Card {

    private int number;
    private Suit suit;

    public void Card(int number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getCardNumber(){
        return number;
    }

    public Suit getCardSuit(){
        return suit;
    }
}
