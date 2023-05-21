package by.game.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public final String PLAYER_ROLE = "player";
    public final String RENDERER_ROLE = "renderer";

    @UtilityClass
    public class ActionConstants {
        public final String CONNECT = "connect";
        public final String START_TURN = "startTurn";
        public final String ROLE = "role";
        public final String GET_NAME = "getName";
        public final String END_TURN = "endTurn";
    }
}
