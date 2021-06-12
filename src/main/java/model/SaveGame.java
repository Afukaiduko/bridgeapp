package model;

import enums.Position;

import java.util.List;

public class SaveGame {

    private Game game;
    private List<Bid> biddingSequence;
    private Position startingBidderPosition;

    public SaveGame(Game game, List<Bid> biddingSequence, Position startingBidderPosition) {
        this.game = game;
        this.biddingSequence = biddingSequence;
        this.startingBidderPosition = startingBidderPosition;
    }
}
