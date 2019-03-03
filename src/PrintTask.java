import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PrintTask {
    public void printAll(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void printPast(ArrayList<Task> tasks) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = null;

        for (Task task : tasks) {
            duration = Duration.between(now, task.getDate());
            long min = duration.toMinutes();
            if (min < 0) {
                System.out.println(task);
            }
        }
    }

    public void printFuture24(ArrayList<Task> tasks) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = null;

        for (Task task : tasks) {
            duration = Duration.between(now, task.getDate());
            long hours = duration.toHours();
            long min = duration.toMinutes();
            if (min >= 0 && hours <= 24) {
                System.out.println(task);
            }
        }
    }

    public void printFuture30Day(ArrayList<Task> tasks) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime daysPlus30 = now.plusDays(30);

        taskBetweenNowDate(tasks, now, daysPlus30);
    }

    private void taskBetweenNowDate(ArrayList<Task> tasks, LocalDateTime now, LocalDateTime day) {
        for (Task task : tasks) {
            Duration duration1 = Duration.between(now, task.getDate());
            Duration duration2 = Duration.between(day, task.getDate());
            long min1 = duration1.toMinutes();
            long min2 = duration2.toMinutes();
            if (min1 >= 0 && min2 < 0) {
                System.out.println(task);
            }
        }
    }

    public void printFutureEndWeek(ArrayList<Task> tasks) {
        LocalDateTime now = LocalDateTime.now();
        int hourNow = now.getHour();
        int minNow = now.getMinute();
        int secNow = now.getSecond();
        String nowName = now.getDayOfWeek().name();

        DayWeek[] values = DayWeek.values();
        LocalDateTime endWeek = null;

        for (DayWeek value : values) {
            if (value.name().equals(nowName)) {
                endWeek = now.plusDays(value.getDayToEndWeek()).minusHours(hourNow).minusMinutes(minNow).minusSeconds(secNow + 1);
            }
        }
        taskBetweenNowDate(tasks, now, endWeek);
        }

    }
