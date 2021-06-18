package model;

import enums.Position;

public class InGameModel {

    private Game game;
    private Position startingPlayerPosition;

    public InGameModel() {
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Position getStartingPlayerPosition() {
        return startingPlayerPosition;
    }

    public void setStartingPlayerPosition(Position startingPlayerPosition) {
        this.startingPlayerPosition = startingPlayerPosition;
    }
}
