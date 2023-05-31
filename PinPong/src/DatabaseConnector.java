import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnector {
    private Connection connection;
    private String connectionString = "jdbc:sqlserver://DESKTOP-O55NP8M\\INTERGENSERVER;databaseName=Games;trustServerCertificate=true;";
    private Statement statement;

    private int userID = 0;

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
        } finally {
            this.userID = getUserIdByUsername(username);
        }
    }

    public void saveScore(int score1, int score2) {
        try {
            String query = "SELECT COUNT(*) FROM Scores WHERE userID = ?";
            PreparedStatement countStatement = connection.prepareStatement(query);
            countStatement.setInt(1, this.userID);
            ResultSet resultSet = countStatement.executeQuery();

            resultSet.next();
            int rowCount = resultSet.getInt(1);
            System.out.println(rowCount);

            if (rowCount > 0) {
                query = "UPDATE Scores SET score1 = ?, score2 = ? WHERE userID = ?";
                PreparedStatement updateStatement = connection.prepareStatement(query);
                updateStatement.setInt(1, score1);
                updateStatement.setInt(2, score2);
                updateStatement.setInt(3, this.userID);
                updateStatement.executeUpdate();
            } else {
                query = "INSERT INTO Scores (userID, score1, score2) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(query);
                insertStatement.setInt(1, this.userID);
                insertStatement.setInt(2, score1);
                insertStatement.setInt(3, score2);
                insertStatement.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Score saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public int getUserIdByUsername(String username) {
        try {
            String query = "SELECT id FROM Profiles WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getUserID() {
        return userID;
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
