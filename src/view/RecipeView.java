package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeView {

    public static void main(String[] args) {
        // Create the frame on the event dispatching thread
        SwingUtilities.invokeLater(RecipeView::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Recipe Search Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the layout and components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 20, 10, 60);

        JLabel instructionLabel = new JLabel("Please enter the search filters you would like to apply for your recipe");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(instructionLabel, constraints);

        // Query field
        JLabel queryLabel = new JLabel("Query");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(queryLabel, constraints);

        JTextField queryTextField = new JTextField(20);
        constraints.gridx = 2;
        panel.add(queryTextField, constraints);

        // Cuisine field
        JLabel cuisineLabel = new JLabel("Cuisine (Optional)");
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(cuisineLabel, constraints);

        JTextField cuisineTextField = new JTextField(20);
        constraints.gridx = 2;
        panel.add(cuisineTextField, constraints);

        // Weight field
        JLabel weightLabel = new JLabel("Max time required to make the recipe (Optional)");
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(weightLabel, constraints);

        JTextField weightTextField = new JTextField(20);
        constraints.gridx = 2;
        panel.add(weightTextField, constraints);

        // Search button
        JButton searchButton = new JButton("Search");
        constraints.gridy = 4;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        panel.add(searchButton, constraints);

        // Action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryTextField.getText();
                String cuisine = cuisineTextField.getText();
                String weight = weightTextField.getText();
                // You can replace this with the actual search logic
                System.out.println("Search with Query: " + query + ", Cuisine: " + cuisine + ", Weight: " + weight);
            }
        });

        // Adding the panel to the frame and setting its size
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}




