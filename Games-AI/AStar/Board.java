package AStar;
public class Board {

    private int[][] tiles;
    private int dim;

    public Board(int[][] tiles) {
        int dimension = tiles.length;
        for (int i = 0; i < dimension; i++) {
            if (tiles[i].length != dimension) {
                throw new IllegalArgumentException("Le tableau doit être de dimensions NxN");
            }
        }
        this.tiles = tiles;
        dim = dimension;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * tiles.length + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tiles[i][j] != 0) {
                    int targetRow = (tiles[i][j] - 1) / dim;
                    int targetCol = (tiles[i][j] - 1) % dim;
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
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
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
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                str += tiles[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}