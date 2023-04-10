package seedu.duke.parser;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.InvalidInputCharactersException;

public class AddRecipeParserTest {
    private static final String RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP = "\nRecipe is missing the \"NAME\" "
            + "or \"INGREDIENTS\" or \"TAG\" or \"SUM of the STEPs," +
            "\nor there is more than one\n" +
            "\"NAME\" or \"INGREDIENTS\" or \"TAG\" or \"SUM of the STEPs\"!\n";
    private static final String RECIPE_WRONG_LEADING_STRING = "Recipe contains the leading string!\n";
    private static final String RECIPE_MISSING_NAME = "Recipe is missing \"NAME\"!\n";
    private static final String RECIPE_MISSING_INGREDIENTS = "Recipe is missing \"INGREDIENTS\"!\n";
    private static final String RECIPE_MISSING_TAG = "Recipe is missing \"TAG\"!\n";
    private static final String RECIPE_MISSING_STEP = "Recipe is missing \"SUM of the STEPs\"!\n";
    private static final String INVALID_CHARACTERS_NAME_ERROR = "The NAME parameter contains invalid characters!\n";
    private static final String INVALID_CHARACTERS_INGREDIENTS_ERROR = "The INGREDIENTS parameter contains invalid " +
            "characters!\n";
    private static final String INVALID_CHARACTERS_TAG_ERROR = "The TAG parameter contains invalid characters!\n";
    private static final String INVALID_CHARACTERS_STEP_ERROR = "The SUM OF STEPS parameter contains " +
            "invalid characters!\n";
    /**
     * Test for incomplete name.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteNameA() {
        try {
            Parser.parseRecipe("n/ i/Pasta, Tomato Sauce, Cheese t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_NAME, e.getMessage());
        }
    }

    /**
     * Test for incomplete name.(trim test)
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteNameB() {
        try {
            Parser.parseRecipe("n/    i/Pasta, Tomato Sauce, Cheese t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_NAME, e.getMessage());
        }
    }

    /**
     * Test for incomplete ingredients.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteIngredientsA() {
        try {
            Parser.parseRecipe("n/Spaghetti i/ t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_INGREDIENTS, e.getMessage());
        }
    }

    /**
     * Test for incomplete ingredients.(trim test)
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteIngredientsB() {
        try {
            Parser.parseRecipe("n/Spaghetti i/    t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_INGREDIENTS, e.getMessage());
        }
    }

    /**
     * Test for incomplete tag.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteTagA() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/ s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_TAG, e.getMessage());
        }
    }

    /**
     * Test for incomplete tag.(trim test)
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteTagB() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/    s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_TAG, e.getMessage());
        }
    }

    /**
     * Test for incomplete sum of steps.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteSumOfStepsA() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian s/");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_STEP, e.getMessage());
        }
    }

    /**
     * Test for incomplete sum of steps.(trim test)
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionIncompleteSumOfStepsB() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian s/    ");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_MISSING_STEP, e.getMessage());
        }
    }

    /**
     * Test for no name.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoName() {
        try {
            Parser.parseRecipe("i/Pasta, Tomato Sauce, Cheese t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for no ingredients.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoIngredients() {
        try {
            Parser.parseRecipe("n/Spaghetti t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for no tag.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoTag() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for no sum of steps.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoSumOfSteps() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for too many name arguments.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionTooManyName() {
        try {
            Parser.parseRecipe("n/Spaghetti n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for too many ingredients arguments.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionTooManyIngredients() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, i/Tomato Sauce, Cheese t/Italian s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for too many tag arguments.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionTooManyTag() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian t/Chinese s/0");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for too many sum of steps arguments.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionTooManySumOfSteps() {
        try {
            Parser.parseRecipe("n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian s/0 s/1");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for no meaning input.
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoMeaningInputA() {
        try {
            Parser.parseRecipe("");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for no meaning input.(with random string)
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoMeaningInputB() {
        try {
            Parser.parseRecipe("asdfasdfa");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP, e.getMessage());
        }
    }

    /**
     * Test for no meaning input.(with random string)
     * @throws IncompleteInputException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionNoMeaningInputC() {
        try {
            Parser.parseRecipe("asdfasdfa    n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/4");
        } catch (Exception e) {
            assertTrue(e instanceof IncompleteInputException);
            Assertions.assertEquals(RECIPE_WRONG_LEADING_STRING, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in name. (With special characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersNameA() {
        try {
            Parser.parseRecipe("n/H$*tpot i/Beef, Potatoes, Carrots t/Chinese s/4");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_NAME_ERROR, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in name. (With numerical characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersNameB() {
        try {
            Parser.parseRecipe("n/123 i/Beef, Potatoes, Carrots t/Chinese s/4");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_NAME_ERROR, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in ingredient. (With special characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersIngredientA() {
        try {
            Parser.parseRecipe("n/Hotpot i//Beef, Potatoes%(#, Carrots t/Chinese s/4");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_INGREDIENTS_ERROR, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in ingredient. (With numerical characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersIngredientB() {
        try {
            Parser.parseRecipe("n/Hotpot i/348985, 343 t/Chinese s/4");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_INGREDIENTS_ERROR, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in tag. (With special characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersTagA() {
        try {
            Parser.parseRecipe("n/Hotpot i/Beef, Potatoes, Carrots t/$(*%Chinese s/4");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_TAG_ERROR, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in tag. (With numerical characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersTagB() {
        try {
            Parser.parseRecipe("n/Hotpot i/Beef, Potatoes, Carrots t/873487 s/4");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_TAG_ERROR, e.getMessage());
        }
    }

    /**
     * Test for invalid characters in sum of steps. (With alphabetical characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersSumOfStepsA() {
        try {
            Parser.parseRecipe("n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/Asde");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_STEP_ERROR, e.getMessage());
        }
    }
    /**
     * Test for invalid characters in sum of steps. (With special characters only)
     * @throws InvalidInputCharactersException if input is incomplete.
     */
    @Test
    void parseRecipeExceptionInvalidCharactersSumOfStepsB() {
        try {
            Parser.parseRecipe("n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/@#");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidInputCharactersException);
            Assertions.assertEquals(INVALID_CHARACTERS_STEP_ERROR, e.getMessage());
        }
    }
}
