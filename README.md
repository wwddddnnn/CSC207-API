# CSC207-Project: Hungry Students' Recipe Organizer

## Problem Domain

Description:

Our application will be a platform where users can search for recipes, save recipes, and make
customized meal plans. Features include:

- being able to search for recipes from the database, with filters such as cuisine, query, and max time required to make the recipe

- being able to view each filtered recipe from search results in a clear manner

- being able to save a recipe locally from the search results

- being able to view each saved recipe in a clear manner

- being able to put any saved recipe into an organized "meal plan" list which includes what 'day' and 'meal time' they plan on making this meal

Note: A recipe file would include: the recipe name, instructions, an image, tags, etc.

## API Documentation
  
API documentation link we will be using during our project:
https://spoonacular.com/food-api/docs

## Proposed Entities

- Recipe
  - int id;
  - String name;
  - String[] image;
  - RecipeTag recipeTag;
  - String instructions;
  - HashMap<String, ArrayList<Object>> ingredients;

- RecipeTag
  - int recipeMinutes;
  - int servings;
  - ArrayList<String> cuisines;
  - boolean vegetarianBool;
  - boolean veganBool;
  - ArrayList<String> intolerances;

- MealPlan
  - HashMap<String, int> MealPlanDay
  - MealPlanDay[] MealPlanWeek
 
## Links
Demo video: https://drive.google.com/file/d/1osvUE1HZ1pUqnLwTonvwhvrI9vCU192t/view?usp=sharing
