public class Snake {
    private int xHead = 300; // starting x position of the snake's head
    private int yHead = 300; // starting y position of the snake's head
    private int length = 4; // starting length of the snake
    public Snake(){
        System.out.println("Snake created!");
    }

    public void setLength(int length) { // sets the length of the snake
        this.length = length;
    }

    public int getLength() { // gets the length of the snake
        return this.length;
    }
}
