package model;

import enums.Rank;
import enums.Suit;

public class Card {

    private Rank rank;
    private Suit suit;

    public void Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
