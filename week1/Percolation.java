import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import myunionUF.MyUnionUFRandom;
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
    // private final MyUnionUFRandom uniUF;

    // to tackle the backwash problem, i.e., if use virtual top and down node,
    // easy to check percolates, but say it is possible actually only one bottom block is connected
    // to the top, the rest is still not able to reach the top, but the virtual bottom node
    // make all bottom connected to the top
    // so needs to separte all bottom node, (treat them separately), but to keep checking percolation fast
    // dynamically maintain a n^2 + 1 (the + 1 because the virtual top node, note we do not use the virtual bottom node)
    // and for each open, will update the connectedToBottom array
    private boolean[] connectedToBottom;
    private boolean percolated;


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
        // uniUF = new MyUnionUFRandom(points + 1);
        // // connect first row to 0
        // for (int i = 1; i <= n; i++){
        //     uniUF.union(i, 0);
        // }
        connectedToBottom = new boolean[points + 1];
        percolated = false;
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

        // return uniUF.connected(0, xy2VectIndex(row, col)); // connected deprecated
        return uniUF.find(0) == uniUF.find(xy2VectIndex(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        // return uniUF.connected(0, points + 1);
        // boolean isPercolate = connectedToBottom[uniUF.find(0)] || percolated;
        // connectedToBottom[uniUF.find(0)] = isPercolate;
        // return isPercolate;
        return percolated;
    }

    // test client (optional)
    public static void main(String[] args) {
        // test the percolates
        Percolation percNew = new Percolation(2);
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
            // if (percolated) { // dont needed!
            //     connectedToBottom[uniUF.find(0)] = true;
            // }
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
                    // for the test case which substitute weightedUnionFind with another data
                    // structure that pick leader randomly, so not sure if the queried will be 
                    // updated that is connected to bottom, if the newly opened makes it connected
                    // to bottom
                    // connectedToBottom[uniUF.find(coord-rowcolBound)] = true;
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
                    // connectedToBottom[uniUF.find(coord+rowcolBound)] = true;
                    // if (row == 1) {
                    //     connectedToBottom[uniUF.find(0)] = true;
                    // }       
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
                    // connectedToBottom[uniUF.find(coord-1)] = true;
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
                    // connectedToBottom[uniUF.find(coord+1)] = true;
                }
            }
        }
        if (connectedToBottom[uniUF.find(0)]) {
            percolated = true;
        }
    }
}
// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// // import myunionUF.MyUnionUFRandom;
// // public class Percolation {
// public class Percolation 
// {
//     private int n;
//     private int top;
//     private int bottom;
//     private WeightedQuickUnionUF uf;
//     private WeightedQuickUnionUF ufPerc;
//     private byte[] site; // 0 - closed site, 1 - open site, 2 - full site;
//     private int openCount;
    
//     // create N-by-N grid, with all sites blocked
//     public Percolation(int N)              
//     {
//         n = N;
//         uf = new WeightedQuickUnionUF(n*n + 2);
//         ufPerc = new WeightedQuickUnionUF(n*n + 2);
//         site = new byte[n*n];
//         top = n*n;
//         bottom = n*n + 1;
//         openCount = 0;
//     }
    
//     // open site (row i, column j) if it is not already
//     public void open(int i, int j)         
//     {
//         isInBounds(i, j);
//         if (isOpen(i, j))
//         {
//             return;
//         }
//         int currentSite = convert2dTo1dCoord(i, j); 
//         this.site[currentSite] = 1;
//         openCount++;
        
//         // union with top virtuall cell
//         if (i == 1 && !(uf.find(currentSite) == uf.find(top)))
//         {
//             uf.union(currentSite, top);
//             ufPerc.union(currentSite, top);
//         }
        
        
//         // union with bottom artificial cell
//         if (i == n)
//         {
//             ufPerc.union(currentSite, bottom);
//         }
        
        
//         // union with bottom cell
//         if (i < n)
//         {
//             if (isOpen(i+1, j))
//             {
//                 uf.union(currentSite, convert2dTo1dCoord(i+1, j));
//                 ufPerc.union(currentSite, convert2dTo1dCoord(i+1, j));
//             }
//         }
//         // union with upper cell
//         if (i > 1)
//         {
//             if (isOpen(i-1, j))
//             {
//                 uf.union(currentSite, convert2dTo1dCoord(i-1, j));
//                 ufPerc.union(currentSite, convert2dTo1dCoord(i-1, j));
//             }
//         }
//         // union with left cell
//         if (j > 1)
//         {
//             if (isOpen(i, j-1))
//             {
//                 uf.union(currentSite, convert2dTo1dCoord(i, j-1));
//                 ufPerc.union(currentSite, convert2dTo1dCoord(i, j-1));
//             }
//         }
//         // union with left cell
//         if (j < n)
//         {
//             if (isOpen(i, j+1))
//             {
//                 uf.union(currentSite, convert2dTo1dCoord(i, j+1));
//                 ufPerc.union(currentSite, convert2dTo1dCoord(i, j+1));
//             }
//         }
//     }
    
//     private boolean isInBounds(int i, int j)
//     {
//         if (i < 1 || i > n || j < 1 || j > n)
//         {
//             throw new IndexOutOfBoundsException();
//         }
//         return true;
//     }

//     // is site (row i, column j) open?
//     public boolean isOpen(int i, int j)    
//     {
//         isInBounds(i, j);
//         if (site[convert2dTo1dCoord(i, j)] == 1)
//             return true;
//         return false;
//     }
    
//     // is site (row i, column j) full?
//     public boolean isFull(int i, int j)    
//     {
//         isInBounds(i, j);
//         if (!isOpen(i, j))
//             return false;
//         int currentSite = convert2dTo1dCoord(i, j);
//         // if (uf.connected(top, currentSite))
//         if (uf.find(top) == uf.find(currentSite))
//             return true;
//         return false;
//     }

//     private int convert2dTo1dCoord(int i, int j)
//     {
//         int pos = n*(i - 1) + j - 1;
//         return pos;
//     }
    
//     // does the system percolate?
//     public boolean percolates()            
//     {
//         // if (ufPerc.connected(top, bottom))
//         if (ufPerc.find(top) == ufPerc.find(bottom))
//             return true;
//         return false;
//     }

    
//     // returns the number of open sites
//     public int numberOfOpenSites() {
//         return openCount;
//     }
// }
