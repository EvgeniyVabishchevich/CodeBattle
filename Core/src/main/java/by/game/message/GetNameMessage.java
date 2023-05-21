package by.game.message;

import by.game.enums.MessageType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize
public class GetNameMessage extends BaseMessage {
    @Override
    public MessageType action() {
        return MessageType.GET_NAME;
    }
}
