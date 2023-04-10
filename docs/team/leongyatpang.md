# Leong Yat Pang - Project Portfolio Page

## Overview
Taste of Mom's is a CLI application for managing, storing and viewing recipes, 
optimized for use via a Command Line Interface (CLI)
while still having the features of a recipe-managing application.
<br>

Our app will help users through the process of cooking their favourite dishes stored within the app.

## Summary of Contributions

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=YatPang&tabRepo=AY2223S2-CS2113-F13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented to Taste of Mom's
* **Saving Recipe List**<br>
Implemented the logic to save recipes in recipe list automatically after changes are made to the recipe list.
The app saves recipes in the `/data` directory as individual `.txt` files.
<br>
This is one of the basic features of the application.
* **Loading Saved Data**<br>
Implemented the logic to load recipes saved from previous sessions.
The app scans the `/data` directory for `.txt` files, 
and reads files individually to generate stored recipes.
<br>
This is one of the basic features of the application.
* **Searching Recipe List by Name**<br>
`findname`<br>
Implemented the command and methods to search the recipe list for items with names that contain the searched term.
* **Searching Recipe List by Tag**<br>
`findtag`<br>
Implemented the command and methods to search the recipe list for items with tags that contain the searched term.
* **Adding an Element to Recipe**<br>
`addtorecipe`<br>
Implemented the command and logic to add step and ingredients to an existing recipe on the recipe list.
* **Deleting an Element from Recipe**<br>
`deletefromrecipe`<br>
  Implemented the command and logic to delete step and ingredients to an existing recipe on the recipe list.

### Contributions to UG
Added user documentation to explain how to use the following commands:
* `findname`
* `findtag`
* `addtorecipe`
* `deletefromrecipe`

### Contributions to DG
* Added UML diagram and technical documentation to illustrate how recipe data is stored and loaded.
![image](../PlantUML/StorageComponent.png)

### Issues Resolved
1. [Issue #66](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/66)
2. [Issue #69](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/69)
3. [Issue #70](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/70)
4. [Issue #82](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/82)
5. [Issue #83](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/83)
6. [Issue #88](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/88)
7. [Issue #90](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/90)
8. [Issue #92](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/92)
9. [Issue #102](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/102)
10. [Issue #103](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/103)
11. [Issue #120](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/120)
12. [Issue #121](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/121)