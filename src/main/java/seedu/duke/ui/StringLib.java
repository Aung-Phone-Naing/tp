package seedu.duke.ui;

public interface StringLib {
    String LOGO = "_____         _         _____  __  ___  ___                _         _______ "
            + "________  ____\n"
            + "|_   _|       | |       |  _  |/ _| |  \\/  |               ( )       / /_   _|  _  |  \\/  \\ \\\n"
            + "  | | __ _ ___| |_ ___  | | | | |_  | .  . | ___  _ __ ___ |/ ___   | |  | | | | | | .  . || |\n"
            + "  | |/ _` / __| __/ _ \\ | | | |  _| | |\\/| |/ _ \\| '_ ` _ \\  / __|  | |  | | | | | | |\\/| || |\n"
            + "  | | (_| \\__ \\ ||  __/ \\ \\_/ / |   | |  | | (_) | | | | | | \\__ \\  | |  | | \\ \\_/ / |  | || |\n"
            + "  \\_/\\__,_|___/\\__\\___|  \\___/|_|   \\_|  |_/\\___/|_| |_| |_| |___/  | |  \\_/  \\___/\\_|  |_/| |"
            + "\n"
            + "                                                                     \\_\\                  /_/\n";
    String WELCOME_MESSAGE = "\nHELLO there! I am\n "
            + LOGO + '\n'
            + "Your personal recipes assistant!\n"
            + "What can I do for you today?\n\n"
            + "You can start by adding recipes to a recipe list that I can generate,"
            + " simply follow the format below:\n\n"
            + "Add recipe : \"add n/<insert recipe name> i/<insert ingredients with \", \" separation> "
            + "t/<insert cuisine> s/<insert number of steps>\"\n\n"
            + "If you wish to view the full list of commands, simply type \"help\"!\n";
    String HELP = "\nHelp is here! You may find the list of commands below useful. \n \n \n"
            + "COMMANDS LIST: \n \n \n"
            + "## List ## \n"
            + "Description : Displays all recipes in the list. \n"
            + "Format      : \"list\" \n"
            + "Example use : \"list\" \n \n"
            + "## Add ## \n"
            + "Description : Adds a new recipe to the list. \n"
            + "Format      : \"add n/<insert recipe name> i/<insert ingredients with \", \" separation> "
            + "t/<insert cuisine> s/<insert number of steps in recipe>\"\n"
            + "Example use : \"add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/4\" \n \n"
            + "## Edit Step ## \n"
            + "Description : Edits the steps of a recipe in the list. \n"
            + "Format      : \"editstep <insert recipe index>\"\n"
            + "Example use : \"editstep 1\" \n \n"
            + "## Edit Ingredient ## \n"
            + "Description : Edits the ingredients of a recipe in the list. \n"
            + "Format      : \"editingredient <insert recipe index>\"\n"
            + "Example use : \"editingredient 1\" \n \n"
            + "## View ## \n"
            + "Description : Displays the details of a particular recipe from the recipe list. "
            + "Requires either the recipe name or index as the input. \n"
            + "Format      : \"view <insert recipe name>\" or \"view <insert recipe index on list>\" \n"
            + "Example use : \"view Hotpot\" or \"view 1\" \n \n"
            + "## Find ## \n"
            + "Description : Finds all recipes names containing keywords inputted in the description. "
            + "Requires keywords as an input. \n"
            + "Format      : \"find <insert keywords>\" \n"
            + "Example use : \"find sushi\" \n \n"
            + "## Delete ## \n"
            + "Description : Removes a recipe from the recipe list, if it exists. "
            + "Requires the recipe index on the list as an input. \n"
            + "Format      : \"delete <insert recipe index number>\" \n"
            + "Example use : \"delete 10\" \n \n"
            + "## Clear ## \n"
            + "Description : Deletes all existing recipes that are stored on the recipe manager. \n"
            + "Format      : \"clear\" \n"
            + "Example use : \"clear\" \n \n"
            + "## Exit ## \n"
            + "Description : Ends the programme. See you next time! \n"
            + "Format      : \"exit\" \n"
            + "Example use : \"exit\" \n \n \n"
            + "Hope this list has been informational to you! \n";
    String EXIT_MESSAGE = "\nBye. Hope to see you again soon!";
    String EMPTY_LIST_MESSAGE = "\nMATE! Your list is empty!\n";
    String NO_MATCHING_FIND_RESULTS_MESSAGE = "\nSeems like you do not have any recipes matching: ";
    String CREATING_NEW_FILE_AND_DIRECTORY = "\nNew file and directory will be created.";
    String FIND_LIST_MESSAGE = "\nHere are the matching recipes in your list:\n";
    String DUDE_MAIN_ERROR = "\nError in my run method!"
            + "\nException occurred : ";
    String UNRECOGNIZABLE_ERROR = "\nOOPS!!! I'm sorry, but I don't know what that means"
            + "\nTry typing \"help\" to see the valid commands you can use!\n";
    String UNRECOGNIZABLE_COMMAND_ERROR = "\nYou have just inputted an unrecognizable command!"
            + "Please try \"help\" to see the valid commands you can use!\n";
    String MISSING_INPUTS_ERROR = "\nError in inputs!"
            + "\nException occurred: ";
    String PREFIX_EMPTY_LIMIT_LIST_ERROR = "\nError in finding index!"
            + "\nException occurred: Your list is either EMPTY or does not contain "
            + "recipes up to the index you inputted yet,\n"
            + "so you cannot use the ";
    String SUFFIX_EMPTY_LIMIT_LIST_ERROR = " command yet! Try filling up the list first!\n";

