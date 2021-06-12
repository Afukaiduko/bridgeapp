package model;

import enums.Position;

import java.util.List;

public class SaveGame {

    private final Game game;
    private final List<Bid> biddingSequence;
    private final Position startingBidderPosition;

    public SaveGame(Game game, List<Bid> biddingSequence, Position startingBidderPosition) {
        this.game = game;
        this.biddingSequence = biddingSequence;
        this.startingBidderPosition = startingBidderPosition;
    }
}
