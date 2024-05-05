import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main {
    Color BACKGROUND_COLOR = new Color(84,145,89);
    JMenuBar menuBar;
    JMenuItem menuItem;
    JButton duckButton;
    Canvas myGraphic;
    JPanel panel = new JPanel();
    Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        Scanner kb = new Scanner(System.in);
        kb.nextLine();

        this.setTitle(kb.nextLine());
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.getContentPane().setPreferredSize(new Dimension(w,h));
        this.panel.setPreferredSize(new Dimension(w,h));
        Snake s = new Snake();
        s.hello();
    }
}