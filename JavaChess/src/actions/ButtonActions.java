package actions;

import chessgui.WinGUI;
import maps.PiecePointMap;
import utils.PieceColor;
import utils.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonActions {

    private static PieceColor color = PieceColor.BLACK;

    public static void addActionsToButtons(JButton[][] jButtons, JPanel jPanel, JFrame f, PiecePointMap piecePointMap, List<Point> fromToPointList){
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[0].length; j++) {
                addActionsToButton(jButtons[i][j], jPanel, f, piecePointMap, fromToPointList);
            }
        }
    }


    public static void addActionsToButton(JButton b, JPanel jPanel, JFrame f, PiecePointMap piecePointMap, java.util.List<Point> fromToPointList){
        b.addActionListener(new ActionListener() {
            // add move action to each button
            public void actionPerformed(ActionEvent e) {
//                System.out.println(fromToPointList);
                if (fromToPointList.size() == 1){
                    // sometimes choose a wrong piece, no worries, you can re-choose another valid piece
                    if (MoveActions.isAnotherPiece(ButtonActions.pieceAt(jPanel, b), piecePointMap, color)){
                        fromToPointList.remove(0);
                        fromToPointList.add(ButtonActions.pieceAt(jPanel, b));
                    }
                    else{
                        Point from = fromToPointList.get(0);
                        Point to = ButtonActions.pieceAt(jPanel, b);
                        if (MoveActions.isValidMove(jPanel, from, to, piecePointMap)){
                            fromToPointList.add(ButtonActions.pieceAt(jPanel, b));
                            MoveActions.move(jPanel, f, piecePointMap, fromToPointList);
                            f.validate();
                            fromToPointList.remove(1);
                            fromToPointList.remove(0);
                            if (WinActions.isPawnRaceWin(to, color)){
                                WinGUI.winView(f, jPanel);
                            }
                            switchColor();

                        }
                    }


                }
                else{
                    if (MoveActions.isValidPiece(ButtonActions.pieceAt(jPanel, b), piecePointMap, color)){
//                        System.out.println(ButtonActions.pieceAt(jPanel, b));

                        fromToPointList.add(ButtonActions.pieceAt(jPanel, b));

                    }

                }
            }
        });
    }

    private static void switchColor(){
        if (color == PieceColor.BLACK){
            color = PieceColor.WHITE;
        }
        else color = PieceColor.BLACK;
    }
    public static Point pieceAt(JPanel jPanel, JButton b){
        int idx = 0;
        Component[] components = jPanel.getComponents();
        for (int i = 0; i < components.length ; i++) {
            if (b == components[i]){
                idx = i;
                break;
            }
        }
        int row = idx / 9 - 1;
        int col = idx % 9 - 1;
        return new Point(row, col);
    }

}
