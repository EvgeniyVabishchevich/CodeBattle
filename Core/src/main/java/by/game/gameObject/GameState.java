package by.game.gameObject;

import java.util.List;
import java.util.Map;

public class GameState {
    private int width;
    private int height;
    private Map<Integer, List<GameObject>> units;

    public GameState(int width, int height, Map<Integer, List<GameObject>> units) {
        this.width = width;
        this.height = height;
        this.units = units;
    }

    public GameState() {
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setUnits(Map<Integer, List<GameObject>> units) {
        this.units = units;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Integer, List<GameObject>> getUnits() {
        return units;
    }
}
