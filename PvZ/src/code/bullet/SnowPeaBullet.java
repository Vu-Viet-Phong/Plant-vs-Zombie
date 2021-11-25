package code.bullet;

public class SnowPeaBullet extends Bullet {
    private static String name = "PeaBullet";
    private static int damage = 50;

    public SnowPeaBullet(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
        loadImage("/images/bullet/snow_pea_bullet.png");
        getImageDimensions();
    }
}
