package by.game.player;

import by.game.Game;
import by.game.gameObject.GameObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface Player {
    void setGame(Game game);

    Future<String> askName();

    Future<Void> startTurn(Map<String, List<GameObject>> gameObjects);

    Future<Void> stopTurn();
}
