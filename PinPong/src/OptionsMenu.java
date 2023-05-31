import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JMenuBar {
    private DatabaseConnector databaseConnector;
    private GamePanel panel;

    OptionsMenu(DatabaseConnector connector, GamePanel panel) {
        this.databaseConnector = connector;
        this.panel = panel;

        JMenu optionsMenu = new JMenu("Options");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem createProfileItem = new JMenuItem("Create Profile");
        createProfileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter username:");
                String password = JOptionPane.showInputDialog("Enter password:");

                databaseConnector.createProfile(username, password);
            }
        });

        JMenuItem saveScoreItem = new JMenuItem("Save score");
        saveScoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.saveScore();
            }
        });

        optionsMenu.add(createProfileItem);
        optionsMenu.add(saveScoreItem);
        optionsMenu.add(exitItem);

        this.add(optionsMenu);
    }
}
