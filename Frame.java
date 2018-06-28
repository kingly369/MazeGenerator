import javax.swing.*;
import java.awt.*;

public class Frame extends Canvas {


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setTitle("Maze");
        Maze a = new Maze();
        frame.add(a);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        a.start();
        
    }
}