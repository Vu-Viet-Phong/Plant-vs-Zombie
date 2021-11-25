package code.bullet;

import code.Character;

public class Bullet extends Character {
    protected static int speed = 1;

    public Bullet(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
    }

    @Override
    protected void move() {
        x += 1;
    }  
}
