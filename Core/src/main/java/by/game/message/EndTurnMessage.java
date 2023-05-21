package by.game.message;

import by.game.enums.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EndTurnMessage extends BaseMessage {
    @Override
    public MessageType action() {
        return MessageType.ENT_TURN;
    }
}
