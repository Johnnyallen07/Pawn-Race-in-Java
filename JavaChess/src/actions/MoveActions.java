package actions;

import maps.PiecePointMap;
import utils.*;
import chessgui.ButtonGUI;
import maps.PieceResourceMap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import utils.Point;

public class MoveActions {

    // 0 means white, 1 means black
    private final static boolean[][] recordENPASSENTPawns = new boolean[8][2];

    // convert 2D in chessboardsqaure to index of component of Jpanel
    public static void move(JPanel jp, JFrame f, PiecePointMap piecePointMap, List<Point> recordList) {
        Point from = recordList.get(0);
        Point to = recordList.get(1);
        int xFrom = from.getRow();
        int yFrom = from.getCol();
        int xTo = to.getRow();
        int yTo = to.getCol();
        // calculator index in jp
        Map<Point, Pair<PieceName, PieceColor>> pointPieceNameandColorMap = piecePointMap.getPointPieceNameandColorMap();
        int fromIndex = (xFrom + 1) * 9 + (yFrom + 1);

        int toIndex = (xTo + 1) * 9 + (yTo + 1);

        // get resources each other

        String resourseFrom = PieceResourceMap.filePlaceByName(pointPieceNameandColorMap.get(recordList.get(0)));
        String resourseTo = PieceResourceMap.filePlaceByName(pointPieceNameandColorMap.get(recordList.get(1)));
        JButton fromButton = ButtonGUI.createButton(/*resourseTo,*/ fromIndex);
        JButton toButton = ButtonGUI.createButton(resourseFrom, toIndex);

        // update jbuttons
        jp.remove(fromIndex);
        jp.add(fromButton, fromIndex);
        jp.remove(toIndex);
        jp.add(toButton, toIndex);
        Component[] components = jp.getComponents();

        // add actions to button
        ButtonActions.addActionsToButton(fromButton, jp, f, piecePointMap, recordList);
        ButtonActions.addActionsToButton(toButton, jp, f, piecePointMap, recordList);

        // update map
        Pair<PieceName, PieceColor> infoFrom = pointPieceNameandColorMap.get(recordList.get(0));
        Pair<PieceName, PieceColor> infoTo = pointPieceNameandColorMap.get(recordList.get(1));
        pointPieceNameandColorMap.put(recordList.get(0), infoTo);
        pointPieceNameandColorMap.put(recordList.get(1), infoFrom);

    }

    public static boolean isValidPiece(Point point, PiecePointMap piecePointMap, PieceColor color) {
        PieceName aPiece = piecePointMap.getPointPieceNameandColorMap().get(point).first;
        PieceColor aColor = piecePointMap.getPointPieceNameandColorMap().get(point).second;
        return aPiece != PieceName.grid && aColor == color;
    }

    public static boolean isAnotherPiece(Point point, PiecePointMap piecePointMap, PieceColor color){
        PieceColor aColor = piecePointMap.getPointPieceNameandColorMap().get(point).second;
        return aColor == color;
    }

    public static boolean isValidMove(JPanel jPanel, Point from, Point to, PiecePointMap piecePointMap){
         PieceName name = piecePointMap.getPointPieceNameandColorMap().get(from).first;
         switch (name) {
             case pawn:
                 return isValidPawnMove(jPanel, from, to, piecePointMap);
             default:
                 return false;
         }
    }

    // replace piece as simple grid
    private static void deletePiece(Point point, JPanel jPanel, PiecePointMap piecePointMap){
        int row = point.getRow();
        int col = point.getCol();
        JButton b = (JButton) jPanel.getComponent((row + 1) * 9 + col + 1);
        b.setMargin(new Insets(0,0,0,0));
        // our chess pieces are 64x64 px in size, so we'll
        // 'fill this in' using a transparent icon
        ImageIcon icon = new ImageIcon(
                new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

        if ((row + col) % 2 == 0) {
            b.setBackground(Color.WHITE);
        } else {
            b.setBackground(Color.GRAY);
        }
        b.setIcon(icon);
        piecePointMap.getPointPieceNameandColorMap().put(point, new Pair<>(PieceName.grid, PieceColor.NULL));

    }

    private static boolean isValidPawnMove(JPanel jPanel, Point from, Point to, PiecePointMap piecePointMap){
        int xFrom = from.getRow();
        int yFrom = from.getCol();
        int xTo = to.getRow();
        int yTo = to.getCol();

        Map<Point, Pair<PieceName, PieceColor>> map = piecePointMap.getPointPieceNameandColorMap();
        PieceColor fromColor = map.get(from).second;
        PieceColor toColor = map.get(to).second;
        // PEACEFUL
        if (yFrom == yTo){
            if (fromColor == PieceColor.BLACK){
                // a special case: when pawn starts from the origin
                if (xFrom == 1){
                    if (xTo - xFrom == 2){
                        recordENPASSENTPawns[yTo][1] = true;
                    }
                    else if (xTo - xFrom == 1){
                        recordENPASSENTPawns[yTo][1] = false;
                    }
                    else return false;
                    return true;
                }
                if (xTo - xFrom == 1){
                    return true;
                }
            }
            else {
                if (xFrom == 6){
                    if (xFrom - xTo == 2){
                        recordENPASSENTPawns[yTo][0] = true;
                    }
                    else if (xFrom - xTo == 1){
                        recordENPASSENTPawns[yTo][0] = false;
                    }
                    else return false;
                    return true;
                }
                if (xFrom - xTo == 1){
                    return true;
                }
            }
        }
        // CAPTURE
        if (Math.abs(yFrom - yTo) == 1){
            if (fromColor == PieceColor.BLACK){
                // EN-PASSENT
                // black case
                if (xFrom == 4){
                    Point whitePosition = new Point(xFrom, yTo);
                    if (map.get(whitePosition).second == PieceColor.WHITE &&
                            map.get(whitePosition).first == PieceName.pawn &&
                    recordENPASSENTPawns[yTo][0]){
                        deletePiece(whitePosition, jPanel, piecePointMap);
                        return true;
                    }
                }
                return (xTo - xFrom == 1 && toColor == PieceColor.WHITE);
            }
            if (fromColor == PieceColor.WHITE){
                // EN-PASSENT
                // white case
                if (xFrom == 3){
                    Point blackPosition = new Point(xFrom, yTo);
                    if (map.get(blackPosition).second == PieceColor.BLACK &&
                            map.get(blackPosition).first == PieceName.pawn &&
                            recordENPASSENTPawns[yTo][1]){
                        deletePiece(blackPosition, jPanel, piecePointMap);
                        return true;
                    }
                }
                return (xFrom - xTo == 1 && toColor == PieceColor.BLACK);
            }

        }


        return false;
    }
}
