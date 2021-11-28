package code.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.Cards;
import code.Lawnmower;
import code.gui.Background;

public class Play extends JFrame {
    enum PlantType {
        None, Sunflower, Peashooter, Peashooter2, Torchwood, Wallnut
    }

    public Play() {
        initUI();
    }
    
    private void initUI() {
        setSize(1812, 1030);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        JLabel sun = new JLabel("SUN");
        sun.setIcon(new ImageIcon(getClass().getResource("/images/sun_score.png")));
        sun.setLocation(32, 50);
        sun.setSize(120, 35);

        Background bg = new Background(sun);
        bg.setLocation(0, 0);

        getLayeredPane().add(bg, new Integer(0));
        getLayeredPane().add(sun, new Integer(1));

        Cards sunflower = new Cards(new ImageIcon(this.getClass().getResource("/images/cards/Sunflower.png")).getImage());
        sunflower.setLocation(0, 100);
        sunflower.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));

        Cards peashooter = new Cards(new ImageIcon(this.getClass().getResource("/images/cards/Peashooter.png")).getImage());
        peashooter.setLocation(0, 250);
        peashooter.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Peashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));

        Cards peashooter2 = new Cards(new ImageIcon(this.getClass().getResource("/images/cards/Peashooter2.png")).getImage());
        peashooter2.setLocation(0, 400);
        peashooter2.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Peashooter2);
        });
        getLayeredPane().add(peashooter2, new Integer(3));

        Cards torchwood = new Cards(new ImageIcon(this.getClass().getResource("/images/cards/Torchwood.png")).getImage());
        torchwood.setLocation(0, 550);
        torchwood.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Torchwood);
        });
        getLayeredPane().add(torchwood, new Integer(3));

        Cards wallnut = new Cards(new ImageIcon(this.getClass().getResource("/images/cards/Wallnut.png")).getImage());
        wallnut.setLocation(0, 700);
        wallnut.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Wallnut);
        });
        getLayeredPane().add(wallnut, new Integer(3));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Play();
        });
    }
}
