package by.game.player;

import by.game.Game;
import by.game.gameObject.GameObject;
import by.game.message.MoveUnitMessage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class DummyPlayer implements Player {
    private final String dummyName;

    private Game game;

    public DummyPlayer(String dummyName) {
        this.dummyName = dummyName;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Future<String> askName() {
        return CompletableFuture.completedFuture(dummyName);
    }

    @Override
    public Future<Void> startTurn(Map<String, List<GameObject>> gameObjects) {
        for (GameObject gameObject : gameObjects.get(dummyName)) {
            game.moveUnit(this, new MoveUnitMessage(gameObject.getId(), gameObject.getX() + 1, gameObject.getY() + 1));
        }

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public Future<Void> stopTurn() {
        return null;
    }
}
