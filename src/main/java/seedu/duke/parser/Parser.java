package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.command.OperationType;
import seedu.duke.exceptions.EditFormatException;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.InvalidInputCharactersException;
import seedu.duke.exceptions.MissingIngredientInputException;
import seedu.duke.recipe.Step;
import seedu.duke.recipe.StepList;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Ingredient;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.ui.IntLib.RECIPE_NAME_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_INGREDIENTS_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_TAG_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_SUM_OF_STEPS_INDEX;
import static seedu.duke.ui.StringLib.INVALID_CHARACTERS_INGREDIENTS_ERROR;
import static seedu.duke.ui.StringLib.INVALID_CHARACTERS_NAME_ERROR;
import static seedu.duke.ui.StringLib.INVALID_CHARACTERS_STEP_ERROR;
import static seedu.duke.ui.StringLib.INVALID_CHARACTERS_TAG_ERROR;

public class Parser {

    private static final Pattern specialCharacters = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    private static final Pattern specialCharactersIngredients =
            Pattern.compile("[^a-z0-9, ]", Pattern.CASE_INSENSITIVE);
    private static final Pattern numericCharacters = Pattern.compile("^[0-9 ]*$");
    private static final Pattern nonNumericCharacters = Pattern.compile("[^0-9 ]");
    private static final String RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP = "\nRecipe is missing the \"NAME\" "
            + "or \"INGREDIENTS\" or \"TAG\" or \"SUM of the STEPs," +
            "\nor there is more than one\n" +
            "\"NAME\" or \"INGREDIENTS\" or \"TAG\" or \"SUM of the STEPs\"!\n";
    private static final String RECIPE_WRONG_LEADING_STRING = "Recipe contains the leading string!\n";
    private static final String RECIPE_MISSING_NAME = "Recipe is missing \"NAME\"!\n";
    private static final String RECIPE_MISSING_INGREDIENTS = "Recipe is missing \"INGREDIENTS\"!\n";
    private static final String RECIPE_MISSING_TAG = "Recipe is missing \"TAG\"!\n";
    private static final String RECIPE_MISSING_STEP = "Recipe is missing \"SUM of the STEPs\"!\n";

    /**
     * Returns a <code>Command</code> type which contains the command to be
     * executed and its full description to support its execution. It takes
     * in a string representing the user input.
     *
     * @param line the input line by user.
     * @return a <code>Command</code> containing the command to be executed.
     */
    public static Command parseCommands(String line) {
        line = line.strip();
        String[] lineSpaced = line.split(" ");
        String fullDescription = "";
        if (lineSpaced.length > 1) {
            for (int i = 2; i < lineSpaced.length; i++) {
                lineSpaced[1] = lineSpaced[1].concat(" " + lineSpaced[i]);
            }
            fullDescription = lineSpaced[1].trim();
        }
        CommandType type;
        switch (lineSpaced[0].toLowerCase()) {
        case "list":
            type = CommandType.LIST;
            break;
        case "add":
            type = CommandType.ADD;
            break;
        case "addtorecipe":
            type = CommandType.ADDTORECIPE;
            break;
        case "view":
            type = CommandType.VIEW;
            break;
        case "findname":
            type = CommandType.FINDNAME;
            break;
        case "findtag":
            type = CommandType.FINDTAG;
            break;
        case "editstep":
            type = CommandType.EDITSTEP;
            break;
        case "editingredient":
            type = CommandType.EDITINGREDIENT;
            break;
        case "edit":
            type = CommandType.EDIT;
            break;
        case "delete":
            type = CommandType.DELETE;
            break;
        case "deletefromrecipe":
            type = CommandType.DELETEFROMRECIPE;
            break;
        case "help":
            type = CommandType.HELP;
            break;
        case "clear":
            type = CommandType.CLEAR;
            break;
        case "exit":
            type = CommandType.EXIT;
            break;
        default:
            type = CommandType.UNKNOWN;
        }
        return new Command(type, fullDescription);
    }

    private static boolean matchString(String input, String regex) {
        String matcher = input;
        int count = 0;
        while (matcher.contains(regex)) {
            matcher = matcher.substring(matcher.indexOf(regex) + 1);
            ++count;
        }
        boolean isMatch = (count == 1);
        return isMatch;
    }

    public static int matchCount(String input, String regex) {
        String matcher = input;
        int count = 0;
        while (matcher.contains(regex)){
            matcher=matcher.substring(matcher.indexOf(regex)+1);
            ++count;
        }
        return count;
    }

