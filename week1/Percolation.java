import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {


    // static variable
    private static final boolean OPEN = true;

    // some private variable
    private boolean[][] board;
    private final int rowcolBound;
    // the number of open sites
    private int openCount;
    // can be made final since uniUF is initialized only in the constructor
    private final WeightedQuickUnionUF uniUF;
    private boolean[] connectedToBottom;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("illegal size value");
        }
        board = new boolean[n][n];
        rowcolBound = n;
        int points = n * n;
        openCount = 0;
        // use only the top virtual node
        uniUF = new WeightedQuickUnionUF(points + 1);
        connectedToBottom = new boolean[points + 1];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("illegal index");
        }
        // allowing open to called on the opened site for more than one time
        if (!board[row - 1][col - 1]) {
            openCount++;
        }
        board[row - 1][col - 1] = OPEN;
        unionNeighbors(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("illegal index");
        }
        return board[row - 1][col - 1] == OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("illegal index");
        }
        // convert the (row,col) to index

        return uniUF.connected(0, xy2VectIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        // return uniUF.connected(0, points + 1);
        return connectedToBottom[uniUF.find(0)];
    }

    // test client (optional)
    public static void main(String[] args) {
        // test the percolates
        Percolation percNew = new Percolation(4);
        percNew.open(2, 2);
        percNew.open(1, 2);
        StdOut.println("percolates? " + percNew.percolates());
    }

    // test if the index is valid
    private boolean isIndexValid(int row, int col) {
        return !(row < 1 || row > rowcolBound || col < 1 || col > rowcolBound);
    }

    // convert the (row,col) to a one dimensional coordinate
    private int xy2VectIndex(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new IllegalArgumentException("illegal index");
        }
        int temp = (row - 1) * rowcolBound + (col - 1) + 1;
        return temp;
    }

    // union the neighbors if necessary
    private void unionNeighbors(int row, int col) {
        // the top row
        int coord = xy2VectIndex(row, col);
        boolean connect2Bot = false;
        // each union will modify the connectedToBottom array
        // if one of the subtree is connected to Bottom then the merged tree
        // is also connected to bottom
        if (row == 1) {
            uniUF.union(coord, 0);
        }
        if (row == rowcolBound) {
            // uniUF.union(coord, points + 1);
            // uniUF.union(coord + rowcolBound, points + 1);
            connectedToBottom[uniUF.find(coord)] = true;
            connect2Bot = true;
        }
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                if (!connect2Bot && connectedToBottom[uniUF.find(coord - rowcolBound)]) {
                    connect2Bot = true;
                }
                uniUF.union(coord, coord - rowcolBound);
                if (connect2Bot) {
                    connectedToBottom[uniUF.find(coord)] = true;
                }
            }
        }
        if (row < rowcolBound) {
            if (isOpen(row + 1, col)) {
                if (!connect2Bot && connectedToBottom[uniUF.find(coord + rowcolBound)]) {
                    connect2Bot = true;
                }
                uniUF.union(coord, coord + rowcolBound);
                if (connect2Bot) {
                    connectedToBottom[uniUF.find(coord)] = true;
                }
            }
        }
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                if (!connect2Bot && connectedToBottom[uniUF.find(coord - 1)]) {
                    connect2Bot = true;
                }
                uniUF.union(coord, coord - 1);
                if (connect2Bot) {
                    connectedToBottom[uniUF.find(coord)] = true;
                }
            }
        }
        if (col < rowcolBound) {
            if (isOpen(row, col + 1)) {
                if (!connect2Bot && connectedToBottom[uniUF.find(coord + 1)]) {
                    connect2Bot = true;
                }
                uniUF.union(coord, coord + 1);
                if (connect2Bot) {
                    connectedToBottom[uniUF.find(coord)] = true;
                }
            }
        }
    }
}
