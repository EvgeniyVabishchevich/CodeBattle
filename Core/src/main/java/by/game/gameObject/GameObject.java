package by.game.gameObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameObject {
    private int id;
    private int x;
    private int y;
    private int movePoints;
    private int attackPoint;

    public GameObject(int id, int x, int y, int movePoints, int attackPoint) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.movePoints = movePoints;
        this.attackPoint = attackPoint;
    }
}
