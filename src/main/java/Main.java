import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

    public static void main(String[] args) {
        try {
            Forum f = new ConsoleBasedForum(
                                "jdbc:mysql://localhost:3306/forum",
                                "root",
                                "admin"
                               );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

