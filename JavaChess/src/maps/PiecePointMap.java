package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.Map;

public class PiecePointMap {
    private Map<Point, Pair<PieceName, PieceColor>> pointPieceNameandColorMap;

    public PiecePointMap(Map<Point, Pair<PieceName, PieceColor>> pointPieceNameandColorMap) {
        this.pointPieceNameandColorMap = pointPieceNameandColorMap;
    }

    public Map<Point, Pair<PieceName, PieceColor>> getPointPieceNameandColorMap() {
        return pointPieceNameandColorMap;
    }

    public void setPointPieceNameandColorMap(Map<Point, Pair<PieceName, PieceColor>> pointPieceNameandColorMap) {
        this.pointPieceNameandColorMap = pointPieceNameandColorMap;
    }
}

