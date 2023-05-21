package by.game;

import by.game.enums.ClientRole;
import by.game.gameObject.GameState;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8080;
    private static final int THREADS_NUMBER = 3;
    private static final String RENDERER_ROLE = "renderer";
    private static final String PLAYER_ROLE = "player";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    /*    private static ClientThread playerThread1;
        private static ClientThread playerThread2;
        private static ClientThread rendererThread;*/
    private Game game;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);

    public void start() throws IOException {
/*        ServerSocket serverSocket = new ServerSocket(PORT);

        while (playerThread1 == null || playerThread2 == null || rendererThread == null) {
            Socket client = serverSocket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String role = objectMapper.readValue(input.readLine(), String.class);

            checkClientRole(client, role);
        }

        System.out.println("starting game");
        startGame();
        //closeConnections();*/
    }

    public void askTheClient(ClientRole clientRole, GameState gameState) throws IOException {
/*        String msg = objectMapper.writeValueAsString(gameState);
        System.out.println("Asking client and texting to renderer");
        rendererThread.send(msg);

        if (clientRole == ClientRole.PLAYER1) {
            playerThread1.send(msg);
        } else if (clientRole == ClientRole.PLAYER2) {
            playerThread2.send(msg);
        }*/
    }

    private void checkClientRole(Socket client, String role) throws IOException {
/*
        if (role.equals(RENDERER_ROLE) && rendererThread == null) {
            rendererThread = new RendererThread(client, this, ClientRole.RENDERER);
            executorService.execute(rendererThread);
        } else if (role.equals(PLAYER_ROLE)) {
            if (playerThread1 == null) {
                playerThread1 = new PlayerThread(client, this, ClientRole.PLAYER1);
                executorService.execute(playerThread1);
            } else if (playerThread2 == null) {
                playerThread2 = new PlayerThread(client, this, ClientRole.PLAYER2);
                executorService.execute(playerThread2);
            } else client.close();
        } else client.close();
*/
    }

    public void receiveMsg(ClientRole clientRole, String msg) throws IOException {
        //Move move = objectMapper.readValue(msg, Move.class);
        //game.receiveNextMove(move);
    }

    public void startGame() throws IOException {
        //game = new Game(this);
    }

    public void endGame(GameState gameState) throws IOException {
 /*       String msg = objectMapper.writeValueAsString(gameState);
        rendererThread.send(msg);
        System.out.println("calling game over");
        closeConnections();
 */
    }

    private static void closeConnections() throws IOException {
 /*       playerThread1.closeConnections();
        playerThread2.closeConnections();
        rendererThread.closeConnections();*/
    }
}
