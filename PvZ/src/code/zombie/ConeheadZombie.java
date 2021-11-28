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
        getImageDimensions();
    }

    public static void main(String[] args) {
        Zombie z = new ConeheadZombie(5, 5);
        System.out.println(z.getImage());
        System.out.println(z.isVisible());
    }
}
