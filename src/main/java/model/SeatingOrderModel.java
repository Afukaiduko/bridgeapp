package model;

import enums.Position;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SeatingOrderModel {
    private final Map<Position, Player> playerPositions;
    private int nextID;
    private Position startingBidderPosition;

    public SeatingOrderModel(PlayerDatabase model) {
        this.nextID = model.getNumberOfPlayers() + 1;
        this.playerPositions = new HashMap<>();
    }

    public void incrementID() {
        nextID++;
    }

    public int getID() {
        return nextID;
    }

    public Position getStartingBidderPosition() {
        return startingBidderPosition;
    }

    public void setStartingBidderPosition(Position p) {
        startingBidderPosition = p;
    }

    public void putPlayerPosition(Position position, Player player) {
        if (playerPositions.containsValue(player)) {
            Iterator<Map.Entry<Position, Player>> iterator = playerPositions.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Position, Player> entry = iterator.next();
                if (player.equals(entry.getValue())) {
                    iterator.remove();
                }
            }
        }
        playerPositions.put(position, player);
    }

    public Map<Position, Player> getPlayerPositionsMap() {
        return playerPositions;
    }
}
