/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final char[][] board;
    // the size of the board;
    private final int n;
    private final int hammingDist;
    private final int manhattanDist;

    public Board(int[][] tiles) {
        int row = tiles.length;
        int col = tiles[0].length;
        assert col == row;
        assert col >= 2 && col <= 128;
        n = col;
        board = new char[col][col];
        int bound = col * col - 1;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                assert tiles[i][j] >= 0 && tiles[i][j] <= bound;
                board[i][j] = (char) tiles[i][j];
            }
        }
        int hammCount = 0;
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 0 tile should not be counted;
                if (board[i][j] == 0) {
                    continue;
                }
                dist += movesToGoal(i, j);
                if (board[i][j] != correctValue(i, j)) {
                    hammCount++;
                }
            }
        }
        hammingDist = hammCount;
        manhattanDist = dist;
    }

    // for the Board constructor in the pushNeighbors
    private Board(char[][] tiles) {
        n = tiles[0].length;
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tiles[i][j];
            }
        }
        int hammCount = 0;
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 0 tile should not be counted;
                if (board[i][j] == 0) {
                    continue;
                }
                dist += movesToGoal(i, j);
                if (board[i][j] != correctValue(i, j)) {
                    hammCount++;
                }
            }
        }
        hammingDist = hammCount;
        manhattanDist = dist;
    }

    // string representation of this board
    public String toString() {
        // use the given implementation
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", (int) board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return hammingDist;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattanDist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        return equalBoard(that);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // find the 0 first
        int posx = 0, posy = 0;
        boolean found = false;
        for (posx = 0; posx < n; posx++) {
            for (posy = 0; posy < n; posy++) {
                if (board[posx][posy] == 0) {
                    found = true;
                    break;
                }
            }
            // have to use a flag here
            // because the break will only jump out of the inner for loop
            if (found) {
                break;
            }
        }
        Queue<Board> queue = new Queue<Board>();
        // int[][] arr = new int[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         arr[i][j] = board[i][j];
        //     }
        // }

        // left wall
        if (posx == 0) {
            // top left corner
            if (posy == 0) {
                pushNeighbor(posx, posy, 0, 1, queue);

                pushNeighbor(posx, posy, 1, 0, queue);
            }
            // top right corner
            else if (posy == n - 1) {
                pushNeighbor(posx, posy, posx, posy - 1, queue);

                pushNeighbor(posx, posy, posx + 1, posy, queue);
            }
            else {
                pushNeighbor(posx, posy, posx, posy - 1, queue);
                pushNeighbor(posx, posy, posx, posy + 1, queue);
                pushNeighbor(posx, posy, posx + 1, posy, queue);
            }
        }
        else if (posx == n - 1) {
            // bottom left corner
            if (posy == 0) {
                pushNeighbor(posx, posy, posx - 1, posy, queue);
                pushNeighbor(posx, posy, posx, posy + 1, queue);
            }
            else if (posy == n - 1) {
                pushNeighbor(posx, posy, posx - 1, posy, queue);
                pushNeighbor(posx, posy, posx, posy - 1, queue);
            }
            else {
                pushNeighbor(posx, posy, posx - 1, posy, queue);
                pushNeighbor(posx, posy, posx, posy - 1, queue);
                pushNeighbor(posx, posy, posx, posy + 1, queue);
            }
        }
        else if (posy == 0) {
            pushNeighbor(posx, posy, posx - 1, posy, queue);
            pushNeighbor(posx, posy, posx + 1, posy, queue);
            pushNeighbor(posx, posy, posx, posy + 1, queue);
        }
        else if (posy == n - 1) {
            pushNeighbor(posx, posy, posx - 1, posy, queue);
            pushNeighbor(posx, posy, posx + 1, posy, queue);
            pushNeighbor(posx, posy, posx, posy - 1, queue);
        }
        else {
            pushNeighbor(posx, posy, posx - 1, posy, queue);
            pushNeighbor(posx, posy, posx + 1, posy, queue);
            pushNeighbor(posx, posy, posx, posy - 1, queue);
            pushNeighbor(posx, posy, posx, posy + 1, queue);
        }
        return queue;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int posx = 0, posy = 0;
        int destx = 1, desty = 1;
        // the test says twin must return the same Board every time called so dont use random version
        // while (board[posx][posy] == 0) {
        //     posx = StdRandom.uniform(n);
        //     posy = StdRandom.uniform(n);
        // }
        // if (posx - 1 >= 0) {
        //     destx = posx - 1;
        //     desty = posy;
        //     if (board[destx][desty] != 0) {
        //         swap(board, posx, posy, destx, desty);
        //         Board b1 = new Board(board);
        //         swap(board, posx, posy, destx, desty);
        //         return b1;
        //     }
        // }
        // should use if instead of else if
        // because if the first if is true while the inner if is false
        // then it will ignore the following else if
        // if (posx + 1 <= n - 1) {
        //     destx = posx + 1;
        //     desty = posy;
        //     if (board[destx][desty] != 0) {
        //         swap(board, posx, posy, destx, desty);
        //         Board b1 = new Board(board);
        //         swap(board, posx, posy, destx, desty);
        //         return b1;
        //     }
        // }
        // if (posy - 1 >= 0) {
        //     destx = posx;
        //     desty = posy - 1;
        //     if (board[destx][desty] != 0) {
        //         swap(board, posx, posy, destx, desty);
        //         Board b1 = new Board(board);
        //         swap(board, posx, posy, destx, desty);
        //         return b1;
        //     }
        // }
        // if (posy + 1 <= n - 1) {
        //     destx = posx;
        //     desty = posy + 1;
        //     if (board[destx][desty] != 0) {
        //         swap(board, posx, posy, destx, desty);
        //         Board b1 = new Board(board);
        //         swap(board, posx, posy, destx, desty);
        //         return b1;
        //     }
        // }

        if (board[posx][posy] == 0) {
            posx = 1;
            posy = 0;
        }
        if (board[destx][desty] == 0) {
            destx = 0;
            desty = 1;
        }
        swap(board, posx, posy, destx, desty);
        Board b1 = new Board(board);
        swap(board, posx, posy, destx, desty);
        return b1;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // int n = 3;
        int[][] arr = {
                { 0, 1, 3 },
                { 4, 2, 5 },
                { 7, 8, 6 }
        };
        // int[][] arr = new int[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         arr[i][j] = i * n + j;
        //     }
        // }

        // int[][] temp = arr;
        // int tempV;
        // tempV = temp[1][2];
        // temp[1][2] = temp[2][1];
        // temp[2][1] = tempV;
        //
        // swap(arr, 0, 0, 0, 3);
        // StdOut.println("the array: ");
        print2DArr(arr);

        Board board = new Board(arr);
        StdOut.println("the board:");
        StdOut.println(board);
        StdOut.println(
                "The hamming: " + board.hamming() + " \n the manhattan : " + board.manhattan());
        Iterable<Board> iter = board.neighbors();
        StdOut.println("The neighbors:");
        for (
                Board b : iter) {
            StdOut.println(b);
        }

        Board twinB = board.twin();
        StdOut.println("an arbitrary twin");
        StdOut.println(twinB);

    }

    // give back the goal value at pos i, j
    private int correctValue(int i, int j) {
        int result = i * n + j + 1;
        if (result == n * n) {
            // should be the 0
            result = 0;
        }
        return result;
    }

    private int movesToGoal(int i, int j) {
        int result = correctValue(i, j);
        int actual = board[i][j];
        if (result == actual) {
            return 0;
        }
        else {
            // calculate the correct position for the value actual
            int posx, posy;
            if (actual == 0) {
                posx = n - 1;
                posy = n - 1;
            }
            else {
                posy = (actual - 1) % n;
                posx = (actual - 1) / n;
            }
            // calculate the manhattan dist
            int deltax = Math.abs(posx - i);
            int deltay = Math.abs(posy - j);
            int dist = deltax + deltay;
            return dist;
        }
    }

    private boolean equalBoard(Board bod) {
        if (n != bod.n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bod.board[i][j] != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void print2DArr(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                StdOut.print(arr[i][j] + " ");
            }
            StdOut.println();
        }
    }

    private static void swap(char[][] arr, int ax, int ay, int bx, int by) {
        char temp = arr[ax][ay];
        arr[ax][ay] = arr[bx][by];
        arr[bx][by] = temp;
    }


    private void pushNeighbor(int posx, int posy, int nx, int ny, Queue<Board> queue) {
        swap(board, posx, posy, nx, ny);
        Board bo = new Board(board);
        swap(board, posx, posy, nx, ny);
        queue.enqueue(bo);
    }
}
