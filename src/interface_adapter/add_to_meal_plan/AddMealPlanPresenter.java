package interface_adapter.add_to_meal_plan;

import interface_adapter.ViewManagerModel;
import use_case.add_to_meal_plan.AddMealPlanOutputBoundary;

public class AddMealPlanPresenter implements AddMealPlanOutputBoundary {
    private final AddMealPlanViewModel addMealPlanViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddMealPlanPresenter(AddMealPlanViewModel addMealPlanViewModel,
                                ViewManagerModel viewManagerModel) {
        this.addMealPlanViewModel = addMealPlanViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        System.out.println("prepareSuccessView");
        addMealPlanViewModel.setMessage(message);
        addMealPlanViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        addMealPlanViewModel.setMessage(error);
        addMealPlanViewModel.firePropertyChanged();
    }
}
