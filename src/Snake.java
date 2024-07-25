/**
 * Snake.java
 */
public class Snake {
    private SnakePart head;
    private SnakePart tail;
    private boolean justAte = false; // if the snake has just eaten
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
     * Get current direction of the snake
     * @param d
     */
    public void setCurrentDirection(int d) { // sets the length of the snake
        this.currentDirection = d;
    }

    /**
     * Set next direction of the snake
     * @param d
     */
    public void setNextDirection(int d) { // sets the length of the snake
        this.nextDirection = d;
    }

    /**
     * Set head of the snake
     * @param sp
     */
    public void setHead(SnakePart sp){ // sets the head of the snake
        sp.setHead(true);
        this.head = sp;
    }

    /**
     * Set tail of the snake
     * @param t
     */
    public void setTail(SnakePart t){
        this.tail = t;
    }

    /**
     * Set alive status of the snake
     * @param a
     */
    public void setAlive(boolean a){
        this.alive = a;
    }

    /**
     * Decrement length of the snake
     */
    public void decrementLength(){
        this.length--;
    }
    /**
     * Set just ate status of the snake
     * @param j
     */
    public void setJustAte(boolean j){
        System.out.println("Just ate set to " + j);
        this.justAte = j;
        System.out.println(this.justAte);
    }
    /**
     * Get just ate status of the snake
     * @return
     */
    public boolean getJustAte(){
        return(this.justAte);
    }

    /**
     * Get alive status of the snake
     * @return
     */
    public boolean getAlive(){
        return this.alive;
    }
    /**
     * Get tail of the snake
     * @return
     */
    public SnakePart getTail() {
        return tail;
    }
    /**
     * Get head of the snake
     * @return
     */
    public SnakePart getHead() {
        return head;
    }
    /**
     * Get current direction of the snake
     * @return
     */
    public int getCurrentDirection() { // sets the length of the snake
        return currentDirection;
    }
    /**
     * Get next direction of the snake
     * @return
     */
    public int getNextDirection() { // sets the length of the snake
        return nextDirection;
    }

    /**
     * Get length of the snake
     * @return
     */
    public int getLength() { // gets the length of the snake
        return this.length;
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
}
