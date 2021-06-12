package model;

import enums.Position;
import enums.Suit;

import java.util.HashMap;
import java.util.Map;

public class Round {

    private Suit trumpSuit;
    private Position starting;
    private Map<Position, Card> cardsPlayed;
    private Position capturer;

    public Round(Position starting, Suit trumpSuit) {
        this.starting = starting;
        this.cardsPlayed = new HashMap<>();
        this.trumpSuit = trumpSuit;
    }

    /*
     *  Note: largest is initially set to starting player's card. If currentCard and largest are the same suit, then we compare ranks.
     *  If largest and currentCard have different suits and currentCard is trumpSuit, then largest becomes currentCard (and thus largest's
     *  suit will be trumpSuit, and so only cards have same suit as trumpSuit will be compared thereafter)
     */
    public void findCapturer() {
        Card largest = cardsPlayed.get(starting);
        Card currentCard;
        for (Map.Entry<Position, Card> entry : cardsPlayed.entrySet()) {
            currentCard = entry.getValue();
            if (currentCard.getSuit() == largest.getSuit()) {
                if (currentCard.getRank().compareTo((largest.getRank())) < 0) {
                    largest = currentCard;
                }
            } else if (currentCard.getSuit() == trumpSuit) {
                largest = currentCard;
            }
        }
        this.capturer = getKey(largest);
    }

    public Position getKey(Card card) {
        Position key = null;
        for (Map.Entry<Position, Card> entry : cardsPlayed.entrySet()) {
            if (entry.getValue() == card) {
                key = entry.getKey();
            }
        }
        return key;
    }

    public void addCard(Position position, Card card) {
        cardsPlayed.put(position, card);
    }

    public Position getCapturer() {
        return this.capturer;
    }

    public Map<Position, Card> getCardsPlayed() {
        return cardsPlayed;
    }

    public Position getStarting() {
        return starting;
    }
}
