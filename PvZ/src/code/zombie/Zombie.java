package code.zombie;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import code.Character;

public class Zombie extends Character {
    protected static int speed = 1;

    public Zombie(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
    }

    protected void move() {
        x -= 1;
    }
}
