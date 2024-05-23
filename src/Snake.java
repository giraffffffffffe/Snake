public class Snake {
    private SnakePart head;
    private SnakePart tail;
    private int currentDirection; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private int length = 0; // starting length of the snake
    public Snake(SnakePart s){
        this.tail = s;
        this.head = s;
        length++;
        s.isHead(true);
        s.isTail(true);
        System.out.println("Snake created!");
    }
    public void setCurrentDirection(int d) { // sets the length of the snake
        this.currentDirection = d;
    }
    public void setHead(SnakePart s){
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
        SnakePart s = new SnakePart(x,y,tail,d);
        this.getHead().setFollower(s);
        this.getHead().isHead(false);
        this.setHead(s);
        this.length++;
    }
}
