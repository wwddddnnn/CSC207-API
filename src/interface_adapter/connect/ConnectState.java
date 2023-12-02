package interface_adapter.connect;

public class ConnectState {

    private String title; // Holds the user input from ConnectView

    public ConnectState() {
        this.title = ""; // Initialize with an empty string
    }

    public ConnectState(ConnectState copy) {
        this.title = copy.title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Additional methods can be added as required
}
