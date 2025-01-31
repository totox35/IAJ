package AStar;
public class Board {

    private int[][] tiles;

    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * tiles.length + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int dist = 0;
        int n = tiles.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    int targetRow = (tiles[i][j] - 1) / n;
                    int targetCol = (tiles[i][j] - 1) % n;
                    dist += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return dist;
    }

    public boolean equals(Object y) throws IllegalArgumentException {
        if (y instanceof Board) {
            Board board = (Board) y;
            if (board.tiles.length != tiles.length) {
                return false;
            } else {
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles.length; j++) {
                        if (tiles[i][j] != board.tiles[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } else {
            throw new IllegalArgumentException("L'objet passé en paramètre doit être une Board");
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                str += tiles[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}