public class Box {
    private boolean fruit = false; // if the box has a fruit
    private boolean snake = false; // if the box has a snake
    private boolean wall = false; // if the box is a wall

    public Box(){
    }
    public void setFruit(boolean fruit) { // sets the box to have a fruit
        this.fruit = fruit;
    }
    public void setSnake(boolean snake) { // sets the box to have a snake
        this.snake = snake;
    }
    public void setWall(boolean wall) { // sets the box to be a wall
        this.wall = wall;
    }
    public boolean isFruit() { // returns if the box has a fruit
        return fruit;
    }
    public boolean isSnake() { // returns if the box has a snake
        return snake;
    }
    public boolean isWall() { // returns if the box is a wall
        return wall;
    }
}