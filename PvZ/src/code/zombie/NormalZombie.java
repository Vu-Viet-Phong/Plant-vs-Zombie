package code.zombie;

/**
 * @author Trương Đăng Khoa
 */
public class NormalZombie extends Zombie {
    private static String name = "NormalZombie";
    private static int health = 300;
    private static int damage = 50;

    public NormalZombie(int x, int y) {
        super(x, y, name, speed, health, damage);
        loadImage("/images/zombies/normal_zombie.gif");
        getImageDimensions();
    }
}
