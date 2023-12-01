package CACoding.src.app;

import CACoding.src.interface_adapter.clear_users.ClearController;
import CACoding.src.interface_adapter.clear_users.ClearPresenter;
import CACoding.src.interface_adapter.clear_users.ClearViewModel;
import CACoding.src.interface_adapter.login.LoginViewModel;
import CACoding.src.interface_adapter.signup.SignupController;
import CACoding.src.interface_adapter.signup.SignupPresenter;
import CACoding.src.interface_adapter.signup.SignupViewModel;
import CACoding.src.use_case.clear_users.ClearInputBoundary;
import CACoding.src.use_case.clear_users.ClearInteractor;
import CACoding.src.use_case.clear_users.ClearOutputBoundary;
import CACoding.src.use_case.clear_users.ClearUserDataAccessInterface;
import CACoding.src.use_case.signup.SignupUserDataAccessInterface;
import CACoding.src.entity.CommonUserFactory;
import CACoding.src.entity.UserFactory;
import interface_adapter.*;
import CACoding.src.use_case.signup.SignupInputBoundary;
import CACoding.src.use_case.signup.SignupInteractor;
import CACoding.src.use_case.signup.SignupOutputBoundary;
import CACoding.src.view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject, ClearViewModel clearViewModel, ClearUserDataAccessInterface clearDataAccessObject) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            ClearController clearController = createUserClearUseCase(clearViewModel, clearDataAccessObject);
            return new SignupView(signupController, signupViewModel, clearController, clearViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static ClearController createUserClearUseCase(ClearViewModel clearViewModel, ClearUserDataAccessInterface clearUserDataAccessObject){
        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(clearViewModel);

        ClearInputBoundary clearInteractor = new ClearInteractor(
                clearUserDataAccessObject, clearOutputBoundary);

        return new ClearController(clearInteractor);
    }
}