    String RECIPE_ADDING_DEFAULT_ERROR = "\nError in adding recipe!"
            + "\nException occurred: ";
    String RECIPE_DELETING_DEFAULT_ERROR = "\nError in deleting recipe!"
            + "\nException occurred: ";
    String RECIPE_FINDING_DEFAULT_ERROR = "\nError in finding recipe!"
            + "\nException occurred: ";
    String RECIPE_VIEWING_DEFAULT_ERROR = "\nError in viewing recipe!"
            + "\nException occurred: ";
    String RECIPE_EDITING_DEFAULT_ERROR = "\nError in editing recipe!"
            + "\nException occurred: ";
    String FORMAT_CONVERT_ERROR = "\nError in inputs!"
            + "\nException occurred: The number is too big to process or the inputs contains words when "
            + "it is supposed to be numbers."
            + "\nIf it is the former I can only process up to 2,147,483,647 for now... Please lower your expectations!"
            + "\nAs for the latter, please provide me with proper inputs!\n";
    String MISSING_DESCRIPTION_ERROR = "\nError in description of inputs!"
            + "\nException occurred: ";
    String PARSING_STRING_ERROR = "\nError in parsing string!"
            + "\nException occurred: ";
    String FILE_NOT_FOUND_ERROR = "\nError in finding file!"
            + "\nException occurred: ";
    String FILE_PARSE_READING_ERROR = "\nError in reading file!"
            + "\nException occurred: ";
    String FILE_IO_ERROR = "\nError in file IO!"
            + "\nException occurred: ";
    String FILE_LOADING_DEFAULT_ERROR = "\nError in loading file!"
            + "\nException occurred: ";
    String LINE = "__________________________________________________________";

    String ADD_COMMAND_FORMAT = "\"Format: \\\"add n/<insert recipe name>"
            + "i/<insert ingredients with \\\", \\\" separation> \"\n"
            + "\"t/<insert cuisine>\\\"\\n\"\n"
            + "\"Example use : \\\"add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese\\\" \\n \\n\"";
    String RECIPE_CLEARED_MESSAGE = "\nAll recipes have been cleared!";
    String INGREDIENT_LIST = "Ingredient list";
    String STEP_LIST = "Step list";
    String IMPORT_END_RECIPE = "End";
    String RECIPE_SAVED = "\nRecipe list saved!";
    String RECIPE_LOADED = "\nSaved recipes loaded!";
    String MISSING_NUM = "Please enter a valid number for the sum of steps!\n";
    String MISSING_FIND_KEYWORD = "\nFind is missing KEYWORDS!";
    String MATCHING_ITEMS = "\nHere are the matching items:";
    String NO_MATCHES = "\nNo dishes matches what you are looking for! :(";
    String ENTER_STEP_DESCRIPTION = "Enter the description of the step:";
    String STEP_EDIT_SUCCESS = "Step has been edited:";
    String STEP_VIEW_QUIT_KEYWORD = "quit";
    String RECIPE_NO_STEPS = "This recipe has no steps!";
    String STEPBYSTEP_PROMPT = "Do you want to view step-by-step?\nEnter \"yes\" if so";
    String STEPBYSTEP_EARLY_TERMINATION_PROMPT = "To exit recipe view, type \"quit\"\nElse, press enter to continue";
    String ENTER_INGREDIENT_DESCRIPTION = "Enter the description of the ingredient:";
    String INGREDIENT_EDIT_SUCCESS = "Ingredient has been edited:";
    String NO_INGREDIENTS_ERROR = "There are no ingredients to edit!";
    String NO_STEPS_ERROR = "There are no steps to edit!";
    String INPUT_STEPS_INDEX_EXCEEDED = "Input index exceeds the number of steps!";
    String INPUT_INGREDIENTS_INDEX_EXCEEDED = "Input index exceeds the number of ingredients!";
    String INVALID_STEP = "\nThe step you have entered is invalid!\n" +
            "Please enter a valid step below:";
    String INVALID_RANGE = "The index you have entered is invalid!\n\n" +
            "Valid Range: ";
    String DUPLICATE_RECIPE_NAMES_ERROR = "There appears to be duplicate recipe names that exist. \nPlease use the " +
            "FIND feature to get the specific recipe index to be viewed instead.\n";
    String NO_MATCHING_RECIPE_ERROR = "There are no matching recipes found.\n";
    String SAVE_SUCCESS = "\nData saved successfully!";
    String EDIT_TYPE_ERROR = "\nPlease enter a edit type (--s/--i) !\n";
    String EDIT_INGREDIENT_ERROR = "\nPlease keep format as edit --i INDEXOFRECIPE INDEXOFINGREDIENT i/NEWINGREDIENT\n";
    String INVALID_RECIPE_INDEX = "\nPlease enter a valid recipe index!";
    String EMPTY_RECIPE_LIST = "\nThere are no recipes in the list!";
    String INVALID_INGREDIENT_INDEX = "\nPlease enter a valid ingredient index!";
    String EMPTY_INGREDIENT_LIST = "There are no ingredients in the list!";
    String EDIT_STEP_ERROR = "\nPlease keep format as edit --s INDEXOFRECIPE INDEXOFSTEP s/NEWSTEP\n";
    String INVALID_STEP_INDEX = "\nPlease enter a valid step index!";
    String EMPTY_STEP_LIST = "There are no steps in the list!";
    String POS_INT = "\nPlease enter a valid index!";

