/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int movesToGoal;
    private Stack<Board> solution;
    private boolean isBoardSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("board is null");
        }

        // MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        // minPQ.insert(new SearchNode(initial));
        // minPQ.insert(new SearchNode(initial.twin(), 0, null, true));
        //
        // boolean solFound = false;
        // while (!solFound) {
        //     SearchNode minNode = minPQ.delMin();
        //
        //     if (minNode.board.isGoal()) {
        //         solFound = true;
        //         if (!minNode.isTwin) {
        //             movesToGoal = minNode.moves;
        //             solution = new Stack<Board>();
        //             isBoardSolvable = true;
        //             while (minNode.parentNode != null) {
        //                 solution.push(minNode.board);
        //                 minNode = minNode.parentNode;
        //             }
        //             // push the initial board
        //             solution.push(minNode.board);
        //             break;
        //         }
        //         else {
        //             movesToGoal = -1;
        //             isBoardSolvable = false;
        //         }
        //     }
        //     for (Board bd : minNode.board.neighbors()) {
        //         // initial case
        //         if (minNode.parentNode == null) {
        //             minPQ.insert(
        //                     new SearchNode(bd, minNode.moves + 1, minNode, minNode.isTwin));
        //         }
        //         // it could be possible that the node added is already in minNode but with a smaller moves variable
        //         else if (!bd.equals(minNode.parentNode.board)) {
        //             minPQ.insert(
        //                     new SearchNode(bd, minNode.moves + 1, minNode, minNode.isTwin));
        //         }
        //     }
        // }


        // two min PQ version


        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        minPQ.insert(new SearchNode(initial));

        MinPQ<SearchNode> minTwinPQ = new MinPQ<SearchNode>();
        minTwinPQ.insert(new SearchNode(initial.twin(), 0, null, true));
        boolean solFound = false;
        while (!solFound) {
            SearchNode minNode = minPQ.delMin();

            if (minNode.board.isGoal()) {
                solFound = true;
                movesToGoal = minNode.moves;
                solution = new Stack<Board>();
                isBoardSolvable = true;
                while (minNode.parentNode != null) {
                    solution.push(minNode.board);
                    minNode = minNode.parentNode;
                }
                // push the initial board
                solution.push(minNode.board);
                break;
            }
            SearchNode minTwinNode = minTwinPQ.delMin();
            if (minTwinNode.board.isGoal()) {
                solFound = true;
                movesToGoal = -1;
                isBoardSolvable = false;
                break;
            }
            for (Board bd : minNode.board.neighbors()) {
                // initial case
                if (minNode.parentNode == null) {
                    minPQ.insert(
                            new SearchNode(bd, minNode.moves + 1, minNode, minNode.isTwin));
                }
                // it could be possible that the node added is already in minNode but with a smaller moves variable
                else if (!bd.equals(minNode.parentNode.board)) {
                    minPQ.insert(
                            new SearchNode(bd, minNode.moves + 1, minNode, minNode.isTwin));
                }
            }
            for (Board bd : minTwinNode.board.neighbors()) {
                if (minTwinNode.parentNode == null || !bd.equals(minTwinNode.parentNode.board)) {
                    minTwinPQ.insert(new SearchNode(bd, minTwinNode.moves + 1, minTwinNode, true));
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isBoardSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return movesToGoal;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final int priorityMan;
        // public final int priorityHamm;
        private final SearchNode parentNode;
        private final boolean isTwin;

        public SearchNode(Board bd, int move, SearchNode pNode, boolean twin) {
            board = bd;
            moves = move;
            priorityMan = moves + bd.manhattan();
            // priorityHamm = moves + bd.hamming();
            parentNode = pNode;
            isTwin = twin;
        }

        public SearchNode(Board bd) {
            board = bd;
            moves = 0;
            priorityMan = bd.manhattan();
            // priorityHamm = bd.hamming();
            parentNode = null;
            isTwin = false;
        }

        public int compareTo(SearchNode s1) {
            return priorityMan - s1.priorityMan;
        }
        // private static class ByHamming implements Comparator<SearchNode> {
        //     public int compare(SearchNode n1, SearchNode n2) {
        //         return n1.priorityHamm - n2.priorityHamm;
        //     }
        // }

        // public void setMoves(int n) {
        //     moves = n;
        // }
        //
        // public int getMoves() {
        //     return moves;
        // }
        //
        // public SearchNode getParentNode() {
        //     return parentNode;
        // }
        //
        // public void setParentNode(SearchNode pNode) {
        //     parentNode = pNode;
        // }
    }
}
