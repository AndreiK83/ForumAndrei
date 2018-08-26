import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLHandler {
    private static final Logger     LOGGER = Logger.getLogger( MySQLHandler.class.getName() );
    private              String     dbUrl;
    private              String     userDb;
    private              String     password;
    private              Connection connection;

    MySQLHandler(String dbUrl, String userDb, String passDb) throws SQLException {
        LOGGER.log(Level.FINER,"Establishing connection "+dbUrl+", "+userDb+", "+passDb);
        connection = DriverManager.getConnection(dbUrl, userDb, passDb);
    }


    public User getUserByUsername(String providedUsername) {
        User userFromDB = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM user WHERE username = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, providedUsername);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userIdFromDB = resultSet.getInt("user_id");
                String usernameFromDB = resultSet.getString("username");
                String passwordFromDB = resultSet.getString("password");
                String mailFromDB = resultSet.getString("email");
                userFromDB = new User(userIdFromDB, usernameFromDB, passwordFromDB, mailFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFromDB;
    }

    public User getUserByid(int id) {
        User userFromDB = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userIdFromDB = resultSet.getInt("user_id");
                String usernameFromDB = resultSet.getString("username");
                String passwordFromDB = resultSet.getString("password");
                String mailFromDB = resultSet.getString("email");
                userFromDB = new User(userIdFromDB, usernameFromDB, passwordFromDB, mailFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFromDB;
    }

    public boolean insertUser(String username, String password, String mail) {

        String query = " INSERT INTO user (username, password, email) VALUES (?, ?, ?)";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);
            preparedStmt.setString(3, mail);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Category> lista = new ArrayList<Category>();
            Integer idFromDB = null;
            Integer idUserFromDB = null;
            String subjectFromDB = null;

            while (resultSet.next()) {
                idFromDB = resultSet.getInt("category_id");
                idUserFromDB = resultSet.getInt("user_id");
                subjectFromDB = resultSet.getString("subject");
                lista.add(new Category(idFromDB, getUserByid(idUserFromDB), subjectFromDB));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewCategory(String subjectRead, Integer user_id) {
        String query = " INSERT INTO category (user_id, subject) VALUES (?, ?)";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user_id);
            preparedStmt.setString(2, subjectRead);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(Integer idCategory) {
        Category categoryFromDB = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM category WHERE category_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCategory);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int categoryIdFromDB = resultSet.getInt("category_id");
                int userIdFromDB = resultSet.getInt("user_id");
                String subjectFromDB = resultSet.getString("subject");

                categoryFromDB = new Category(userIdFromDB, getUserByid(userIdFromDB), subjectFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryFromDB;
    }

    public void cleanup()
    {
        String query = "DELETE from category";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            query = "DELETE from user";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
