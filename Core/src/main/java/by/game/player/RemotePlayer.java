package by.game.player;

import by.game.Game;
import by.game.gameObject.GameObject;
import by.game.message.BaseMessage;
import by.game.message.GetNameMessage;
import by.game.message.MoveUnitMessage;
import by.game.message.ProvideNameMessage;
import by.game.message.StartTurnMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
public class RemotePlayer implements Runnable, Player {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final Socket socket;
    private final BufferedWriter bufferedWriter;
    private final BufferedReader bufferedReader;
    private Game game;
    private Map<String, List<GameObject>> gameObjects;
    private CompletableFuture<Void> turnFuture;
    private CompletableFuture<String> nameFuture;

    public RemotePlayer(Socket socket) throws IOException {
        this.socket = socket;

        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Future<String> askName() {
        if (nameFuture == null) {
            nameFuture = new CompletableFuture<>();

            send(new GetNameMessage());

            return nameFuture;
        }
        return nameFuture;
    }

    @Override
    public Future<Void> startTurn(Map<String, List<GameObject>> gameObjects) {
        this.gameObjects = gameObjects;

        turnFuture = new CompletableFuture<>();

        send(new StartTurnMessage(gameObjects));

        return turnFuture;
    }

    @Override
    public Future<Void> stopTurn() {
        return null;
    }

    private synchronized void processMessage(BaseMessage message) {
        switch (message.action()) {
            case MOVE -> {
                MoveUnitMessage moveUnitParameters = (MoveUnitMessage) message;
                game.moveUnit(this, moveUnitParameters);
            }
            case ENT_TURN -> {
                if (turnFuture != null) {
                    turnFuture.complete(null);
                    turnFuture = null;
                }
            }
            case PROVIDE_NAME -> {
                if (nameFuture != null && !nameFuture.isDone()) {
                    ProvideNameMessage parameters = (ProvideNameMessage) message;
                    nameFuture.complete(parameters.name);
                }
            }
        }
    }

    public void send(BaseMessage message) {
        try {
            bufferedWriter.write(new ObjectMapper().writeValueAsString(message));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            log.error("Error, when tried send message to player.", e);
        }
    }

    public void closeConnections() throws IOException {
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                String msg = bufferedReader.readLine();
                processMessage(MAPPER.readValue(msg, BaseMessage.class));
            } catch (JsonProcessingException e) {
                log.error("Error, when tried to deserialize msg from player client.", e);
            } catch (IOException e) {
                log.error("Error, when tried to get message from player client.", e);
            }
        }
    }
}
