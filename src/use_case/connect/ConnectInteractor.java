package use_case.connect;

import entity.Recipe;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
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

        boolean isUserExist = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("Info.txt"))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount % 4 == 0) { // Username is on every fourth line
                    String username = line.substring(line.indexOf("username: ") + 10).trim();
                    // Remove any numeric suffixes for comparison
                    String baseUsername = username.replaceAll("\\d*$", "");
                    if (baseUsername.equals(title)) {
                        isUserExist = true; // Matching username found
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isUserExist) {
            // Username exists. Create output data with this username.
            ConnectOutputData outputData = new ConnectOutputData(title);
            connectDataPresenter.prepareView(outputData);
            return;}
        // Get local time from connectData
        String localTime = connectData.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Handling the action based on the title
        HashMap<String, String> result = connectRecipeDAO.getResult(title);

        // Write the result and local time to a file in append mode
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Info.txt", true))) { // Enable append mode
            // Write the local time as the first line
            writer.write("Local Time: " + localTime);
            writer.newLine();

            for (Map.Entry<String, String> entry : result.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectOutputData outputData = new ConnectOutputData(result.get("username"));
        connectDataPresenter.prepareView(outputData);
    }
}