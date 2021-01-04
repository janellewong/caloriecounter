package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class CalorieCounterGUI extends JFrame {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame("Calorie Counter");

                frame.setSize(920, 1000);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            }
        });




    }



}
