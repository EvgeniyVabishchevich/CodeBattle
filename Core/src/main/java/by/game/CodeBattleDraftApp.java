package by.game;

import java.io.IOException;

public class CodeBattleDraftApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.start();
    }
}
