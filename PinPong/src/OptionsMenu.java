import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JMenuBar {
    private DatabaseConnector databaseConnector;

    OptionsMenu(DatabaseConnector connector) {
        this.databaseConnector = connector;

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

        optionsMenu.add(createProfileItem);
        optionsMenu.add(exitItem);

        this.add(optionsMenu);
    }
}
