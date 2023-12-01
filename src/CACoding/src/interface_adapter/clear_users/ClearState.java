package CACoding.src.interface_adapter.clear_users;

public class ClearState {
    private String deletedUsernames = "";

    public ClearState(ClearState copy) {
        deletedUsernames = copy.deletedUsernames;
    }

    public ClearState() {
    }

    public void setDeletedUsernames(String deletedUsernames) {
        this.deletedUsernames = deletedUsernames;
    }

    public String getDeletedUsernames(){
        return deletedUsernames;
    }

    @Override
    public String toString() {
        return "ClearState{" +
                "username='" + deletedUsernames + '}';
    }
}
