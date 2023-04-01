package seedu.duke.command;


import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.OutOfIndexException;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
     * @param recipeList the current list of recipes to be modified or used.
     */
    public void execute(RecipeList recipeList, UI ui) throws IOException {

        int recipeListIndex;

        switch (type) {
        case LIST:
            ui.showRecipeList(recipeList.getRecipeList());
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
                recipeList.addNewRecipe(new Recipe(recipeName, recipeTag, ingredientLists, recipeSteps));
                ui.showRecipeAdded(recipeList.getNewestRecipe(), recipeList.getCurrRecipeNumber());
            } catch (Exception e) {
                ui.showAddingRecipeErrorMessage(e);
            }
            Storage.writeSavedFile();
            break;
        case DELETE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                recipeListIndex = Integer.parseInt(fullDescription);
                Recipe recipeToBeDeleted = recipeList.getRecipeFromList(recipeListIndex);
                ui.showRecipeDeleted(recipeToBeDeleted, recipeList.getCurrRecipeNumber() - 1);
                recipeList.removeRecipe(recipeListIndex);
            } catch (Exception e) {
                ui.showDeletingTaskErrorMessage(e, type);
            }
            Storage.writeSavedFile();
            break;
        case CLEAR:
            recipeList.clearRecipeList();
            ui.showRecipeListCleared();
            Storage.writeSavedFile();
            break;
        case VIEW:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);
                Recipe recipeToBeViewed = recipeList.getRecipeFromList(recipeListNum);
                ui.showRecipeViewed(recipeToBeViewed, ui);
            } catch (Exception e) {
                ui.showViewingRecipeErrorMessage(e);
            }
            break;
        case FIND:
            recipeList.searchRecipeList(fullDescription);
            break;
        case EDITSTEP:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);
                Recipe recipeToEdit = recipeList.getRecipeFromList(recipeListNum);
                StepList recipeToEditStepList = recipeToEdit.getStepList();
                int maxSteps = recipeToEditStepList.getCurrStepNumber();
                if (maxSteps == 0) {
                    assert (maxSteps - 1 == -1);
                    throw new OutOfIndexException(StringLib.NO_STEPS_ERROR);

                }

                recipeToEditStepList.showFullStepList();
                ui.showEditRecipeStepPrompt();
                String input = ui.readCommand();
                if (input.equals("quit")) {
                    break;
                }
                int stepIndex = Integer.parseInt(input) - 1;
                if (stepIndex < maxSteps) {
                    assert (maxSteps - stepIndex > 0);
                    recipeToEditStepList.editStep(stepIndex,ui);
                } else {
                    throw new OutOfIndexException(StringLib.INPUT_STEPS_INDEX_EXCEEDED);
                }
            } catch (Exception e) {
                ui.showEditErrorMessage(e);
            }
            Storage.writeSavedFile();
            break;
            
        case EDITINGREDIENT:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);
                Recipe recipeToEdit = recipeList.getRecipeFromList(recipeListNum);
                IngredientList recipeToEditIngredientList = recipeToEdit.getIngredientList();
                int maxSteps = recipeToEditIngredientList.getCurrIngredientNumber();
                if (maxSteps == 0) {
                    throw new OutOfIndexException(StringLib.NO_INGREDIENTS_ERROR);
                }
                recipeToEditIngredientList.showList();
                ui.showEditRecipeIngredientPrompt();
                String input = ui.readCommand();
                if (input.equals(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                    break;
                }
                int ingredientIndex = Integer.parseInt(input) - 1;

                if (ingredientIndex >= maxSteps) {
                    throw new OutOfIndexException(StringLib.INPUT_INGREDIENTS_INDEX_EXCEEDED);
                }
                recipeToEditIngredientList.editIngredient(ui, ingredientIndex);
            } catch (Exception e) {
                ui.showEditErrorMessage(e);
            }
            Storage.writeSavedFile();
            break;
        case EDIT:
            try {
                EditType editType = Parser.parseEditType(fullDescription);
                boolean isEditIngredient = editType == EditType.INGREDIENT;
                boolean isEditStep = editType == EditType.STEP;
                Object[] parsed = Parser.parseEditRecipeIndex(fullDescription.substring(4),editType);
                int recipeIndex = (int) parsed[0];
                String editDescription = (String) parsed[1];
                if(isEditIngredient) {
                    Parser.parseEditIngredient(recipeList, recipeIndex, editDescription);
                } else if (isEditStep) {
                    Parser.parseEditStep(recipeList, recipeIndex, editDescription);
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
