import dao.dao;
import dao.daoImp;
import model.noteModel;
import util.dbConnection;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn = dbConnection.getConnection();
            dao d = new daoImp(conn);

            noteModel note = new noteModel(
                    2,
                    "Complete Java insert",
                    "Pending",
                    "https://example.com/java-dao"
            );

            d.save(note);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception in main method", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    LOGGER.info("Database connection closed.");
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Failed to close connection", e);
                }
            }
        }
    }
}
