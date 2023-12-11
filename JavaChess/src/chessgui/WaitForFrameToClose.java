package chessgui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WaitForFrameToClose {

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Frame 1");
        frame1.setSize(300, 200);

        JButton openFrame2Button = new JButton("Open Frame 2");
        openFrame2Button.addActionListener(e -> {
//            frame1.setVisible(false);

            // Create and show the second frame
            JFrame frame2 = new JFrame("Frame 2");
            frame2.setSize(300, 200);

            JButton closeButton = new JButton("Close Frame 2");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Perform actions before closing the second frame if needed

                    // Close the second frame
                    frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
                }
            });

            frame2.add(closeButton);
            frame2.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Perform actions after the second frame is closed
                    System.out.println("Frame 2 closed, continuing with the program...");
                    // Here, you can perform any action that needs to be done after Frame 2 is closed.
                    frame1.setVisible(true); // Show the first frame again if needed
                }
            });

            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setVisible(true);
        });

        frame1.add(openFrame2Button);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        System.out.println("??????");
    }
}