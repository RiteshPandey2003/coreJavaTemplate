import dao.dao;
import dao.daoImp;
import model.noteModel;
import util.dbConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);

        try {
            conn = dbConnection.getConnection();
            dao d = new daoImp(conn);

            while (true) {
                System.out.println("\n--- Task Menu ---");
                System.out.println("1. Insert Task");
                System.out.println("2. Update Task");
                System.out.println("3. Get Task");
                System.out.println("4. Delete Task");
                System.out.println("5. Get All Tasks");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Task ID: ");
                        int insertId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Description: ");
                        String insertDesc = scanner.nextLine();
                        System.out.print("Enter Status: ");
                        String insertStatus = scanner.nextLine();
                        System.out.print("Enter Link: ");
                        String insertLink = scanner.nextLine();

                        noteModel newNote = new noteModel(insertId, insertDesc, insertStatus, insertLink);
                        d.save(newNote);
                        System.out.println("Task inserted successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Task ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Description: ");
                        String updateDesc = scanner.nextLine();
                        System.out.print("Enter New Status: ");
                        String updateStatus = scanner.nextLine();
                        System.out.print("Enter New Link: ");
                        String updateLink = scanner.nextLine();

                        noteModel updatedNote = new noteModel(updateId, updateDesc, updateStatus, updateLink);
                        d.update(updatedNote);
                        System.out.println("Task updated successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Task ID to fetch: ");
                        int getId = scanner.nextInt();
                        noteModel fetchedNote = d.get(getId);
                        if (fetchedNote != null) {
                            System.out.println("Task ID: " + fetchedNote.getTask_id());
                            System.out.println("Description: " + fetchedNote.getDescription());
                            System.out.println("Status: " + fetchedNote.getStatus());
                            System.out.println("Link: " + fetchedNote.getLinks());
                        } else {
                            System.out.println("Task not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Task ID to delete: ");
                        int deleteId = scanner.nextInt();
                        d.delete(deleteId);
                        System.out.println("Task deleted successfully.");
                        break;

                    case 5:
                        List<noteModel> allNotes = d.getAll();
                        if (allNotes.isEmpty()) {
                            System.out.println("No tasks found.");
                        } else {
                            for (noteModel note : allNotes) {
                                System.out.println("---------------");
                                System.out.println("Task ID: " + note.getTask_id());
                                System.out.println("Description: " + note.getDescription());
                                System.out.println("Status: " + note.getStatus());
                                System.out.println("Link: " + note.getLinks());
                            }
                        }
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

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
