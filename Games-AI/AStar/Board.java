package AStar;
public class Board {

    private int[][] tiles;
    private int N;

    public Board(int[][] tiles) {
        int dimension = tiles.length;
        for (int i = 0; i < dimension; i++) {
            if (tiles[i].length != dimension) {
                throw new IllegalArgumentException("Le tableau doit être de dimensions NxN");
            }
        }
        this.tiles = tiles;
        N = dimension;
    }

    public int dimension(){
        return N;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * tiles.length + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0) {
                    int targetRow = (tiles[i][j] - 1) / N;
                    int targetCol = (tiles[i][j] - 1) % N;
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
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
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

    public boolean isGoal() {
        int obj=1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tiles[i][j]!=obj){
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public Board twin() {
        return null;
    }

    public Iterable<Board> neighbors() {
         return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}