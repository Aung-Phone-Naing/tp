package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddToRecipeTest {
    ByteArrayOutputStream output;
    PrintStream console;
    @BeforeEach
    public void setup() throws Exception {
        RecipeList.createRecipeList();
        RecipeList.addNewRecipe(new
                Recipe("Spaghetti", "Italian", new IngredientList(), new StepList()));
        output = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
        RecipeList.clearRecipeList();
    }
}
