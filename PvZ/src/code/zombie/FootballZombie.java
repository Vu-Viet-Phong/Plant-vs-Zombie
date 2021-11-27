package code.zombie;

/**
 * @author Đinh Bình Thanh Thông
 */
public class FootballZombie extends Zombie {
    private static String name = "FootballZombie";
    private static int health = 500;
    private static int damage = 75;

    public FootballZombie(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
        loadImage("/src/images/.gif");
        getImageDimensions();
    }
}
