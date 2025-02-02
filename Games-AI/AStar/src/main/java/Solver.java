import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.MinPQ;



public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int nbMoves;
        private int priority;
        private SearchNode parent;


        public SearchNode(SearchNode parent, Board board, int nbMoves, int priority) {
            this.parent = parent;
            this.board = board;
            this.nbMoves = nbMoves;
            this.priority = priority;
        }
        @Override
        public int compareTo(SearchNode searchNode) {
            if(priority < searchNode.priority){
                return -1;
            }
            if(priority > searchNode.priority){
                return 1;
            }
            return 0;
        }
    }

    private Board initial;
    private boolean solvable=false;
    private SearchNode sol;

    public Solver(Board initial) {
        this.initial = initial;
        aStar();
    }

    private void aStar() {
        MinPQ<SearchNode> priorityQueue = new MinPQ<SearchNode>();
        priorityQueue.insert(new SearchNode(null, initial, 0, heur(initial)));
        SearchNode current = priorityQueue.delMin();
        while (!current.board.isGoal()) {
            for (Board neighbor : current.board.neighbors()) {
                if(current.parent ==null || !neighbor.equals(current.parent.board)) {
                    priorityQueue.insert(new SearchNode(current, neighbor, current.nbMoves + 1, heur(neighbor)));
                }
            }
            current = priorityQueue.delMin();
        }
        if(current.board.isGoal()){
            solvable=true;
            sol=current;
        }
    }

    private int heur(Board b){
        return b.manhattan();
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if(!solvable){
            return -1;
        }
	    return sol.nbMoves;
    }

    public Iterable<Board> solution() {
        if(solvable){
            Stack<Board> res = new Stack<>();
            for(SearchNode s = sol; s != null; s = s.parent){
                res.push(s.board);
            }
            return res;
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        // create initial board from file
//        In in = new In(args[0]);
//        int N = in.readInt();
//        int[][] blocks = new int[N][N];
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                blocks[i][j] = in.readInt();
        Board initial = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                System.out.println(board);
        }
    }
}
