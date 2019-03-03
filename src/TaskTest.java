import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskTest {
    public static void main(String[] args) {
        File file = new File("zadania1.csv");
        String pattern1 = "yyyy-MM-dd HH:mm";
        String pattern2 = "dd.MM.yyyy HH:mm";

        Scanner scan = new Scanner(System.in);
        Import importFile = new Import();
        ArrayList<Task> tasks = importFile.getImport(file); //zaczyt z pliku historii
        PrintTask printTask = new PrintTask();

        TaskAction acctionChoice = getTaskAction(scan);

        switch (acctionChoice) {
            case ADD: {
                addMethod(file, pattern1, pattern2, tasks, scan); //dodaje do listy, wyświetla wszystkie i zapisuje do pliku
                break;
            }
            case PRINT: {
                PrintTaskPeriod periodChoice = getPrintTaskPeriod(scan);

                switch (periodChoice) {
                    case ALL: {
                        printTask.printAll(tasks);
                        break;
                    }
                    case PAST: {
                        printTask.printPast(tasks);
                        break;
                    }
                    case FUTURE:
                        System.out.println("W ciągu najbliższych 24 godzin musisz zrobić następujące zadania: ");
                        printTask.printFuture24(tasks);
                        System.out.println("-----------------");
                        System.out.println("W ciągu najbliższych 30 dni musisz zrobić następujące zadania: ");
                        printTask.printFuture30Day(tasks);
                        System.out.println("-----------------");
                        System.out.println("Do końca tygodnia masz zrobić: ");
                        printTask.printFutureEndWeek(tasks);
                        break;
                }
                break;
            }
            default: {
                System.out.println("Nieprawidłowa wartość!");
            }
        }

    }

    private static PrintTaskPeriod getPrintTaskPeriod(Scanner scan) {
        PrintTaskPeriod[] printTaskPeriods = PrintTaskPeriod.values();
        System.out.println("Jakie zadania chcesz wyświetlić? Do wyboru masz");
        for (PrintTaskPeriod printTaskPeriod : printTaskPeriods) {
            System.out.println(printTaskPeriod);
        }

        PrintTaskPeriod periodChoice = null;
        try {
            periodChoice = PrintTaskPeriod.valueOf(scan.nextLine().toUpperCase());

        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowa wartość");
        }
        return periodChoice;
    }

    private static TaskAction getTaskAction(Scanner scan) {
        TaskAction[] actions = TaskAction.values();
        System.out.println("Co chcesz wykonać z danymi zadaniami?");
        for (TaskAction action : actions) {
            System.out.println(action);
        }

        TaskAction acctionChoice = null;
        try {
            acctionChoice = TaskAction.valueOf(scan.nextLine().toUpperCase());

        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowa wartość");
        }
        return acctionChoice;
    }

    private static void addMethod(File file, String pattern1, String pattern2, ArrayList<Task> tasks, Scanner scan) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(pattern1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2);

        System.out.println("Podaj treść zadania");
        String tmpName = scan.nextLine();
        System.out.println("Podaj termin do kiedy ma być wykonane");
        String tmpDate = scan.nextLine();
        try {
            tasks.add(new Task(tmpName, LocalDateTime.parse(tmpDate, formatter1)));
        } catch (DateTimeParseException e) {
            try {
                tasks.add(new Task(tmpName, LocalDateTime.parse(tmpDate, formatter2)));
            } catch (DateTimeParseException e1) {
                System.out.println("Błędny format");
            }
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
        Export export = new Export();
        export.exportCsv(tasks, file);
    }
}