package utils;

import enums.Suit;
import model.Bid;
import model.DoubleBid;
import model.NormalBid;
import model.PassBid;

import javax.swing.*;
import java.awt.*;

public class BidLabelUtils {

    public static JLabel createLabelFromBid(Bid bid) {
        JLabel label;
        if (bid instanceof NormalBid nb) {
            if (nb.getSuit() == Suit.NO_TRUMP) {
                label = new JLabel("" + nb.getCallNumber() + "NT");
            } else {
                Image currentSuitImg = CardImageLoader.getInstance().getSuitImg(nb.getSuit());
                ImageIcon currentSuit = new ImageIcon(currentSuitImg);

                label = new JLabel("" + nb.getCallNumber());
                label.setIcon(currentSuit);
            }
        } else if (bid instanceof PassBid pb) {
            label = new JLabel("Pass");
        } else if (bid instanceof DoubleBid db) {
            if (db.getIsRedouble()) {
                label = new JLabel("XX");
            } else {
                label = new JLabel("X");
            }
        } else {
            label = null;
            System.out.println("This bid type is not recognized");
        }
        return label;
    }
}
