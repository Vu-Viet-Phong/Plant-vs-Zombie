package code.zombie;

public class ConeheadZombie extends Zombie {
    private static String name = "ConeheadZombie";
    private static int health = 400;
    private static int damage = 60;

    public ConeheadZombie(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
        loadImage("/src/images/normal_zombie.gif");
        getImageDimensions();
    }
}
