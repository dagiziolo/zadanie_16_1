import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Import {
    public ArrayList<Task> getImport(File file) {
        ArrayList<Task> lists = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (Scanner scan = new Scanner(file);) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] s = line.split(";");
                lists.add(new Task(s[0], LocalDateTime.parse(s[1], dateTimeFormatter)));
            }
        } catch (FileNotFoundException e1) {
            System.out.println("Brak pliku");
        }
        return lists;
    }
}