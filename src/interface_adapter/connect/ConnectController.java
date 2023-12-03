package interface_adapter.connect;
import use_case.connect.ConnectInputBoundary;
import use_case.connect.ConnectInputData;
import java.time.LocalDateTime;
public class ConnectController {

    final ConnectInputBoundary connectUseCaseInteractor;

    public ConnectController(ConnectInputBoundary connectUseCaseInteractor) {
        this.connectUseCaseInteractor = connectUseCaseInteractor;
    }

    public void execute(String title, LocalDateTime time) {
        ConnectInputData connectInputData = new ConnectInputData(title, time);

        connectUseCaseInteractor.execute(connectInputData);
    }
}
