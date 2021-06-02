package model;

import javax.swing.*;

public class DoubleBid extends Bid{

    private JLabel doubleLabel;
    private boolean isRedouble;

    public DoubleBid(boolean isRedouble){
        this.isRedouble = isRedouble;
        if(isRedouble) {
            this.doubleLabel = new JLabel("XX");
        } else {
            this.doubleLabel = new JLabel("X");
        }
    }

    public boolean getIsRedouble(){
        return isRedouble;
    }

    public JLabel getLabel(){
        return doubleLabel;
    }

    @Override
    public String toString(){
        return doubleLabel.getText();
    }
}
