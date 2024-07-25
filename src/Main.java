import java.awt.image.BufferedImage;
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
    private int points = 0; // points the player has
    private final String[] MENU_NAMES = {"Help", "Configure", "Actions","points "+points};
    private final String[] MENU0_OPTIONS = {"Instructions"}; // options in Help menu
    private final String[] MENU1_OPTIONS = {"Keys", "Snake Speed"}; // options in configure menu
    private final String[] MENU2_OPTIONS = {"Pause"}; // options in actions menu
    private final String[] MENU3_OPTIONS = {}; // options in actions menu
    private final int PIXELS_PER_BOX = 10;
    private int turnNumber = 0;
    private int boardWidth = 50; // length of the board (in boxes)
    private int boardHeight = 50; // height of the board (in boxes)
    private int paneWidth = boardWidth*PIXELS_PER_BOX; // initial width of window
    private int paneHeight = boardWidth*PIXELS_PER_BOX; // initial height of window
    private final Box[][] BOARD = new Box[boardWidth][boardHeight]; // creates a 2D array of boxes
    private int frameRate = 250; // 1 frames per second
    private boolean justAte = false;
    private int xOffset = 8; // x offset of the board (for school, 8) (for home, 0)
    private int yOffset = 54; // y offset of the board (for school, 54) (for home, 49)
    private final int INITIAL_SNAKE_LENGTH = 7;
    private final int INITIAL_HEAD_X = boardWidth/2-5;
    private final int INITIAL_HEAD_Y = boardHeight/2;
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
    public static void main(String[] args) { // starts program
        Main m = new Main();
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
        s = new Snake(); // creates a new Snake
        s.setCurrentDirection(RIGHT);
        s.setNextDirection(RIGHT);

        SnakePart leader = null;
        for(int i = 0; i <= INITIAL_SNAKE_LENGTH; i++) {
            boolean t = false;
            boolean h = false;
            if (i == INITIAL_SNAKE_LENGTH) {
                t = true;
            } else if (i == 0) {
                h = true;
            }
            SnakePart sp = new SnakePart(INITIAL_HEAD_X-i, INITIAL_HEAD_Y, t, h, RIGHT);
            BOARD[INITIAL_HEAD_X-i][INITIAL_HEAD_Y].setSnake(true); // sets the box to have a snake
            s.addToSnake(sp);
            sp.setLifeSpan(INITIAL_SNAKE_LENGTH-i+1);
            pt(""+sp.getLifeSpan());
            sp.setLeader(leader);
            pt(sp+" has a leader "+ sp.getLeader());
            if(leader != null) {
                leader.setFollower(sp);
                pt(leader +" has a follower" + leader.getFollower());
            }
            leader = sp;
        }
        this.setContentPane(panel); // sets the content pane to the panel
        this.setTitle("Snake!"); // sets title of Window to "Snake!"
        //this.setPreferredSize(new Dimension(paneWidth,paneHeight)); //24+8 = 32, 8+8=16
        //this.getContentPane().setBackground(new Color(0,0,0,0)); // sets the background colour of the window
        this.getContentPane().setPreferredSize(new Dimension(paneWidth,paneHeight)); // sets the size of the window
        this.setResizable(false); // stops the user from resizing the window
        this.panel.setPreferredSize(new Dimension(paneWidth,paneHeight));
        this.panel.setOpaque(false);
        this.setUndecorated(false);

        this.menuBar = new JMenuBar(); // makes a new menu bar
        this.setJMenuBar(menuBar); // sets the menu bar to the window
        createMenu(MENU0_OPTIONS, 0); // creates the menus
        createMenu(MENU1_OPTIONS, 1);
        createMenu(MENU2_OPTIONS, 2);
        createMenu(MENU3_OPTIONS, 3);
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
        //this.xOffset = 0;
        //this.yOffset = 49;
        //this.xOffset = xNum-8;
        //this.yOffset = yNum-8;
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
                //pt("key pressed ");
                key(e);
            }
        });
        turn();
    }
    public void pt(String s){
        System.out.println(s);
    }
    public void key(KeyEvent e){
        //if(firstKeyPressed){
        //    firstKeyPressed = false;
        //    turn();
        //}
        pt(""+ e.getKeyCode());
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87){ // key was 'up arrow' or 'w' key
            s.setNextDirection(UP);
        }
        if (e.getKeyCode() == 40 || e.getKeyCode() == 83){ // key was 'down arrow' or 's' key
            s.setNextDirection(DOWN);
        }
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68){ // key was 'right arrow' or 'd' key
            s.setNextDirection(RIGHT);
        }
        if (e.getKeyCode() == 37 || e.getKeyCode() == 65){ // key was 'left arrow' or 'a' key
            s.setNextDirection(LEFT);
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
    private void fruitEaten() { // when the snake eats a fruit
        f.setEaten(); // sets the fruit to 'eaten';
        s.setJustAte(true); // sets the snake to have just eaten
        int x=randomNumber(0, boardWidth -1);
        int y=randomNumber(0, boardHeight -1);
        boolean good  = false;
        while (!good){
            pt("good: "+good+" x: "+x+" y: "+y);
            if (BOARD[x][y].getSnake() || BOARD[x][y].getWall()){
                x=randomNumber(0, boardWidth);
                y=randomNumber(0, boardHeight);
            } else {
                good = true;
            }
        }
        f = new Fruit(randomNumber(0, 2), x, y); // makes a new fruit
        BOARD[x][y].setFruit(true); // sets the box to have a fruit
        pt("new fruit x: "+f.getX()+"; y: "+f.getY()); // makes a new fruit
        points = points+10;
    }
    public int randomNumber(int min, int max) { // generates a random number between min and max
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public void turn(){
        pt("");
        pt("turn "+turnNumber);
        turnNumber ++;
        int nextX  = s.getHead().getBoardX(); // gets the x coordinate of the head
        int nextY = s.getHead().getBoardY(); // gets the y coordinate of the head

        if(s.getNextDirection() != s.getHead().getDirection() && s.getNextDirection() != -s.getHead().getDirection()){
            s.setCurrentDirection(s.getNextDirection());
        }

        int headD = s.getCurrentDirection(); // gets the direction of the head
        pt("head direction: "+headD+"; current x: "+nextX+"; current y: "+nextY);
        switch (headD) { // sets next y and next x based on the direction of the head
            case UP -> nextY--; // up
            case DOWN -> nextY++; // down
            case RIGHT -> nextX++; // right
            case LEFT -> nextX--; // left
        }
        pt("next x: "+nextX+"; next y: "+nextY+" .getSnake: "+BOARD[nextX][nextY].getSnake());

        if (BOARD[nextX][nextY].getFruit()) { // if the snake head will be on a fruit
            pt("fruitEaten(s)");
            BOARD[nextX][nextY].setSnake(true); // sets the box to have a snake
            fruitEaten(); // eats the fruit
        } else if (BOARD[nextX][nextY].getWall()){ // if the Snake head is on a wall
            lost(); // ends the game
        } else if (BOARD[nextX][nextY].getSnake() && !(s.getTail().getBoardX()== nextX && s.getTail().getBoardY() == nextY)){ // if the Snake head will be on another SnakePart
            pt("touching Snake");
            lost(); // ends the game
        }

        if (s.getAlive()) { // if the snake is alive
            pt("");
            SnakePart sp = new SnakePart(nextX, nextY, false, true, headD);
            BOARD[nextX][nextY].setSnake(true);
            sp.setLifeSpan(s.getLength()+1); // added 1 because I'm about to decrement all snakeParts

            SnakePart oldHead = s.getHead();
            pt("old head: "+oldHead);

            sp.setFollower(oldHead);
            oldHead.setLeader(sp);

            oldHead.setHead(false);
            s.addToSnake(sp);

            for(int i = s.getLength(); i>0; i--){
                pt("sp: "+sp);
                pt("justAte: "+justAte);
                if(!justAte) {
                    sp.decrementLifeSpan();
                    pt("" + sp.getLifeSpan());
                    if (sp.getLifeSpan() == 0) {
                        BOARD[sp.getBoardX()][sp.getBoardY()].setSnake(false);
                        pt("removed sp "+sp+" from board.");
                        SnakePart newTail = sp.getLeader();
                        pt("newTail: " + newTail);
                        newTail.setTail(true);
                        s.setTail(newTail);
                        sp.setLeader(null);
                        s.decrementLength();
                    }
                }
                sp = sp.getFollower();
            }
            pt("moved");
            s.setJustAte(false);
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
    private BufferedImage offScreenImage;
    public void paint(Graphics g) { //paints the window
        pt("paint");
        if(turnNumber == 1) {
            super.paint(g);
        }

        if (offScreenImage == null) {
            offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g2 = (Graphics2D) offScreenImage.getGraphics();
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(xOffset, yOffset, getWidth(), getHeight());

        for(int i = 0; i < boardWidth; i++){ // for each box in the board
            for(int j = 0; j < boardHeight; j++){
                g2.setColor(DOT_COLOR); // sets the colour to dark green
                g2.fillRect(i*PIXELS_PER_BOX+xOffset+3, j*PIXELS_PER_BOX+yOffset+3, 4, 4); // draws a dot
                if (BOARD[i][j].getWall()){ // if the box is a wall
                    g2.setColor(WALL_COLOR); // sets the colour to brown
                    g2.fillRect(i*PIXELS_PER_BOX+xOffset, j*PIXELS_PER_BOX+yOffset, PIXELS_PER_BOX, PIXELS_PER_BOX); // draws the box
                    //pt("wall");
                }
                else if (BOARD[i][j].getFruit()){ // if the box is the fruit
                    //pt("fruit");
                    switch (f.getType()){ // draws the fruit based on the type
                        case "Apple" -> {
                            //pt("apple");
                            APPLE.paintIcon(this, g2,fruitX(),fruitY());
                        }
                        case "Orange" -> {
                            //pt("orange");
                            ORANGE.paintIcon(this, g2,fruitX(),fruitY());
                        }
//                       case "Kiwifruit" -> {
//                           //pt("kiwifruit");
//                           KIWIFRUIT.paintIcon(this, g2,fruitX(),fruitY());
//                       }
                        case "Plum" -> {
                            //pt("plum");
                            PLUM.paintIcon(this, g2,fruitX(),fruitY());
                        }
                        default -> pt("Something went wrong");
                    }
                } else if (BOARD[i][j].getSnake()){ // if the box is a snake
                    //pt("snake: "+i+" "+j+" BOARD[i][j].getSnake(): "+BOARD[i][j].getSnake());
                }
            }
        }
        //draw Snake

        SnakePart sp = this.s.getHead();
        int lastDirection = sp.getDirection();
        for (int i = 0; i < s.getLength(); i++) {
            int x = sp.getBoardX() * PIXELS_PER_BOX + xOffset;
            int y = sp.getBoardY() * PIXELS_PER_BOX + yOffset;
            //pt("length: "+s.getLength()+"; head: "+sp.isHead()+"; tail: "+sp.isTail() + "; Board x: "+sp.getBoardX() + "; Board y: "+sp.getBoardY() +"; x coord: "+x+"; y coord: "+y);
            // figures out which icon to use based on the direction of the SnakePart
            if (sp.getTail()) {
                //pt("tail");
                switch (sp.getLeader().getDirection()) {
                    case UP -> U_SNAKE_TAIL.paintIcon(this, g2, x, y); // U
                    case DOWN -> D_SNAKE_TAIL.paintIcon(this, g2, x, y); // D
                    case RIGHT -> R_SNAKE_TAIL.paintIcon(this, g2, x, y); // R
                    case LEFT -> L_SNAKE_TAIL.paintIcon(this, g2, x, y); // L
                    default -> pt("tail direction error. Tail direction: " + sp.getDirection());
                }
            } else if (sp.getHead()) {
                pt("head");
                switch (sp.getDirection()) {
                    case UP -> U_SNAKE_HEAD.paintIcon(this, g2, x, y); // U
                    case DOWN -> D_SNAKE_HEAD.paintIcon(this, g2, x, y); // D
                    case RIGHT -> R_SNAKE_HEAD.paintIcon(this, g2, x, y); // R
                    case LEFT -> L_SNAKE_HEAD.paintIcon(this, g2, x, y); // L
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

                switch (-1 * sp.getDirection()) {
                    case UP -> {
                        switch (lastDirection) { // where the snake came from (if it was going left it means it's coming from the right)
                            case DOWN -> UD.paintIcon(this, g2, x, y); // UD
                            case RIGHT -> UR.paintIcon(this, g2, x, y); // UR
                            case LEFT -> UL.paintIcon(this, g2, x, y); // UL
                            default -> pt("up body. Last direction: " + lastDirection);
                        }
                    }
                    case DOWN -> {
                        switch (lastDirection) {
                            case UP -> UD.paintIcon(this, g2, x, y); // UD
                            case RIGHT -> DR.paintIcon(this, g2, x, y); // DR
                            case LEFT -> DL.paintIcon(this, g2, x, y); // DL
                            default -> pt("down body. Last direction: " + lastDirection);
                        }
                    }
                    case RIGHT -> {
                        switch (lastDirection) {
                            case UP -> UR.paintIcon(this, g2, x, y); // UR
                            case DOWN -> DR.paintIcon(this, g2, x, y); // DR
                            case LEFT -> RL.paintIcon(this, g2, x, y); // RL
                            default -> pt("right body. Last direction: " + lastDirection);
                        }
                    }
                    case LEFT -> {
                        switch (lastDirection) {
                            case UP -> UL.paintIcon(this, g2, x, y); // UL
                            case DOWN -> DL.paintIcon(this, g2, x, y); // DL
                            case RIGHT -> RL.paintIcon(this, g2, x, y); // RL
                            default -> pt("left body. Last direction: " + lastDirection);
                        } //  switch (-1 * lastDirection) {
                    } // case -1 -> {
                    default -> pt("body direction error. Body direction: " + sp.getDirection());
                } // switch (sp.getDirection()) {
            } // else {
            lastDirection = sp.getDirection(); // set the last direction to the current direction
            sp = sp.getFollower(); // move to the next SnakePart
        } // for (int i = 0; i < s.getLength(); i++) {

        g.drawImage(offScreenImage, 0, 0, null);
    } // public void paint(Graphics g) {
    public int fruitX(){
        return f.getX()*PIXELS_PER_BOX+xOffset;
    } // returns the x coordinate of the fruit
    public int fruitY(){
        return f.getY()*PIXELS_PER_BOX+yOffset-1;
    } // returns the y coordinate of the fruit
}