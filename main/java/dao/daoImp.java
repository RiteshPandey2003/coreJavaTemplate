package dao;

import util.dbConnection;
import model.noteModel;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoImp implements dao {

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
        }
    }

    @Override
    public noteModel get(int taskId) {
        String sql = "SELECT * FROM task WHERE task_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new noteModel(
                        rs.getInt("task_id"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("links")
                );
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while fetching note", e);
        } finally {
            dbConnection.closeConnection();
        }
        return null;
    }

    @Override
    public void update(noteModel note) {
        String sql = "UPDATE task SET description = ?, status = ?, links = ? WHERE task_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, note.getDescription());
            stmt.setString(2, note.getStatus());
            stmt.setString(3, note.getLinks());
            stmt.setInt(4, note.getTask_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while updating note", e);
        }
    }

    @Override
    public void delete(int taskId) {
        String sql = "DELETE FROM task WHERE task_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while deleting note", e);
        }
    }

    @Override
    public List<noteModel> getAll() {
        List<noteModel> notes = new ArrayList<>();
        String sql = "SELECT * FROM task";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                notes.add(new noteModel(
                        rs.getInt("task_id"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("links")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while fetching all notes", e);
        }
        return notes;
    }
}
