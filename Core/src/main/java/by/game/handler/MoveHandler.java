package by.game.handler;

import by.game.Game;
import by.game.gameObject.GameObject;
import by.game.message.MoveUnitMessage;

public class MoveHandler {
    private final Game game;

    public MoveHandler(Game game) {
        this.game = game;
    }

    public void handleMove(MoveUnitMessage parameters) {
        GameObject go = game.findById(parameters.getId());
        if (go != null) {
            // TODO validate parameters, change movePoints
            go.setX(parameters.getX());
            go.setY(parameters.getY());
        }
    }
}
