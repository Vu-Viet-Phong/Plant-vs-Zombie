package code.bullet;

import code.Character;

public class Bullet extends Character {
    protected static double speed = 1.25;
    protected static int damage = 50;

    public Bullet(int x, int y) {
        super(x, y);
    }

    public static double getSpeed() {
        return speed;
    }

    public static void setSpeed(double speed) {
        Bullet.speed = speed;
    }

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int damage) {
        Bullet.damage = damage;
    }    
}
