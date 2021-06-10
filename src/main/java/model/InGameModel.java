package model;

import enums.Position;

public class InGameModel {

    private final BiddingModel biddingModel;
    private final SeatingOrderModel seatingOrderModel;
    private Game game;
    private Position startingPlayerPosition;
    private boolean finished;

    public InGameModel(SeatingOrderModel seatingOrderModel, BiddingModel biddingModel) {
        this.biddingModel = biddingModel;
        this.seatingOrderModel = seatingOrderModel;
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
