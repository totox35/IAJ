
public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        @Override
        public int compareTo(SearchNode searchNode) {
            return 0;
        }
    }

    public Solver(Board initial) {
    }

    private void aStar() {
    }

    public boolean isSolvable() {
        return false;
    }

    public int moves() {
	return -1;
    }

    public Iterable<Board> solution() {
        return null;
    }

    // public static void main(String[] args) {
    //     // create initial board from file
    //     //In in = new In(args[0]);
    //     int N = in.readInt();
    //     int[][] blocks = new int[N][N];
    //     for (int i = 0; i < N; i++)
    //         for (int j = 0; j < N; j++)
    //             blocks[i][j] = in.readInt();
    //     Board initial = new Board(blocks);

    //     // solve the puzzle
    //     Solver solver = new Solver(initial);

    //     // print solution to standard output
    //     if (!solver.isSolvable())
    //         System.out.println("No solution possible");
    //     else {
    //         System.out.println("Minimum number of moves = " + solver.moves());
    //         for (Board board : solver.solution())
    //             System.out.println(board);
    //     }
    // }
}
