import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    String content;
    LocalDateTime date;

    public Task(String content, LocalDateTime date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {

        return content + ";" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}