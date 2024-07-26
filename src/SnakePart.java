/**
 * SnakePart.java
 *
 * This class represents a part of the snake. It has a position on the board, a direction, and a reference to the next part of the snake.
 *
 *
 */
class SnakePart {
    private final int BOARD_X; // x coordinate on the board
    private final int BOARD_Y; // y coordinate on the board
    private boolean head; // if the SnakePart is the head
    private boolean tail; // if the SnakePart is the tail
    private final int DIRECTION; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private SnakePart leader = null; // head is null
    private SnakePart follower = null; // tail is null
    private  int lifeSpan;
    /**
     * Creates a new SnakePart with the given position, tail status, head status, and direction.
     * @param x
     * @param y
     * @param tail
     * @param head
     * @param d
     */
    public SnakePart(int x, int y, boolean tail, boolean head, int d){ // constructor
        System.out.println("new SnakePart " + this);
        this.BOARD_X = x;
        this.BOARD_Y = y;
        this.tail = tail;
        this.head = head;
        this.DIRECTION = d;
    }
    /**
     * Decreases lifespan by 1
     */
    public void decrementLifeSpan(){
        this.lifeSpan--;
    }
    public int getDIRECTION(){ // returns the direction of the SnakePart
        return DIRECTION;
    }
    /**
     * These are methods that return a variable or set a variable to a given parameter.
     */
    public boolean getHead(){
        return head;
    }
    public boolean getTail(){
        return tail;
    }
    public int getBOARD_X(){ // returns the x coordinate of the SnakePart
        return BOARD_X;
    }
    public int getBOARD_Y(){ // returns the y coordinate of the SnakePart
        return BOARD_Y;
    }
    public SnakePart getFollower(){ // returns the SnakePart that follows this one
        return follower;
    }
    public int getLifeSpan(){
        return lifeSpan;
    }
    public SnakePart getLeader(){
        return leader;
    }
    public void setLeader(SnakePart l){
        this.leader = l;
    }
    public void setLifeSpan(int l){
        this.lifeSpan = l;
    }
    public void setHead(boolean head) { // sets the SnakePart to be the head
        this.head = head;
    }
    public void setFollower(SnakePart follower) { // sets the SnakePart that follows this one
        this.follower = follower;
    }
    public void setTail(boolean tail) { // sets whether the SnakePart is the tail
        this.tail = tail;
    }
}