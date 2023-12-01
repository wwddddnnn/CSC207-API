package use_case.connect;

import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import use_case.connect.ConnectInputBoundary;
import use_case.connect.ConnectInputData;

public class ConnectInteractor implements ConnectInputBoundary {
    final ConnectDataAccessInterface connectRecipeDAO;
    final ConnectOutputBoundary connectDataPresenter;

    public ConnectInteractor(ConnectDataAccessInterface connectRecipeDAO, ConnectOutputBoundary connectDataPresenter) {
        this.connectRecipeDAO = connectRecipeDAO;
        this.connectDataPresenter = connectDataPresenter;
    }

    @Override
    public void execute(ConnectInputData connectData) {
        String title = connectData.getTitle();
        // Get local time from connectData
        String localTime = connectData.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Handling the action based on the title
        HashMap<String, Object> result = connectRecipeDAO.getResult();

        // Write the result and local time to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Info.txt"))) {
            // Write the local time as the first line
            writer.write("Local Time: " + localTime);
            writer.newLine();

            for (Map.Entry<String, Object> entry : result.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Preparing output data based on the result
        connectDataPresenter.prepareView();
    }
}