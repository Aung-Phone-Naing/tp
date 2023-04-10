# Developer Guide
- [Developer Guide](#developer-guide)
  - [Acknowledgements](#acknowledgements)
  - [Setup and Prerequisites](#setup-and-prerequisites)
  - [Design and implementation](#design-and-implementation)
    - [Architecture](#architecture)
    - [UI Component](#ui-component)
    - [Parser Component](#parser-component)
    - [Command Component](#command-component)
    - [RecipeList Component](#recipelist-component)
    - [Storage Component](#storage-component)
    - [Recipe Manage Feature](#recipe-manage-feature)
      - [Implementation](#implementation)
      - [Example Usage](#example-usage)
    - [Recipe Find Feature](#recipe-find-feature)
      - [Implementation](#implementation-1)
      - [Example Usage](#example-usage-1)
    - [Recipe Steps Edit Feature](#recipe-steps-edit-feature)
      - [Implementation](#implementation-2)
      - [Example Usage](#example-usage-2)
    - [Recipe Ingredients Edit Feature](#recipe-ingredients-edit-feature)
      - [Implementation](#implementation-3)
      - [Example Usage](#example-usage-3)
    - [Recipe View Feature](#recipe-view-feature)
      - [Implementation](#implementation-4)
      - [Example Usage](#example-usage-4)
    - [Help Feature](#help-feature)
      - [Implementation](#implementation-5)
      - [Example Usage](#example-usage-5)
  - [Appendix A - Product Scope](#appendix-a---product-scope)
    - [Target User Profile](#target-user-profile)
    - [Value Proposition](#value-proposition)
  - [Appendix B - User Stories](#appendix-b---user-stories)
  - [Appendix C - Non-Functional Requirements](#appendix-c---non-functional-requirements)
  - [Appendix D - Glossary](#appendix-d---glossary)
  - [Appendix E - Instructions for Manual Testing](#appendix-e---instructions-for-manual-testing)
    - [Initialization](#initialization)
    - [Appendix E.1 - Adding a recipe](#appendix-e1---adding-a-recipe)
    - [Appendix E.2 - Deleting a recipe](#appendix-e2---deleting-a-recipe)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the 
original source as well}

## Setup and Prerequisites
1. Ensure you have `Java 11` installed.
2. Ensure your local repository is synced with the main repository at 
[AY2223S2-CS2113-F13-1/tp](https://github.com/AY2223S2-CS2113-F13-1/tp).
3. Download the latest `tp.main.jar` from [here](https://github.com/AY2223S2-CS2113-F13-1/tp/releases).
4. Copy the file to the folder you want to use as home folder for the recipe manager.
5. Use `Win+R` to open the command prompt and type `cmd` and press Enter.
6. Then `cd` into the folder where you copied the jar file. e.g. `cd C:\Users\Lee\Desktop\MyRecipe`.
7. Type `java -jar tp.main.jar` and press Enter to start the program.

## Design and implementation

### Architecture

**Main components of the architecture**

![image](./PlantUML/MainArchitecture.png)

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of Duke(main) components and how they interact with each other.
It is responsible for,
* At app launch: Initializes all other components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

The rest of the App consists of five components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Parser`**](#parser-component): Parses user input.
* [**`Command`**](#command-component): The command executor.
* [**`RecipeList`**](#recipelist-component): Holds the recipe data in the recipe manager by an ArrayList. 
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues 
the command `delete 1`.

![image](./PlantUML/ArchitectureInteract.png)

### UI Component
The **API** of this component is specified in 
[`UI.java`](https://github.com/AY2223S2-CS2113-F13-1/tp/blob/master/src/main/java/seedu/duke/ui/UI.java).

<!-- ![image](./PlantUML/UIcomponent.png) -->

UI class implements the StringLib interface for some output strings. It is responsible for the following tasks:
* Prints the welcome and goodbye messages.
* Prints the help message, which lists all the commands available and their examples.
* Prints the log messages for some managing operations.
* Prints the error messages for some exceptions.

### Parser Component
The **API** of this component is specified in 
[`Parser.java`](https://github.com/AY2223S2-CS2113-F13-1/tp/blob/master/src/main/java/seedu/duke/parser/Parser.java).

![image](./PlantUML/ParserComponent.png)

Parser class implements the StringLib interface for some output strings and uses `CommandType` enum. 
It is responsible for the following tasks:
* Separates user input into various elements in `Strings` to obtain `Command`, `Recipes`, `Ingredients`, 
`Steps`, `Tag`, etc.
* Uses `CommandType` to generate each type of command.
* Separates `Recipes` into its elements, namely `Ingredients` and `Steps`.
* Handles any other separation tasks in regard to handling various user input `Commands`.

### Command Component
The **API** of this component is specified in 
[`Command.java`](https://github.com/AY2223S2-CS2113-F13-1/tp/blob/master/src/main/java/seedu/duke/command/Command.java).

![image](./PlantUML/CommandComponent.png) 

Command class the StringLib interface for some output strings. It also inherits CommandType Enums for determining the 
correct tasks to be executed. It is responsible for the following tasks:
* Executes the particular command received from the user input, case-by-case.
* Determines if the program termination command has been sent.

### RecipeList Component 
The **API** for this component is specified in 
[`RecipeList.java`](https://github.com/AY2223S2-CS2113-F13-1/tp/blob/master/src/main/java/seedu/duke/recipe/RecipeList.java).

![RecipeList Component](./PlantUML/RecipeListComponent.png)

The class contains the list of `Recipe` objects stored in an `ArrayList` by the program. 
It provides methods for the addition, retrieval and removal of `Recipe` objects from the list.
A `RecipeList#clearRecipeList()` method also exists to allow for quick deletion of all `Recipe` objects.

Each `Recipe` object stores the name of the dish, as well as lists storing `Ingredient` and `Step`
objects, to keep track of the ingredients and steps to create the dish respectively.

While each `Recipe` object will have only one `IngredientList` and `StepList`, each of the lists 
are not limited in how many `Ingredient` and `Step` objects they can store respectively.

### Storage Component
The **API** of this component is specified in 
[`Storage.java`](https://github.com/AY2223S2-CS2113-F13-1/tp/blob/master/src/main/java/seedu/duke/storage/Storage.java).

![image](./PlantUML/StorageComponent.png)

Storage class is responsible for the following tasks:
* Creating the directory for storing save files.
* Setting the file path for save files.
* Creating and writing save files based on the recipe list.
* Scanning the save file directory for saves from previous instances of the programme.
* Loading valid save files found in the directory.

### Recipe Manage Feature
#### Implementation
The recipe manage feature is facilitated by the `command`,`parser`,`recipe` package. 
It implements the following operations: 

- `RecipeList#addNewRecipe()` - Add a new recipe to the recipe list.
- `RecipeList#getRecipeList()` - Get the recipe list.
- `RecipeList#removeRecipe()` - Delete a recipe from the recipe list.
- `RecipeList#clearRecipeList()` - Clear all recipes from the recipe list.
#### Example Usage
Given below is an example usage scenario and how the recipe manage mechanism behaves at each step.

**Step 1.** The user launches the application for the first time, then inputs 
`add n/MaLaXiangGuo i/Beef, Mutton, Mushrooms t/Chinese s/1` to add a new recipe to the recipe manager. `Duke` calls 
the `parseCommands()` method in the `Parser` class to parse the user input, which will return a `Command` object. 
The `Command` object will then be executed by calling the `Command#execute()` method, which will call the 
`Parser#parseSteps()` method to get the steps of the recipe. 

**Step 2.** The user inputs `chop beef` to add a step for the recipe. `Parser#parseSteps()` receives all the steps and 
returns a `StepList` object. Then we return to the `Command#execute()` method in the `Command` class and call the 
`RecipeList#addNewRecipe()` to add the recipe to the recipe list.

**Step 3.** The user inputs `list` to list all the recipes in the recipe list. `Duke` calls the `parseCommands()` 
method in the `Parser` class to parse the user input, which will return a `Command` object. The `Command` object will 
then be executed by calling the `Command#execute()` method, which will call the `RecipeList#getRecipeList()` to get the 
recipe list. Then we return to the `Command#execute()` method in the `Command` class and call the `UI#showRecipeList()` 
to show the recipe list.

**Step 4.** The user inputs `delete 1` to delete the first recipe in the recipe list. `Duke` calls the 
`parseCommands()` method in the `Parser` class to parse the user input, which will return a `Command` object. The 
`Command` object will then be executed by calling the `Command#execute()` method, which will call the 
`RecipeList#removeRecipe()` to remove the recipe from the recipe list.

**Step 5.** The user executes the `clear` to clear all the recipes in the recipe list. `Duke` calls the 
`parseCommands()` method in the `Parser` class to parse the user input, which will return a `Command` object. 
The `Command` object will then be executed by calling the `Command#execute()` method, which will call the 
`RecipeList#clearRecipeList()` to clear all the recipes from the recipe list.

> The following sequence diagrams shows how the recipe manage feature works:
![Sequence Diagram for Recipe Manage ADD and LIST](./PlantUML/RecipeManage_Add_List.png)
![Sequence Diagram for Recipe Manage CLEAR and DELETE](./PlantUML/RecipeManage_Delete_Clear.png)


### Recipe Find Feature
#### Implementation
The recipe find feature is facilitated by the `command`,`parser`,`recipe` package. 
It implements the following operations:
- `RecipeList#searchRecipeList()` - Find a recipe from the recipe list.
#### Example Usage
Given below is an example usage scenario and how the recipe find mechanism behaves at each step.

In the command line, the user inputs `find MaLaXiangGuo` to find a recipe from the recipe list. `Duke` calls the 
`parseCommands()` method in the `Parser` class to parse the user input, which will return a `Command` object. 
The `Command` object will then be executed by calling the `Command#execute()` method, which will call 
the `RecipeList#searchRecipeList()` to search the recipe list for the recipe. Eventually, the `UI` will be called to 
show the recipe list that contains the keyword with its index.

> The following sequence diagram shows how the recipe find feature works:
![Sequence Diagram for Recipe Find](./PlantUML/FindRecipe.png)

### Recipe Steps Edit Feature
#### Implementation
The recipe steps edit feature is handled by the `command`, `recipe` and `stepList` classes.
The following operations are implemented:
* `RecipeList#getRecipe()` - Retrieves the `recipe` object to be edited.
* `Recipe#getStepList()` - Retrieves the `StepList` of the recipe object to be edited.
* `StepList#editStep()` - Takes in a `stepIndex` of the step to edit, and the user's input,
then replaces the step stored at `stepIndex` with a newly created one.

#### Example Usage
The example usage is based on the assumption that there currently exists at least one step in
the recipe specified.

**Step 1.** In the command line, user inputs `editstep 1` to edit the step in the first `Recipe`
object in the `RecipeList`. `Duke` calls the `parseCommands()` method in the `Parser` class to
parse the user input, which returns a `Command` object of type `EDITSTEP`. Under
`Command#execute()`, this object will be executed.

**Step 2.** Under the `EDITSTEP` case, the number of steps in the specified recipe is first checked.
If there is at least one step in the recipe, a further user input `1` is parsed to an `int` to specify
the step number in the list. It is further converted to the 0-based indexing in the `stepList` by subtracting 1.

> The following sequence diagram shows how the recipe steps edit feature works:
![Sequence Diagram for Recipe Edit Step](./PlantUML/EditRecipeSteps.png)

### Recipe Ingredients Edit Feature
#### Implementation

#### Example Usage

> The following sequence diagram shows how the recipe ingredients edit feature works:
![Sequence Diagram for Recipe Ingredients Step](./PlantUML/EditRecipeIngredients.png)


### Recipe View Feature
#### Implementation

Viewing recipes is handled by the `command`, `recipe` and `ui` classes
The following operations are implemented:
* `RecipeList#getRecipeFromList()` - Retrieves a Recipe from the RecipeList
* `Recipe#getIngredientList()` - Retrieves the IngredientList for the Recipe
* `Recipe#getStepList()` - Retrieves the StepList for the Recipe
* `UI#showRecipeViewed()`- Prints the IngredientList and StepList for the recipe

#### Example Usage
The example usage is based on the assumption that there currently exists at least
one Recipe stored in the RecipeList.

**Step 1.** In the command line, the user inputs `view 1` to view the first `Recipe` object
in the `RecipeList`. `Duke` calls the `parseCommands()` method in the `Parser` class to
parse the user input, which returns a `Command` object of type `VIEW`. Under
`Command#execute()`, this object will be executed.

**Step 2** Under the `VIEW` case, the second part of the user's input `1` is parsed to an `int` to obtain the item
number in the list. 

**Step 3.** The method `RecipeList#getRecipeFromList()` is called to retrieve the desired recipe
to be viewed. This method then converts the 1-based item number to the 0-based indexing of `RecipeList`,
then returns the `Recipe` object stored at that index using `recipeList.get()`

**Step 4.** The `Command#execute()` method under case `VIEW` then calls `UI#ShowRecipeViewed` with the retrieved
`Recipe` object as an input parameter. This method calls `Recipe#getIngredientList()` to obtain the
`IngredientList` object for the Recipe, then calls `IngredientList#showList()` to print out the ingredients
for the recipe. The method then follows a similar approach for the steps in the recipe, calling `Recipe#getStepList`
and then `StepList#showStepList()`.

> The following sequence diagram shows how the recipe view feature works:
![Sequence Diagram for Recipe View](./PlantUML/RecipeView.png)

### Help Feature
#### Implementation
The help feature's main functionality is to show users the full list of commands they can use on TOM. 
It is facilitated by the `command`,`parser`,`ui` package. It implements the following operations:

- `UI#showHelp()` - Prints the help message.
- `Parser#parseCommands()` - Parse user input into a Command object containing commandType and fullDescription.
- `Command#excecute()` - Carry out respective tasks based on commandType given.

#### Example Usage

Given below is an example usage scenario and how the help mechanism behaves at each step.

**Step 1.** The user launches the application for the first time, then inputs `help` to see all possible commands that
can be executed. `Duke` calls the `parseCommands()` method in the `Parser` class to parse the user input, which will 
return a `Command` object. The `Command` object will then be executed by calling the `Command#execute()` method, 
which will call the `UI#showHelp()` method to show all possible commands of the recipe.

> The following sequence diagram shows how the help feature works:
![Sequence Diagram for Help](./PlantUML/Help.png)

## Appendix A - Product Scope
### Target User Profile

Product is geared towards users who are familiar with CLI (e.g. Computing professionals, university students).
The user is ideally someone who is conscious about their health and would like to learn/improve their cooking.

### Value Proposition

The user will be able to keep a database of recipes for home cooking, and be able to view both the recipes as well as 
attributes such as calorie count and required ingredients.<br>
It will also allow them to follow recipes in a step-by-step fashion with additional assistance functions such as timers.<br>
The user will be able to keep close tabs on their nutrition based on the recipes that they decide to cook and add to their meal plans.

## Appendix B - User Stories

| Version |    As a ...     |                                     I want to be able to ...                                      |                                 So that I can...                                  |
|:-------:|:---------------:|:-------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|
|  v1.0   | potential user  |                                    read the User Guide easily                                     |           I get to know the feature of the app and get started quickly            |
|  v1.0   | potential user  |                           add ingredients list to a particular recipes                            |                  refer to it when I go shopping for ingredients                   |
|  v1.0   |    new user     |                       initially see the estimated cooking time for recipes                        |                       choose the faster ones when in a rush                       |
|  v1.0   |    new user     |                      see basic instructions for the first time I use the app                      |              avoid having to keep referring back to the user guide.               |
|  v1.0   |    new user     |   ask the app to provide a format guide for me when I type the wrong format for an instruction    |                               get started quickly.                                |
|  v1.0   |    new user     |                          see a full list of recipes currently available                           |                   get an overview of what is already available                    |
|  v1.0   |    new user     |                                            add recipes                                            |                                add the ones i like                                |
|  v1.0   |    new user     |                                          delete recipes                                           |                           remove the ones i don't like                            |
|  v1.0   |    new user     |                                          edit the recipe                                          |                 correct spelling mistakes when typing the recipe                  |
|  v1.0   |    new user     |                                go through the recipe line by line                                 |                    follow the recipe in real time while i cook                    |
|  v1.0   | long-term user  |                             mark the steps I have done for the recipe                             |                    be aware of my next steps as I am cooking.                     |
|  v2.0   | proficient user |                   trigger certain recipes to be displayed once the app launches                   |                  get quick access to my regular cooking recipes                   |
|  v2.0   | proficient user |                                  customize the shortcut commands                                  |                  customize the keystrokes to my own preferences                   |
|  v2.0   | potential user  | see the app with sample data and can easily manage to delete it after the exploration of the app. |                    Iunderstand the function of the app easily.                    |
|  v2.0   | potential user  |                                craft meal plans for different days                                |                         plan my meals ahead for the week                          |
|  v2.0   |    new user     |                          register my dietary requirements / restrictions                          |                          avoid eating food I cannot eat.                          |
|  v2.0   |    new user     |                              start an automatic timer when required                               |           avoid forgetting to set a timer when the recipe calls for it            |
|  v2.0   |    new user     |                             see that the app can provide fuzzy search                             |                  access recipes even if I type the name wrongly                   |
|  v2.0   |    new user     |                              learn more about the shortcut commands                               |                 easily navigate through the app interface quicker                 |
|  v2.0   | long-time user  |                      press any key if prompted to continue to the next step                       |               use my elbow instead of my oily hands on the keyboard               |
|  v2.0   | long-time user  |              get warnings if my fat/sugar intake based on recent dishes is too high               |                              better regulate my diet                              |
|  v2.0   | long-term user  |                sort through stored recipes based on the dishes' nutritional value.                |                             better regulate my diet.                              |
|  v2.0   | long-term user  |                                         sort by cuisines.                                         |                            select a particular cuisine                            |
|  v2.0   | long-term user  |                              add my own notes when I’m done cooking                               |  comment about and adjust the recipe to my liking (e.g. less sweet, less salty)   |
|  v2.0   | long-term user  |                      hide dishes that I am tired of or tried and do not like                      |                     spend less time filtering through dishes                      |
|  v2.0   | long-term user  |                                  “favorite” dishes that I enjoy                                   |                                quickly select them                                |
|  v2.0   |   expert user   |                     get the app to randomly suggest one of my favorite snacks                     | have help in making decisions on what snacks to eat when I hesitate to choose one |
|  v2.0   |   expert user   |                                  rate and comment on the recipe                                   |                  choose my favorite recipe by using the ratings                   |


## Appendix C - Non-Functional Requirements

* Users should be able to run on **any common operating system (Windows, Mac, Linux).**
* Users should not need to manipulate any files in the directory **manually**.
* Users should be able to run all functions of the program **on the CLI only (i.e. keyboard inputs only)**.

## Appendix D - Glossary

* *Recipe* - A set of instructions for preparing a food item. In our implementation it should contain the dish's name,
ingredients required and steps to make the dish
* *Cuisine* - A category of food originating from a given country or religion

## Appendix E - Instructions for Manual Testing

### Initialization 
1. Download the `.jar` file and place it in an empty folder.
2. Open the folder location in a command-line interface. (E.g. Terminal for Windows)
3. Type in `java -jar .\<JAR_FILE_NAME>.jar` where `JAR_FILE_NAME` is the name of the `.jar` file downloaded.
4. A successful launch should show a `data` folder being created and a welcome message as such: 
> ![Welcome Message](./Pictures/welcomeMessage.png)
    
### Appendix E.1 - Adding a recipe
Adding a person by using the `add` command and the recipe to be added.
1. Test case:
   `add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/4`
   `chop beef`
   `add potatoes`
   `add carrots`
   `cook 5 minutes`
    
    Expected: Recipe is added to the list and the message is shown in the result display.
    ```
    Got it. I've added this recipe:
      [Chinese] Hotpot
    Now you have 1 recipes in the list.
    ```
2. Test case: 
   `add i/Beef, Potatoes, Carrots t/Chinese s/0`
   
   Expected: No recipe is added. Error details shown in the result display.
   ```
    Error in description of inputs!
    Exception occurred:
    Recipe is missing the "NAME" or "INGREDIENTS" or "TAG" or "SUM of the STEPs,
    or there is more than one
    "NAME" or "INGREDIENTS" or "TAG" or "SUM of the STEPs"!
   ```
   
3. Other incorrect add commands to try: 
   1. `add`, 
   2. `add x` (where x does not follow the correct format), 
   3. `add n/` (where name is empty).

   Expected: Similar to previous.

### Appendix E.2 - Deleting a recipe
Deleting a person by using the `delete` command and the index of the recipe to be deleted.
1. Test case: `delete 1`

    Expected: First recipe is deleted from the list and the message is shown in the result display.
    ```
    Noted. I've removed this recipe:
    [TAG] NAME 
    you have XX recipes in the list.
    ```
2. Test case: `delete 0`
    
    Expected: No recipe is deleted. Error details shown in the result display.
    ```
    Please enter a valid index!
    Valid range: 1 to 1
    ```
3. Other incorrect delete commands to try: 
   1. `delete`, 
   2. `delete x` (where x is larger than the list size), 
   3. `delete x` (where x is a negative integer or zero), 
   4. `delete XX` (where XX is not a number).
   
   Expected: Similar to previous.

