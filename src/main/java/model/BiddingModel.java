package model;

import java.util.ArrayList;
import java.util.List;

public class BiddingModel {
    private List<Bid> biddingSequence;

    public BiddingModel(){
        biddingSequence = new ArrayList<>();
    }

    public void addBid(Bid bid){
        biddingSequence.add(bid);
    }

    public List getBiddingSequence(){
        return biddingSequence;
    }
}
