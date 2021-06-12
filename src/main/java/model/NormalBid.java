package model;

import enums.Suit;
import utils.CardImageLoader;

import javax.swing.*;
import java.awt.*;

public class NormalBid extends Bid {
    private final Suit suit;
    private final int callNumber;

    public NormalBid(int callNumber, Suit suit) {
        this.callNumber = callNumber;
        this.suit = suit;
    }



    public Suit getSuit() {
        return suit;
    }

    public int getCallNumber() {
        return callNumber;
    }

    @Override
    public String toString() {
        return "" + callNumber + " " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof NormalBid) {
            return this.suit == ((NormalBid) o).getSuit() && this.callNumber == ((NormalBid) o).getCallNumber();
        }
        return false;
    }
}
