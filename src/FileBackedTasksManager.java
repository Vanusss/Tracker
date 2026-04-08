import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileBackedTasksManager extends Manager {
    void save(){
        try (FileWriter writer = new FileWriter("Data.csv")) {
            for(Task task : tasks.values()){
                    writer.write(task.toString());
                    writer.write("\n");
            }
            for(Epic epic : epics.values()){
                writer.write(epic.toString());
                writer.write("\n");
            }
            for(SubTask subTask : subTasks.values()){
                writer.write(subTask.toString());
                writer.write("\n");
            }
            writer.write("\n");
            for(int history : historyManager.calculateHistory()){
                writer.write(history);
            }


            } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения файла");
        }
        }
        void loadTasksFromFile(){
            try (BufferedReader br = new BufferedReader(new FileReader("Data.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(line.isBlank() || line.isEmpty()){
                        break;
                    }
                    String[] parts = line.split(",");
                    if(parts[1] == "TASK"){
                        tasks.put(Integer.parseInt(parts[0]), new Task(parts[4], parts[2], Integer.parseInt(parts[0]), Status.valueOf(parts[3])));
                    }

                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении CSV");
                e.printStackTrace();
            }
        }
        void loadFromFile(){

        }



    }

