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

    public Bid get(int index){
        return biddingSequence.get(index);
    }

    public int getLastIndex(){
        return biddingSequence.size()-1;
    }

    public void remove(int index){
        biddingSequence.remove(index);
    }
}
