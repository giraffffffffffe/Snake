/**
 * Author: Anne Carey-Smith
 * Date: 2024-02-08
 * Description: This is a simple snake game. The player uses the arrow keys or WASD to move the snake. The snake eats fruit to grow. The player loses if the snake runs into a wall or itself.
 * CSC335 Java Programming Project WHS 2024
 * Version Control: GitHub
 * Relevant Implications:
 *  Accessibility: See report
 *  Usability: See report
 *  Aesthetics: See report
 *  Intellectual Property: This is created under creative commons. All images and code were created by me. See report for more details
 */

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main class that runs the game
 */
public class Main extends JFrame {
    public final Color BACKGROUND_COLOR = new Color(84,170,89); //colour that the background is
    public final Color WALL_COLOR = new Color(48, 13, 1); //colour that the walls are
    public final Color DOT_COLOR = new Color(26, 109, 85); //colour that the dots are
    public final int[] MODE_FRAME_RATES = {100, 80, 50}; // possible modes of the game
    private int currentMode = 0; // current mode of the game
    private final String[] MODE_NAMES = {"Easy", "Medium", "Hard"}; // possible modes of the game
    private int frameRate = MODE_FRAME_RATES[currentMode]; // frames per second (1000 = 1 second)
    private final int MARGIN = 30; // margin in pixels from the edge of the board for the text
    private int titleY; // initializes y coordinate of the title
    private final int Y_GAP_AFTER_TITLE = 75; // gap between the title and the next line of text
    private final int Y_GAP_BETWEEN_LINES = 50; // gap between each line of text
    private final int UP = 2; // direction of the snake
    private final int DOWN = -2; // direction of the snake
    private final int RIGHT = 1; // direction of the snake
    private final int LEFT = -1; // direction of the snake
    private int points = 0; // points the player has
    private int[] highscore = {0,0,0}; // highscore of the player in each mode
    private boolean justGotHighScore = false; // if the player has just got a highscore
    private boolean paused = false; // if the game is paused
    private boolean firstTurn = true; // if it is the first turn (only do super.paint(g) on the first turn to help prevent flickering)
    private int turnNumber = 0; // number of turns the game has taken
    private final int PIXELS_PER_BOX = 10; // width and height of each box
    private int boardWidth = 50; // length of the board (in boxes)
    private int boardHeight = 50; // height of the board (in boxes)
    private int paneWidth = boardWidth*PIXELS_PER_BOX; // initial width of window
    private int paneHeight = boardWidth*PIXELS_PER_BOX; // initial height of window
    private final Box[][] BOARD = new Box[boardWidth][boardHeight]; // creates a 2D array of boxes
    private boolean gameRunning = false; // if the game is running
    private int xOffset = 8; // x offset of the board (for school, 8) (for home, 0)
    private int yOffset = 31; // y offset of the board (for school, 31) (for home, 29)
    private boolean lost = false; // if the player has lost
    private boolean instructions = false; // if the player is on the instructions screen
    private final int GROWTH_RATE = 2; // how much the snake grows when it eats a fruit
    private final int WALL_FREQUENCY = 20; // how often walls are added
    private final int INITIAL_SNAKE_LENGTH = 1; // initial length of the snake -1
    private final int INITIAL_HEAD_X = boardWidth/2-5; // initial x coordinate of the head
    private final int INITIAL_HEAD_Y = boardHeight/2; // initial y coordinate of the head
    private final int INITIAL_FRUIT_X = boardWidth/2; // initial x coordinate of the fruit
    private final int INITIAL_FRUIT_Y = boardHeight/2; // initial y coordinate of the fruit
    private boolean firstKeyPressed = false; // if the player has pressed a key (Once they have, the game starts)
    private BufferedImage offScreenImage; // image that is used to draw the graphics
    /**
     * the next three variables are there just in case the user accidentally presses 'q' or 'r' or 'm' and doesn't want to lose their progress.
     */
    private boolean wantsToQuit = false; // if the player wants to quit
    private boolean wantsToRestart = false; // if the player wants to restart
    private boolean wantsToChangeMode = false; // if the player wants to change the mode
    /**
     * Fruit and snake images
     */
    private final String KIWIFRUIT_FILE = "kiwifruit.png";
    private final String APPLE_FILE = "apple.png";
    private final String ORANGE_FILE = "orange.png";
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
    private final ImageIcon KIWIFRUIT = new ImageIcon(KIWIFRUIT_FILE);
    private final ImageIcon APPLE = new ImageIcon(APPLE_FILE);
    private final ImageIcon ORANGE = new ImageIcon(ORANGE_FILE);
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
    private Canvas myGraphic; // canvas that is used for the graphics
    private JPanel panel = new JPanel(); // initialises JPanel
    private Fruit f = new Fruit(randomNumber(0,2), INITIAL_FRUIT_X, INITIAL_FRUIT_Y); // initialises fruit
    public Snake s; // initialises Snake

