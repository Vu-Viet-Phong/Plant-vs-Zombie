package code;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Lawnmower extends Character {
    private static int speed = 20;
    private Image img;

    public Lawnmower(int x, int y) {
        super(x, y, null, speed, 0, 0);
        img = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
    }

    @Override
    protected void move() {
        x += speed;
    }
}
