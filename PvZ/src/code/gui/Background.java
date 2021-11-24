package code.gui;

import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Background extends JPanel {
    private Image backgroundgImg;

    private Image peashooterImg;
    private Image freezePeashooterImg;
    private Image sunflowerImg;
    private Image peaImg;
    private Image freezePeaImg;

    private Image normalZombieImg;
    private Image coneHeadZombieImg;

    private int collectSun;
    private JLabel sunScoreboard;

    public Background() {
        initUI();
    }

    private void initUI() {
        setLayout(null);
        backgroundgImg = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundgImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public int getCollectSun() {
        return collectSun;
    }

    public void setCollectSun(int collectSun) {
        this.collectSun = collectSun;
        sunScoreboard.setText(String.valueOf(collectSun));
    }
}
