package actions;

import maps.PiecePointMap;
import utils.*;
import chessgui.BoardRepaint;
import chessgui.ButtonGUI;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class MenuActions {

    private final JPanel chessBoard;
    private final JFrame f;
    private final PiecePointMap piecePointMap;
    private final List<Point> recordList;


    public MenuActions(JPanel chessBoard, JFrame f, PiecePointMap piecePointMap, List<Point> recordList) {
        this.chessBoard = chessBoard;
        this.f = f;
        this.piecePointMap = piecePointMap;
        this.recordList = recordList;
    }

    // add chess pieces to chessBoard
    public void initBoard(int blackGap, int whiteGap) {
        BoardRepaint.repaintAll(chessBoard, piecePointMap);
        f.validate();

        // black init
        int row = 1;
        Map<Point, Pair<PieceName, PieceColor>> pointPieceNameandColorMap = piecePointMap.getPointPieceNameandColorMap();

        for (int col = 0; col < 8; col++) {
            if (col == blackGap) continue;
            JButton b = ButtonGUI.createButton("D:\\Desktop\\JavaChess\\src\\pieces\\pawn_black.png", row + col);
            chessBoard.remove(9 * (row+1) + col + 1);
            chessBoard.add(b, (9 * (row+1) + col + 1));
            // update b before take actions
            ButtonActions.addActionsToButton(b, chessBoard, f, piecePointMap, recordList);

            pointPieceNameandColorMap.put(new Point(row, col), new Pair<>(PieceName.pawn, PieceColor.BLACK));
        }

        // white init
        row = 6;

        for (int col = 0; col < 8; col++) {
            if (col == whiteGap) continue;
            JButton b = ButtonGUI.createButton("D:\\Desktop\\JavaChess\\src\\pieces\\pawn_white.png", row + col);
            chessBoard.remove(9 * (row+1) + col + 1);
            chessBoard.add(b, (9 * (row+1) + col + 1));
            // update b before take actions
            ButtonActions.addActionsToButton(b, chessBoard, f, piecePointMap, recordList);

            pointPieceNameandColorMap.put(new Point(row, col), new Pair<>(PieceName.pawn, PieceColor.WHITE));
        }




    }
}
