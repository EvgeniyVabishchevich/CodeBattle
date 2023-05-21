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
public class ProvideNameMessage extends BaseMessage {
    public String name;

    @Override
    public MessageType action() {
        return MessageType.PROVIDE_NAME;
    }
}
