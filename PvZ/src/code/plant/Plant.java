package code.plant;

import java.util.List;

public abstract class Plant {
    private int dx;
    private int dy;
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

