import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class Main extends JFrame implements ActionListener{
    public Color BACKGROUND_COLOR = new Color(84,170,89); //colour that the background is
    private final String[] MENU_NAMES = {"Help", "Configure", "Actions"};
    private final String[] MENU0_OPTIONS = {"Instructions"}; // options in Help menu
    private final String[] MENU1_OPTIONS = {"Keys", "Snake Speed"}; // options in configure menu
    private final String[] MENU2_OPTIONS = {"Pause"}; // options in actions menu
    private box[][] board = new box[50][50]; // creates a 2D array of boxes
    private int windWidth = 500; // initial width of window
    private int windHeight = 500; // initial height of window

    JMenuBar menuBar; // creates a menubar
    JMenuItem menuItem; // creates a menu item
    Canvas myGraphic; // canvas that is used for the graphics
    JPanel panel = new JPanel(); // initialises JPanel
    Scanner kb = new Scanner(System.in); // initialises keyboard
    Fruit f = new Fruit(randomNumber(0,3), 100, 100); // initialises fruit
    public static void main(String[] args) { // starts program
        new Main();
    }

    public Main(){ // runs initially
        this.setTitle("Snake!"); // sets title of Window to "Snake!"
        this.getContentPane().setBackground(BACKGROUND_COLOR); // sets the background colour of the window
        this.getContentPane().setPreferredSize(new Dimension(windWidth,windHeight)); // sets the size of the window
        this.setResizable(false); // stops the user from resizing the window
        this.panel.setPreferredSize(new Dimension(windWidth,windHeight));
        Snake s = new Snake(); // initialises Snake

        this.menuBar = new JMenuBar(); // makes a new menubar
        this.setJMenuBar(menuBar); // sets the menubar to the window
        createMenu(MENU0_OPTIONS, 0); // creates the menus
        createMenu(MENU1_OPTIONS, 1);
        createMenu(MENU2_OPTIONS, 2);

        this.myGraphic = new Canvas(); // initialises graphic
        this.panel.add(myGraphic); // adds canvas to panel

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // stops program when window is closed
        this.pack(); // makes the window
        this.toFront(); // puts this window on top of other windows the user might have open.
        this.setLocationRelativeTo(null); // opens the window in the middle of the screen
        this.setVisible(true); // makes the window visible

    }
    private void createMenu(String[] menuOptions, int numMenus) { // creates menus in window
        JMenu menu = new JMenu(MENU_NAMES[numMenus]); // creates a new menu
        menuBar.add(menu); // adds the menu to the menubar

        for (String o : menuOptions){ // for each option in the menu
            menuItem = new JMenuItem(o); // creates a new menu item
            switch (o){ // sets the accelerator for the menu item
                case "Instructions" -> menuItem.setAccelerator(KeyStroke.getKeyStroke('i'));
                case "Pause" -> menuItem.setAccelerator(KeyStroke.getKeyStroke('p'));
            }
            menuItem.addActionListener(this); // adds an action listener to the menu item
            menu.add(menuItem); // adds the menu item to the menu
        }
    }
    public void actionPerformed(ActionEvent e) { // when a menu item is clicked
        String command = e.getActionCommand(); // gets the command of the menu item
        switch (command){ // does something based on the command
            case "Instructions" -> {
                JOptionPane.showMessageDialog(this, "Use the arrow keys to move the snake. Eat the food to grow. Don't hit the walls or yourself.");
            }
            case "Pause" -> {
                JOptionPane.showMessageDialog(this, "Game is paused. Press 'p' to unpause.");
                pause(); // pauses the game
            }
        }
    }

    private void pause() { // pauses the game
        while (true){ // infinite loop
            if (kb.nextLine().equals("p")){ // if the user types 'p'
                break; // breaks the loop
            }
        }
    }

    private void fruitEaten(Snake s) { // when the snake eats a fruit
        s.setLength(s.getLength() + 1); // increases the length of the snake
        f.eaten(); // sets the fruit to 'eaten';
        f = new Fruit(randomNumber(0, 3), randomNumber(0, windWidth), randomNumber(0, windHeight)); // makes a new fruit
    }

    public int randomNumber(int min, int max) { // generates a random number between min and max
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public void paint(Graphics g) { //paints the window
        System.out.println("paint");
        super.paint(g);
        String fruit = f.getType(); // gets the type of fruit
//        for (int dotX = 0; dotX < windHeight+100; dotX += 10) { // draws the grid
//            for (int dotY = 0; dotY < windHeight+100; dotY += 10) {
//                g.setColor(Color.GREEN);
//                g.fillOval(dotX, dotY, 10, 2);
//                g.fillOval(dotX, dotY, 2, 10);
//            }
//        }
        for (int x = 0; x < windWidth; x += 10) { // draws the grid
            for (int y = 0; y < windHeight; y += 10) {
                g.setColor(new Color (1,150,100));
                Line2D line = new Line2D.Float(x, y, windWidth, windHeight);
                System.out.println("line");
            }
        }
        switch (fruit){ // draws the fruit based on the type
            case "Apple" -> {
                g.setColor(Color.RED);
            }
            case "Orange" -> {
                g.setColor(Color.ORANGE);
            }
            case "Kiwifruit" -> {
                g.setColor(Color.GREEN);
            }
            case "Plum" -> {
                g.setColor(Color.MAGENTA);
            }
            default -> {
                System.out.println("Something went wrong");
            }
        }
    }
}