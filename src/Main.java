import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    public Color BACKGROUND_COLOR = new Color(84,170,89); //colour that the background is
    public Color WALL_COLOR = new Color(48, 13, 1); //colour that the walls are
    public Color DOT_COLOR = new Color(6, 89, 55); //colour that the snake is
    private final String[] MENU_NAMES = {"Help", "Configure", "Actions"};
    private final String[] MENU0_OPTIONS = {"Instructions"}; // options in Help menu
    private final String[] MENU1_OPTIONS = {"Keys", "Snake Speed"}; // options in configure menu
    private final String[] MENU2_OPTIONS = {"Pause"}; // options in actions menu
    private Box[][] board = new Box[50][50]; // creates a 2D array of boxes
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // width of the screen
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // height of the screen
    private int xOffset = 0; // x offset of the board (for school, 8) (for home, 0)
    private int yOffset = 0; // y offset of the board (for school, 54) (for home, 49)
    private int paneWidth = 500; // initial width of window
    private int paneHeight = 500; // initial height of window
    private final String APPLE_FILE = "apple.png";
    private final String ORANGE_FILE = "orange.png";
    private final String KIWIFRUIT_FILE = "kiwifruit.png";
    private final String PLUM_FILE = "plum.png";
    private final ImageIcon APPLE = new ImageIcon(APPLE_FILE);
    private final ImageIcon ORANGE = new ImageIcon(ORANGE_FILE);
    private final ImageIcon KIWIFRUIT = new ImageIcon(KIWIFRUIT_FILE);
    private final ImageIcon PLUM = new ImageIcon(PLUM_FILE);
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
        for(int i = 0; i < 50; i++){ // for each box in the board
            for(int j = 0; j < 50; j++){
                board[i][j] = new Box(); // creates a new box
                if (i == 0 || i == 49 || j == 0 || j == 49){ // if the box is on the edge
                    board[i][j].setWall(true); // sets the box to be a wall
                }
            }
        }
        this.setTitle("Snake!"); // sets title of Window to "Snake!"

        this.getContentPane().setBackground(BACKGROUND_COLOR); // sets the background colour of the window
        this.getContentPane().setPreferredSize(new Dimension(paneWidth,paneHeight)); // sets the size of the window
        this.setResizable(false); // stops the user from resizing the window
        this.panel.setPreferredSize(new Dimension(paneWidth,paneHeight));
        Snake s = new Snake(); // initialises Snake

        this.menuBar = new JMenuBar(); // makes a new menu bar
        this.setJMenuBar(menuBar); // sets the menu bar to the window
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
        System.out.println(this.getHeight()+" "+this.getWidth());
        System.out.println(this.getContentPane().getHeight()+" "+this.getContentPane().getWidth());
        this.xOffset = this.getWidth() - paneWidth;
        this.yOffset = this.getHeight() - paneHeight;
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
        f = new Fruit(randomNumber(0, 3), randomNumber(0, paneWidth), randomNumber(0, paneHeight)); // makes a new fruit
    }

    public int randomNumber(int min, int max) { // generates a random number between min and max
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public void paint(Graphics g) { //paints the window
        System.out.println("paint");
        super.paint(g);
        for(int i = 0; i < 50; i++){ // for each box in the board
            for(int j = 0; j < 50; j++){
                if (board[i][j].isWall()){ // if the box is a wall
                    g.setColor(WALL_COLOR); // sets the colour to dark green
                    g.fillRect(i*10+xOffset, j*10+yOffset, 10, 10); // draws the box
                }
                else if (board[i][j].isFruit()){ // if the box is the fruit
                    switch (f.getType()){ // draws the fruit based on the type
                        case "Apple" -> {
                            APPLE.paintIcon(this, g,f.getX(),f.getY());
                        }
                        case "Orange" -> {
                            ORANGE.paintIcon(this, g,0,53);
                        }
                        case "Kiwifruit" -> {
                            KIWIFRUIT.paintIcon(this, g,0,53);
                        }
                        case "Plum" -> {
                            PLUM.paintIcon(this, g,0,53);
                        }
                        default -> {
                            System.out.println("Something went wrong");
                        }
                    }
                }
                if (board[i][j].isSnake()){ // if the box is a snake

                }
            }
        }
//
    }
}