# CSC207-Project: Hungry Students' Recipe Organizer

## Problem Domain

Description:

Our application will be a platform where users can search and save recipes, and make
customized meal plans as well as shopping lists. Features include:

- being able to write the ingredients users have at home with a fridge template 

- being able to search for recipes from the database, with filters such as 
  - Type of cuisine
  - Type of meal (breakfast, lunch, dinner)
  - Time taken to make the recipe
  - Calorie count
  - Dietary restrictions (ingredient exclusions)
  - Number of missing ingredients

- being able to save a filtered recipe

- being able to generate a shopping list based on the list of missing ingredients

- being able to save a recipe and put it into an organized "meal plan" list 
which includes what 'day' and 'meal time' they plan on making this meal

Note: A recipe file would include: a short description, ingredients, prep/cook time, calories, taste, etc.

## API Documentation
  
Api documentation link we will be using during our project:
https://spoonacular.com/food-api/docs

## Proposed Entities

- User
  - String username
  - String password

- Recipe
  - int serialNumber
  - String title
  - RecipeTag tags

- RecipeTag
  - String taste
  - String cuisine
  - String diet
  - String intolerances
  - Int calories
  - String[] ingredientsList
  - String[] nutrition List

- Fridge
  - String[] ingredientsList

- Kitchen
  - HashMap<String, int> MealPlanDay
  - MealPlanDay[] MealPlanWeek

- ShoppingList
  - HashMap<String, int> MissingIngredients
