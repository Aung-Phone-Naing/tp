package seedu.duke.command;

import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.OutOfIndexException;
import seedu.duke.exceptions.RecipeListEmptyException;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.Step;
import seedu.duke.recipe.StepList;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Ingredient;
import seedu.duke.storage.Storage;
import seedu.duke.ui.IntLib;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.ui.IntLib.RECIPE_NAME_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_INGREDIENTS_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_TAG_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_SUM_OF_STEPS_INDEX;

/**
 * Represents a particular command to be carried out consisting of the
 * command type and command description.
 * <p></p>
 * A <code>Command</code> object corresponds to a particular command represented
 * by <code>type</code> and <code>fullDescription</code> (e.g. <code>DELETE,6</code>)
 */
public class Command {
    private final CommandType type;
    private final String fullDescription;

    /**
     * Class constructor specifying the type of command and its follow-up description.
     *
     * @param type an Enum that represents a particular command.
     * @param fullDescription a String that contains the follow-up description for the command.
     */
    public Command(CommandType type, String fullDescription) {
        this.type = type;
        this.fullDescription = fullDescription;
    }

    /**
     * Returns if the command type is <code>CommandType.EXIT</code>
     * in order to terminate the programme.
     *
     * @return      if the command is the exit command type.
     */
    public boolean isExit() {
        return this.type == CommandType.EXIT;
    }

