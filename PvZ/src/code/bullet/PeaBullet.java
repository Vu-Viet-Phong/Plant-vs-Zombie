package code.bullet;

public class PeaBullet extends Bullet {
    private static String name = "PeaBullet";
    private static int damage = 50;

    public PeaBullet(int x, int y, String name, int speed, int health, int damage) {
        super(x, y, name, speed, health, damage);
        loadImage("/images/bullet/pea_bullet.png");
        getImageDimensions();
    }
}
