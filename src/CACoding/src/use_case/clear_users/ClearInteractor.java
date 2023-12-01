package CACoding.src.use_case.clear_users;

public class ClearInteractor implements ClearInputBoundary{

    final ClearOutputBoundary clearPresenter;

    final ClearUserDataAccessInterface clearUserDataAccessObject;

    public ClearInteractor(ClearUserDataAccessInterface clearUserDataAccessObject,
                           ClearOutputBoundary clearOutputPresenter){
        this.clearPresenter = clearOutputPresenter;
        this.clearUserDataAccessObject = clearUserDataAccessObject;
    }
    @Override
    public void execute() {
        ClearOutputData clearOutputData = new ClearOutputData(this.clearUserDataAccessObject.getAllUsernames());
        this.clearUserDataAccessObject.deleteAllUsernames();
        this.clearPresenter.prepareSuccessView(clearOutputData);
    }
}
