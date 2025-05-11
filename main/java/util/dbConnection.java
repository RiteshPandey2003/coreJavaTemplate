package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnection {
    public static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(dbConnection.class.getName());
    public static Connection getConnection(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "03@March$2025");
        }catch (ClassNotFoundException | SQLException e){
            LOGGER.log(Level.SEVERE,"connection failed", e);
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
                System.out.println("connection close successfully");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE,"connection failed", e);
            }
        }
    }
}
