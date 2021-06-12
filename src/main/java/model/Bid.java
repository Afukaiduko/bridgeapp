package model;

import enums.Position;

import javax.swing.*;

public abstract class Bid {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
