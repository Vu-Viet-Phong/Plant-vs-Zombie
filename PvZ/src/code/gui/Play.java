package code.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Play extends JFrame {
    public Play() {
        initUI();
    }

    private void initUI() {
        add(new Background());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> 
            new Wallpaper().setVisible(true)
        );
    }
}
