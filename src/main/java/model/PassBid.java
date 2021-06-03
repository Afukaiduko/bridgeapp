package model;

import javax.swing.*;

public class PassBid extends Bid {
    private final JLabel passLabel;

    public PassBid() {
        this.passLabel = new JLabel("Pass");
    }

    public JLabel getLabel() {
        return passLabel;
    }

    @Override
    public String toString() {
        return passLabel.getText();
    }
}
