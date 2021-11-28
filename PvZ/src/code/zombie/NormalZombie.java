package code.zombie;

/**
 * @author Trương Đăng Khoa
 */
public class NormalZombie extends Zombie {
    public NormalZombie(int x, int y) {
        super(x, y);
        setHealth(300);
        setDamage(50);
        loadImage("/images/zombies/normal_zombie.gif");
        getImageDimensions();
    }
}
