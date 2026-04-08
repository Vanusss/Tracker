public class SubTask extends Task {
    private int epicId;
    private Type type = Type.SUBTASK;

    public SubTask(String name, String description) {
        super(name, description);
    }
    @Override
    public String toString(){
       return super.toString() + getEpicId() + ",";
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
