package dao;
import util.dbConnection;
import model.noteModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class daoImp implements dao{


    private static final Logger LOGGER = Logger.getLogger(daoImp.class.getName());
    private final Connection conn;

    public daoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(noteModel note) {
        String sql = "INSERT INTO task (task_id, description, status, links) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, note.getTask_id());
            stmt.setString(2, note.getDescription());
            stmt.setString(3, note.getStatus());
            stmt.setString(4, note.getLinks());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while saving note", e);
        } finally {
            if (conn != null) {
                dbConnection.closeConnection();
            }
        }
    }
}

