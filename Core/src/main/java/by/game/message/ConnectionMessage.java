package by.game.message;

import by.game.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionMessage extends BaseMessage {
    private String command;

    @Override
    public MessageType action() {
        return MessageType.CONNECT;
    }
}
