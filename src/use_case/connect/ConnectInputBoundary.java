package use_case.connect;

import use_case.login.LoginInputData;

public interface ConnectInputBoundary {
    void execute(ConnectInputData connectInputData);
}
