package enums;

public enum Direction {
    NS, EW;

    @Override
    public String toString() {
        if (this == NS) {
            return "N/S";
        } else {
            return "E/W";
        }
    }
}
