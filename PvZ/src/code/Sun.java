package code;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sun {
    private int speed = 2;
    private Image image;

    public Sun() {
        image = new ImageIcon(this.getClass().getResource("/images/sun.png")).getImage();
    }

    
}
