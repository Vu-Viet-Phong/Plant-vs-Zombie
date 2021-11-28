package code.zombie;

/**
 * @author Đinh Bình Thanh Thông
 */
public class FootballZombie extends Zombie {
    public FootballZombie(int x, int y) {
        super(x, y);
        setSpeed(1.5);
        setHealth(500);
        setDamage(75);
        loadImage("/images/zombies/football_zombie.gif");
    }
}
