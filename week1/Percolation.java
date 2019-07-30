import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class Percolation {
    // some private variable

    private int[][] board;
    private int points;
    private int rowcolBound;
    // the number of open sites
    private int openCount;
    private WeightedQuickUnionUF uniUF;
    private boolean[] connectedToBottom;


    final static int Open = 1;
    // final static int Full = 2;
    final static int Closed = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        board = new int[n][n];
        rowcolBound = n;
        points = n * n;
        openCount = 0;
        // use only the top virtual node
        uniUF = new WeightedQuickUnionUF(points + 1);
        connectedToBottom = new boolean[points + 1];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new java.lang.IllegalArgumentException("illegal index");
        }
        board[row - 1][col - 1] = Open;
        unionNeighbors(row, col);
        openCount++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new java.lang.IllegalArgumentException("illegal index");
        }
        return board[row - 1][col - 1] == Open;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new java.lang.IllegalArgumentException("illegal index");
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

    }

    // test if the index is valid
    private boolean isIndexValid(int row, int col) {
        if (row < 1 || row > rowcolBound || col < 1 || col > rowcolBound) {
            return false;
        }
        else {
            return true;
        }
    }

    // convert the (row,col) to a one dimensional coordinate
    private int xy2VectIndex(int row, int col) {
        if (!isIndexValid(row, col)) {
            throw new java.lang.IllegalArgumentException("illegal index");
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
        else if (row == rowcolBound) {
            // uniUF.union(coord, points + 1);
            // uniUF.union(coord + rowcolBound, points + 1);
            connectedToBottom[coord] = true;
        }
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                if (connectedToBottom[uniUF.find(coord - rowcolBound)] || row == rowcolBound) {
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
