package model;

import enums.Direction;
import enums.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    private final List<Round> rounds;
    private final Contract contract;
    private final Map<Position, Player> playerPositions;
    private final Direction contractors;
    private int tricksNS;
    private int tricksEW;
    private Direction winner;


    public Game(Contract contract, Direction contractors, Map<Position, Player> playerPositions) {
        this.contract = contract;
        this.contractors = contractors;
        this.tricksNS = 0;
        this.tricksEW = 0;
        this.rounds = new ArrayList<>();
        this.playerPositions = playerPositions;
    }

    public void addRound(Round round) {
        rounds.add(round);
    }

    public Contract getContract() {
        return this.contract;
    }

    public void removeLastRound() {
        if (rounds.size() > 0) {
            rounds.remove(rounds.size() - 1);
        }
    }

    public void calculateWinner() {
        int tricksNeeded = 6 + contract.getContractBid().getCallNumber();
    }

    public void incrementTricksNS() {
        tricksNS++;
    }

    public void incrementTricksEW() {
        tricksEW++;
    }

    public int getTricksNS() {
        return tricksNS;
    }

    public int getTricksEW() {
        return tricksEW;
    }
}
