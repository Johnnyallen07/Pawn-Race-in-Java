package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;

import java.util.HashMap;
import java.util.Map;

public class PieceResourceMap {
    public static String filePlaceByName(Pair<PieceName, PieceColor> pieceNamePieceColorPair){
        Map<Pair<PieceName, PieceColor>, String> pieceNameStringMap = new HashMap<>();
        pieceNameStringMap.put(new Pair<>(PieceName.bishop, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\bishop_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.bishop, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\bishop_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.pawn, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\pawn_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.pawn, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\pawn_black.png");
        return pieceNameStringMap.get(pieceNamePieceColorPair);
    }
}