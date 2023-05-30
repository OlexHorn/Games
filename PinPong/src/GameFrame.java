import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel panel;
    private DatabaseConnector databaseConnector;
    GameFrame() {
        LoginFrame loginFrame = new LoginFrame(this); // Передаем владельца как текущий GameFrame
        loginFrame.setVisible(true); // Отображаем диалоговое окно
        if (loginFrame.isAuthenticated()) {
            panel = new GamePanel();
            this.add(panel);
            this.setTitle("Pong Game");
            this.setResizable(false);
            this.setBackground(Color.BLACK);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            databaseConnector = new DatabaseConnector();
            OptionsMenu optionsMenu = new OptionsMenu(databaseConnector);
            this.setJMenuBar(optionsMenu);

            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }
    }

}
