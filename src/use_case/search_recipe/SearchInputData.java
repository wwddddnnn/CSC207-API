package use_case.search_recipe;

import java.util.ArrayList;
import java.util.List;


public class SearchInputData {
    private final String query;

    public SearchInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
