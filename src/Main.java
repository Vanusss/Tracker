import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new FileBackedTasksManager();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Задачу какого типа вы хотите создать?");
                    int type = manager.chooseTaskType();
                    switch (type) {
                        case 1:
                            manager.saveTask();
                            break;
                        case 2:
                            manager.saveSubTask();
                            break;
                        case 3:
                            manager.saveEpic();
                            break;
                        default:
                            System.out.println("Ошибка создания задачи");
                    }
                    break;
                case 2:
                    manager.updateTask();
                    break;
                case 3:
                    manager.printAllTasks();
                    break;
                case 4:
                    manager.deleteAllTasks();
                    break;
                case 5:
                    manager.deleteById();
                    break;
                case 6:
                    manager.historyManager.linkLast(manager.getById());
                    break;
                case 7:
                    manager.printAllEpicsSubTasks();
                    break;
                    case 8:
                        manager.historyManager.printHistory();
                        break;
                default:
                    System.out.println("Ошибка меню");
            }
        }
    }

    public static void printMenu() {
        System.out.println("1 - Создать новую задачу");
        System.out.println("2 - Обновить задачу");
        System.out.println("3 - Получить список всех задач");
        System.out.println("4 - Удалить все задачи");
        System.out.println("5 - Удалить задачу по идентификатору");
        System.out.println("6 - Получить задачу по идентификатору");
        System.out.println("7 - Получить все подзадачи определенного эпика");
        System.out.println("8 - Получить историю просмотра задач");

    }
}