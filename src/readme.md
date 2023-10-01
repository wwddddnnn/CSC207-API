# Exploring API's - CSC207 Project Proposal

## Problem Domain

A personalized and socially-connected digital cookbook.

Description:

Our application will be a platform where users can save recipes from online, write their
own recipes and share their recipes with their friends. Features include:
- a recipe template for users to write their own recipes (rough fill-in blanks, maybe 
drop-down with multiple options)
- being able to organize their saved recipes by cuisine, type of meal (breakfast, lunch, dinner)
and diet restrictions
- being able to input ingredients, and getting a list of all saved recipes that can be
made with those ingredients
- being able to tag and comment on your own recipes
- being able to like and review your friends' recipes

Note: A recipe file would include: a short description, ingredients, materials, instructions,
  equipment, prep/cook time

## API Documentation
  
Api documentation link we will be using during our project:
https://spoonacular.com/food-api/docs

## Screenshot of testing out API in hopscotch.io

![Screenshot 2023-09-30 at 16.56.14.png](..%2F..%2F..%2FDesktop%2FScreenshot%202023-09-30%20at%2016.56.14.png)

## Screenshot of example call to API in Java

![Screenshot 2023-09-30 at 18.01.46.png](..%2F..%2F..%2FDesktop%2FScreenshot%202023-09-30%20at%2018.01.46.png)

The example code is:

```
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
public static void main(String[] args) {
OkHttpClient client = new OkHttpClient().newBuilder()
.build();
Request request = new Request.Builder()
.url(String.format("https://api.spoonacular.com/recipes/findByIngredients?apiKey=c8eb3493121949b592c890859e2a5240&ingredients=chocolate"))
.addHeader("Content-Type", "application/json")
.build();
try {
Response response = client.newCall(request).execute();
System.out.println(response);
JSONObject responseBody = new JSONObject(response.body().string().substring(1));

            String foodStr = responseBody.toString();
            System.out.println(foodStr);
            /*String foodName = responseBody.getString("title");
            System.out.println(foodName);*/
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Technical Problems Blocking Progress

For our Java code that calls our API, we wrote it by mainly copy and pasting code from
the grade-api lab, and tinkering around with it until it returned the output we wanted. We
still do not understand how this code actually calls the API, and also what the function of
an API (in general) really is, and how to use it in our application in the future. For example: is the
recipe API we found just an online database of many recipes that we can gain the data of? How
is this an "Application Programming Interface" then, if it's just a database?

Also, we created this project NOT as a Maven project, so we had to create a new project to test out
writing Java code to call our API (otherwise, JSON wouldn't work). We don't know how to 
configure this existing project to become a Maven project, so we are unable to write and 
test our Java code into this project right now. That's why we could only include a screenshot 
of it into this readme.md file.

