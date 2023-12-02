package view;

//public class SaveView extends JFrame implements ActionListener, PropertyChangeListener {
//    public final String viewName = "Search results";
//
//    private final SearchResultsViewModel searchResultsViewModel;
//    private final SaveController saveController;
//
////    private final SearchViewModel searchViewModel;
//
//    private final ViewManagerModel viewManagerModel;
//
//    private JLabel title = new JLabel();
//
////    private JLabel[] recipes = new JLabel[5];
//
//    private HashMap<String, SearchedRecipe> foundRecipes;
//
//    private JButton[] recipesTitle = new JButton[5];
//
//    //    final JButton confirm;
//    final JButton next;
//
//    public SaveDisplayRecipeView(SearchResultsViewModel searchResultsViewModel,
//                            SearchViewModel searchViewModel,
//                            ViewManagerModel viewManagerModel,
//                            SearchController searchController, SaveController saveController) {
//        for (int i = 0; i < 5; i++) recipesTitle[i] = new JButton("");
//        this.searchResultsViewModel = searchResultsViewModel;
////        this.searchViewModel = searchViewModel;
//        this.viewManagerModel = viewManagerModel;
//        this.searchResultsViewModel.addPropertyChangeListener(this);
//        this.saveController = saveController;
//
//        this.setLayout(new GridLayout(6, 6)); // GridLayout to organize labels
//
//
//        this.add(title);
//        JPanel buttons = new JPanel();
////        this.confirm = new JButton(searchResultsViewModel.CONFIRM_BUTTON_LABEL);
////        // return to the searchView after clicking confirm button.
////        this.confirm.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                viewManagerModel.setActiveView(searchViewModel.getViewName());
////                viewManagerModel.firePropertyChanged();
////            }
////        });
//        this.refresh = new JButton(searchResultsViewModel.REFRESH_BUTTON_LABEL);
//        this.refresh.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // search next 5 recipes after clicking refresh button.
////                SearchState currentState = searchViewModel.getState();
////                currentState.setOffset(currentState.getOffset() + 5);
////
////                viewManagerModel.setActiveView(searchViewModel.getViewName());
////                viewManagerModel.firePropertyChanged();
//
//                SearchState currentState = searchViewModel.getState();
//                currentState.setOffset(currentState.getOffset() + 5);
//                System.out.println("offset = " + currentState.getOffset());
//                searchController.execute(currentState.getQuery(),
//                        currentState.getCuisine(),
//                        currentState.getMaxTime(),
//                        currentState.getOffset());}
//        });
//
//        buttons.add(refresh);
////        buttons.add(confirm);
//
//        for (JButton rt: recipesTitle) this.add(rt);
//        this.add(buttons);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        SearchState currentState = (SearchState) evt.getNewValue();
//        HashMap<String, SearchedRecipe> recipes = currentState.getRecipe();
//        this.foundRecipes = recipes;
//        if (!foundRecipes.isEmpty()) {
//            String[] rtList = foundRecipes.keySet().toArray(new String[0]);;
//            for (int i = 0; i < rtList.length; i++) {
//                recipesTitle[i].setText(rtList[i]);
//                int finalI = i;
//                recipesTitle[i].addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        DisplayRecipeViewModel displayRecipeViewModel= new DisplayRecipeViewModel();
//                        SaveViewModel saveViewModel = new SaveViewModel();
//                        DisplayRecipeView displayRecipeView = new DisplayRecipeView(foundRecipes.get(rtList[finalI]),
//                                displayRecipeViewModel, searchResultsViewModel, viewManagerModel, saveViewModel, saveController);
//                        viewManagerModel.setActiveView(displayRecipeViewModel.getViewName());
//                        viewManagerModel.firePropertyChanged();
//                    }
//                });
//            }
////            int totalRecipeAmount = this.searchResultsViewModel.getState().getTotalRecipeAmount();
////            System.out.println(totalRecipeAmount + " in ResultView");
//            this.title.setText("These are the recipes you want: ");
//        } else {
//            this.title.setText("No recipes found!");
//        }
//    }
//}
