package code.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.Cards;

public class Play extends JFrame {
    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }

    public Play() {
        initUI();
    }

    private void initUI() {
        JLabel sun = new JLabel("SUN");
        sun.setIcon(new ImageIcon(getClass().getResource("/images/sun_score.png")));
        sun.setLocation(100, 60);
        sun.setSize(88, 22);
        
        Background bg = new Background(sun);
        add(bg);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Cards sunflower = new Cards(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110, 8);
        sunflower.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));

        Cards peashooter = new Cards(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175, 8);
        peashooter.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.Peashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));

        Cards freezepeashooter = new Cards(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
        freezepeashooter.setLocation(240, 8);
        freezepeashooter.setAction((ActionEvent e) -> {
            bg.setActivePlantingBrush(PlantType.FreezePeashooter);
        });
        getLayeredPane().add(freezepeashooter, new Integer(3));
        
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> 
            new Wallpaper().setVisible(true)
        );
    }
}
