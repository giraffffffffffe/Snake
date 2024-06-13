public class Box {
    private boolean fruit = false; // if the box has a fruit
    private boolean snake = false; // if the box has a snake
    private boolean wall = false; // if the box is a wall
    private boolean redraw = false; // if the box needs to be redrawn
    public Box(){
    }
    public void setFruit(boolean fruit) { // sets the box to have a fruit
        this.fruit = fruit;
    } // sets the box to have a fruit
    public void setSnake(boolean snake) { // sets the box to have a snake
        this.snake = snake;
    } // sets the box to have a snake
    public void setWall(boolean wall) { // sets the box to be a wall
        this.wall = wall;
    }
    public void setRedraw(boolean redraw) { // sets the box to be redrawn
        this.redraw = redraw;
    } // sets the box to be redrawn
    public boolean isFruit() { // returns if the box has a fruit
        return fruit;
    } // returns if the box has a fruit
    public boolean isSnake() { // returns if the box has a snake
        return snake;
    } // returns if the box has a snake
    public boolean isWall() { // returns if the box is a wall
        return wall;
    } // returns if the box is a wall
    public boolean getRedraw() { // returns if the box needs to be redrawn
        return redraw;
    } // returns if the box needs to be redrawn
}