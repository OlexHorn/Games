import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnector {
    private Connection connection;
    private String connectionString = "jdbc:sqlserver://DESKTOP-O55NP8M\\INTERGENSERVER;databaseName=Games;trustServerCertificate=true;";
    private Statement statement;

    public DatabaseConnector() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(connectionString, "sa", "sa123");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProfile(String username, String password) {
        try {
            String query = "INSERT INTO Profiles (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Profile created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
        try {
            String query = "SELECT * FROM Profiles WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Return true if a matching row is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
