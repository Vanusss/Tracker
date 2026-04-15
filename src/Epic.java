import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>();

    public Epic(String description, String name, int id, Status status) {
        super(description, name, id, status);
        setType(Type.EPIC);
    }

    public Epic(String name, String description) {
        super(name, description);
        setType(Type.EPIC);

    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
