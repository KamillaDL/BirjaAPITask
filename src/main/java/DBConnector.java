import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private static final Logger log = Logger.getLogger(DBConnector.class);
    static Connection connection = null;

    public static Connection getConnection() throws IOException {
        try {
            Properties props = new Properties();
            props.load(DBConnector.class.getClassLoader()
                    .getResourceAsStream("database.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String driver = props.getProperty("driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException x) {
            System.out.println("Connection Failed");
            log.error("SQLException: connection with Database failed");
            x.printStackTrace();
        } catch (ClassNotFoundException e) {
            log.error("Error with JDBC Driver");
            e.printStackTrace();
        }

        log.info("Successfully connected to DB");
        return connection;
    }

    public static void close(){
        try {
            if(connection != null){
                connection.close();
            }
        }
        catch (SQLException e) {
            log.error("SQLException");
            e.printStackTrace();
        }
    }
}