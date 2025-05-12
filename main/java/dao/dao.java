package dao;
import model.noteModel;
import java.util.List;

public interface dao {
    void save(noteModel note);
    noteModel get(int taskId);
    void update(noteModel note);
    void delete(int taskId);
    List<noteModel> getAll(); // Optional: fetch all notes
}

