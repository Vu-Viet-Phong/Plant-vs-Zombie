package code.plant;

import java.util.List;
import code.Character;

public abstract class Plant extends Character {
    
    protected static int health = 200;
    private List<Bullet> bullets;
    
    protected Plant(int dx, int dy, int health) {
        this.dx = dx;
        this.dy = dy;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void shoot();
}

