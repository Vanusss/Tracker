public class Task {
    private String name;
    private String description;
    private int id;
    private Status status;
    private Type type = Type.TASK;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(String description, String name, int id, Status status) {
        this.description = description;
        this.name = name;
        this.id = id;
        this.status = status;
        this.type = Type.TASK;
    }

    @Override
    public String toString(){
        return getId() + "," + getType() + "," + getName() + "," + getStatus() + "," + getDescription() + ",";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    protected void setType(Type type) {
        this.type = type;
    }
}