    /**
     * Based on the <code>type</code>, carries out different tasks assigned
     * while fully checking for any exceptions that may occur along the way.
     *
     * @param ui the instantiated object to handle all user interactions.
     */
    public void execute(UI ui) throws IOException {

        int recipeListIndex;
        int recipeCount = RecipeList.getCurrRecipeNumber();
        switch (type) {
        case LIST:
            ui.showRecipeList(RecipeList.getRecipeList());
            break;
        case ADD:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The description of " + type + " cannot be empty.\n");
                }
                ArrayList<String> parsed = Parser.parseRecipe(fullDescription);
                String recipeName = parsed.get(RECIPE_NAME_INDEX);
                IngredientList ingredientLists =
                        Parser.parseIngredients(parsed.get(RECIPE_INGREDIENTS_INDEX));
                String recipeTag = parsed.get(RECIPE_TAG_INDEX).toString();
                int sumOfSteps = Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX));
                StepList recipeSteps = Parser.parseSteps(ui,sumOfSteps);
                RecipeList.addNewRecipe(new Recipe(recipeName, recipeTag, ingredientLists, recipeSteps));
                recipeCount = RecipeList.getCurrRecipeNumber();
                ui.showRecipeAdded(RecipeList.getNewestRecipe(), recipeCount);
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showAddingRecipeErrorMessage(e);
            }
            break;
        case ADDTORECIPE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The description of " + type + " cannot be empty.\n");
                }
                if (!Parser.isValidAddToRecipe(fullDescription)) {
                    ui.showInvalidAddToRecipeDescription();
                    break;
                }
                String[] parsed = Parser.parseAddToRecipeDescription(fullDescription);
                if (parsed.length != 3) {
                    ui.showInvalidAddToRecipeDescription();
                    break;
                }
                String elementType = parsed[0];
                String id = parsed[1];
                String description = parsed[2];
                if (description.trim().equals(StringLib.EMPTY_STRING) && elementType.equals("s")) {
                    ui.showEmptyStepDescription();
                    break;
                }
                if (description.trim().equals(StringLib.EMPTY_STRING) && elementType.equals("i")) {
                    ui.showEmptyIngredientDescription();
                    break;
                }
                if (id.trim().equals(StringLib.EMPTY_STRING) && elementType.equals("s")) {
                    ui.showEmptyStepID();
                    break;
                }
                if (id.trim().equals(StringLib.EMPTY_STRING) && elementType.equals("i")) {
                    ui.showEmptyIngredientID();
                    break;
                }
                Recipe recipeToAddTo = RecipeList.getRecipe(id);
                int index;
                switch (elementType) {
                case "s":
                    StepList stepListToAddTo = recipeToAddTo.getStepList();
                    if (Parser.isDuplicateStep(stepListToAddTo, description)) {
                        ui.showDuplicateStep();
                        break;
                    }
                    stepListToAddTo.showFullStepList();
                    int maxStep = stepListToAddTo.getCurrStepNumber();
                    index = ui.getIndexToAdd(maxStep);
                    if (index == IntLib.ADD_STEP_INDEX_BREAKOUT) {
                        ui.showStepQuitMessage();
                        break;
                    }
                    stepListToAddTo.addStep(new Step(description), index);
                    ui.showStepAdded();
                    Storage.writeSavedFile();
                    break;
                case "i":
                    IngredientList ingredientListToAddTo = recipeToAddTo.getIngredientList();
                    if (Parser.isDuplicateIngredient(ingredientListToAddTo, description)){
                        ui.showDuplicateIngredient();
                        break;
                    } else {
                        int maxNum = ingredientListToAddTo.getCurrIngredientNumber();
                        ingredientListToAddTo.addIngredient(new Ingredient(description), maxNum);
                        ui.showIngredientAdded();
                        Storage.writeSavedFile();
                    }
                    break;
                default:
                }
            } catch (Exception e) {
                ui.showAddingRecipeElementErrorMessage(e);
            }
            break;
        case DELETE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                recipeListIndex = Integer.parseInt(fullDescription);
                if (recipeCount == 0) {
                    System.out.println(StringLib.EMPTY_LIST_MESSAGE);
                    break;
                }
                if (recipeListIndex <= 0 || recipeListIndex > recipeCount) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + recipeCount);
                    break;
                }
                Recipe recipeToBeDeleted = RecipeList.getRecipeFromList(recipeListIndex);
                RecipeList.removeRecipe(recipeListIndex);
                recipeCount = RecipeList.getCurrRecipeNumber();
                ui.showRecipeDeleted(recipeToBeDeleted, recipeCount);
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showDeletingTaskErrorMessage(e, type);
            }
            break;
        case DELETEFROMRECIPE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The description of " + type + " cannot be empty.\n");
                }
                if (!Parser.isValidDeleteFromRecipe(fullDescription)) {
                    ui.showInvalidDeleteFromRecipeDescription();
                    break;
                }
                String[] parsed = Parser.parseDeleteFromRecipeDescription(fullDescription);
                if (parsed.length != 2) {
                    ui.showInvalidDeleteFromRecipeDescription();
                    break;
                }
                String elementType = parsed[0];
                String id = parsed[1];
                if (id.trim().equals(StringLib.EMPTY_STRING) && elementType.equals("s")) {
                    ui.showEmptyStepID();
                    break;
                }
                if (id.trim().equals(StringLib.EMPTY_STRING) && elementType.equals("i")) {
                    ui.showEmptyIngredientID();
                    break;
                }
                Recipe recipeToDeleteFrom = RecipeList.getRecipe(id);
                int index;
                switch (elementType) {
                case "s":
                    StepList stepListToDeleteFrom = recipeToDeleteFrom.getStepList();
                    if (stepListToDeleteFrom.isEmpty()) {
                        ui.showEmptyStepList();
                        break;
                    }
                    stepListToDeleteFrom.showFullStepList();
                    int maxStep = stepListToDeleteFrom.getCurrStepNumber();
                    index = ui.getIndexToDelete(maxStep);
                    if (index == IntLib.ADD_STEP_INDEX_BREAKOUT) {
                        ui.showStepQuitMessage();
                        break;
                    }
                    stepListToDeleteFrom.removeStep(index);
                    ui.showStepDeleted();
                    Storage.writeSavedFile();
                    break;
                case "i":
                    IngredientList ingredientListToDeleteFrom = recipeToDeleteFrom.getIngredientList();
                    if (ingredientListToDeleteFrom.isEmpty()) {
                        ui.showEmptyIngredientList();
                        break;
                    }
                    int maxCount = ingredientListToDeleteFrom.getCurrIngredientNumber();
                    if (maxCount == 1) {
                        ui.showMinimumIngredientError();
                        break;
                    }
                    ingredientListToDeleteFrom.showList();

                    index = ui.getIndexToDelete(maxCount);
                    if (index == IntLib.ADD_STEP_INDEX_BREAKOUT) {
                        ui.showIngredientQuitMessage();
                        break;
                    }
                    ingredientListToDeleteFrom.removeIngredient(index);
                    ui.showIngredientDeleted();
                    Storage.writeSavedFile();
                    break;
                default:
                    ui.showDefaultCaseError();
                }
            } catch (Exception e) {
                ui.showDeletingRecipeElementErrorMessage(e);
            }
            break;
        case CLEAR:
            RecipeList.clearRecipeList();
            ui.showRecipeListCleared();
            Storage.writeSavedFile();
            break;
        case VIEW:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The "+ type +" command requires an input parameter, " +
                            "<Recipe NAME> / <Recipe INDEX>.\nIt cannot be empty!\n");
                }
                if (RecipeList.isEmpty()) {
                    throw new RecipeListEmptyException();
                }
                Recipe recipeToBeViewed = RecipeList.viewRecipe(fullDescription);
                ui.showRecipeViewed(recipeToBeViewed, ui);
            } catch (Exception e) {
                ui.showViewingRecipeErrorMessage(e);
            }
            break;
        case FINDNAME:
            RecipeList.searchRecipeList(fullDescription);
            break;
        case FINDTAG:
            RecipeList.searchByTag(fullDescription);
            break;
        case EDITSTEP:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);
                if (recipeCount == 0) {
                    System.out.println(StringLib.EMPTY_LIST_MESSAGE);
                    break;
                }
                if (recipeListNum <= 0 || recipeListNum > recipeCount) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + recipeCount);
                    break;
                }
                Recipe recipeToEdit = RecipeList.getRecipeFromList(recipeListNum);
                StepList recipeToEditStepList = recipeToEdit.getStepList();
                int maxSteps = recipeToEditStepList.getCurrStepNumber();
                if (maxSteps == 0) {
                    assert (maxSteps - 1 == -1);
                    throw new OutOfIndexException(StringLib.NO_STEPS_ERROR);

                }

                recipeToEditStepList.showFullStepList();
                ui.showEditRecipeStepPrompt();
                String input = ui.readCommand();
                if (input.trim().equals(StringLib.STEP_VIEW_QUIT_KEYWORD) ||
                        input.contains(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                    break;
                }
                int stepIndex = Integer.parseInt(input) - 1;
                if (stepIndex < maxSteps && stepIndex >= 0) {
                    assert (maxSteps - stepIndex > 0);
                    recipeToEditStepList.editStep(stepIndex, ui);
                } else if (stepIndex <= 0) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + maxSteps);
                } else {
                    throw new OutOfIndexException(StringLib.INPUT_STEPS_INDEX_EXCEEDED +
                            "\nValid Range: 1 to " + maxSteps);
                }
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showEditErrorMessage(e);
            }
            break;
            
        case EDITINGREDIENT:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);

                if (recipeCount == 0) {
                    System.out.println(StringLib.EMPTY_LIST_MESSAGE);
                    break;
                }
                if (recipeListNum <= 0 || recipeListNum > recipeCount) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + recipeCount);
                    break;
                }
                Recipe recipeToEdit = RecipeList.getRecipeFromList(recipeListNum);
                IngredientList recipeToEditIngredientList = recipeToEdit.getIngredientList();
                int maxSteps = recipeToEditIngredientList.getCurrIngredientNumber();
                if (maxSteps == 0) {
                    throw new OutOfIndexException(StringLib.NO_INGREDIENTS_ERROR);
                }
                recipeToEditIngredientList.showList();
                ui.showEditRecipeIngredientPrompt();
                String input = ui.readCommand();
                if (input.trim().equals(StringLib.STEP_VIEW_QUIT_KEYWORD) ||
                        input.contains(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                    break;
                }
                int ingredientIndex = Integer.parseInt(input) - 1;

                if (recipeToEditIngredientList.isIndexWithinRange(ingredientIndex)) {
                    System.out.println(StringLib.ENTER_INGREDIENT_DESCRIPTION);
                    String newIngredientDescription = ui.readCommand();
                    recipeToEditIngredientList.editIngredient(newIngredientDescription, ingredientIndex);
                    Storage.writeSavedFile();
                }
            } catch (Exception e) {
                ui.showEditErrorMessage(e);
            }
            break;
        case EDIT:
            try {
                OperationType editType = Parser.parseEditType(fullDescription);
                boolean isEditIngredient = editType == OperationType.INGREDIENT;
                boolean isEditStep = editType == OperationType.STEP;
                Object[] parsed = Parser.parseEditRecipeIndex(fullDescription.substring(4),editType);
                int recipeIndex = (int) parsed[0];
                String editDescription = (String) parsed[1];
                if(isEditIngredient) {
                    Parser.parseEditIngredient(recipeIndex, editDescription);
                } else if (isEditStep) {
                    Parser.parseEditStep(recipeIndex, editDescription);
                } else {
                    ui.showDefaultElseConditionError();
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
            Storage.writeSavedFile();
            break;
        case HELP:
            ui.showHelp();
            break;
        case EXIT:
            ui.showExit();
            break;
        case UNKNOWN:
            ui.showUnrecognizableErrorMessage();
            break;
        default:
            ui.showUnrecognizableCommandErrorMessage();
        }
    }
}
