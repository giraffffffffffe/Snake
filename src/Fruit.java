import java.awt.*;

public class Fruit {
    public String[] fruits = {"Apple", "Orange", "Kiwifruit", "Plum",}; // possible types of fruit
    private int x; // x coordinate of the fruit
    private int y; // y coordinate of the fruit
    private String fruitType; // type of fruit
    private boolean eaten = false; // if the fruit has been eaten
    public Fruit(int n, int fx, int fy){ // constructor
        System.out.println("Fruit created!");
        String fT = fruits[n]; // sets the type of fruit
        this.fruitType = fT;
        this.x = fx;
        this.y = fy;
    }
    public void eaten() { // sets the fruit to eaten
        this.eaten = true;
    }
    public int getX() { // gets the x coordinates of the fruit
        return x;
    }
    public int getY() { // gets the y coordinates of the fruit
        return y;
    }
    public String getType() { // gets the type of fruit
        return fruitType;
    }
}
