package use_case.save_recipe;

public interface SaveOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
