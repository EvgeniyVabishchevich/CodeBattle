package by.game;

import by.game.player.DummyPlayer;
import by.game.player.RemotePlayer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

@Slf4j
public class App {
    private static final int PORT = 8080;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            RemotePlayer remotePlayer = new RemotePlayer(socket);
            new Thread(remotePlayer).start();
            Game game = new Game(remotePlayer, new DummyPlayer("dummy2"));
            game.run();
        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
