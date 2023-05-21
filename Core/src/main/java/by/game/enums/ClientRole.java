package by.game.enums;

public enum ClientRole {
    PLAYER1("player1"),
    PLAYER2("player2"),
    RENDERER("renderer");
    private final String value;

    ClientRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
