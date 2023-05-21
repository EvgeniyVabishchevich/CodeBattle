package by.game.message;

import by.game.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @Type(value = ConnectionMessage.class, name = "connection"),
        @Type(value = EndTurnMessage.class, name = "endTurn"),
        @Type(value = GetNameMessage.class, name = "getName"),
        @Type(value = MoveUnitMessage.class, name = "moveUnit"),
        @Type(value = ProvideNameMessage.class, name = "provideName"),
        @Type(value = StartTurnMessage.class, name = "startTurn"),
})
public class BaseMessage {
    public MessageType action() {
        return MessageType.UNDEFINED;
    }
}
