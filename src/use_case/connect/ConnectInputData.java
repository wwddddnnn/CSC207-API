package use_case.connect;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConnectInputData {
    final private String title;
    final  private LocalDate time;
    public ConnectInputData(String title, LocalDate time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle(){return title;}
    public LocalDate getTime(){return time;}

}