    String INVALID_INPUT_VALID_RANGE_PREFIX = "Invalid Input! Valid Range: ";
    String MISSING_INGREDIENT_INPUT = "Ingredient cannot be empty!";
    String EMPTY_STRING = "";
    String[] FORBIDDEN_CHARS = {"!","@","#","$","%","&","*","(",")","+",",",
                                ".","/",":",";","<","=",">","?","[","]","^","_","`","{","|","}"};
    String SAVE_SEPARATOR = "#######";
    String INVALID_CHARACTERS_NAME_ERROR = "The NAME parameter contains invalid characters!\n";
    String INVALID_CHARACTERS_INGREDIENTS_ERROR = "The INGREDIENTS parameter contains invalid characters!\n";
    String INVALID_CHARACTERS_TAG_ERROR = "The TAG parameter contains invalid characters!\n";
    String INVALID_CHARACTERS_STEP_ERROR = "The SUM OF STEPS parameter contains invalid characters!\n";
    String OVERFLOW_NUMBER_ERROR = "you have entered a non-integer number!";
    String END_OF_RECIPE_STEPS = "\nEnd of Recipe Steps!";
    String RECIPE_ADDING_TO_DEFAULT_ERROR = "\nError in adding element to recipe!"
            + "\nException occurred: ";
    String RECIPE_DELETING_FROM_DEFAULT_ERROR = "\nError in deleting element from recipe!"
            + "\nException occurred: ";
    String INVALID_ADD_TO_RECIPE_DESCRIPTION = "OOPS!!!\n" +
            "Looks like the description of the command is invalid!\n" +
            "To add elements to the recipe, please follow the following layout:\n" +
            "\naddtorecipe --[s/i] id/[index] desc/[description of step/ingredient]";
    String INDEX_REQUEST = "Enter \"quit\" to cancel." +
            "\nEnter step index below:";
    String DUPLICATE_INGREDIENT_ERROR = "The ingredient you wish to add is already on " +
            "the ingredient list and hence cannot be added.";
    String DUPLICATE_STEP_ERROR = "The step you wish to add is already on " +
            "the step list and hence cannot be added.";
    String STEP_ADD_SUCCESS = "The step has been successfully added to the step list!";
    String INGREDIENT_ADD_SUCCESS = "The ingredient has been successfully added to the ingredient list!";
    String EMPTY_STEP_DESCRIPTION_MESSAGE = "Description of step cannot be empty!";
    String EMPTY_INGREDIENT_DESCRIPTION_MESSAGE = "Description of ingredient cannot be empty!";
    String STEP_QUIT_MESSAGE = "The step was not added to the step list!";
    String INVALID_DELETE_FROM_RECIPE_DESCRIPTION = "OOPS!!!\n" +
            "Looks like the description of the command is invalid!\n" +
            "To add elements to the recipe, please follow the following format:\n" +
            "\ndeletefromrecipe --[s/i] id/[index]";
    String INGREDIENT_DELETE_SUCCESS = "The ingredient has been successfully deleted from the ingredient list!";
    String INVALID_NUMBER_ERROR = "Index must be a number!";
    String STEP_DELETE_SUCCESS = "The step has been successfully deleted from the step list!";
    String EMPTY_STEP_ID_MESSAGE = "Index of step cannot be empty!";
    String EMPTY_INGREDIENT_ID_MESSAGE = "Index of ingredient cannot be empty!";
    String DEFAULT_CASE_WARNING = "Warning! Unhandled default of case statement reached";
    String DEFAULT_ELSE_CONDITION_WARNING = "Warning! Unhandled default \"else\" condition was reached";
    String EDIT_RECIPE_INGREDIENT_PROMPT = "Which ingredient do you want to edit?\nType 'quit' to exit the edit view";
    String EDIT_RECIPE_STEP_PROMPT = "Which step do you want to edit?\nType 'quit' to exit the edit view";
    String EDIT_NUMBERFORMATEXCEPTION_MESSAGE = "Error! Input should be a positive integer value!";
    String MIN_NUM_INGREDIENT_ERROR = "There must be at least one ingredient in the recipe!";
}
