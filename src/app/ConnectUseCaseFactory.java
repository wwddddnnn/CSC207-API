package app;

import interface_adapter.connect.ConnectController;
import interface_adapter.connect.ConnectPresenter;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.ViewManagerModel;
import use_case.connect.ConnectInputBoundary;
import use_case.connect.ConnectInteractor;
import use_case.connect.ConnectOutputBoundary;
import use_case.connect.ConnectDataAccessInterface;
import view.ConnectView;

public class ConnectUseCaseFactory {
    private ConnectUseCaseFactory() {}

    public static ConnectView create(ViewManagerModel viewManagerModel,
                                     ConnectViewModel connectViewModel,
                                     ConnectDataAccessInterface connectDataAccessObject) {
        ConnectController connectController = createConnectUseCase(viewManagerModel, connectViewModel, connectDataAccessObject);
        return new ConnectView(connectController, connectViewModel);
    }

    private static ConnectController createConnectUseCase(ViewManagerModel viewManagerModel,
                                                          ConnectViewModel connectViewModel,
                                                          ConnectDataAccessInterface connectDataAccessObject) {

        ConnectOutputBoundary connectOutputBoundary = new ConnectPresenter(viewManagerModel, connectViewModel);
        ConnectInputBoundary connectInteractor = new ConnectInteractor(connectDataAccessObject, connectOutputBoundary);

        return new ConnectController(connectInteractor);
    }
}

