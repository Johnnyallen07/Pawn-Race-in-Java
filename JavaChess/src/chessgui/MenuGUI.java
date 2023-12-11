package chessgui;

import actions.MenuActions;
import utils.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
    public static void menus(JFrame f, JPanel jPanel, MenuActions actions, JMenuBar jb){



        JMenu newMenu = new JMenu("New Game");




        JMenu m1 = new JMenu("Pawn Race");
        JMenu m2 = new JMenu("Standard Game (Comming Soon)");
        // randomize gap or specify the gap
        JMenuItem randomGap = new JMenuItem("Random Gap");
        randomGap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actions.initBoard((int) (Math.random() * 8), (int) (Math.random() * 8));
                f.validate();
            }
        });
        m1.add(randomGap);
        JMenuItem specGap = new JMenuItem("Spec Gap");
        specGap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextFrame textFrame = new TextFrame();
                System.out.println("hahaha The Thread is not synchronized! F**K");


                Pair<Integer, Integer> position = textFrame.getPosition();

                actions.initBoard(position.first, position.second);
                f.validate();


            }
        });
        m1.add(specGap);


        newMenu.add(m1);
        newMenu.add(m2);

        jb.add(newMenu);

        f.setJMenuBar(jb);
    }
}
