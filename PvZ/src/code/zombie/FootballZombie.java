package code.zombie;

public class FootballZombie extends Zombie {
    private static String name = "FootballZombie";
    private static int health = 500;
    private static int damage = 75;

    public FootballZombie(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
        loadImage("/src/images/normal_zombie.gif");
        getImageDimensions();
    }
}
