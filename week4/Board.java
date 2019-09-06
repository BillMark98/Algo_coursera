/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int[][] board;
    // the size of the board;
    private final int n;

    public Board(int[][] tiles) {
        int col = tiles.length;
        int row = tiles[0].length;
        assert col == row;
        assert col >= 2 && col <= 128;
        n = col;
        board = new int[col][col];
        int bound = col * col - 1;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                assert tiles[i][j] >= 0 && tiles[i][j] <= bound;
                board[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        // use the given implementation
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
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
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != correctValue(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist += movesToGoal(i, j);
            }
        }
        return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y)

    // all neighboring boards
    public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()

    // unit testing (not graded)
    public static void main(String[] args) {

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
}