    /**
     * Main method that starts the program
     * @param args
     */
    public static void main(String[] args) {
        Main m = new Main();
    }

    /**
     * Constructor for Main which runs initially
     */
    public Main(){
        for(int i = 0; i <boardWidth; i++){ // for each box in the board
            for(int j = 0; j < boardHeight; j++){
                BOARD[i][j] = new Box(); // creates a new box
                if (i == 0 || i == boardWidth-1 || j == 0 || j == boardHeight-1){ // if the box is on the edge
                    BOARD[i][j].setWall(true); // sets the box to be a wall
                }
                if (i == INITIAL_FRUIT_X && j == INITIAL_FRUIT_Y){ // if the box is in the middle (the start location of the fruit)
                    BOARD[i][j].setFruit(true); // sets the box to be a fruit
                }
            }
        }
        makeSnake();// creates the initial snake by making snakeParts and adding them to the snake

        this.setContentPane(panel); // sets the content pane to the panel
        this.setTitle("Snake!"); // sets title of Window to "Snake!"
        this.getContentPane().setPreferredSize(new Dimension(paneWidth,paneHeight)); // sets the size of the window
        this.setResizable(false); // stops the user from resizing the window
        this.panel.setPreferredSize(new Dimension(paneWidth,paneHeight));
        this.panel.setOpaque(false); // makes the panel transparent
        this.setUndecorated(false);  // adds the title bar

        this.myGraphic = new Canvas(); // initialises graphic
        this.panel.add(myGraphic); // adds canvas to panel

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // stops program when window is closed
        this.pack(); // makes the window
        this.toFront(); // puts this window on top of other windows the user might have open.
        this.setLocationRelativeTo(null); // opens the window in the middle of the screen
        this.setVisible(true); // makes the window visible

        this.panel.repaint();

        /* debug for offset
        pt("xn Yn         "+xNum+"  "+yNum);
        pt("this.g W H    "+this.getWidth()+" "+this.getHeight());
        pt("this.g cp W H "+this.getContentPane().getWidth()+" "+this.getContentPane().getHeight());
        pt("pane W H      "+paneWidth+" "+paneHeight);
        pt("menu bar W H  "+this.menuBar.getWidth()+" "+this.menuBar.getHeight());
        pt("panel x y     "+this.panel.getX()+"   "+this.panel.getY());
        pt("xOffset yOffset "+this.xOffset+"   "+this.yOffset);
            */
        addKeyListener(new KeyAdapter() { // adds a key listener
            public void keyPressed(KeyEvent e) { // when a key is pressed
                key(e); // calls the key method
            }
        });
        gameLoop(); // calls the gameLoop method
    }
    /**
     * Method that runs the game loop
     */
    private void gameLoop() {
        while (true) { // while the snake is alive
            turn();
            try {
                Thread.sleep((long) (frameRate));
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
    /**
     * Method that creates the snake
     */
    public void makeSnake(){
        s = new Snake(); // creates a new Snake
        s.setCurrentDirection(RIGHT); // sets the current direction of the snake
        s.setNextDirection(RIGHT); // sets the next direction of the snake
        SnakePart leader = null; // initialises leader (the head of the snake has no leader)
        for(int i = 0; i <= INITIAL_SNAKE_LENGTH; i++) {
            boolean t = false; // if the SnakePart is the tail
            boolean h = false; // if the SnakePart is the head
            if (i == INITIAL_SNAKE_LENGTH) { // if it is the last snakePart added to the snake
                t = true; // sets the SnakePart to be the tail
            } else if (i == 0) { // if it is the first snakePart added to the snake
                h = true; // sets the SnakePart to be the head
            }
            SnakePart sp = new SnakePart(INITIAL_HEAD_X-i, INITIAL_HEAD_Y, t, h, RIGHT); // creates a new SnakePart
            BOARD[INITIAL_HEAD_X-i][INITIAL_HEAD_Y].setSnake(true); // sets the box to have a snake
            s.addToSnake(sp); // adds the SnakePart to the snake
            sp.setLifeSpan(INITIAL_SNAKE_LENGTH-i+1); // sets the lifeSpan of the SnakePart
            sp.setLeader(leader); // sets the leader of the SnakePart to the SnakePart in front of the current one
            if(leader != null) { // If the SnakePart is not the head
                leader.setFollower(sp); // sets the follower of the leader to the current SnakePart
            }
            leader = sp; // sets the leader to the current SnakePart
        }
    }
    /**
     * Prints a string to the console
     * @param s
     */
    public void pt(String s){
        System.out.println(s);
    }

    /**
     * Method that is called when a key is pressed
     * @param e
     */
    public void key(KeyEvent e){
        if(wantsToQuit ){ // if the player wants to quit and presses 'y'
            if (e.getKeyCode() == 89) { // key was 'y' key
                System.exit(0); // quits the program
            } else if (e.getKeyCode() == 78) {
                wantsToQuit = false; // sets the player to not want to quit
                if (firstKeyPressed && !lost && !paused) { // if the player has started the game, and they haven't lost or paused
                    gameRunning = true; // sets the game to be running
                }
            }
        } else if(wantsToRestart){
            if (e.getKeyCode() == 89) { // key was 'y' key
                wantsToRestart = false; // sets the player to not want to restart
                restart(); // restarts the game
            } else if (e.getKeyCode() == 78) { // key was 'n' key
                wantsToRestart = false; // sets the player to not want to restart
                if (firstKeyPressed && !lost && !paused) { // if the player has started the game, and they haven't lost or paused
                    gameRunning = true; // sets the game to be running
                }
            }
        } else if(wantsToChangeMode){
            if(e.getKeyCode() == 69){ // if the player presses 'e'
                currentMode = 0; // sets the mode to the first mode
                wantsToChangeMode = false;
                firstKeyPressed = true; // so that it doesn't go back to the welcome screen
                restart(); // restarts the game
            } else if(e.getKeyCode() == 77){ // if the player presses 'm'
                currentMode = 1; // sets the mode to the second mode
                wantsToChangeMode = false;
                firstKeyPressed = true;
                restart(); // restarts the game
            } else if(e.getKeyCode() == 72){ // if the player presses 'h'
                currentMode = 2; // sets the mode to the third mode
                wantsToChangeMode = false;
                firstKeyPressed = true;
                restart(); // restarts the game
            } else if(e.getKeyCode() == 66){
                wantsToChangeMode = false;
                if (firstKeyPressed && !lost && !paused) { // if the player has started the game, and they haven't lost or paused
                    gameRunning = true; // sets the game to be running
                }
            }
        }else {
            if (gameRunning && (e.getKeyCode() == 38 || e.getKeyCode() == 87)) { // key was 'up arrow' or 'w' key
                s.setNextDirection(UP);
            }
            if (gameRunning && (e.getKeyCode() == 40 || e.getKeyCode() == 83)) { // key was 'down arrow' or 's' key
                s.setNextDirection(DOWN);
            }
            if (gameRunning && (e.getKeyCode() == 39 || e.getKeyCode() == 68)) { // key was 'right arrow' or 'd' key
                s.setNextDirection(RIGHT);
            }
            if (gameRunning && (e.getKeyCode() == 37 || e.getKeyCode() == 65)) { // key was 'left arrow' or 'a' key
                s.setNextDirection(LEFT);
            }
            if ((e.getKeyCode() == 80 && firstKeyPressed) && !instructions && !lost) { // key was 'p' key
                paused = !paused;
                gameRunning = !gameRunning; // sets the game to be running or not
            }
            if (e.getKeyCode() == 81) { // key was 'q' key
                gameRunning = false; // sets the game to be running or not
                wantsToQuit = true; // sets the player to want to quit
            }
            if (e.getKeyCode() == 82 && !instructions) { // if the player presses 'r'
                if (!gameRunning) { // if the game is not currently in action
                    restart(); // restart the game
                } else {
                    wantsToRestart = true; // sets the player to want to restart
                    gameRunning = false; // sets the game to not be running
                }
            }
            if (e.getKeyCode() == 73) { // if the player presses 'i'
                instructions = !instructions;
                gameRunning = false;
                if (firstKeyPressed && !lost && !paused && !instructions) { // if the player has started the game, and they haven't lost or paused
                    gameRunning = true; // sets the game to be running
                }
            }
            if(e.getKeyCode() == 77){ // if the player presses 'm'
                wantsToChangeMode = true;
                gameRunning = false;
            }
            if ((!firstKeyPressed && e.getKeyCode() != 81) && (e.getKeyCode() != 73 && e.getKeyCode() != 77) && !instructions) { // if the player has not pressed a key yet and the first key they press isn't 'q' or 'i' or 'm'
                firstKeyPressed = true;
                gameRunning = true; // start the game
            }
        }
    }
    /**
     * Method that adds a wall to the board which makes the game harder as it goes
     */
    private void addWall(int nextX, int nextY){ // adds a wall to the board
        points++;
        int x = randomNumber(0, boardWidth - 1);
        int y = randomNumber(0, boardHeight - 1);
        boolean good = false;
        while (!good) {
            if (BOARD[x][y].getSnake() || BOARD[x][y].getWall() || BOARD[x][y].getFruit() || x == nextX || y == nextY) {
                x = randomNumber(0, boardWidth -1);
                y = randomNumber(0, boardHeight -1);
            } else {
                good = true;
            }
        }
        BOARD[x][y].setWall(true);
        turnNumber = 0;
    }
    /**
     * Method that is called when the snake eats a fruit
     */
    private void fruitEaten() {
        s.addToJustAteTurns(GROWTH_RATE); // sets the snake to have just eaten
        int x=randomNumber(0, boardWidth -1);
        int y=randomNumber(0, boardHeight -1);
        boolean good  = false;
        while (!good){
            if (BOARD[x][y].getSnake() || BOARD[x][y].getWall()){
                x=randomNumber(0, boardWidth -1);
                y=randomNumber(0, boardHeight -1);
            } else {
                good = true;
            }
        }
        BOARD[f.getX()][f.getY()].setFruit(false); // sets the box to not have a fruit
        if (currentMode == 2) {
            f = new Fruit(randomNumber(2,3), x, y); // makes a new plum if it's in hard mode
        }else{
            f = new Fruit(randomNumber(0,1), x, y); // makes a new fruit

        }
        BOARD[x][y].setFruit(true); // sets the box to have a fruit
        points = points+10;
    }
    /**
     * Generates a random number between min and max
     * @param min
     * @param max
     * @return
     */
    public int randomNumber(int min, int max) { // generates a random number between min and max
        return (int) (Math.random() * (max - min + 1) + min);
    }
    /**
     * Method that is called to refresh the screen (move the snake)
     */
    public void turn(){

        int nextX  = s.getHead().getBOARD_X(); // gets the x coordinate of the head
        int nextY = s.getHead().getBOARD_Y(); // gets the y coordinate of the head

        if(s.getNextDirection() != -s.getHead().getDIRECTION()){ // if the next direction is not the opposite of the current direction
            s.setCurrentDirection(s.getNextDirection());
        }

        int headD = s.getCurrentDirection(); // gets the direction of the head
        switch (headD) { // sets next y and next x based on the direction of the head
            case UP -> nextY--;
            case DOWN -> nextY++;
            case RIGHT -> nextX++;
            case LEFT -> nextX--;
        }
        if (BOARD[nextX][nextY].getFruit()) { // if the snake head will be on a fruit
            BOARD[nextX][nextY].setSnake(true); // sets the box to have a snake
            fruitEaten(); // eats the fruit
        } else if (BOARD[nextX][nextY].getWall()){ // if the Snake head will be on a wall
            lost(); // ends the game
        } else if (BOARD[nextX][nextY].getSnake() && !(s.getTail().getBOARD_X()== nextX && s.getTail().getBOARD_Y() == nextY)){ // if the Snake head will be on another SnakePart
            lost(); // ends the game
        }

        if (s.getAlive() && gameRunning) { // if the snake is alive
            if(turnNumber == WALL_FREQUENCY){
                addWall(nextX, nextY);
            }
            SnakePart sp = new SnakePart(nextX, nextY, false, true, headD); // creates a new SnakePart
            BOARD[nextX][nextY].setSnake(true); // sets the box to have a snake
            sp.setLifeSpan(s.getLength()+1); // added 1 because I'm about to decrement all snakeParts

            SnakePart oldHead = s.getHead(); // gets the head of the snake

            sp.setFollower(oldHead); // sets the follower of the new head to the old head
            oldHead.setLeader(sp); // sets the leader of the old head to the new head

            oldHead.setHead(false); // sets the old head to not be the head
            s.addToSnake(sp); // adds the new head to the snake

            // for each snakePart, decrement the lifeSpan and if it is 0, remove it from the board
            for(int i = s.getLength(); i>0; i--){
                if(s.getJustAteTurns()==0) {
                    sp.decrementLifeSpan();
                    if (sp.getLifeSpan() == 0) {
                        BOARD[sp.getBOARD_X()][sp.getBOARD_Y()].setSnake(false);
                        SnakePart newTail = sp.getLeader();
                        newTail.setTail(true);
                        s.setTail(newTail);
                        sp.setLeader(null);
                        s.decrementLength();
                    }
                }
                sp = sp.getFollower(); // move on to the next SnakePart
            }
            if(s.getJustAteTurns() > 0) {
                s.decrementJustAteTurns(); // decreases the number of turns the snake has 'just eaten' for by 1
            }
            turnNumber ++;
        }
        repaint(); // repaints the window
        // waits for the frame rate
    }

    /**
     * Method that is called when the player loses
     */
    public void lost(){
        gameRunning = false; // sets the game to not be running
        s.setAlive(false); // sets the snake to be dead
        if(points>highscore[currentMode]){ // if the player has a new highscore
            highscore[currentMode] = points; // sets the highscore to the points they got that round
            justGotHighScore = true;
        }
        lost = true; // sets the player to have lost
    }
    public void restart(){
        pt("restart");
        makeSnake();
        firstKeyPressed = false;
        frameRate = MODE_FRAME_RATES[currentMode];
        lost = false;
        justGotHighScore = false;
        points = 0;
        if(currentMode == 2) {
            f = new Fruit(randomNumber(2, 3), INITIAL_FRUIT_X, INITIAL_FRUIT_Y);
        }else {
            f = new Fruit(randomNumber(0, 1), INITIAL_FRUIT_X, INITIAL_FRUIT_Y);
        }
        paused = false;
        turnNumber = 0;
        // resets the board
        for(int i = 0; i <boardWidth; i++){ // for each box in the board
            for(int j = 0; j < boardHeight; j++){
                BOARD[i][j].setWall(false); // sets the box to not be a wall
                BOARD[i][j].setSnake(false);
                BOARD[i][j].setFruit(false);
                if (i == 0 || i == boardWidth-1 || j == 0 || j == boardHeight-1){ // if the box is on the edge
                    BOARD[i][j].setWall(true); // sets the box to be a wall
                }
                if (i == INITIAL_FRUIT_X && j == INITIAL_FRUIT_Y){ // if the box is in the middle (the start location of the fruit)
                    BOARD[i][j].setFruit(true); // sets the box to be a fruit
                }
            }
        }
    }

    /**pt
     * Method that is called to paint the window
     * @param g the specified Graphics window
     */
    public void paint(Graphics g) { //paints the window
        if (firstTurn) { // if it is the first turn
            firstTurn = false;
            super.paint(g); // paints the window
        }
        // I use an offscreen image because it gets rid of flickering
        if (offScreenImage == null) {
            offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g2 = (Graphics2D) offScreenImage.getGraphics();
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(xOffset, yOffset, getWidth(), getHeight());
        for (int i = 0; i < boardWidth; i++) { // for each box in the board
            for (int j = 0; j < boardHeight; j++) {
                g2.setColor(DOT_COLOR); // sets the colour to dark green
                g2.fillRect(i * PIXELS_PER_BOX + xOffset + 3, j * PIXELS_PER_BOX + yOffset + 3, 4, 4); // draws a dot
                if (BOARD[i][j].getWall()) { // if the box is a wall
                    g2.setColor(WALL_COLOR); // sets the colour to brown
                    g2.fillRect(i * PIXELS_PER_BOX + xOffset, j * PIXELS_PER_BOX + yOffset, PIXELS_PER_BOX, PIXELS_PER_BOX); // draws the box
                } else if (BOARD[i][j].getFruit()) { // if the box is the fruit
                    switch (f.getType()) { // draws the fruit based on the type
                        case "Apple" -> {
                            APPLE.paintIcon(this, g2, fruitX(), fruitY());
                        }
                        case "Orange" -> {
                            ORANGE.paintIcon(this, g2, fruitX(), fruitY());
                        }
                        case "Plum" -> {
                            PLUM.paintIcon(this, g2, fruitX(), fruitY());
                        }
                        case "Kiwifruit" -> {
                            KIWIFRUIT.paintIcon(this, g2, fruitX(), fruitY());
                        }
                        default -> pt("Something went wrong");
                    }
                } else if (BOARD[i][j].getSnake()) { // if the box is a snake
                }
            }
        }
        //draw Snake
        SnakePart sp = this.s.getHead(); // gets the head of the snake
        int leaderDirection = sp.getDIRECTION(); // gets the direction of the head
        //draws each snakePart
        for (int i = 0; i < s.getLength(); i++) {
            int x = sp.getBOARD_X() * PIXELS_PER_BOX + xOffset; // x coordinate of the SnakePart (Pixels)
            int y = sp.getBOARD_Y() * PIXELS_PER_BOX + yOffset; // y coordinate of the SnakePart (Pixels)
            // figures out which icon to use for the tail based on the direction of the SnakePart in front of it. Draws the tail
            if (sp.getTail()) {
                switch (sp.getLeader().getDIRECTION()) {
                    case UP -> U_SNAKE_TAIL.paintIcon(this, g2, x, y); // U
                    case DOWN -> D_SNAKE_TAIL.paintIcon(this, g2, x, y); // D
                    case RIGHT -> R_SNAKE_TAIL.paintIcon(this, g2, x, y); // R
                    case LEFT -> L_SNAKE_TAIL.paintIcon(this, g2, x, y); // L
                    default -> pt("tail direction error. Tail direction: " + sp.getDIRECTION());
                }
            } else if (sp.getHead()) { //draws the head
                switch (sp.getDIRECTION()) {
                    case UP -> U_SNAKE_HEAD.paintIcon(this, g2, x, y); // U
                    case DOWN -> D_SNAKE_HEAD.paintIcon(this, g2, x, y); // D
                    case RIGHT -> R_SNAKE_HEAD.paintIcon(this, g2, x, y); // R
                    case LEFT -> L_SNAKE_HEAD.paintIcon(this, g2, x, y); // L
                    default -> pt("head direction error. Head direction: " + sp.getDIRECTION());
                }
            } else { // Draws the body based on the direction of the current SnakePart and the direction of the SnakePart in front of it
                // possible outcomes: UD, UL, UR, DL, DR, LR
                switch (-1 * sp.getDIRECTION()) {
                    case UP -> {
                        switch (leaderDirection) { // where the snake came from (if it was going left it means it's coming from the right)
                            case DOWN -> UD.paintIcon(this, g2, x, y); // UD
                            case RIGHT -> UR.paintIcon(this, g2, x, y); // UR
                            case LEFT -> UL.paintIcon(this, g2, x, y); // UL
                            default -> pt("up body. Last direction: " + leaderDirection);
                        }
                    }
                    case DOWN -> {
                        switch (leaderDirection) {
                            case UP -> UD.paintIcon(this, g2, x, y); // UD
                            case RIGHT -> DR.paintIcon(this, g2, x, y); // DR
                            case LEFT -> DL.paintIcon(this, g2, x, y); // DL
                            default -> pt("down body. Last direction: " + leaderDirection);
                        }
                    }
                    case RIGHT -> {
                        switch (leaderDirection) {
                            case UP -> UR.paintIcon(this, g2, x, y); // UR
                            case DOWN -> DR.paintIcon(this, g2, x, y); // DR
                            case LEFT -> RL.paintIcon(this, g2, x, y); // RL
                            default -> pt("right body. Last direction: " + leaderDirection);
                        }
                    }
                    case LEFT -> {
                        switch (leaderDirection) {
                            case UP -> UL.paintIcon(this, g2, x, y); // UL
                            case DOWN -> DL.paintIcon(this, g2, x, y); // DL
                            case RIGHT -> RL.paintIcon(this, g2, x, y); // RL
                            default -> pt("left body. Last direction: " + leaderDirection);
                        } //  switch (-1 * lastDirection) {
                    } // case -1 -> {
                    default -> pt("body direction error. Body direction: " + sp.getDIRECTION());
                } // switch (sp.getDirection()) {
            } // else {
            leaderDirection = sp.getDIRECTION(); // set the last direction to the current direction
            sp = sp.getFollower(); // move to the next SnakePart
        } // for (int i = 0; i < s.getLength(); i++) {
        g2.setColor(Color.WHITE); // sets the colour to white
        g2.setFont(new Font("Arial", Font.PLAIN, 10));
        titleY = getHeight()/4; // y coordinate of the title

        g2.drawString("Points: " + points, MARGIN, yOffset + 8);
        // for all the following, the number taken away from the x coordinate is half the width of the text. It is to center the text.
        int lines = 0; // number of lines of text, this is a multiplier so that the text lines are not on top of each other
        if (wantsToQuit) { // screen if the player wants to quit
            g2.setColor(new Color(0, 0, 0, 100));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("Quit program?", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            g2.drawString("Press 'y' to quit or 'n' to stay in the game.", MARGIN, titleY + Y_GAP_AFTER_TITLE);
        }else if(wantsToRestart){
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("Restart game?", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            g2.drawString("Press 'y' to restart or 'n' to continue", MARGIN, titleY+Y_GAP_AFTER_TITLE);
        }else if(wantsToChangeMode){
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("Change mode?", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            g2.drawString("This will reset your game, to go back", MARGIN, titleY+Y_GAP_AFTER_TITLE);
            lines++;
            g2.drawString("without changing press 'b'.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines = lines+2;
            g2.drawString("Press 'e' for "+MODE_NAMES[0]+", 'm' for "+MODE_NAMES[1]+", or 'h'", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("for "+MODE_NAMES[2]+". (Look closely, "+MODE_NAMES[2]+" mode has", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("low contrast fruit!)", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
        }else if(instructions){
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("Instructions:", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            g2.drawString("You are a snake that's trying to eat fruit.", MARGIN, titleY+Y_GAP_AFTER_TITLE);
            lines++;
            g2.drawString("Use WASD or arrow keys to move.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("Don't run into walls or yourself.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("At anytime: 'q' quits, 'p' pauses, 'r' restarts,", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("'m' will change the difficulty of the game,", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("and 'i' will open and close this page", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
        }else if(paused) { // screen if the game is paused
            g2.setColor(new Color(0, 0, 0, 100));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("Paused", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            g2.drawString("Press 'p' to unpause", MARGIN, titleY + Y_GAP_AFTER_TITLE);
            lines++;
            g2.drawString("Press 'q' to quit.", MARGIN, titleY + Y_GAP_AFTER_TITLE + Y_GAP_BETWEEN_LINES * lines);
            lines++;
            g2.drawString("Press 'i' to see instructions.", MARGIN, titleY + Y_GAP_AFTER_TITLE + Y_GAP_BETWEEN_LINES * lines);
        }else if(!firstKeyPressed){ // screen if the player has started
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("Welcome to Snake!", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            g2.drawString("Press any key to start.", MARGIN, titleY+Y_GAP_AFTER_TITLE);
            lines++;
            g2.drawString("You're on difficulty "+MODE_NAMES[currentMode]+".", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("Press 'm' to change the difficulty.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("Press 'i' for instructions.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("Have Fun!", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
        }else if(lost){ // screen if the player has lost
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.drawString("You lost!", MARGIN, titleY);
            g2.setFont(new Font("Arial", Font.PLAIN, 25));
            if(justGotHighScore){
                g2.drawString("You got a highscore of "+ points+"!", MARGIN, titleY+Y_GAP_AFTER_TITLE);
            } else {
                g2.drawString("You got "+points+" points!", MARGIN, titleY+Y_GAP_AFTER_TITLE);
                lines++;
                g2.drawString("Your highscore is "+highscore[currentMode]+".", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            }
            lines++;
            g2.drawString("Difficulty: "+MODE_NAMES[currentMode], MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines = lines + 2;
            g2.drawString("Press 'i' for instructions.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("Press 'q' to quit.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
            lines++;
            g2.drawString("Press 'r' to restart.", MARGIN, titleY+Y_GAP_AFTER_TITLE+Y_GAP_BETWEEN_LINES*lines);
        }
        g.drawImage(offScreenImage, 0, 0, null);
    } // public void paint(Graphics g) {

    /**
     * Method that is called when the fruit needs to be tested.
     * @return
     */
    public int fruitX(){
        return f.getX()*PIXELS_PER_BOX+xOffset; // returns the x coordinate of the fruit (pixels)
    } // returns the x coordinate of the fruit
    /**
     * Method that is called when the fruit needs to be tested.
     * @return
     */
    public int fruitY(){
        return f.getY()*PIXELS_PER_BOX+yOffset-1; // returns the y coordinate of the fruit (pixels)
    } // returns the y coordinate of the fruit
}