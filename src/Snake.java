public class Snake {
    private SnakePart head;
    private SnakePart tail;
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
    public void setHead(SnakePart s){
        System.out.println("setHead");
        this.head = s;
    }
    public void setTail(SnakePart t){
        this.tail = t;
    }
    public SnakePart getTail() {
        return tail;
    }
    public SnakePart getHead() {
        return tail;
    }
    public int getCurrentDirection() { // sets the length of the snake
        return currentDirection;
    }
    public int getLength() { // gets the length of the snake
        return this.length;
    }
    public SnakePart getSnakePart(int x,int y){
        SnakePart s = this.tail;
        while (s!=null){
            if(s.getBoardX() == x && s.getBoardY() == y){
                return s;
            } else{
                s = s.getFollower();
            }
        }
        return s; // this shouldn't happen
    }
    public void addToSnake(int x, int y, boolean tail, int d){
        SnakePart s = new SnakePart(x,y,tail,d); // create a new SnakePart
        System.out.println();
        System.out.println("s: "+s);
        this.getHead().setFollower(s); // set the new SnakePart as the follower of the current head
        System.out.println(this.getHead());
        this.getHead().isHead(false); // set the current head to not be the head
        s.isHead(true); // set the new SnakePart to be the head
        this.setHead(s); // set the new SnakePart as the head
        System.out.println(this.getHead());
        this.length++; // increase the length of the snake
        System.out.println("snake length: "+this.length);
        System.out.println();
    }
}
