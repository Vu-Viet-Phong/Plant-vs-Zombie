package code.zombie;

import code.Character;

/**
 * @author Vũ Viết Phong
 */
public class Zombie extends Character {
    protected static double speed = 1;
    protected static int health;
    protected static int damage;

    public Zombie(int x, int y) {
        super(x, y);
    }

    public static double getSpeed() {
        return speed;
    }

    public static void setSpeed(double speed) {
        Zombie.speed = speed;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        Zombie.health = health;
    }

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int damage) {
        Zombie.damage = damage;
    }

    public static Zombie getZombie(String type) {
        Zombie z = new Zombie(x, y);
        
        switch (type) {
            case "NormalZombie":
                z = new NormalZombie(z.getX(), z.getY());
                break;
            case "ConeHeadZombie":
                z = new ConeheadZombie(z.getX(), z.getY());
                break;
            case "FootballZombie":
                z = new FootballZombie(z.getX(), z.getY());
                break;
        }

        return z;
    }
}
