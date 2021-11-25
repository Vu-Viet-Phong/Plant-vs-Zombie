package code.plant;

import java.awt.Image;
import java.util.Timer;

import javax.swing.ImageIcon;

public class PeaShooter extends Plant {
    private Image peaShooterImg;
    public Timer shootTimer;

    public PeaShooter(int x, int y) {
        super(x, y, health);
        peaShooterImg = new ImageIcon(this.getClass().getResource("/images/plants/peashooter.gif")).getImage();
    }

    public Image getImage() {
        return peaShooterImg;
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }
}
