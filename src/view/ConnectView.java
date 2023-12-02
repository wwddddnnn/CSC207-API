package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.*;
import java.time.LocalDateTime;

import interface_adapter.connect.ConnectController;
import interface_adapter.connect.ConnectState;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.ViewManagerModel;

public class ConnectView extends JPanel {
    public final String viewName = "Connect";
    private JTextField userInputField;
    private JButton submitButton;
    private ConnectController connectController;
    private ConnectViewModel connectViewModel;

    public ConnectView(ConnectController controller, ConnectViewModel viewModel) {
        this.connectController = controller;
        this.connectViewModel = viewModel;

        // Set up the layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Initialize the components
        userInputField = new JTextField(20);
        submitButton = new JButton("Submit");

        // KeyListener for the userInputField
        userInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ConnectState currentState = connectViewModel.getState();
                String text = userInputField.getText() + e.getKeyChar();
                currentState.setTitle(text);
                connectViewModel.setState(currentState);
            }
        });

        // Add components to the panel
        this.add(new JLabel("What is your name?"));
        this.add(userInputField);
        this.add(submitButton);

        // Set up the action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtain the title from the current state and pass it to the controller
                String title = connectViewModel.getState().getTitle();
                if (!title.matches("[a-zA-Z]+")) { // Check if the title contains only letters
                    JOptionPane.showMessageDialog(ConnectView.this, "Invalid name!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Do not proceed further if the name is invalid
                }
                LocalDateTime currentDateTime = LocalDateTime.now();
                connectController.execute(title, currentDateTime);
            }
        });
    }
}
