package enums;

public enum ContractDouble {

    NOT_DOUBLED(""), DOUBLED("X"), REDOUBLED("XX");

    private final String displayName;

    ContractDouble(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
