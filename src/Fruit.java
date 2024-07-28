/**
 *  The `Fruit` class represents a fruit object with coordinates on a board, a type, and an eaten status.
 */
public class Fruit {
    public String[] fruits = {"Apple", "Orange", "Plum",}; // possible types of fruit
    private final int X; // x coordinate of the fruit on the board
    private final int Y; // y coordinate of the fruit on the board
    private final String FRUIT_TYPE; // type of fruit

    /**
     * Constructs a new `Fruit` object with the specified type index and coordinates.
     * @param n
     * @param fx
     * @param fy
     */
    public Fruit(int n, int fx, int fy){ // constructor
        this.FRUIT_TYPE = fruits[n]; //
        this.X = fx;
        this.Y = fy;
    }

    /**
     * These are methods that return a variable
     * @return
     */
    public int getX() { // gets the x coordinates of the fruit
        return X;
    }
    public int getY() { // gets the y coordinates of the fruit
        return Y;
    }
    public String getType() { // gets the type of fruit
        return FRUIT_TYPE;
    }
}