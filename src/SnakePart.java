/**
 * SnakePart.java
 *
 * This class represents a part of the snake. It has a position on the board, a direction, and a reference to the next part of the snake.
 *
 *
 */
class SnakePart {
    private int boardX; // x coordinate on the board
    private int boardY; // y coordinate on the board
    private boolean head = true; // if the SnakePart is the head
    private boolean tail = false; // if the SnakePart is the tail
    private int direction; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private SnakePart leader = null; // head is null
    private SnakePart follower = null; // tail is null
    private  int lifeSpan;
    public SnakePart(int x, int y, boolean tail, boolean head, int d){ // constructor
        System.out.println("new SnakePart " + this);
        this.boardX = x;
        this.boardY = y;
        this.tail = tail;
        this.head = head;
        this.direction = d;
    }
    public boolean getHead(){ // returns if the SnakePart is the head
        return head;
    }
    public boolean getTail(){ // returns if the SnakePart is the tail
        return tail;
    }
    public void decrementLifeSpan(){
        this.lifeSpan--;
    }
    public int getDirection(){ // returns the direction of the SnakePart
        return direction;
    }
    public int getBoardX(){ // returns the x coordinate of the SnakePart
        return boardX;
    }
    public int getBoardY(){ // returns the y coordinate of the SnakePart
        return boardY;
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
    public void setDirection(int d) { // sets the direction of the SnakePart
        this.direction = d;
    }
    public void setFollower(SnakePart follower) { // sets the SnakePart that follows this one
        this.follower = follower;
    }
    public void setTail(boolean tail) { // sets whether the SnakePart is the tail
        this.tail = tail;
    }
    public void setBoardX(int x) { // sets the x coordinate of the SnakePart on the board
        this.boardX = x;
    }
    public void setBoardY(int y) { // sets the y coordinate of the SnakePart on the board
        this.boardY = y;
    }
}