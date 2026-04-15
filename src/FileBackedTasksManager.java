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
                writer.write(String.valueOf(history));
                writer.write(",");
            }


            } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения файла");
        }
        }

        void loadFromFile(){
        loadTasksFromFile();
        try{
            loadHistoryFromFile();
            System.out.println("История просмотра задач: ");
            historyManager.printHistory();
        } catch (NumberFormatException e){
            System.out.println("В файле нет истории");
        }
        System.out.println("Данные из файла восстановлены");
        System.out.println("Список задач: ");
        printAllTasks();

        }


        void loadTasksFromFile() {
            try (BufferedReader br = new BufferedReader(new FileReader("Data.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isBlank() || line.isEmpty()) {
                        break;
                    }
                    String[] parts = line.split(",");
                    if (parts[1].equals("TASK")) {
                        tasks.put(Integer.parseInt(parts[0]), new Task(parts[4], parts[2], Integer.parseInt(parts[0]), Status.valueOf(parts[3])));
                    } else if (parts[1].equals("EPIC")) {
                        epics.put(Integer.parseInt(parts[0]), new Epic(parts[4], parts[2], Integer.parseInt(parts[0]), Status.valueOf(parts[3])));
                    } else if (parts[1].equals("SUBTASK")) {
                        subTasks.put(Integer.parseInt(parts[0]), new SubTask(parts[4], parts[2], Integer.parseInt(parts[0]), Status.valueOf(parts[3]), Integer.parseInt(parts[5])));
                    }
                    idTracker = Integer.parseInt(parts[0]) + 1;

                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении CSV");
                e.printStackTrace();
            }

        }
        void loadHistoryFromFile(){
            String backedHistory = null;

            try (BufferedReader br = new BufferedReader(new FileReader("Data.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    backedHistory = line;
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении истории");
                e.printStackTrace();
            }
            String[] parts = backedHistory.split(",");
            int[] ids = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                ids[i] = Integer.parseInt(parts[i]); // нужно передавать задачи, подзадачи и эпики, а не только их id
            }
            historyManager.clear();
            historyManager.recoverHistory(ids, tasks, subTasks, epics);


        }
        @Override
        void saveTask(){
        super.saveTask();
        save();
        }
        @Override
        void saveSubTask(){
        super.saveSubTask();
        save();
        }
        @Override
        void updateEpicStatus(Epic epic){
        super.updateEpicStatus(epic);
        save();
        }
        @Override
        void saveEpic(){
        super.saveEpic();
        save();
        }
        @Override
        void reWriteTask(Task task) {
        super.reWriteTask(task);
        save();
        }
        @Override
        void updateTask(){
        super.updateTask();
        save();
        }
        @Override
        void rewriteSubTask(SubTask subTask){
        super.rewriteSubTask(subTask);
        save();
        }
        @Override
        void rewriteEpic(Epic epic){
        super.rewriteEpic(epic);
        save();
        }
        @Override
        void deleteAllTasks(){
        super.deleteAllTasks();
        save();
        }
        @Override
        void deleteById(){
        super.deleteById();
        save();
        }




    }

