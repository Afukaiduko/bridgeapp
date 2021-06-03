package model;

import enums.Position;

import java.util.ArrayList;

public class Round {

    private final Position starting;
    private ArrayList<Card> played;

    public Round(Position starting) {
        this.starting = starting;
    }
}
