package by.player;

import by.game.gameObject.GameObject;
import by.game.message.BaseMessage;
import by.game.message.ConnectionMessage;
import by.game.message.MoveUnitMessage;
import by.game.message.ProvideNameMessage;
import by.game.message.StartTurnMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

@Slf4j
public class Player {
    private static final int PORT = 8080;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static String name = "MyRemotePlayer1";
    private static BufferedWriter bufferedWriter;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", PORT)) {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//            Message message = new Message(ROLE, new ConnectionParameters(PLAYER_ROLE));
//            sendMessage(message);

            while (!socket.isClosed()) {
                String jsonMessage = bufferedReader.readLine();
                getMessage(jsonMessage);
            }
        }
    }

    private static void getMessage(String jsonMessage) {
        try {
            BaseMessage message = objectMapper.readValue(jsonMessage, BaseMessage.class);

            switch (message.action()) {
                case CONNECT -> {
                    name = ((ConnectionMessage) message).getCommand();
                }
                case GET_NAME -> {
                    sendMessage(new ProvideNameMessage("MyRemotePlayer1"));
                }
                case START_TURN -> {
                    StartTurnMessage parameters = (StartTurnMessage) message;
                    for (GameObject gameObject : parameters.getGameObjects().get(name)) {
                        sendMessage(new MoveUnitMessage(gameObject.getId(), gameObject.getX() - 1, gameObject.getY() - 1));
                    }
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Error, when tried to deserialize msg from server.", e);
        }
    }

    private synchronized static void sendMessage(BaseMessage message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            bufferedWriter.write(jsonMessage);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (JsonProcessingException e) {
            log.error("Error, when tried to serialize message.", e);
        } catch (IOException e) {
            log.error("Error, when tried to send message to server.", e);
        }
    }
}