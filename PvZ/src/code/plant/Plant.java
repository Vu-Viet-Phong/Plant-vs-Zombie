package code.plant;

import java.util.List;

import code.Character;
import code.bullet.Bullet;

public class Plant extends Character {
    protected static int health = 200;
    protected static int speed = 0;
    protected static int damage = 0;
    private List<Bullet> bullets;
    
    protected Plant(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
    }

    @Override
    protected void move() {
        // TODO Auto-generated method stub
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}

