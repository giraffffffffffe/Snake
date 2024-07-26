/**
 * Box class that represents a box in the grid
 * A box can have a fruit, a snake, a wall, or be empty
 * A box can only have one of these attributes at a time
 */
public class Box {
    private boolean fruit = false; // if the box has a fruit
    private boolean snake = false; // if the box has a snake
    private boolean wall = false; // if the box is a wall

    /**
     * Constructor (Currently empty)
     */
    public Box() {
    }
    /**
     * These are methods that return a variable
     * All variables are about what is inside the box
     */
    public boolean getFruit() {
        return fruit;
    }
    public boolean getSnake() {
        return snake;
    }
    public boolean getWall() {
        return wall;
    }
    /**
     * These are methods that set a variable to a given parameter.
     * All variables are about what is inside the box
     */
    public void setFruit(boolean fruit) {
        this.fruit = fruit;
    }
    public void setSnake(boolean snake) {
        this.snake = snake;
    }
    public void setWall(boolean wall) {
        this.wall = wall;
    }
}