package seedu.duke.ui;

import seedu.duke.command.CommandType;
import seedu.duke.exceptions.FileParseReadingException;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.InvalidInputCharactersException;
import seedu.duke.exceptions.OutOfIndexException;
import seedu.duke.exceptions.RecipeListEmptyException;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.StepList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {
    private static Scanner in;
    public UI() {
        in = new Scanner(System.in);
    }
    public String readCommand() {
        return in.nextLine();
    }
    public void showRecipeList(ArrayList<Recipe> list) {
        if (list.size() == 0) {
            System.out.println(StringLib.EMPTY_LIST_MESSAGE);
            return;
        }
        System.out.println("\nRECIPE LIST\n");
        int i = 1;
        for (Recipe t : list) {
            System.out.println(i + ". " + t.toString());
            i += 1;
        }
        System.out.println();
    }
    public void showRecipeAdded(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Got it. I've added this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public void showRecipeDeleted(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Noted. I've removed this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public void showRecipeListCleared() {
        System.out.println(StringLib.RECIPE_CLEARED_MESSAGE);
    }
    public void showWelcome() {
        System.out.println(StringLib.WELCOME_MESSAGE);
    }
    public void showExit() {
        System.out.println(StringLib.EXIT_MESSAGE);
    }
    public void showHelp() {
        System.out.println(StringLib.HELP);
    }
    public void showLine() {
        System.out.println(StringLib.LINE);
    }
    public void showStepInsertMessage(int stepNumber) {
        System.out.println("\nPlease enter the description of step " + stepNumber + ":");
    }
    public void showDukeMainError(Exception e) {
        if (e instanceof IOException) {
            System.out.println(StringLib.FILE_IO_ERROR + e.getMessage());
        } else {
            System.out.println(StringLib.DUDE_MAIN_ERROR + e.getMessage());
        }
    }
    public void showUnrecognizableErrorMessage() {
        System.out.println(StringLib.UNRECOGNIZABLE_ERROR);
    }
    public void showUnrecognizableCommandErrorMessage() {
        System.out.println(StringLib.UNRECOGNIZABLE_COMMAND_ERROR);
    }

    public void showLoadingErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            System.out.println(StringLib.FILE_NOT_FOUND_ERROR +
                    e.getMessage() +
                    StringLib.CREATING_NEW_FILE_AND_DIRECTORY);
        } else if (e instanceof FileParseReadingException) {
            System.out.println(StringLib.FILE_PARSE_READING_ERROR + e.getMessage());
        } else {
            System.out.println(StringLib.FILE_LOADING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showAddingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(StringLib.PARSING_STRING_ERROR + e.getMessage());
        } else if (e instanceof InvalidInputCharactersException) {
            System.out.println(StringLib.RECIPE_ADDING_DEFAULT_ERROR + e.getMessage());
        } else {
            System.out.println(StringLib.RECIPE_ADDING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showAddingRecipeElementErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(StringLib.PARSING_STRING_ERROR + e.getMessage());
        } else if (e instanceof RecipeListEmptyException) {
            System.out.println(StringLib.EMPTY_LIST_MESSAGE);
        } else {
            System.out.println(StringLib.RECIPE_ADDING_TO_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showDeletingRecipeElementErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(StringLib.PARSING_STRING_ERROR + e.getMessage());
        } else if (e instanceof RecipeListEmptyException) {
            System.out.println(StringLib.EMPTY_LIST_MESSAGE);
        } else {
            System.out.println(StringLib.RECIPE_DELETING_FROM_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showInvalidAddToRecipeDescription() {
        System.out.println(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION);
    }
    public void showInvalidDeleteFromRecipeDescription() {
        System.out.println(StringLib.INVALID_DELETE_FROM_RECIPE_DESCRIPTION);
    }
    public void showDeletingTaskErrorMessage(Exception e, CommandType type) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                   e instanceof RecipeListEmptyException) {
            System.out.println(StringLib.PREFIX_EMPTY_LIMIT_LIST_ERROR +
                    type +
                    StringLib.SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(StringLib.RECIPE_DELETING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showRecipeViewed(Recipe recipe, UI ui) {
        System.out.println("Here is the recipe you requested, which is "+ recipe.getTag() + " flavour:");
        System.out.println("name: " + recipe.getName());
        showLine();
        IngredientList ingredients = recipe.getIngredientList();
        ingredients.showList();
        showLine();
        StepList steps = recipe.getStepList();
        steps.showStepList(ui);
    }
    public void showViewingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof OutOfIndexException) {
            System.out.println(StringLib.RECIPE_VIEWING_DEFAULT_ERROR + e.getMessage());
        } else if (e instanceof NullPointerException || e instanceof RecipeListEmptyException) {
            System.out.println(StringLib.PREFIX_EMPTY_LIMIT_LIST_ERROR +
                    CommandType.VIEW +
                    StringLib.SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(StringLib.RECIPE_VIEWING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showLoad() {
        System.out.println(StringLib.RECIPE_LOADED);
    }
    public void showEditRecipeStepPrompt() {
        System.out.println(StringLib.EDIT_RECIPE_STEP_PROMPT);
    }
    public void showEditErrorMessage (Exception e) {
        if (e instanceof OutOfIndexException) {
            System.out.println(e.getMessage());
        } else if (e instanceof NumberFormatException) {
            System.out.println(StringLib.EDIT_NUMBERFORMATEXCEPTION_MESSAGE);
        } else {
            System.out.println(StringLib.RECIPE_EDITING_DEFAULT_ERROR + e.getMessage());
        }

    }
    public void showEditRecipeIngredientPrompt() {
        System.out.println(StringLib.EDIT_RECIPE_INGREDIENT_PROMPT);
    }
    public void showInvalidStepMessage() {
        System.out.println(StringLib.INVALID_STEP);
    }

    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    public boolean isValidIntegerInputToAdd(String input, int maxSteps) {
        try {
            int integerInput = Integer.parseInt(input.trim());
            if (integerInput < 1 || integerInput > maxSteps + 1) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean isValidIntegerInputToDelete(String input, int maxSteps) {
        try {
            int integerInput = Integer.parseInt(input.trim());
            if (integerInput < 1 || integerInput > maxSteps) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void showInvalidIndexMessage() {
        System.out.println(StringLib.INVALID_STEP_INDEX);
    }
    public void requestIndexInput() {
        System.out.println(StringLib.INDEX_REQUEST);
    }
    public int getIndexToAdd(int maxSteps) {
        System.out.println("Valid range: " + 1 + " to " + (maxSteps + 1));
        System.out.println(StringLib.INDEX_REQUEST);
        String userInput = in.nextLine();
        while (!isValidIntegerInputToAdd(userInput, maxSteps)) {
            showInvalidIndexMessage();
            System.out.println("Valid range: " + 1 + " to " + (maxSteps + 1));
            requestIndexInput();
            userInput = in.nextLine();
            if (userInput.trim().toLowerCase().equals(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                return IntLib.ADD_STEP_INDEX_BREAKOUT;
            }
        }
        return Integer.parseInt(userInput.trim()) - 1;
    }
    public int getIndexToDelete(int maxSteps) {
        System.out.println("Valid range: " + 1 + " to " + maxSteps);
        System.out.println(StringLib.INDEX_REQUEST);
        String userInput = in.nextLine();
        if (userInput.trim().toLowerCase().equals(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
            return IntLib.ADD_STEP_INDEX_BREAKOUT;
        }
        while (!isValidIntegerInputToDelete(userInput, maxSteps)) {
            showInvalidIndexMessage();
            if (maxSteps != 0) {
                System.out.println("Valid range: " + 1 + " to " + maxSteps);
            }
            requestIndexInput();
            userInput = in.nextLine();
            if (userInput.trim().toLowerCase().equals(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                return IntLib.ADD_STEP_INDEX_BREAKOUT;
            }
        }
        return Integer.parseInt(userInput.trim());
    }
    public void showDuplicateIngredient() {
        System.out.println(StringLib.DUPLICATE_INGREDIENT_ERROR);
    }
    public void showDuplicateStep() {
        System.out.println(StringLib.DUPLICATE_STEP_ERROR);
    }
    public void showStepAdded() {
        System.out.println(StringLib.STEP_ADD_SUCCESS);
    }
    public void showIngredientAdded() {
        System.out.println(StringLib.INGREDIENT_ADD_SUCCESS);
    }
    public void showEmptyStepDescription() {
        System.out.println(StringLib.EMPTY_STEP_DESCRIPTION_MESSAGE);
    }
    public void showEmptyIngredientDescription() {
        System.out.println(StringLib.EMPTY_INGREDIENT_DESCRIPTION_MESSAGE);
    }
    public void showStepQuitMessage() {
        System.out.println(StringLib.STEP_QUIT_MESSAGE);
    }
    public void showIngredientQuitMessage() {
        System.out.println(StringLib.STEP_QUIT_MESSAGE);
    }
    public void showIngredientDeleted() {
        System.out.println(StringLib.INGREDIENT_DELETE_SUCCESS);
    }
    public void showStepDeleted() {
        System.out.println(StringLib.STEP_DELETE_SUCCESS);
    }
    public void showEmptyStepID() {
        System.out.println(StringLib.EMPTY_STEP_ID_MESSAGE);
    }
    public void showEmptyIngredientID() {
        System.out.println(StringLib.EMPTY_INGREDIENT_ID_MESSAGE);
    }
    public void showEmptyStepList() {
        System.out.println(StringLib.EMPTY_STEP_LIST);
    }
    public void showEmptyIngredientList() {
        System.out.println(StringLib.EMPTY_INGREDIENT_LIST);
    }
    public void showDefaultCaseError() {
        System.out.println(StringLib.DEFAULT_CASE_WARNING);
    }
    public void showDefaultElseConditionError() {
        System.out.println(StringLib.DEFAULT_ELSE_CONDITION_WARNING);
    }
    public void showMinimumIngredientError() {
        System.out.println(StringLib.MIN_NUM_INGREDIENT_ERROR);
    }
}
