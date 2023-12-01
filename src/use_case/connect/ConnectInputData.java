package use_case.connect;
import java.time.LocalDateTime;

public class ConnectInputData {
    final private String title;
    final  private LocalDateTime time;
    public ConnectInputData(String title, LocalDateTime time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle(){return title;}
    public LocalDateTime getTime(){return  time;}

}
