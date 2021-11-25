package code.zombie;

import code.Character;

public class Zombie extends Character {
    protected static int speed = 1;
    protected static int health = 0;

    public Zombie(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
    }

    @Override
    protected void move() {
        x -= 1;
    }
}
