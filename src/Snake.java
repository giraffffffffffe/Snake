public class Snake {
    private SnakePart head;
    private SnakePart tail;
    private boolean alive = true; // if the snake is alive
    private int currentDirection; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private int length = 0; // starting length of the snake
    public Snake(SnakePart s){
        this.tail = s; // set the tail of the snake
        this.head = s; // set the head of the snake
        length++; // increase the length of the snake
        s.isHead(true); // set the SnakePart to be the head
        s.isTail(true); // set the SnakePart to be the tail
        System.out.println("Snake created!");
    }
    public void setCurrentDirection(int d) { // sets the length of the snake
        this.currentDirection = d;
    }
    public void setHead(SnakePart s){ // sets the head of the snake
        s.isHead(true);
        this.head = s;
    }
    public void setTail(SnakePart t){
        this.tail = t;
    } // sets the tail of the snake
    public void setAlive(boolean a){
        this.alive = a;
    } // sets the snake to be alive or dead
    public boolean getAlive(){
        return this.alive;
    } // returns if the snake is alive
    public SnakePart getTail() {
        return tail;
    } // gets the tail of the snake
    public SnakePart getHead() {
        return head;
    } // gets the head of the snake
    public int getCurrentDirection() { // sets the length of the snake
        return currentDirection;
    } // gets the current direction of the snake
    public int getLength() { // gets the length of the snake
        return this.length;
    } // gets the length of the snake
//    public SnakePart getSnakePart(int x,int y){ // gets the SnakePart at the given coordinates
//        SnakePart s = this.tail; // start at the tail
//        while (s!=null){ // while there are more SnakeParts
//            if(s.getBoardX() == x && s.getBoardY() == y){ // if the SnakePart is at the given coordinates
//                return s; // return the SnakePart
//            } else{ // if the SnakePart is not at the given coordinates
//                s = s.getFollower(); // move to the next SnakePart
//            }
//        }
//        return s; // this shouldn't happen
//    }
    public void addToSnake(int x, int y, boolean tail, int d){ // adds a SnakePart to the snake
        System.out.println("Adding to snake");
        System.out.println(this.getHead());
        SnakePart s = new SnakePart(x,y,tail,d); // create a new SnakePart
        this.getHead().setFollower(s); // set the new SnakePart as the follower of the current head
        this.getHead().isHead(false); // set the current head to not be the head
        this.setHead(s); // set the new SnakePart as the head
        System.out.println(this.getHead());
        this.length++; // increase the length of the snake
    }
}
