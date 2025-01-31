package AStar;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] tiles;
    private int N;
    private int videI;
    private int videJ;
    private int manhattanValue = -1;
    private int hammingValue = -1;

    public Board(int[][] tiles) {
        int dimension = tiles.length;
        for (int i = 0; i < dimension; i++) {
            if (tiles[i].length != dimension) {
                throw new IllegalArgumentException("Le tableau doit être de dimensions NxN");
            }
        }
        this.tiles = tiles;
        N = dimension;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    videI = i;
                    videJ = j;
                }
            }
        }
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        if (hammingValue != -1)
            return hammingValue;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * tiles.length + j + 1) {
                    count++;
                }
            }
        }
        hammingValue = count;
        return count;
    }

    public int manhattan() {
        if (manhattanValue != -1)
            return manhattanValue;
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
        manhattanValue = dist;
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

    //Méthode pour vérifier si le board est le but recherché : cad distance de Mahattan à 0
    public boolean isGoal() {
        return manhattan() == 0;
    }

    public Board twin() {
        return null;
    }

    //Méthode pour obtenir les voisins d'un board en réalisant un déplacement de notre case vide.
    //Au maximum on en récupère 4, un pour chaque direction possible : si on est au milieu.
    public Iterable<Board> neighbors() {
        List<Board> res = new ArrayList<Board>();
        Board next;
        //Si videI-1 > 0, alors on est sur la ligne du milieu ou du bas, donc on peut échanger avec un voisin du haut
        if (videI - 1 > 0) {
            next = new Board(tiles);
            swap(next, videI, videJ, videI - 1, videJ);
            next.videI = videI - 1;
            res.add(next);
        }
        //Si videI+1 < N, alors on est sur la ligne du milieu ou du haut, donc on peut échanger avec un voisin du bas
        if(videI +1 < N){
            next = new Board(tiles);
            swap(next, videI, videJ, videI + 1, videJ);
            next.videI = videI + 1;
            res.add(next);            
        }
        //Si videJ-1 > 0, alors on est sur la colonne du milieu ou de droite, donc on peut échanger avec un voisin de gauche
        if(videJ - 1 > 0){
            next = new Board(tiles);
            swap(next, videI, videJ, videI, videJ-1);
            next.videJ = videJ-1;
            res.add(next); 
        }
        //Si videJ+1 < N, alors on est sur la colonne du milieu ou de gauche, donc on peut échanger avec un voisin de droite
        if(videJ + 1 <N){
            next = new Board(tiles);
            swap(next, videI, videJ, videI, videJ+1);
            next.videJ = videJ+1;
            res.add(next); 
        }
        return res;
    }

    //Méthode pour échanger deux cases
    private void swap(Board board, int i1, int j1, int i2, int j2) {
        byte tmp = board.tiles[i1][j1];
        board.tiles[i1][j1] = board.tiles[i2][j2];
        board.tiles[i2][j2] = tmp;
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