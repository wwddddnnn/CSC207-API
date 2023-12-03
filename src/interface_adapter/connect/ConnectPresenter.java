package interface_adapter.connect;

import app.UserInfoRetriever;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchViewModel;
import use_case.connect.ConnectOutputBoundary;
import use_case.connect.ConnectOutputData;

import javax.swing.*;

public class ConnectPresenter implements ConnectOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private final ConnectViewModel connectViewModel;

    public ConnectPresenter(ViewManagerModel viewManagerModel,ConnectViewModel connectViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.connectViewModel = connectViewModel;
    }

    @Override
    public void prepareView(ConnectOutputData outputData) {

        UserInfoRetriever userInfoRetriever = new UserInfoRetriever(outputData.getOutputData());

        ConnectState connectState = this.connectViewModel.getState();
        connectState.setTitle((String) outputData.getOutputData().get("username"));
        // Create a JOptionPane to show the "Connected!" message
        final JOptionPane optionPane = new JOptionPane("Connected!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        // Create a dialog from JOptionPane
        final JDialog dialog = new JDialog();
        dialog.setTitle("Message");
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();

        // Create a new thread to manage the display
        new Thread(() -> {
            try {
                dialog.setVisible(true);
                Thread.sleep(5000); // Display for 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interruption
            } finally {
                dialog.dispose(); // Close the dialog
            }

            // Transition back to the SearchView
            SwingUtilities.invokeLater(() -> {
                viewManagerModel.setActiveView("Search recipes"); // Replace with actual SearchView name
                viewManagerModel.firePropertyChanged();
            });
        }).start();
    }
}
