---
layout: page
title: User Guide
---
<h1> User Guide </h1>
<h2> Introduction </h2>
Taste of Mom's (TOM) is a <strong>desktop recipe manager application for managing recipes, optimized for use via a Command Line Interface</strong> (CLI). <br>

Our application will help students to **keep track and modify recipes** which they can learn to cook, so that they can enjoy the same taste of food made like as if they were at home as they live on campus.
It also helps them filter recipes with ease.
<h2> Table of Contents </h2>

* **[Quick Start](#quick-start)**
* **[Features](#features)**
    * **[Viewing help：`help`](#viewing-help-help)**
    * **[Adding a recipe: `add`](#adding-a-recipe-add)**
    * **[Adding an element to a recipe: `addtorecipe`](#adding-elements-to-recipe)**
    * **[Editing steps for a recipe: `editstep`](#editing-a-recipe-step-editstep)**
    * **[Editing ingredients for a recipe: `editingredient`](#editing-a-recipe-ingredient-editingredient)**
    * **[Editing recipe with one line command:`edit`](#editing-a-recipe-edit)**
    * **[Deleting a recipe: `delete`](#deleting-a-recipe-delete)**
    * **[Deleting an element from a recipe: `deletefromrecipe`](#deleting-elements-from-recipe)**
    * **[Finding recipes by name: `findname`](#finding-recipes-findname)**
    * **[Finding recipes by tag: `findtag`](#finding-recipes-findtag)**
    * **[Viewing a recipe: `view`](#viewing-a-recipe-view)**
    * **[Listing all recipes: `list`](#listing-all-recipes-list)**
    * **[Clearing all entries: `clear`](#clearing-all-entries-clear)**
    * **[Exiting the program: `exit`](#exiting-the-program-exit)**
    * **[Saving the data](#save-data)**
    * **[Editing the data file](#edit-data)**
* **[FAQ](#faq)**
* **[Command Summary](#command-summary)**

<h2 id="quick-start"> Quick Start</h2>

1. Ensure you have `Java 11` installed on your Computer.
2. Download the latest `tp.main.jar` from [here](https://github.com/AY2223S2-CS2113-F13-1/tp/releases).
3. Copy the file to an **empty folder** you want to use as home folder for the recipe manager.
4. Open the command prompt.
5. Then `cd` into the folder where you copied the jar file. e.g. `cd C:\Users\Lee\Desktop\MyRecipe`.
6. Type `java -jar tp.main.jar` and press Enter to start the program.
<h2 id="features"> Features</h2>
<ol>
<li>
Words in <strong>UPPER_CASE</strong> are the parameters to be supplied by the user.
    
e.g. in <code class="language-plaintext highlighter-rouge">add DESCRIPTION</code>, <code class="language-plaintext highlighter-rouge">DESCRIPTION</code> is a parameter which can be used as <code class="language-plaintext highlighter-rouge">add n/NAME i/INGREDIENTS t/TAG s/NUMBEROFSTEPS</code>.
</li>    
<li>
<strong>Extraneous parameters</strong> for commands that do not take in parameters (such as <code class="language-plaintext highlighter-rouge">exit</code> and <code class="language-plaintext highlighter-rouge">list</code>) <strong>will be ignored</strong>.

e.g. if the command specifies <code class="language-plaintext highlighter-rouge">exit 123</code>, it will be interpreted as <code class="language-plaintext highlighter-rouge">exit</code>.
</li>
<li>
APP accept the <strong>same name for different recipes</strong>. For example, you can have two recipes named <code class="language-plaintext highlighter-rouge">Hotpot</code>.
</li>
<li>
APP saves <strong>automatically</strong> when changes are made to the recipes on the recipe list or to the recipe list. There is no notification of the recipe list being saved.
<br>The following commands will trigger the <strong>automatic saving</strong> after successful operation:<br>
1. <code class="language-plaintext highlighter-rouge">add</code><br>
2. <code class="language-plaintext highlighter-rouge">addtorecipe</code><br>
3. <code class="language-plaintext highlighter-rouge">delete</code><br>
4. <code class="language-plaintext highlighter-rouge">deletefromrecipe</code><br>
5. <code class="language-plaintext highlighter-rouge">edit</code><br>
6. <code class="language-plaintext highlighter-rouge">editstep</code><br>
7. <code class="language-plaintext highlighter-rouge">editingredient</code><br>
</li>
</ol>

<h2 id="viewing-help-help"> Viewing help:<code class="language-plaintext highlighter-rouge">help</code></h2>

Shows a message explaining how to access the help page.<br>
**Format**: `help`<br>

<h2 id="adding-a-recipe-add"> Adding a recipe: <code class="language-plaintext highlighter-rouge">add</code></h2>

Adds a recipe to the recipe manager. App will prompt you to start entering the steps to the recipe will automatically count the steps and add the recipe to the recipe list.<br>
**Format**: `add n/NAME i/INGREDIENTS t/TAG s/NUMBEROFSTEPS`<br>
**Hint**: Number of steps is the number of steps in the recipe, then you will be prompted to enter the steps.<br>
**Constraints**:
* NAME, INGREDIENTS, TAG: These recipe parameters must only be ***alphanumeric*** in nature and should **not contain only numeric characters** or
any other **special characters**.

**Examples**:<br>
```
__________________________________________________________
add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/4

Please enter the description of step 1:
chop beef

Please enter the description of step 2:
add potatoes

Please enter the description of step 3:
add carrots

Please enter the description of step 4:
cook 5 minutes

Got it. I've added this recipe:
  [Chinese] Hotpot
Now you have 2 recipes in the list.

Data saved successfully!
__________________________________________________________
```
<h2 id="adding-elements-to-recipe"> Adding an element to a recipe: <code class="language-plaintext highlighter-rouge">addtorecipe</code></h2>

Adds a recipe to the recipe manager. App will prompt you to start entering the steps to the recipe will automatically count the steps and add the recipe to the recipe list.<br>
**Format**: `addtorecipe --[s/i] id/[index] desc/[description of step/ingredient]`<br>
**Examples**:<br>
```
__________________________________________________________
addtorecipe --i id/1 desc/mala sauce
The ingredient has been successfully added to the ingredient list!
__________________________________________________________
```
```
__________________________________________________________
addtorecipe --s id/1 desc/Add mala sauce when water has reached a rolling boil.
There are 4 steps in the list
1. chop beef
2. add potatoes
3. add carrots
4. cook 5 minutes
Valid range: 1 to 5
Enter "quit" to cancel.
Enter step index below:
4
The step has been successfully added to the step list!
__________________________________________________________
```
<p>App will add an element to the recipe with the given index.</p>
<p>If a step is added, the user will be prompted for an index which the new step will be given.</p>

<h2 id="editing-a-recipe-step-editstep"> Editing a recipe's steps: <code class="language-plaintext highlighter-rouge">editstep</code></h2>

Edits a step for a recipe in the recipe manager.<br>
**Format**: `editstep INDEX`, then input the step number<br>
**Hint**: At the edit page, you can input `quit` if you decide not to edit.<br>
**Examples**:<br>

```
__________________________________________________________
editstep 2
There are 1 steps in the list
1. chop beef
Which step do you want to edit?
Type 'quit' to exit the edit view
1
Enter the description of the step:
go to haidilao
Step has been edited:
1. go to haidilao

Data saved successfully!
__________________________________________________________
```
```
editstep 1
There are 3 steps in the list
1. chop beef
2. add car
3. eat food
Which step do you want to edit?
Type 'quit' to exit the edit view
quit
__________________________________________________________
```
The function will replace the specified step of a specified recipe with a new user input.
First input the index of the recipe to edit, then input the step number you would like to edit.
Following which, type in the description for the step.

Note that to `exit` the whole program from the edit window, you have to `quit` first then `exit`. 

<h2 id="editing-a-recipe-ingredient-editingredient"> Editing a recipe's ingredients: <code class="language-plaintext highlighter-rouge">editingredient</code></h2>

Edits an ingredient for a recipe in the recipe manager.<br>

**Format**: `editingredient INDEX`, then input the ingredient number<br>
**Hint**: At the edit page, you can input `quit` if you decide not to edit.<br>
**Examples**:<br>

```
__________________________________________________________
editingredient 1
Here are 3 ingredients in the list:
1. Beef
2. Potatoes
3. Carrots
Which ingredient do you want to edit?
Type 'quit' to exit the edit view
2
Enter the description of the ingredient:
Chicken
Ingredient has been edited:
2. Chicken

Data saved successfully!
__________________________________________________________
```
```
__________________________________________________________
editingredient 1
Here are 3 ingredients in the list:
1. Beef
2. Chicken
3. Carrots
Which ingredient do you want to edit?
Type 'quit' to exit the edit view
quit
__________________________________________________________
```

<p> The function will replace the specified ingredient of a specified recipe with a new user input.
First input the index of the recipe to edit, then input the ingredient number you would like to edit.
Following which, type in the description for the ingredient. </p>

Note that to `exit` the whole program from the edit window, you have to `quit` first then `exit`. 

<h2 id="editing-a-recipe-edit"> Editing a recipe with one line command: <code class="language-plaintext highlighter-rouge">edit</code></h2>

For expert users, you can edit a recipe with one line command.<br>
For editing ingredients, you can use `edit --i` to edit ingredients.<br>
**Format**: `edit --i INDEXOFRECIPE INDEXOFINGREDIENT i/NEWINGREDIENT`<br>
**Examples**:<br>
```
__________________________________________________________
edit --i 1 2 i/pork
Ingredient has been edited:
2. pork
__________________________________________________________
```
For editing steps, you can use `edit --s` to edit steps.<br>
**Format**: `edit --s INDEXOFRECIPE INDEXOFSTEP s/NEWSTEP`<br>
**Examples**:<br>
```
__________________________________________________________
edit --s 1 1 s/wash beef
Step has been edited:
1. wash beef
__________________________________________________________
```

<h2 id="deleting-a-recipe-delete"> Deleting a recipe: <code class="language-plaintext highlighter-rouge">delete</code></h2>

Deletes a recipe from the recipe list.<br>
**Format**: `delete INDEX`<br>
**Examples**:<br>
```
__________________________________________________________
delete 1

Noted. I've removed this recipe:
  [Chinese] MaLaXiangGuo
Now you have 1 recipes in the list.


Data saved successfully!
__________________________________________________________
```
<p>App will remove the recipe with the corresponding index.</p>

<h2 id="deleting-elements-from-recipe"> Deleting an element from a recipe: <code class="language-plaintext highlighter-rouge">deletefromrecipe</code></h2>

Deletes either a step or an ingredient from a particular recipe in the recipe list.<br> 
As shown in the format, the flag for `--[s/i]` can be used interchangeably, `s` to delete a step and `i` to delete an ingredient.<br>
**Format**: `deletefromrecipe --[s/i] id/[index]`<br>
**Examples**:<br>
```
__________________________________________________________
deletefromrecipe --i id/1
There are 4 ingredients in the list:
1. Beef
2. Potatoes
3. Carrots
4. mala sauce
Enter step index below:
4
The ingredient has been successfully deleted from the ingredient list!
__________________________________________________________
```
```
__________________________________________________________
deletefromrecipe --s id/1
There are 5 steps in the list
1. chop beef
2. add potatoes
3. add carrots
4. Add mala sauce when water has reached a rolling boil.
5. cook 5 minutes
Valid range: 1 to 5
Enter "quit" to cancel.
Enter step index below:
4
The step has been successfully deleted from the step list!
__________________________________________________________
```
<p>App will remove the element of the second given index from the recipe with the first given index.</p>

<h2 id="finding-recipes-findname"> Finding recipes: <code class="language-plaintext highlighter-rouge">findname</code></h2>

Find recipes whose names contain any of the given keywords. The results will be displayed in a list, telling you the index of the recipe in the recipe list. Only the name of the recipe will be searched.<br>

**Format**: `findname KEYWORD`<br>
**Constraints**: `KEYWORD` cannot be empty.<br>
**Examples**:<br>
```
__________________________________________________________
findname hotpot

Here are the matching items:
  [Chinese] Hotpot [Index: 2]
__________________________________________________________
```

<h2 id="finding-recipes-findtag"> Finding recipes: <code class="language-plaintext highlighter-rouge">findtag</code></h2>

Find recipes whose tag contain any of the given keywords. The results will be displayed in a list, telling you the index of the recipe in the recipe list. Only the tag of the recipe will be searched.<br>
**Format**: `findtag KEYWORD`<br>
**Constraints**: 
* `KEYWORD` cannot be empty.<br>

**Examples**:<br>
```
__________________________________________________________
findtag chinese

Here are the matching items:
  [Chinese] Hotpot [Index: 2]
__________________________________________________________
```
<h2 id="viewing-a-recipe-view"> Viewing a recipe: <code class="language-plaintext highlighter-rouge">view</code></h2>

<div>Views a detailed recipe from the recipe manager if INDEX is entered.</div>

**Format**: `view INDEX` or `view NAME`<br>
**Constraints**: <br>
* INDEX: The index must be a positive integer within the range: `[1, total number of recipes in the list]`
(Provided the list is not empty).
* NAME: The **exact** full name of the recipe must be given as input, partial names will not be processed.

**Examples**:
<br>
* INDEX:
  
```
__________________________________________________________
view 1
Here is the recipe you requested, which is a Chinese flavour:
name: Hotpot
__________________________________________________________
Here are 3 ingredients in the list:
1. Beef
2. Potatoes
3. Carrots
__________________________________________________________
There are 4 steps in the list
Do you want to view step-by-step?
Type "yes" if so
yes
To exit recipe view, type "quit"
Else, enter any key to continue
1. chop beef

2. add potatoes

3. add carrots

4. cook 5 minutes

__________________________________________________________
```

* NAME:
  
```
__________________________________________________________
view Hotpot
Here is the recipe you requested, which is a Chinese flavour:
name: Hotpot
__________________________________________________________
Here are 3 ingredients in the list:
1. Beef
2. Potatoes
3. Carrots
__________________________________________________________
There are 4 steps in the list
Do you want to view step-by-step?
Type "yes" if so
yes
To exit recipe view, type "quit"
Else, enter any key to continue
1. chop beef

2. add potatoes

3. add carrots

4. cook 5 minutes

__________________________________________________________
```

<h2 id="listing-all-recipes-list"> Listing all recipes: <code class="language-plaintext highlighter-rouge">list</code></h2>

This feature allows you to **list all recipes** you currently have. This feature is useful after using the add, edit and delete commands to make changes to the recipe list, as it shows you the updated list.<br>
**Format**: `list`<br>
**Examples**:<br>
* `list`

<h2 id="clearing-all-entries-clear"> Clearing all entries: <code class="language-plaintext highlighter-rouge">clear</code></h2>

This feature allows you to **delete all recipes** that are currently stored on the recipe manager. Users may use this to clear all data when starting to use the app or when they wish to clear all data.<br>
**Format**: `clear`<br>
**Examples**:<br>
* `clear`

<h2 id="exiting-the-program-exit"> Exiting the program: <code class="language-plaintext highlighter-rouge">exit</code></h2>

Exits the program.<br>
**Format**: `exit`<br>
**Examples**:<br>
* `exit`

<h2 id="save-data"> Saving the data </h2>
The recipe manager data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<h2 id="edit-data"> Editing the data file </h2>

The recipe data are saved as a text file `[JAR file location]/data/[NUMBER].txt`. 
Here is an example of a valid file, `data/1.txt`:
```
Hotpot
Chinese
3
Beef
Potatoes
Carrots
4
chop beef
add potatoes
add carrots
cook 5 minutes
```
The first line is the name of the dish. And the second line is the tag of the dish. The third line is number of the ingredient list $X$. The following $X$ lines are the ingredients. Then the next line is the number of the step list $Y$. The following $Y$ lines are the steps.
***WARNING***:
* Do **NOT** modify/delete the save files.
* If you modify/delete the data file, and save data has **the wrong format**, the recipe manager will not process the data correctly.

<h2 id='faq'> FAQ </h2>
<strong>Q:</strong> What happens if I don't format the parameter correctly?<br>
<strong>A:</strong> If you input the correct action name, the app will prompt you for the correct input format, otherwise the app will ignore this instruction.<br>

<h2 id='command-summary'> Command Summary </h2>


| Action                                                            | 	Format                                                                                                    | Example                                                                                         |
|-------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| [**help**](#viewing-help-help)                                    | `help`                                                                                                     |                                                                                                 |
| [**add**](#adding-a-recipe-add)                                   | `add n/NAME i/INGREDIENT ... t/TAG s/SUMOFSTEP [STEP]...`                                                  | `add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/2` <br/>`add carrots` <br/>`cook 5 minutes` |
| [**addtorecipe**](#adding-elements-to-recipe)                     | `addtorecipe --[s/i] id/[index] desc/[description of step/ingredient]`                                     | `addtorecipe --i id/1 desc/mala sauce`                                                          |
| [**editingredient**](#editing-a-recipe-ingredient-editingredient) | `editingredient INDEX`                                                                                     | `editingredient 1`                                                                              |
| [**editstep**](#editing-steps-editstep)                           | `editstep INDEX`                                                                                           | `editstep 1`                                                                                    |
| [**edit**](#editing-a-recipe-edit)                                | `edit --i INDEXOFRECIPE INDEXOFINGREDIENT i/NEWINGREDIENT`  `edit --s INDEXOFRECIPE INDEXOFSTEP s/NEWSTEP` | `edit --i 1 2 i/pork` `edit --s 1 1 s/wash beef`                                                |
| [**delete**](#deleting-a-recipe-delete)                           | `delete INDEX`                                                                                             | `delete 1`                                                                                      |
| [**deletefromrecipe**](#deleting-elements-from-recipe)            | `deletefromrecipe --[s/i] id/[index]`                                                                      | `deletefromrecipe --i id/1`                                                                     |
| [**findname**](#finding-recipes-findname)                         | `find KEYWORD `                                                                                            | `find Hotpot`                                                                                   |
| [**findtag**](#finding-recipes-findtag)                           | `find KEYWORD `                                                                                            | `find Chinese`                                                                                  |
| [**view**](#viewing-a-recipe-view)                                | `view INDEX`<br/>`view NAME`                                                                               | `view 1`<br/>`view Hotpot`                                                                      |
| [**list**](#listing-all-recipes-list)                             | `list`                                                                                                     |                                                                                                 |
| [**clear**](#clearing-all-entries-clear)                          | `clear`                                                                                                    |                                                                                                 |
| [**exit**](#exiting-the-program-exit)                             | `exit`                                                                                                     |                                                                                                 |

