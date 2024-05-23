import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    public Color BACKGROUND_COLOR = new Color(84,170,89); //colour that the background is
    public Color WALL_COLOR = new Color(48, 13, 1); //colour that the walls are
    public Color DOT_COLOR = new Color(26, 109, 75); //colour that the dots are
    private final String[] MENU_NAMES = {"Help", "Configure", "Actions"};
    private final String[] MENU0_OPTIONS = {"Instructions"}; // options in Help menu
    private final String[] MENU1_OPTIONS = {"Keys", "Snake Speed"}; // options in configure menu
    private final String[] MENU2_OPTIONS = {"Pause"}; // options in actions menu
    private final Box[][] BOARD = new Box[50][50]; // creates a 2D array of boxes
    private final int PIXELS_PER_BOX = 10;
    private int xOffset; // x offset of the board (for school, 8) (for home, 0)
    private int yOffset; // y offset of the board (for school, 54) (for home, 49)
    private int paneWidth = 500; // initial width of window
    private int paneHeight = 500; // initial height of window
    private final int INITIAL_SNAKE_LENGTH = 4;
    private final int INITIAL_SNAKE_X = 20;
    private final int INITIAL_SNAKE_Y = 25;
    private final String APPLE_FILE = "apple.png";
    private final String ORANGE_FILE = "orange.png";
    private final String KIWIFRUIT_FILE = "kiwifruit.png";
    private final String PLUM_FILE = "plum.png";
    private final String U_SNAKE_HEAD_FILE = "uSnakeHead.png";
    private final String D_SNAKE_HEAD_FILE = "dSnakeHead.png";
    private final String L_SNAKE_HEAD_FILE = "lSnakeHead.png";
    private final String R_SNAKE_HEAD_FILE = "rSnakeHead.png";
    private final String U_SNAKE_TAIL_FILE= "uSnakeTail.png";
    private final String D_SNAKE_TAIL_FILE= "dSnakeTail.png";
    private final String L_SNAKE_TAIL_FILE= "lSnakeTail.png";
    private final String R_SNAKE_TAIL_FILE= "rSnakeTail.png";
    private final String UD_FILE = "ud.png";
    private final String UR_FILE = "ur.png";
    private final String UL_FILE = "ul.png";
    private final String DR_FILE = "dr.png";
    private final String DL_FILE = "dl.png";
    private final String RL_FILE = "rl.png";
    private final ImageIcon APPLE = new ImageIcon(APPLE_FILE);
    private final ImageIcon ORANGE = new ImageIcon(ORANGE_FILE);
    private final ImageIcon KIWIFRUIT = new ImageIcon(KIWIFRUIT_FILE);
    private final ImageIcon PLUM = new ImageIcon(PLUM_FILE);
    private final ImageIcon U_SNAKE_HEAD = new ImageIcon(U_SNAKE_HEAD_FILE);
    private final ImageIcon D_SNAKE_HEAD = new ImageIcon(D_SNAKE_HEAD_FILE);
    private final ImageIcon L_SNAKE_HEAD = new ImageIcon(L_SNAKE_HEAD_FILE);
    private final ImageIcon R_SNAKE_HEAD = new ImageIcon(R_SNAKE_HEAD_FILE);
    private final ImageIcon U_SNAKE_TAIL = new ImageIcon(U_SNAKE_TAIL_FILE);
    private final ImageIcon D_SNAKE_TAIL = new ImageIcon(D_SNAKE_TAIL_FILE);
    private final ImageIcon L_SNAKE_TAIL = new ImageIcon(L_SNAKE_TAIL_FILE);
    private final ImageIcon R_SNAKE_TAIL = new ImageIcon(R_SNAKE_TAIL_FILE);
    private final ImageIcon UD = new ImageIcon(UD_FILE);
    private final ImageIcon UR = new ImageIcon(UR_FILE);
    private final ImageIcon UL = new ImageIcon(UL_FILE);
    private final ImageIcon DR = new ImageIcon(DR_FILE);
    private final ImageIcon DL = new ImageIcon(DL_FILE);
    private final ImageIcon RL = new ImageIcon(RL_FILE);
    private JMenuBar menuBar; // creates a menu bar
    private JMenuItem menuItem; // creates a menu item
    private Canvas myGraphic; // canvas that is used for the graphics
    private JPanel panel = new JPanel(); // initialises JPanel
    private Scanner kb = new Scanner(System.in); // initialises keyboard
    private Fruit f = new Fruit(randomNumber(0,3), 25, 25); // initialises fruit
    public Snake s; // initialises Snake
    private int points = 0;
    public static void main(String[] args) { // starts program
        new Main();
    }

    public Main(){ // runs initially
        SnakePart snakeStart = new SnakePart(INITIAL_SNAKE_X-INITIAL_SNAKE_LENGTH,INITIAL_SNAKE_Y,true, 1);
        s = new Snake(snakeStart);
        s.setHead(snakeStart);
        s.setTail(snakeStart);
        int x = INITIAL_SNAKE_X-INITIAL_SNAKE_LENGTH+1;
        for (int i = 0; i<INITIAL_SNAKE_LENGTH; i++){
            s.addToSnake(x, INITIAL_SNAKE_Y, false, 1);
            x++;
        }
        SnakePart sp = s.getTail();
        while (sp != null){
            System.out.println(sp);
            sp = sp.getFollower();
        }

        for(int i = 0; i < 50; i++){ // for each box in the board
            for(int j = 0; j < 50; j++){
                BOARD[i][j] = new Box(); // creates a new box
                if (i == 0 || i == 49 || j == 0 || j == 49){ // if the box is on the edge
                    BOARD[i][j].setWall(true); // sets the box to be a wall
                }
                if (i == 25 && j == 25){ // if the box is on the edge
                    BOARD[i][j].setFruit(true); // sets the box to be a wall
                }
            }
        }
        this.setContentPane(panel); // sets the content pane to the panel
        this.setTitle("Snake!"); // sets title of Window to "Snake!"
        //this.setPreferredSize(new Dimension(paneWidth,paneHeight)); //24+8 = 32, 8+8=16
        this.getContentPane().setBackground(BACKGROUND_COLOR); // sets the background colour of the window
        this.getContentPane().setPreferredSize(new Dimension(paneWidth,paneHeight)); // sets the size of the window
        this.setResizable(false); // stops the user from resizing the window
        this.panel.setPreferredSize(new Dimension(paneWidth,paneHeight));
        this.setUndecorated(false);

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

        int xNum = this.getWidth()-500;
        int yNum = this.getHeight()-500;
        this.xOffset = xNum-8;
        this.yOffset = yNum-8;
        this.panel.repaint();

        /* debug for offset*/
//        System.out.println("xn Yn         "+xNum+"  "+yNum);
//        System.out.println("this.g W H    "+this.getWidth()+" "+this.getHeight());
//        System.out.println("this.g cp W H "+this.getContentPane().getWidth()+" "+this.getContentPane().getHeight());
//        System.out.println("pane W H      "+paneWidth+" "+paneHeight);
//        System.out.println("menu bar W H  "+this.menuBar.getWidth()+" "+this.menuBar.getHeight());
//        System.out.println("panel x y     "+this.panel.getX()+"   "+this.panel.getY());
//        System.out.println("xoffst Yoffst "+this.xOffset+"   "+this.yOffset);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                key(e);
            }
        });


    }
    public void key(KeyEvent e){ // -1 = Left; -2 = Down; 1 = Right; 2 = Up
        if (e.getKeyCode() == 38){ // key was 'up arrow' key
            s.setCurrentDirection(2);
        }
        if (e.getKeyCode() == 40){ // key was 'down arrow' key
            s.setCurrentDirection(-2);
        }
        if (e.getKeyCode() == 39){ // key was 'right arrow' key
            s.setCurrentDirection(1);
        }
        if (e.getKeyCode() == 37){ // key was 'left arrow' key
            s.setCurrentDirection(-1);
        }
    }
    private void createMenu(String[] menuOptions, int numMenus) { // creates menus in window
        JMenu menu = new JMenu(MENU_NAMES[numMenus]); // creates a new menu
        menuBar.add(menu); // adds the menu to the menu bar

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
            case "Instructions" -> JOptionPane.showMessageDialog(this, "Use the arrow keys to move the snake. Eat the food to grow. Don't hit the walls or yourself.");
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
        s.addToSnake(f.getX()*PIXELS_PER_BOX,f.getY()*PIXELS_PER_BOX, false, s.getCurrentDirection());
        f.eaten(); // sets the fruit to 'eaten';
        f = new Fruit(randomNumber(0, 3), randomNumber(0, paneWidth), randomNumber(0, paneHeight)); // makes a new fruit
        points = points+10;
    }

    public int randomNumber(int min, int max) { // generates a random number between min and max
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public void paint(Graphics g) { //paints the window
        System.out.println("paint");
        super.paint(g);
        for(int i = 0; i < 50; i++){ // for each box in the board
            for(int j = 0; j < 50; j++){
                g.setColor(DOT_COLOR); // sets the colour to dark green
                g.fillRect(i*PIXELS_PER_BOX+xOffset+3, j*PIXELS_PER_BOX+yOffset+3, 4, 4); // draws a dot
                if (BOARD[i][j].isWall()){ // if the box is a wall
                    g.setColor(WALL_COLOR); // sets the colour to brown
                    g.fillRect(i*PIXELS_PER_BOX+xOffset, j*PIXELS_PER_BOX+yOffset, PIXELS_PER_BOX, PIXELS_PER_BOX); // draws the box
                }
                else if (BOARD[i][j].isFruit()){ // if the box is the fruit
                    System.out.println("fruit");
                    switch (f.getType()){ // draws the fruit based on the type
                        case "Apple" -> {
                            System.out.println("apple");
                            APPLE.paintIcon(this, g,fruitX(),fruitY());
                        }
                        case "Orange" -> {
                            System.out.println("orange");
                            ORANGE.paintIcon(this, g,fruitX(),fruitY());
                        }
                        case "Kiwifruit" -> {
                            System.out.println("kiwifruit");
                            KIWIFRUIT.paintIcon(this, g,fruitX(),fruitY());
                        }
                        case "Plum" -> {
                            System.out.println("plum");
                            PLUM.paintIcon(this, g,fruitX(),fruitY());
                        }
                        default -> System.out.println("Something went wrong");
                    }
                } else if (BOARD[i][j].isSnake()){ // if the box is a snake

                }
            }
        }
        //draw Snake
        SnakePart sp = this.s.getTail();
        int lastDirection = sp.getDirection();
        for (int i = 0; i < s.getLength(); i++) {
            System.out.println("length: "+s.getLength()+"; head: "+sp.isHead()+"; tail: "+sp.isTail());
            int x = sp.getBoardX() * PIXELS_PER_BOX + xOffset;
            int y = sp.getBoardX() * PIXELS_PER_BOX + xOffset;

            if (sp.isTail()) {
                switch (sp.getDirection()) {
                    case 2 -> U_SNAKE_TAIL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                    case -2 -> D_SNAKE_TAIL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                    case 1 -> R_SNAKE_TAIL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                    case -1 -> L_SNAKE_TAIL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                }
            } else if (sp.isHead()) {
                switch (sp.getDirection()) {
                    case 2 -> U_SNAKE_HEAD.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                    case -2 -> D_SNAKE_HEAD.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                    case 1 -> R_SNAKE_HEAD.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                    case -1 -> L_SNAKE_HEAD.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                }
            } else {
                // possible outcomes: UD, UL, UR, DL, DR, LR
                //    U   D   L   R
                // U [XX][UD][UL][UR]
                // D [XX][XX][DL][DR]
                // L [XX][XX][XX][LR]
                // R [XX][XX][XX][XX]
                switch (sp.getDirection()) {
                    case 2 -> {
                        switch (-1 * lastDirection) { // where the snake came from (if it was going left it means it's coming from the right)
                            case -2 -> UD.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case 1 -> UR.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case -1 -> UL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                        }
                    }
                    case -2 -> {
                        switch (-1 * lastDirection) {
                            case 2 -> UD.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case 1 -> DR.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case -1 -> DL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                        }
                    }
                    case 1 -> {
                        switch (-1 * lastDirection) {
                            case 2 -> UR.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case -2 -> DR.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case -1 -> RL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                        }
                    }
                    case -1 -> {
                        switch (-1 * lastDirection) {
                            case 2 -> UL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case -2 -> DL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                            case 1 -> RL.paintIcon(this, g, sp.getBoardX(), sp.getBoardY());
                        }
                    }
                }
            }
            lastDirection = sp.getDirection();
            sp = sp.getFollower();
        }
    }
    public int fruitX(){
        return f.getX()*PIXELS_PER_BOX+xOffset;
    }
    public int fruitY(){
        return f.getY()*PIXELS_PER_BOX+yOffset-1;
    }
}