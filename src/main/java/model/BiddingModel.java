package model;

import enums.Direction;
import enums.Position;
import enums.Suit;

import java.util.ArrayList;
import java.util.List;

public class BiddingModel {
    private final List<Bid> biddingSequence;
    private Contract contract;
    private Direction direction;
    private Position startingPlayerPosition;

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

    public Direction getDirection(){
        return direction;
    }

    public Position getStartingPlayerPosition(){
        return startingPlayerPosition;
    }

    public void setDirection(){
        if(contract.getContractBid().getPosition() == Position.NORTH || contract.getContractBid().getPosition() == Position.SOUTH ){
            direction = Direction.NS;
        } else {
            direction = Direction.EW;
        }
        System.out.println("Direction: "+direction);
    }

    public NormalBid findLatestNormalBid() {
        for (int i = biddingSequence.size() - 1; i >= 0; i--) {
            if (biddingSequence.get(i) instanceof NormalBid) {
                return (NormalBid) biddingSequence.get(i);
            }
        }
        return null;
    }

    public void setStartingPlayerPosition(){
        Suit bidSuit = contract.getContractBid().getSuit();
        for(Bid bid : biddingSequence){
            if(bid instanceof NormalBid){
                if(bidSuit == ((NormalBid)bid).getSuit()){
                    if(direction == Direction.NS){
                        if(bid.getPosition() == Position.NORTH || bid.getPosition() == Position.SOUTH){
                            startingPlayerPosition = bid.getPosition().next();
                            System.out.println("Starting player position: "+startingPlayerPosition);
                            return;
                        }
                    } else {
                        if(bid.getPosition() == Position.EAST || bid.getPosition() == Position.WEST){
                            startingPlayerPosition = bid.getPosition().next();
                            System.out.println("Starting player position: "+startingPlayerPosition);
                            return;
                        }
                    }
                }
            }
        }
    }
}
