public class SnakePart {
    private int boardX;
    private int boardY;
    private boolean head = true;
    private boolean tail = false;
    private int direction; // -1 = Left; -2 = Down; 1 = Right; 2 = Up
    private SnakePart follower = null;
    public SnakePart(int x, int y, boolean tail, int d){
        this.boardX = x;
        this.boardY = y;
        this.tail = tail;
        this.direction = d;
    }
    public boolean isHead(){
        return head;
    }
    public boolean isTail(){
        return tail;
    }
    public int getDirection(){
        return direction;
    }
    public int getBoardX(){
        return boardX;
    }
    public int getBoardY(){
        return boardY;
    }
    public SnakePart getFollower(){
        return follower;
    }
    public void isHead(boolean head) {
        this.head = head;
    }
    public void setDirection(int d) {
        this.direction = d;
    }
    public void setFollower(SnakePart follower) {
        this.follower = follower;
    }
    public void isTail(boolean tail) {
        this.tail = tail;
    }
    public void setBoardX(int x) {
        this.boardX = x;
    }
    public void setBoardY(int y) {
        this.boardY = y;
    }
}