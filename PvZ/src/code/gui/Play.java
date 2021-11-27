package code.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Play extends JFrame {
    public Play() {
        initUI();
    }

    private void initUI() {
        add(new Background());
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> 
            new Wallpaper().setVisible(true)
        );
    }
}
