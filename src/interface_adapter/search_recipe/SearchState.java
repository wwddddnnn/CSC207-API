package interface_adapter.search_recipe;

public class SearchState {

    private String query = "";

    private String cuisine = "";

    private String recipe = "";

    public SearchState(SearchState copy){
        query = copy.query;
        cuisine = copy.cuisine;
        recipe = copy.recipe;
    }
    public SearchState(){}

    public String getQuery(){return query;}

    public String getCuisine(){return cuisine;}

    public String getRecipe(){return recipe;}

    public void setQuery(String query){this.query = query;}

    public void setCuisine(String cuisine){this.cuisine = cuisine;}

    public void setRecipe(String recipe){this.recipe = recipe;}

    public String toString(){
        return "SearchState{" +
                "query=" + query + ", cuisine=" + cuisine +
                ", \nresult = " + recipe;
    }
}
