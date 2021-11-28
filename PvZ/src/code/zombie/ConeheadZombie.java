package code.zombie;

/**
 * @author Trương Đăng Khoa
 */
public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int x, int y) {
        super(x, y);
        setHealth(400);
        setDamage(60);
        loadImage("/images/zombies/conehead_zombie.gif");
    }
}
