package actions;


import utils.PieceColor;
import utils.Point;


public class WinActions {
    public static boolean isPawnRaceWin(Point point, PieceColor color){
        if (color == PieceColor.WHITE){
            return point.getRow() == 0 ;
        }
        else return point.getRow() == 7;
    }
}
