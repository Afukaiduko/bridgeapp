package model;

import javax.swing.*;

public class DoubleBid extends Bid {

    private final boolean isRedouble;

    public DoubleBid(boolean isRedouble) {
        this.isRedouble = isRedouble;
    }

    public boolean getIsRedouble() {
        return isRedouble;
    }

    @Override
    public String toString() {
        if(isRedouble){
            return "XX";
        } else {
            return "X";
        }
    }
}
