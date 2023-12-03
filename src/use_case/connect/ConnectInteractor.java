package use_case.connect;

import entity.Recipe;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
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
        String userHash = null;
        LocalDate date = null;

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

                        BufferedReader reader2 = new BufferedReader(new FileReader("Info.txt"));
                        String line2;
                        for (int lineCount2 = 0; lineCount2 < lineCount; lineCount2++) {
                            line2 = reader2.readLine();
                            if (lineCount2 == lineCount - 4) {
                                date = LocalDate.parse(line2.substring(line.indexOf("Local Time: ") + 12).trim());
                            }
                            if (lineCount2 == lineCount - 2) {
                                userHash = line2.substring(line.indexOf("hash: ") + 6).trim();
                            }
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isUserExist) {
            // Username exists. Create output data with this username.
            HashMap<String, Object> outputHash = new HashMap<String, Object>(3);
            outputHash.put("username", title);
            outputHash.put("userHash", userHash);
            outputHash.put("date", date);
            ConnectOutputData outputData = new ConnectOutputData(outputHash);
            connectDataPresenter.prepareView(outputData);
            return;}
        // Get local time from connectData
        String localTime = connectData.getTime().toString();

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

        userHash = result.get("hash");
        date = connectData.getTime();

        HashMap<String, Object> outputHash = new HashMap<String, Object>(3);
        outputHash.put("username", title);
        outputHash.put("userHash", userHash);
        outputHash.put("date", date);

        ConnectOutputData outputData = new ConnectOutputData(outputHash);
        connectDataPresenter.prepareView(outputData);
    }
}