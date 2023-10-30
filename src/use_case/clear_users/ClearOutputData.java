package use_case.clear_users;

public class ClearOutputData {
    private final String deletedUsernames;

    public ClearOutputData(String deletedUsernames) {
        this.deletedUsernames = deletedUsernames;
    }

    public String getDeletedUsernames() {
        return deletedUsernames;
    }
}
