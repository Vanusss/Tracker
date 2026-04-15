public class SubTask extends Task {
    private int epicId;


    public SubTask(String name, String description) {
        super(name, description);
        setType(Type.SUBTASK);

    }
    @Override
    public String toString(){
       return super.toString() + getEpicId() + ",";
    }

    public int getEpicId() {
        return epicId;
    }

    public SubTask(String description, String name, int id, Status status, int epicId) {
        super(description, name, id, status);
        setType(Type.SUBTASK);
        this.epicId = epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
