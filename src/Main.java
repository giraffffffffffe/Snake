import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame implements ActionListener{
    public Color BACKGROUND_COLOR = new Color(84,170,89); //colour that the background is
    private final String[] MENU_NAMES = {"Help", "Configure", "Actions"};
    private final String[] MENU0_OPTIONS = {"Instructions"}; // options in Help menu
    private final String[] MENU1_OPTIONS = {"Keys", "Snake Speed"}; // options in configure menu
    private final String[] MENU2_OPTIONS = {"Pause"}; // options in actions menu
    private int windWidth = 500; // initial width of window
    private int windHeight = 500; // initial height of window
    JMenuBar menuBar; // creates a menubar
    JMenuItem menuItem; // creates a menu item
    Canvas myGraphic; // canvas that is used for the graphics
    JPanel panel = new JPanel(); // initialises JPanel
    Scanner kb = new Scanner(System.in); // initialises keyboard
    public static void main(String[] args) { // starts program
        new Main();
    }

    public Main(){ // runs initially

        this.setTitle("Snake!"); // sets title of Window to "Snake!"
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.getContentPane().setPreferredSize(new Dimension(windWidth,windHeight));
        this.panel.setPreferredSize(new Dimension(windWidth,windHeight));
        Snake s = new Snake(); // initialises Snake
        s.hello();

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
        System.out.println("createMenu");
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
    public void actionPerformed(ActionEvent e) {
    }
}