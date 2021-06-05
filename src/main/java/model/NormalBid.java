package model;

import enums.Suit;
import utils.CardImageLoader;

import javax.swing.*;
import java.awt.*;

public class NormalBid extends Bid {
    private final Suit suit;
    private final int callNumber;
    private JLabel normalLabel;

    public NormalBid(int callNumber, Suit suit) {
        this.callNumber = callNumber;
        this.suit = suit;
        createLabel();
    }

    public void createLabel() {
        if (this.getSuit() == Suit.NO_TRUMP) {
            normalLabel = new JLabel("" + this.getCallNumber() + "NT");
        } else {
            Image currentSuitImg = CardImageLoader.getInstance().getSuitImg(this.getSuit());
            ImageIcon currentSuit = new ImageIcon(currentSuitImg);

            normalLabel = new JLabel("" + this.getCallNumber());
            normalLabel.setIcon(currentSuit);
        }
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

    //can probably not use arraylist of labels
    public JLabel getLabel() {
        return normalLabel;
    }
}
