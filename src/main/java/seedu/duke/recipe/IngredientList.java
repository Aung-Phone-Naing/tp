package seedu.duke.recipe;

import seedu.duke.exceptions.InvalidIndexRangeException;
import seedu.duke.exceptions.ListEmptyException;
import seedu.duke.ui.IntLib;
import seedu.duke.ui.StringLib;

import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> list;

    protected int currIngredientNumber;

    /**
     * Class constructor without arguments.
     */
    public IngredientList() {
        list = new ArrayList<>();
        currIngredientNumber = 0;
    }

    /**
     * Class constructor with argument. An existing array list of ingredients
     * is used as the argument
     *
     * @param ingredientList - list of all ingredients in the recipe.
     */
    public IngredientList(ArrayList<Ingredient> ingredientList) {
        list = ingredientList;
        currIngredientNumber = ingredientList.size();
    }

    public int getCurrIngredientNumber() {
        return currIngredientNumber;
    }

    /**
     * Ingredient getter method
     * @param ingredientIndex index of ingredient in ingredientList
     * @return Ingredient object stored at ingredientIndex
     * @throws Exception if list does not contain specified index
     */
    public Ingredient getIngredient(int ingredientIndex) throws Exception{
        try {
            return list.get(ingredientIndex);
        } catch (IndexOutOfBoundsException e) {
            if (currIngredientNumber == 0) {
                throw new ListEmptyException();
            } else {
                // add 1 to display in 1-based
                throw new InvalidIndexRangeException(1,currIngredientNumber+1);
            }
        }
    }
    /**
     * Adds a new ingredient to the list.
     *
     * @param item - the ingredient to be added to the list.
     * @param ingredientIndex - position to be added. Current Ingredient at this position is
     *              shifted towards the back.
     */
    public void addIngredient(Ingredient item, int ingredientIndex) {
        list.add(ingredientIndex, item);
        currIngredientNumber++;
        assert (currIngredientNumber == list.size());
    }

    /**
     * Removes an ingredient from the list.
     *
     * @param ingredientIndex - the index of the ingredient to be removed from the list.
     */
    public void removeIngredient(int ingredientIndex) {
        list.remove(ingredientIndex-1);
        currIngredientNumber--;
        assert (currIngredientNumber == list.size());
    }
    public void showList() {
        System.out.println("There are " + currIngredientNumber + " ingredients in the list:");
        for (int i = 0; i < currIngredientNumber; i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName());
        }
    }
    public ArrayList<Ingredient> getList() {
        return list;
    }

    public boolean isIndexWithinRange(int ingredientIndex) throws Exception{
        if (currIngredientNumber == 0) {
            throw new ListEmptyException();
        } else if (ingredientIndex < 0 || ingredientIndex >= currIngredientNumber) {
            throw new InvalidIndexRangeException(IntLib.NONEMPTY_START_NUMBER, currIngredientNumber);
        }
        return true;
    }
    public void editIngredient(int ingredientIndex, String description) throws Exception{
        Ingredient newIngredient = new Ingredient(description);
        if (isIndexWithinRange(ingredientIndex)) {
            list.set(ingredientIndex, newIngredient);
            System.out.println(StringLib.INGREDIENT_EDIT_SUCCESS);
            System.out.print((ingredientIndex + 1) + ". ");
            System.out.println(list.get(ingredientIndex).getName());
        }
    }
    public void editIngredient(String ingredientDescription, int ingredientIndex) {
        Ingredient newIngredient = new Ingredient(ingredientDescription);
        list.set(ingredientIndex, newIngredient);
        System.out.println(StringLib.INGREDIENT_EDIT_SUCCESS);
        System.out.print((ingredientIndex + 1) + ". ");
        System.out.println(list.get(ingredientIndex).getName());
    }
    public boolean isEmpty() {
        return (getCurrIngredientNumber() == 0);
    }
}
