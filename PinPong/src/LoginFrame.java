import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private DatabaseConnector databaseConnector;
    private boolean authenticated;

    public LoginFrame(Frame owner) {
        super(owner, "Login", true); // Устанавливаем диалоговый режим

        databaseConnector = new DatabaseConnector();
        authenticated = false;
        initializeComponents();
        setupLayout();
        setupListeners();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Центрируем диалоговое окно на экране
        setResizable(false);
    }

    private void initializeComponents() {
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
    }

    private void setupLayout() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel()); // Пустая метка для отступа
        add(loginButton);
    }

    private void setupListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (databaseConnector.authenticate(username, password)) {
                    authenticated = true;
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                } else {
                    authenticated = false;
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password");
                }

                dispose(); // Закрываем диалоговое окно после аутентификации
            }
        });
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
