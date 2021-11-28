package code.plant;

import java.util.List;

import code.Character;
import code.bullet.Bullet;

/**
 * @author Vu Viet Phong
 */
public class Plant extends Character {
    protected static int health = 200;
    private List<Bullet> bullets;
    
    protected Plant(int x, int y) {
        super(x, y);
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        Plant.health = health;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}

