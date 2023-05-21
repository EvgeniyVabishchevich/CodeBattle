package by.game;

import by.game.gameObject.GameObject;
import by.game.gameObject.Warrior;
import by.game.handler.MoveHandler;
import by.game.message.MoveUnitMessage;
import by.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Game implements Runnable {
    private static final long playerTurnTimeoutMs = 30;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;

    private final Map<String, List<GameObject>> units = new HashMap<>();

    private List<Player> players;
    private Player currentPlayer;

    private boolean gameOver = false;

    private final MoveHandler moveHandler;

    public Game(Player... players) throws InterruptedException, ExecutionException {
        assert players.length >= 2 : "Provide minimum 2 players to start the game";

        this.players = Arrays.asList(players);
        this.players.forEach((player -> player.setGame(this)));
        this.currentPlayer = players[0];

        this.moveHandler = new MoveHandler(this);

        ArrayList<GameObject> player1Units = new ArrayList<>();
        player1Units.add(new Warrior(1, 1, 1));
        ArrayList<GameObject> player2Units = new ArrayList<>();
        player2Units.add(new Warrior(2, 10, 10));
        units.put(players[0].askName().get(), player1Units);
        units.put(players[1].askName().get(), player2Units);
    }

    @Override
    public void run() {
        while (!gameOver) {
            for (Player player : players) {
                currentPlayer = player;
                playerTurn(player);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moveUnit(Player player, MoveUnitMessage parameters) {
        if (player != currentPlayer) {
            return;
        }

        try {
            System.out.printf("%s moving %d to %d:%d%n", player.askName().get(), parameters.getId(), parameters.getX(), parameters.getY());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        moveHandler.handleMove(parameters);
    }

    private void playerTurn(Player player) {
        try {
            player.startTurn(units).get(playerTurnTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            // player turn time is out
            player.stopTurn();
        }
    }

//    private GameState buildGameState() {
//        return new GameState(WIDTH, HEIGHT, units);
//    }

    private GameObject findByXY(int x, int y) {
        return units.keySet().stream()
                .map(units::get)
                .flatMap(Collection::stream)
                .filter(gameObject -> gameObject.getX() == x && gameObject.getY() == y)
                .findFirst().get();
    }

    public GameObject findById(int id) {
        return units.keySet().stream()
                .map(units::get)
                .flatMap(Collection::stream)
                .filter(gameObject -> gameObject.getId() == id)
                .findFirst().get();
    }
}
