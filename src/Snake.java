/**
 * Snake.java
 * This class represents the snake.
 * The Snake is essentially a linked list of SnakeParts. (A queue)
 */
public class Snake {
    private SnakePart head; // head of the snake
    private SnakePart tail; // tail of the snake
    private int justAteTurns = 0; // number of turns for which the snake doesn't grow
    private boolean alive = true; // if the snake is alive
    private int currentDirection; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private int nextDirection; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private int length = 0; // starting length of the snake
    /**
     * Constructor
     */
    public Snake(){
        System.out.println("Snake created!");
    }
    /**
     * Add to snake
     * @param sp
     */
    public void addToSnake(SnakePart sp){
        if (sp.getHead()){
            this.setHead(sp);
        }
        if(sp.getTail()){
            this.setTail(sp);
        }
        this.length++;
    }
    /**
     * these two methods decrease a variable by 1
     */
    public void decrementLength(){
        this.length--;
    }
    public void decrementJustAteTurns(){
        this.justAteTurns--;
    }
    /**
     * Set the head of the snake to the given SnakePart and sets the given snakePart to be a head
     */
    public void setHead(SnakePart sp){
        sp.setHead(true);
        this.head = sp;
    }
    /**
     * Set the tail of the snake to the given SnakePart and sets the given snakePart to be a tail
     */
    public void addToJustAteTurns(int i){
        this.justAteTurns += i;
    }
    /**
     * These are methods that return a variable.
     */
    public int getJustAteTurns(){
        return(this.justAteTurns);
    }
    public boolean getAlive(){
        return this.alive;
    }
    public SnakePart getTail() {
        return tail;
    }
    public SnakePart getHead() {
        return head;
    }
    public int getCurrentDirection() { // sets the length of the snake
        return currentDirection;
    }
    public int getNextDirection() { // sets the length of the snake
        return nextDirection;
    }
    public int getLength() { // gets the length of the snake
        return this.length;
    }
    /**
     * These are methods that set a variable to a given parameter.
     */
    public void setCurrentDirection(int d) { // sets the length of the snake
        this.currentDirection = d;
    }
    public void setNextDirection(int d) { // sets the length of the snake
        this.nextDirection = d;
    }
    public void setTail(SnakePart t){
        this.tail = t;
    }
    public void setAlive(boolean a){
        this.alive = a;
    }
    public void setJustAteTurns(int j){
        this.justAteTurns = j;
    }

}
