import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    public Color BACKGROUND_COLOR = new Color(84,170,89); //colour that the background is
    public Color WALL_COLOR = new Color(48, 13, 1); //colour that the walls are
    public Color DOT_COLOR = new Color(26, 109, 85); //colour that the dots are
    private final int UP = 2; // direction of the snake
    private final int DOWN = -2; // direction of the snake
    private final int RIGHT = 1; // direction of the snake
    private final int LEFT = -1; // direction of the snake
    private final String[] MENU_NAMES = {"Help", "Configure", "Actions"};
    private final String[] MENU0_OPTIONS = {"Instructions"}; // options in Help menu
    private final String[] MENU1_OPTIONS = {"Keys", "Snake Speed"}; // options in configure menu
    private final String[] MENU2_OPTIONS = {"Pause"}; // options in actions menu
    private final int PIXELS_PER_BOX = 10;
    private int boardWidth = 50; // length of the board (in boxes)
    private int boardHeight = 50; // height of the board (in boxes)
    private int paneWidth = boardWidth*PIXELS_PER_BOX; // initial width of window
    private int paneHeight = boardWidth*PIXELS_PER_BOX; // initial height of window
    private final Box[][] BOARD = new Box[boardWidth][boardHeight]; // creates a 2D array of boxes
    private int frameRate = 1000; // 1 frames per second
    private int xOffset; // x offset of the board (for school, 8) (for home, 0)
    private int yOffset; // y offset of the board (for school, 54) (for home, 49)
    private final int INITIAL_SNAKE_LENGTH = 4;
    private final int INITIAL_SNAKE_X = boardWidth/2-5;
    private final int INITIAL_SNAKE_Y = boardHeight/2;
    private boolean firstKeyPressed = true;
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
    private final String RL_FILE = "rl.png";
    private final String UD_FILE = "ud.png";
    private final String UR_FILE = "ur.png";
    private final String UL_FILE = "ul.png";
    private final String DR_FILE = "dr.png";
    private final String DL_FILE = "dl.png";
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
    private final ImageIcon RL = new ImageIcon(RL_FILE);;
    private JMenuBar menuBar; // creates a menu bar
    private JMenuItem menuItem; // creates a menu item
    private Canvas myGraphic; // canvas that is used for the graphics
    private JPanel panel = new JPanel(); // initialises JPanel
    private Scanner kb = new Scanner(System.in); // initialises keyboard
    private Fruit f = new Fruit(randomNumber(0,2), boardWidth/2, boardHeight/2); // initialises fruit
    public Snake s; // initialises Snake
    private int points = 0;
    public static void main(String[] args) { // starts program
        new Main();
    }

    public Main(){ // runs initially
        for(int i = 0; i <boardWidth; i++){ // for each box in the board
            for(int j = 0; j < boardHeight; j++){
                BOARD[i][j] = new Box(); // creates a new box
                if (i == 0 || i == boardWidth-1 || j == 0 || j == boardHeight-1){ // if the box is on the edge
                    BOARD[i][j].setWall(true); // sets the box to be a wall
                }
                if (i == boardWidth/2 && j == boardHeight/2){ // if the box is in the middle
                    BOARD[i][j].setFruit(true); // sets the box to be a wall
                }
            }
        }
        SnakePart snakeStart = new SnakePart(INITIAL_SNAKE_X-INITIAL_SNAKE_LENGTH,INITIAL_SNAKE_Y,true, RIGHT); // creates a new SnakePart
        BOARD[INITIAL_SNAKE_X][INITIAL_SNAKE_Y].setSnake(true); // sets the box to have a snake
        s = new Snake(snakeStart); // creates a new Snake
        s.setHead(snakeStart); // sets the head of the snake
        s.setTail(snakeStart); // sets the tail of the snake
        s.setCurrentDirection(RIGHT);
        int x = INITIAL_SNAKE_X-INITIAL_SNAKE_LENGTH+1; // x coordinate of the next SnakePart
        for (int i = 0; i<INITIAL_SNAKE_LENGTH; i++){ // for each SnakePart in the snake
            s.addToSnake(x, INITIAL_SNAKE_Y, false, RIGHT); // adds a SnakePart to the snake
            BOARD[x][INITIAL_SNAKE_Y].setSnake(true); // sets the box to have a snake
            x++;
        }
        SnakePart sp = s.getTail(); // gets the tail of the snake
        while (sp != null){ // while there are more SnakeParts
            sp = sp.getFollower(); // move to the next SnakePart
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

        int xNum = this.getWidth()-paneWidth;
        int yNum = this.getHeight()-paneHeight;
        this.xOffset = xNum-8;
        this.yOffset = yNum-8;
        this.panel.repaint();

        /* debug for offset*/
//        pt("xn Yn         "+xNum+"  "+yNum);
//        pt("this.g W H    "+this.getWidth()+" "+this.getHeight());
//        pt("this.g cp W H "+this.getContentPane().getWidth()+" "+this.getContentPane().getHeight());
//        pt("pane W H      "+paneWidth+" "+paneHeight);
//        pt("menu bar W H  "+this.menuBar.getWidth()+" "+this.menuBar.getHeight());
//        pt("panel x y     "+this.panel.getX()+"   "+this.panel.getY());
//        pt("xoffst Yoffst "+this.xOffset+"   "+this.yOffset);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                key(e);
            }
        });
        turn();
    }
    public void pt(String s){
        System.out.println(s);
    }
    public void key(KeyEvent e){
        if(firstKeyPressed == true){
            firstKeyPressed = false;
            turn();
        }
        if (e.getKeyCode() == 38){ // key was 'up arrow' key
            s.setCurrentDirection(UP);
        }
        if (e.getKeyCode() == 40){ // key was 'down arrow' key
            s.setCurrentDirection(DOWN);
        }
        if (e.getKeyCode() == 39){ // key was 'right arrow' key
            s.setCurrentDirection(RIGHT);
        }
        if (e.getKeyCode() == 37){ // key was 'left arrow' key
            s.setCurrentDirection(LEFT);
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
        f = new Fruit(randomNumber(0, 2), randomNumber(0, paneWidth), randomNumber(0, paneHeight)); // makes a new fruit
        points = points+10;
    }

    public int randomNumber(int min, int max) { // generates a random number between min and max
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public void turn(){
        pt("turn");
        int nextX  = s.getHead().getBoardX(); // gets the x coordinate of the head
        int nextY = s.getHead().getBoardY(); // gets the y coordinate of the head
        int headD = s.getCurrentDirection(); // gets the direction of the head
        pt("head direction: "+headD+"; next x: "+nextX+"; next y: "+nextY);
        switch (headD) { // sets next y and next x based on the direction of the head
            case UP -> nextY--; // up
            case DOWN -> nextY++; // down
            case RIGHT -> nextX++; // right
            case LEFT -> nextX--; // left
        }
        pt("; next x: "+nextX+"; next y: "+nextY);

        if (BOARD[nextX][nextY].isFruit()) { // if the snake head will be on a fruit
            fruitEaten(s); // eats the fruit
        } else if (BOARD[nextX][nextY].isWall()){ // if the Snake head is on a wall
            lost(); // ends the game
        } else if (BOARD[nextX][nextY].isSnake()){ // if the Snake head will be on another SnakePart
            if (s.getTail().getBoardX() == nextX && s.getTail().getBoardY() == nextY){
            } else {
                lost(); // ends the game
            }
        }
        if (s.getAlive()) { // if the snake is alive
            SnakePart sp = s.getTail(); // gets the tail of the snake
            int x;
            int y;
            while (sp != null) { // while there are more SnakeParts
                x = sp.getBoardX(); // gets the x coordinate of the SnakePart
                y = sp.getBoardY(); // gets the y coordinate of the SnakePart
                int currentD = sp.getDirection(); // gets the direction of the tail

                switch (currentD) { // moves the SnakePart
                    case UP -> y--; // up
                    case DOWN -> y++; // down
                    case RIGHT -> x++; // right
                    case LEFT -> x--; // left
                }
                if (sp.getFollower() != null){
                    int nextD = sp.getFollower().getDirection(); // gets the direction of the SnakePart that follows
                    sp.setDirection(nextD); // sets the direction of the SnakePart
                }else if (sp.isHead()){
                    sp.setDirection(s.getCurrentDirection());
                }
                sp.setBoardX(x); // sets the x coordinate of the SnakePart
                sp.setBoardY(y); // sets the y coordinate of the SnakePart
                sp = sp.getFollower(); // moves to the next SnakePart
            }
            pt("moved");
        }
        repaint();
        try {
            Thread.sleep((long) (frameRate));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        turn();
    }
    public void lost(){
        s.setAlive(false);
        pt("lost");
        JOptionPane.showMessageDialog(this, "You lost! You got "+points+" points.");
        System.exit(0);
    }
    public void paint(Graphics g) { //paints the window
        pt("paint");
        super.paint(g);
        for(int i = 0; i < boardWidth; i++){ // for each box in the board
            for(int j = 0; j < boardHeight; j++){
                g.setColor(DOT_COLOR); // sets the colour to dark green
                g.fillRect(i*PIXELS_PER_BOX+xOffset+3, j*PIXELS_PER_BOX+yOffset+3, 4, 4); // draws a dot
                if (BOARD[i][j].isWall()){ // if the box is a wall
                    g.setColor(WALL_COLOR); // sets the colour to brown
                    g.fillRect(i*PIXELS_PER_BOX+xOffset, j*PIXELS_PER_BOX+yOffset, PIXELS_PER_BOX, PIXELS_PER_BOX); // draws the box
                }
                else if (BOARD[i][j].isFruit()){ // if the box is the fruit
                    pt("fruit");
                    switch (f.getType()){ // draws the fruit based on the type
                        case "Apple" -> {
                            pt("apple");
                            APPLE.paintIcon(this, g,fruitX(),fruitY());
                        }
                        case "Orange" -> {
                            pt("orange");
                            ORANGE.paintIcon(this, g,fruitX(),fruitY());
                        }
//                        case "Kiwifruit" -> {
//                            pt("kiwifruit");
//                            KIWIFRUIT.paintIcon(this, g,fruitX(),fruitY());
//                        }
                        case "Plum" -> {
                            pt("plum");
                            PLUM.paintIcon(this, g,fruitX(),fruitY());
                        }
                        default -> pt("Something went wrong");
                    }
                } else if (BOARD[i][j].isSnake()){ // if the box is a snake
                    //pt("snake: "+i+" "+j);
                }
            }
        }
        //draw Snake

        SnakePart sp = this.s.getTail();
        int lastDirection = sp.getDirection();
        for (int i = 0; i < s.getLength(); i++) {
            int x = sp.getBoardX() * PIXELS_PER_BOX + xOffset;
            int y = sp.getBoardY() * PIXELS_PER_BOX + yOffset;
            //pt("length: "+s.getLength()+"; head: "+sp.isHead()+"; tail: "+sp.isTail() + "; Board x: "+sp.getBoardX() + "; Board y: "+sp.getBoardY() +"; x coord: "+x+"; y coord: "+y);
            // figures out which icon to use based on the direction of the SnakePart
            if (sp.isTail()) {
                //pt("tail");
                switch (sp.getDirection()) {
                    case UP -> U_SNAKE_TAIL.paintIcon(this, g, x, y); // U
                    case DOWN -> D_SNAKE_TAIL.paintIcon(this, g, x, y); // D
                    case RIGHT -> R_SNAKE_TAIL.paintIcon(this, g, x, y); // R
                    case LEFT -> L_SNAKE_TAIL.paintIcon(this, g, x, y); // L
                    default -> pt("tail direction error. Tail direction: " + sp.getDirection());
                }
            } else if (sp.isHead()) {
                //pt("head");
                switch (sp.getDirection()) {
                    case UP -> U_SNAKE_HEAD.paintIcon(this, g, x, y); // U
                    case DOWN -> D_SNAKE_HEAD.paintIcon(this, g, x, y); // D
                    case RIGHT -> R_SNAKE_HEAD.paintIcon(this, g, x, y); // R
                    case LEFT -> L_SNAKE_HEAD.paintIcon(this, g, x, y); // L
                    default -> pt("head direction error. Head direction: " + sp.getDirection());
                }
            } else {
                //pt("body");
                // possible outcomes: UD, UL, UR, DL, DR, LR
                //    U   D   L   R
                // U [XX][UD][UL][UR]
                // D [XX][XX][DL][DR]
                // L [XX][XX][XX][LR]
                // R [XX][XX][XX][XX]

                switch (sp.getDirection()) {
                    case UP -> {
                        switch (-1 * lastDirection) { // where the snake came from (if it was going left it means it's coming from the right)
                            case DOWN -> UD.paintIcon(this, g, x, y); // UD
                            case RIGHT -> UR.paintIcon(this, g, x, y); // UR
                            case LEFT -> UL.paintIcon(this, g, x, y); // UL
                            default -> pt("up body. Last direction: " + lastDirection);
                        }
                    }
                    case DOWN -> {
                        switch (-1 * lastDirection) {
                            case UP -> UD.paintIcon(this, g, x, y); // UD
                            case RIGHT -> DR.paintIcon(this, g, x, y); // DR
                            case LEFT -> DL.paintIcon(this, g, x, y); // DL
                            default -> pt("down body. Last direction: " + lastDirection);
                        }
                    }
                    case RIGHT -> {
                        switch (-1 * lastDirection) {
                            case UP -> UR.paintIcon(this, g, x, y); // UR
                            case DOWN -> DR.paintIcon(this, g, x, y); // DR
                            case LEFT -> RL.paintIcon(this, g, x, y); // RL
                            default -> pt("right body. Last direction: " + lastDirection);
                        }
                    }
                    case LEFT -> {
                        switch (-1 * lastDirection) {
                            case UP -> UL.paintIcon(this, g, x, y); // UL
                            case DOWN -> DL.paintIcon(this, g, x, y); // DL
                            case RIGHT -> RL.paintIcon(this, g, x, y); // RL
                            default -> pt("left body. Last direction: " + lastDirection);
                        } //  switch (-1 * lastDirection) {
                    } // case -1 -> {
                    default -> pt("body direction error. Body direction: " + sp.getDirection());
                } // switch (sp.getDirection()) {
            } // else {
            lastDirection = sp.getDirection(); // set the last direction to the current direction
            sp = sp.getFollower(); // move to the next SnakePart
        } // for (int i = 0; i < s.getLength(); i++) {
    } // public void paint(Graphics g) {
    public int fruitX(){
        return f.getX()*PIXELS_PER_BOX+xOffset;
    } // returns the x coordinate of the fruit
    public int fruitY(){
        return f.getY()*PIXELS_PER_BOX+yOffset-1;
    } // returns the y coordinate of the fruit
}