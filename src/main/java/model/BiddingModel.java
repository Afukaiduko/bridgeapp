package model;

import java.util.ArrayList;
import java.util.List;

public class BiddingModel {
    private final List<Bid> biddingSequence;
    private Contract contract;

    public BiddingModel() {
        biddingSequence = new ArrayList<>();
    }

    public void addBid(Bid bid) {
        biddingSequence.add(bid);
    }

    public List getBiddingSequence() {
        return biddingSequence;
    }

    public Bid get(int index) {
        return biddingSequence.get(index);
    }

    public int getLastIndex() {
        return biddingSequence.size() - 1;
    }

    public void remove(int index) {
        biddingSequence.remove(index);
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isDoubled() {
        return this.get(biddingSequence.size() - 4) instanceof DoubleBid;
    }

    public boolean isRedoubled() {
        return this.get(biddingSequence.size() - 3) instanceof DoubleBid;
    }
}
