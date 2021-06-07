package enums;

public enum Position {
    NORTH, EAST, SOUTH, WEST {
        @Override
        public Position next() {
            return values()[0];
        }
    };

    public Position next() {
        return values()[ordinal() + 1];
    }

    public Position previous() {
        if (values()[ordinal()] == NORTH) {
            return WEST;
        }
        return values()[ordinal() - 1];
    }
}