package chessgui;

import actions.WinActions;

import javax.swing.*;
import java.awt.*;

public class WinGUI {
    public static void winView(JFrame f, JPanel jPanel){
        JTextArea winText = new JTextArea(5, 5);
        winText.setText("Win!!!");
        winText.setSize(new Dimension(200, 200));

        f.add(winText, BorderLayout.WEST);
        ButtonGUI.deleteActions(ButtonGUI.getButtons(jPanel));
        f.validate();

    }
}
