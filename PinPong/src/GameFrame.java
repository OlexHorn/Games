import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel panel;
    private DatabaseConnector databaseConnector;
    GameFrame() {
        this.databaseConnector = new DatabaseConnector();
        LoginFrame loginFrame = new LoginFrame(this, databaseConnector);
        loginFrame.setVisible(true);
        if (loginFrame.isAuthenticated()) {
            panel = new GamePanel(databaseConnector);
            this.add(panel);
            this.setTitle("Pong Game");
            this.setResizable(false);
            this.setBackground(Color.BLACK);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            OptionsMenu optionsMenu = new OptionsMenu(databaseConnector, panel);
            this.setJMenuBar(optionsMenu);

            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }
    }

}
