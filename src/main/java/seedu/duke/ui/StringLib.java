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
    String WELCOME_MESSAGE = "\nHELLLOO there! I am\n "
            + LOGO + '\n'
            + "Your personal recipes assistant!\n"
            + "What can I do for you today?\n\n"
            + "You can start by adding recipes to a recipe list that I can generate,"
            + " simply follow the format below:\n\n"
            + "Add recipe : \"add n/<insert recipe name> i/<insert ingredients with \", \" separation> "
            + "t/<insert cuisine>\"\n\n"
            + "If you wish to view the full list of commands, simply type \"help\"!\n";
    String HELP = "\nHelp is here! You may find the list of commands below useful.\n\n\n"
            + "COMMANDS LIST:\n\n\n"
            + "## List ##\n"
            + "Description : Displays all recipes in the list.\n"
            + "Format      : \"list\"\n"
            + "Example use : \"list\"\n\n"
            + "## Add ##\n"
            + "Description : Adds a new recipe to the list.\n"
            + "Format      : \"add n/<insert recipe name> i/<insert ingredients with \", \" separation> "
            + "t/<insert cuisine>\"\n"
            + "Example use : \"add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese\"\n\n"
            + "## Edit ##\n"
            + "Description : Edits a recipe in the list.\n"
            + "Format      : \"edit n/<insert recipe name>\"\n"
            + "Example use : \"edit n/Hotpot\"\n\n"
            + "## View ##\n"
            + "Description : Displays the details of a particular recipe from the recipe list. "
            + "Requires either the recipe name or index as the input.\n"
            + "Format      : \"view n/<insert recipe name>\" or \"view <insert recipe index on list>\"\n"
            + "Example use : \"view n/Hotpot\" or \"view 1\"\n\n"
            + "## Find ##\n"
            + "Description : Finds all recipes names containing keywords inputted in the description. "
            + "Requires keywords as an input.\n"
            + "Format      : \"find <insert keywords>\"\n"
            + "Example use : \"find sushi\"\n\n"
            + "## Delete ##\n"
            + "Description : Removes a recipe from the recipe list, if it exists. "
            + "Requires the recipe index on the list as an input.\n"
            + "Format      : \"delete <insert recipe index number>\"\n"
            + "Example use : \"delete 10\"\n\n"
            + "## Clear ##\n"
            + "Description : Deletes all existing recipes that are stored on the recipe manager.\n"
            + "Format      : \"clear\"\n"
            + "Example use : \"clear\"\n\n"
            + "## Exit ##\n"
            + "Description : Ends the programme. See you next time!\n"
            + "Format      : \"exit\"\n"
            + "Example use : \"exit\"\n\n\n"
            + "Hope this list has been informational to you!\n";
    String EXIT_MESSAGE = "\nBye. Hope to see you again soon!\n";
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

}
