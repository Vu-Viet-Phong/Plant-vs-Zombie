package code.zombie;

import code.Character;

/**
 * @author Vũ Viết Phong
 */
public class Zombie extends Character {
    protected static double speed = 10;
    protected static int health;
    protected static int damage;
    private static int locX = 1800;

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

    public void move() {
        x -= Zombie.getSpeed();
    }

    public static Zombie getZombie(int type, int locY) {
        Zombie z = new Zombie(locX, locY);
        
        switch (type) {
            case 1:
                z = new NormalZombie(locX, locY);
                break;
            case 2:
                z = new ConeheadZombie(locX, locY);
                break;
            case 3:
                z = new FootballZombie(locX, locY);
                break;
        }

        return z;
    }

    
}