    /**
     * Returns an Array of Strings containing the parsed full description
     * of a <code>Recipe</code> into its name, ingredients and tag.
     *
     * @param description the full description of the <code>Recipe</code>.
     * @return parsed ArrayList of Strings containing the recipe's name, ingredients list and tag.
     * @throws IncompleteInputException if full description input is missing the description or due date or both.
     */
    public static ArrayList<String> parseRecipe(String description) throws IncompleteInputException,
            InvalidInputCharactersException {
        ArrayList<String> parsed = new ArrayList<>();
        if (!matchString(description, "n/") || !matchString(description, "i/")
                || !matchString(description, "t/") || !matchString(description, "s/")) {
            throw new IncompleteInputException(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP);
        }
        if (!description.substring(0, 2).equals("n/")) {
            throw new IncompleteInputException(RECIPE_WRONG_LEADING_STRING);
        }
        String[] parsedName = description.split(" i/");
        String[] parsedIngredientsTag = parsedName[1].split(" t/");
        String[] parsedTagStep = parsedIngredientsTag[1].split(" s/");
        parsed.add(parsedName[0].substring(2).trim());
        parsed.add(parsedIngredientsTag[0].trim());
        parsed.add(parsedTagStep[0].trim());
        try {
            parsed.add(parsedTagStep[1].trim());
        } catch (Exception e) {
            throw new IncompleteInputException(RECIPE_MISSING_STEP);
        }

        if (parsed.size() < 4) {
            throw new IncompleteInputException(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP);
        }
        if (parsed.get(RECIPE_NAME_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_NAME);
        }
        if (parsed.get(RECIPE_INGREDIENTS_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_INGREDIENTS);
        }
        if (parsed.get(RECIPE_TAG_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_TAG);
        }
        if (parsed.get(RECIPE_SUM_OF_STEPS_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_STEP);
        }
        checkValidAddParameters(parsed);
        try {
            Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX));
            if (Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX)) < 0) {
                throw new IncompleteInputException(StringLib.MISSING_NUM);
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IncompleteInputException(StringLib.MISSING_NUM);
        }
    }

    public static void checkValidAddParameters(ArrayList<String> parsedAddRecipe)
            throws InvalidInputCharactersException {
        String NAME = parsedAddRecipe.get(RECIPE_NAME_INDEX);
        String INGREDIENT = parsedAddRecipe.get(RECIPE_INGREDIENTS_INDEX);
        String TAG = parsedAddRecipe.get(RECIPE_TAG_INDEX);
        String STEP = parsedAddRecipe.get(RECIPE_SUM_OF_STEPS_INDEX);
        Matcher matchSpecialCharactersNAME = specialCharacters.matcher(NAME);
        Matcher matcherNumericCharactersOnlyNAME = numericCharacters.matcher(NAME);
        Matcher matchSpecialCharactersINGREDIENT = specialCharactersIngredients.matcher(INGREDIENT);
        Matcher matcherNumericCharactersOnlyINGREDIENT = numericCharacters.matcher(INGREDIENT);
        Matcher matchSpecialCharactersTAG = specialCharacters.matcher(TAG);
        Matcher matcherNumericCharactersOnlyTAG = numericCharacters.matcher(TAG);
        Matcher matchNonNumericCharactersSTEP = nonNumericCharacters.matcher(STEP);
        if (matchSpecialCharactersNAME.find() || matcherNumericCharactersOnlyNAME.find()) {
            throw new InvalidInputCharactersException(INVALID_CHARACTERS_NAME_ERROR);
        }
        if (matchSpecialCharactersINGREDIENT.find() || matcherNumericCharactersOnlyINGREDIENT.find()) {
            throw new InvalidInputCharactersException(INVALID_CHARACTERS_INGREDIENTS_ERROR);
        }
        if (matchSpecialCharactersTAG.find() || matcherNumericCharactersOnlyTAG.find()) {
            throw new InvalidInputCharactersException(INVALID_CHARACTERS_TAG_ERROR);
        }
        if (matchNonNumericCharactersSTEP.find()) {
            throw new InvalidInputCharactersException(INVALID_CHARACTERS_STEP_ERROR);
        }
    }

    public static IngredientList parseIngredients(String inputIngredients) throws MissingIngredientInputException {
        ArrayList<Ingredient> parsed = new ArrayList<>();
        String[] parsedIngredients = inputIngredients.split(",");
        if (parsedIngredients.length == 0) {
            throw new MissingIngredientInputException();
        }
        for (String ingredient : parsedIngredients) {
            if (ingredient.trim().isEmpty()) {
                throw new MissingIngredientInputException();
            } else {
                parsed.add(new Ingredient(ingredient.trim()));
            }
        }
        assert (parsed.size() != 0);
        return new IngredientList(parsed);
    }

    public static StepList parseSteps(UI ui, int sumOfSteps) {
        ArrayList<Step> parsedStepList = new ArrayList<>();
        String inputStep;
        for (int i = 1; i <= sumOfSteps; i++) {
            ui.showStepInsertMessage(i);
            inputStep = ui.readCommand();
            while (inputStep.trim().equals("")) {
                ui.showInvalidStepMessage();
                inputStep = ui.readCommand();
            }
            parsedStepList.add(new Step(inputStep));
        }
        return new StepList(parsedStepList);
    }

    public static OperationType parseEditType(String description) throws IncompleteInputException {
        boolean isIngredient = description.startsWith("--i ");
        boolean isStep = description.startsWith("--s ");
        if (isIngredient) {
            return OperationType.INGREDIENT;
        } else if (isStep) {
            return OperationType.STEP;
        } else {
            if(description.equals("--s")){
                throw new IncompleteInputException(StringLib.EDIT_STEP_ERROR);
            }
            if(description.equals("--i")){
                throw new IncompleteInputException(StringLib.EDIT_INGREDIENT_ERROR);
            }
            throw new IncompleteInputException(StringLib.EDIT_TYPE_ERROR);
        }
    }


    public static Object[] parseEditRecipeIndex(String description, OperationType type)
            throws IncompleteInputException {
        String[] parsedDescription = description.split(" ",2);
        String errorLog = type.equals(OperationType.INGREDIENT) ?
                StringLib.EDIT_INGREDIENT_ERROR : StringLib.EDIT_STEP_ERROR;
        if (parsedDescription.length < 2) {
            throw new IncompleteInputException(errorLog);
        }
        try {
            int recipeIndex = Integer.parseInt(parsedDescription[0]);
            return new Object[]{recipeIndex, parsedDescription[1].trim()};
        } catch (NumberFormatException e) {
            try {
                new BigInteger(parsedDescription[0]);
            } catch (Exception e1) {
                throw new IncompleteInputException(errorLog);
            }
            throw new IncompleteInputException(StringLib.OVERFLOW_NUMBER_ERROR);
        }
    }

    public static void parseEditIngredient(Integer recipeIndex, String description) throws Exception {
        String[] parsedDescription = description.split("i/");
        if (parsedDescription.length < 2) {
            throw new IncompleteInputException(StringLib.EDIT_INGREDIENT_ERROR);
        }
        if (!matchString(description, "i/")) {
            throw new IncompleteInputException(StringLib.EDIT_INGREDIENT_ERROR);
        }
        try {
            int ingredientIndex = Integer.parseInt(parsedDescription[0].trim());
            String newIngredient = parsedDescription[1].trim();
            if (newIngredient.isEmpty()) {
                throw new IncompleteInputException(StringLib.EDIT_INGREDIENT_ERROR);
            }
            RecipeList.editIngredient(recipeIndex, ingredientIndex, newIngredient);
        } catch (NumberFormatException e) {
            try {
                new BigInteger(parsedDescription[0].trim());
            } catch (Exception e1) {
                throw new IncompleteInputException(StringLib.EDIT_INGREDIENT_ERROR);
            }
            throw new IncompleteInputException(StringLib.OVERFLOW_NUMBER_ERROR);
        } catch (EditFormatException e) {
            throw new Exception("error in edit ingredient:\n" + e.getMessage());
        }
    }

    public static void parseEditStep(Integer recipeIndex, String description) throws Exception {
        String[] parsedDescription = description.split("s/");
        if (parsedDescription.length < 2) {
            throw new IncompleteInputException(StringLib.EDIT_STEP_ERROR);
        }
        if (!matchString(description, "s/")) {
            throw new IncompleteInputException(StringLib.EDIT_STEP_ERROR);
        }
        try {
            int stepIndex = Integer.parseInt(parsedDescription[0].trim());
            String newStep = parsedDescription[1].trim();
            if (newStep.isEmpty()) {
                throw new IncompleteInputException(StringLib.EDIT_STEP_ERROR);
            }
            RecipeList.editStep(recipeIndex, stepIndex, newStep);
        } catch (NumberFormatException e) {
            try {
                new BigInteger(parsedDescription[0].trim());
            } catch (Exception e1) {
                throw new IncompleteInputException(StringLib.EDIT_STEP_ERROR);
            }
            throw new IncompleteInputException(StringLib.OVERFLOW_NUMBER_ERROR);
        } catch (EditFormatException e) {
            throw new Exception("error in edit step:\n" + e.getMessage());
        }
    }

    public static String removeForbiddenChars(String ingredient) {
        for (String chara : StringLib.FORBIDDEN_CHARS) {
            ingredient.replaceAll(chara, "");
        }
        return ingredient;
    }

    public static OperationType parseAddToRecipeIndex(String description) throws IncompleteInputException {
        boolean isIngredient = description.startsWith("--i ");
        boolean isStep = description.startsWith("--s ");
        if (isIngredient) {
            return OperationType.INGREDIENT;
        } else if (isStep) {
            return OperationType.STEP;
        } else {
            throw new IncompleteInputException(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION);
        }
    }

    public static String[] parseAddToRecipeDescription(String description) throws IncompleteInputException{
        OperationType parsedDescription = parseAddToRecipeIndex(description);
        String[] out = new String[3];
        out[0] = parsedDescription.equals(OperationType.INGREDIENT) ? "i" : "s";
        String subDescription = description.substring(4).trim();
        if (!subDescription.startsWith("id/")) {
            throw new IncompleteInputException(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION);
        }
        String[] subDescriptions = subDescription.substring(3).split("desc/",2);
        if (subDescriptions.length < 2) {
            throw new IncompleteInputException(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION);
        }
        out[1] = subDescriptions[0].trim();
        out[2] = subDescriptions[1].trim();
        return out;
    }
    public static OperationType parseDeleteFromRecipeIndex(String description) throws IncompleteInputException {
        boolean isIngredient = description.startsWith("--i ");
        boolean isStep = description.startsWith("--s ");
        if (isIngredient) {
            return OperationType.INGREDIENT;
        } else if (isStep) {
            return OperationType.STEP;
        } else {
            throw new IncompleteInputException(StringLib.INVALID_DELETE_FROM_RECIPE_DESCRIPTION);
        }
    }
    public static String[] parseDeleteFromRecipeDescription(String description) throws IncompleteInputException{
        OperationType parsedDescription = parseDeleteFromRecipeIndex(description);
        String[] out = new String[2];
        out[0] = parsedDescription.equals(OperationType.INGREDIENT) ? "i" : "s";
        String subDescription = description.substring(4).trim();
        if (!subDescription.startsWith("id/")) {
            throw new IncompleteInputException(StringLib.INVALID_DELETE_FROM_RECIPE_DESCRIPTION);
        }
        out[1] = subDescription.substring(3).trim();
        return out;
    }

    public static boolean isValidAddToRecipe(String description) {
        String descLowerCase = description.toLowerCase().trim();
        if ((descLowerCase.contains("--s") && descLowerCase.contains("--i"))){
            return false;
        }
        if (matchCount(descLowerCase,"id/") != 1) {
            return false;
        }
        if (matchCount(descLowerCase,"desc/") != 1){
            return false;
        }
        if (matchCount(descLowerCase, "--i") == 0 && matchCount(descLowerCase, "--s") == 0) {
            return false;
        }
        if (matchCount(descLowerCase, "--i") > 1) {
            return false;
        }
        if (matchCount(descLowerCase, "--s") > 1) {
            return false;
        }
        return true;
    }
    public static boolean isValidDeleteFromRecipe(String description) {
        String descLowerCase = description.toLowerCase().trim();
        if ((descLowerCase.contains("--s") && descLowerCase.contains("--i"))){
            return false;
        }
        if (matchCount(descLowerCase,"id/") != 1) {
            return false;
        }
        if (matchCount(descLowerCase, "--i") == 0 && matchCount(descLowerCase, "--s") == 0) {
            return false;
        }
        if (matchCount(descLowerCase, "--i") > 1) {
            return false;
        }
        if (matchCount(descLowerCase, "--s") > 1) {
            return false;
        }
        return true;
    }
    public static boolean isDuplicateIngredient(IngredientList ingredientList, String newIngredient) {
        for (Ingredient ingredient : ingredientList.getList()) {
            if (ingredient.getName().toLowerCase().equals(newIngredient)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isDuplicateStep(StepList stepList, String newStep) {
        for (Step step : stepList.getList()) {
            if (step.getStepDescription().toLowerCase().equals(newStep)) {
                return true;
            }
        }
        return false;
    }
}
