package code.zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static Zombie getZombie(int type) {
        Zombie z = new Zombie(x, y);
        
        switch (type) {
            case 1:
                z = new NormalZombie(z.getX(), z.getY());
                break;
            case 2:
                z = new ConeheadZombie(z.getX(), z.getY());
                break;
            case 3:
                z = new FootballZombie(z.getX(), z.getY());
                break;
        }

        return z;
    }

    public void randomZombie(int N) {
        List<Zombie> z = new ArrayList<>();
        Random rd = new Random();

        for (int i = 0; i < N; i++) {
            int num = rd.nextInt(3) + 1;
            z.add(getZombie(num));
        }
    }
}
