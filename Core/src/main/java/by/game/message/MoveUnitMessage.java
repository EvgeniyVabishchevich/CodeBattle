package by.game.message;

import by.game.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoveUnitMessage extends BaseMessage {
    private int id;
    private int x;
    private int y;

    @Override
    public MessageType action() {
        return MessageType.MOVE;
    }
}


