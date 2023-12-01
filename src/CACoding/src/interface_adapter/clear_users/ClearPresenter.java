package CACoding.src.interface_adapter.clear_users;


import interface_adapter.ViewManagerModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;

public class ClearPresenter implements ClearOutputBoundary {

    private final ClearViewModel clearViewModel;

    public ClearPresenter(ClearViewModel clearViewModel) {
        this.clearViewModel = clearViewModel;
    }

    @Override
    public void prepareSuccessView(ClearOutputData deletedUsernames) {
        ClearState clearState = this.clearViewModel.getState();
        clearState.setDeletedUsernames(deletedUsernames.getDeletedUsernames());
        this.clearViewModel.setState(clearState);
        this.clearViewModel.firePropertyChanged();
    }

}
