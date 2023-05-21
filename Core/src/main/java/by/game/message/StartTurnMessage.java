package by.game.message;

import by.game.enums.MessageType;
import by.game.gameObject.GameObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartTurnMessage extends BaseMessage {
    private Map<String, List<GameObject>> gameObjects;

    @Override
    public MessageType action() {
        return MessageType.START_TURN;
    }
}
