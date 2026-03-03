import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    int idTracker = 1;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    Task prepareTask(Task task) {
        task.setId(idTracker);
        idTracker += 1;
        int choice = 0;
        task.setStatus(Status.NEW);
        return task;


    }

    void saveTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Имя задачи:");
        String name = scanner.nextLine();
        System.out.println("Описание задачи:");
        String description = scanner.nextLine();
        Task task = new Task(name, description);
        Task taskToSave = prepareTask(task);
        tasks.put(taskToSave.getId(), taskToSave);

    }

    void saveSubTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Имя подзадачи:");
        String name = scanner.nextLine();
        System.out.println("Описание подзадачи:");
        String description = scanner.nextLine();
        System.out.println("Введите id эпика:");
        int epicId = scanner.nextInt();
        if(!epics.containsKey(epicId)) {
            System.out.println("Неверный идентификатор эпика");
            return;
        }
        SubTask subTask = new SubTask(name, description);
        SubTask subTaskToSave = (SubTask) prepareTask(subTask);
        subTaskToSave.setEpicId(epicId);
        subTasks.put(subTaskToSave.getId(), subTaskToSave);
        epics.get(epicId).getSubTasks().add(subTaskToSave);
        updateEpicStatus(epics.get(epicId));
    }

    void updateEpicStatus(Epic epic) {
        int statusNew = 0;
        int statusInProgress = 0;
        int statusDone = 0;
        int k = 0;
        for (SubTask subTask : epic.getSubTasks()) {
            switch (subTask.getStatus()) {
                case NEW:
                    statusNew += 1;
                    k += 1;
                    break;
                case IN_PROGRESS:
                    statusInProgress += 1;
                    k += 1;
                    break;
                case DONE:
                    statusDone += 1;
                    k += 1;
                    break;
                default:
                    System.out.println("Возникла ошибка при проверке статусов");
            }
        }
        if ((statusNew == 0) && (statusInProgress == 0) && (statusDone == k)) {
            epic.setStatus(Status.DONE);
        } else if ((statusDone == 0) && (statusInProgress == 0) && (statusNew == k)) {
            epic.setStatus(Status.NEW);
        } else if ((statusDone != k) && (statusNew != k)) {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    Epic prepareEpic(Epic epic) {
        epic.setId(idTracker);
        idTracker += 1;
        epic.setStatus(Status.NEW);
        return epic;
    }

    void saveEpic() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Имя эпика:");
        String name = scanner.nextLine();
        System.out.println("Описание эпика:");
        String description = scanner.nextLine();
        Epic epic = new Epic(name, description);
        Epic epicToSave = prepareEpic(epic);
        epics.put(epicToSave.getId(), epicToSave);
    }

    void reWriteTask(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите обновленное имя задачи:");
        String name = scanner.nextLine();
        System.out.println("Введите обновленное описание задачи:");
        String description = scanner.nextLine();
        System.out.println("Введите статус задачи:");
        System.out.println("1 - new");
        System.out.println("2 - in progress");
        System.out.println("3 - done");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                task.setStatus(Status.NEW);
                break;
            case 2:
                task.setStatus(Status.IN_PROGRESS);
                break;
            case 3:
                task.setStatus(Status.DONE);
                break;
            default:
                System.out.println("Возникла ошибка");
        }

        task.setDescription(description);
        task.setName(name);
        tasks.put(task.getId(), task);
    }

    void updateTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите идентификатор задачи:");
        int id = scanner.nextInt();
        if (tasks.containsKey(id)) {
            reWriteTask(tasks.get(id));
        } else if (subTasks.containsKey(id)) {
            rewriteSubTask(subTasks.get(id));
        } else if (epics.containsKey(id)) {
            rewriteEpic(epics.get(id));
        } else if ((!subTasks.containsKey(id)) && (!epics.containsKey(id)) && (!tasks.containsKey(id))) {
            System.out.println("Неверный идентификатор");
        }

    }

    void rewriteSubTask(SubTask subTask) { // не забыть проверить статус эпика
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите обновленное имя подзадачи:");
        String name = scanner.nextLine();
        System.out.println("Введите обновленное описание подзадачи:");
        String description = scanner.nextLine();
        System.out.println("Введите статус подзадачи:");
        System.out.println("1 - new");
        System.out.println("2 - in progress");
        System.out.println("3 - done");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                subTask.setStatus(Status.NEW);
                break;
            case 2:
                subTask.setStatus(Status.IN_PROGRESS);
                break;
            case 3:
                subTask.setStatus(Status.DONE);
                break;
            default:
                System.out.println("Возникла ошибка");
        }
        subTask.setDescription(description);
        subTask.setName(name);
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus(epics.get(subTask.getEpicId()));
    }

    void rewriteEpic(Epic epic) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите обновленное имя эпика:");
        String name = scanner.nextLine();
        System.out.println("Введите обновленное описание эпика:");
        String description = scanner.nextLine();
        epic.setName(name);
        epic.setDescription(description);
    }

    void deleteAllTasks() {
        tasks.clear();
        subTasks.clear();
        epics.clear();
        System.out.println("Список всех задач очищен");
    }

    void printAllTasks() {
        if (!tasks.isEmpty()) {
            int k = 1;
            System.out.println("Задачи:");
            for (Task task : tasks.values()) {
                System.out.println(k + ")" + "имя: " + task.getName());
                System.out.println("описание:" + task.getDescription());
                System.out.println("идентификатор:" + task.getId());
                System.out.println("статус: " + task.getStatus());
                k += 1;
            }
        }
        if (!epics.isEmpty()) {
            int k = 1;
            System.out.println("Эпики:");
            for (Epic epic : epics.values()) {
                System.out.println(k + ")" + "имя: " + epic.getName());
                System.out.println("описание:" + epic.getDescription());
                System.out.println("идентификатор:" + epic.getId());
                System.out.println("статус: " + epic.getStatus());
                k += 1;

            }
        }
        if (!subTasks.isEmpty()) {
            int k = 1;
            System.out.println("Подзадачи:");
            for (SubTask subTask : subTasks.values()) {
                System.out.println(k + ")" + "имя: " + subTask.getName());
                System.out.println("описание:" + subTask.getDescription());
                System.out.println("идентификатор:" + subTask.getId());
                System.out.println("статус: " + subTask.getStatus());
                k += 1;

            }
        }
    }

    void printAllEpicsSubTasks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите идентификатор эпика:");
        int id = scanner.nextInt();
        if (epics.containsKey(id)) {
            if (!epics.get(id).getSubTasks().isEmpty()) {
                int k = 1;
                System.out.println("Подзадачи:");
                for (SubTask subTask : epics.get(id).getSubTasks()) {
                    System.out.println(k + ")" + "имя: " + subTask.getName());
                    System.out.println("описание:" + subTask.getDescription());
                    System.out.println("идентификатор:" + subTask.getId());
                    System.out.println("статус: " + subTask.getStatus());
                    k += 1;

                }
            } else System.out.println("У этого эпика нет подзадач");
        } else System.out.println("Неверный идентификатор");
    }

    void deleteById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id задачи:");
        int id = scanner.nextInt();
        if (tasks.containsKey(id)) {
            System.out.println("Задача '" + tasks.get(id).getName() + "' успешно удалена");
            tasks.remove(id);
        } else if (subTasks.containsKey(id)) {
            System.out.println("Подзадача '" + subTasks.get(id).getName() + "' успешно удалена");
            int epicId = subTasks.get(id).getEpicId();
            epics.get(epicId).getSubTasks().remove(subTasks.get(id));
            subTasks.remove(id);
            updateEpicStatus(epics.get(epicId));
        } else if (epics.containsKey(id)) {
            System.out.println("Эпик '" + epics.get(id).getName() + "' успешно удален");
            for(SubTask subTask : epics.get(id).getSubTasks()) {
                subTasks.remove(subTask.getId());
            }
            epics.remove(id);
        } else if ((!subTasks.containsKey(id)) && (!epics.containsKey(id)) && (!tasks.containsKey(id))) {
            System.out.println("Неверный идентификатор");
        }
    }

    void getById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id задачи:");
        int id = scanner.nextInt();
        if (tasks.containsKey(id)) {
            System.out.println("Задача:");
            System.out.println("имя: " + tasks.get(id).getName());
            System.out.println("описание:" + tasks.get(id).getDescription());
            System.out.println("идентификатор:" + tasks.get(id).getId());
            System.out.println("статус: " + tasks.get(id).getStatus());
        } else if (subTasks.containsKey(id)) {
            System.out.println("Подзадача:");
            System.out.println("имя: " + subTasks.get(id).getName());
            System.out.println("описание:" + subTasks.get(id).getDescription());
            System.out.println("идентификатор:" + subTasks.get(id).getId());
            System.out.println("статус: " + subTasks.get(id).getStatus());
        } else if (epics.containsKey(id)) {
            System.out.println("Эпик:");
            System.out.println("имя: " + epics.get(id).getName());
            System.out.println("описание:" + epics.get(id).getDescription());
            System.out.println("идентификатор:" + epics.get(id).getId());
            System.out.println("статус: " + epics.get(id).getStatus());

        } else if ((!subTasks.containsKey(id)) && (!epics.containsKey(id)) && (!tasks.containsKey(id))) {
            System.out.println("Неверный идентификатор");
        }
    }

    int chooseTaskType() {
        int choice = 0;
        while ((choice != 1) && (choice != 2) && (choice != 3)) {
            System.out.println("1 - Задача");
            System.out.println("2 - Подзадача");
            System.out.println("3 - Эпик");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            if ((choice != 1) && (choice != 2) && (choice != 3)) {
                System.out.println("Ошибка ввода, выберите еще раз");
            }
        }
        return choice;
    }

}
