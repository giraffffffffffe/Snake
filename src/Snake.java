public class Snake {
    private SnakePart head;
    private SnakePart tail;
    private boolean justAte = false; // if the snake has just eaten
    private boolean alive = true; // if the snake is alive
    private int currentDirection; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private int nextDirection; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private int length = 0; // starting length of the snake
    public Snake(){
        System.out.println("Snake created!");
    }
    public void setCurrentDirection(int d) { // sets the length of the snake
        this.currentDirection = d;
    }
    public void setNextDirection(int d) { // sets the length of the snake
        this.nextDirection = d;
    }
    public void setHead(SnakePart sp){ // sets the head of the snake
        sp.setHead(true);
        this.head = sp;
    }
    public void setTail(SnakePart t){
        this.tail = t;
    } // sets the tail of the snake
    public void setAlive(boolean a){
        this.alive = a;
    } // sets the snake to be alive or dead
    public void decrementLength(){
        this.length--;
    }
    public void setJustAte(boolean j){
        System.out.println("Just ate set to " + j);
        this.justAte = j;
        System.out.println(this.justAte);
    } // sets if the snake has just eaten
    public boolean getJustAte(){
        return(this.justAte);
    } // gets if the snake has just eaten
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
    public int getNextDirection() { // sets the length of the snake
        return nextDirection;
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
    // public void addToSnake(int x, int y, boolean tail, boolean head, int d){ // adds a SnakePart to the snake
    //    System.out.println("Adding to snake");
    //    System.out.println(this.getHead());
    //    SnakePart s = new SnakePart(x,y,tail,d); // create a new SnakePart
    //    this.getHead().setFollower(s); // set the new SnakePart as the follower of the current head
    //    this.getHead().isHead(false); // set the current head to not be the head
    //    this.setHead(s); // set the new SnakePart as the head
    //    System.out.println(this.getHead());
    //    this.length++; // increase the length of the snake
    // }
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
