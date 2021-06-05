package model;

import enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Round> rounds;
    private Contract contract;
    private int tricksNS;
    private int tricksEW;
    private Direction winner;
    private Direction contractors;

    public Game(Contract contract,Direction contractors){
        this.contract = contract;
        this.contractors = contractors;
        this.tricksNS = 0;
        this.tricksEW = 0;
        this.rounds = new ArrayList<>();
    }

    public void addRound(Round round){
        rounds.add(round);
    }

    public void removeLastRound(){
        if(rounds.size()>0){
            rounds.remove(rounds.size()-1);
        }
    }

    public void calculateWinner(){
        int tricksNeeded = 6 + contract.getContractBid().getCallNumber();


    }
}
