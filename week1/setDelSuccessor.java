/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class setDelSuccessor {
    // private MyUnionUF[] unionArr;
    private int[] set;
    private static final int deleted = -1;
    private static final int noDelNeighbour = -1;
    private static final int noSuccessor = -1;
    private MyUnionUF delElemUnion;

    public setDelSuccessor(int n) {
        set = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }
        // unionArr = new MyUnionUF[n];
        delElemUnion = new MyUnionUF(n);
    }

    public void delete(int x) {
        delNeighour delN = deletedNeighbor(x);
        if (delN.neigh1 != noDelNeighbour) {
            // unionArr[delN.neigh1].union(delN.neigh1, x);
            // unionArr[x] = unionArr[delN.neigh1];
            delElemUnion.union(x, delN.neigh1);
            if (delN.doubleDeleted) {
                // unionArr[delN.neigh1].union(x, delN.neigh2);
                // unionArr[delN.neigh2] = unionArr[delN.neigh1];
                delElemUnion.union(delN.neigh1, delN.neigh2);
            }
        }
        else {
            // unionArr[x] = new MyUnionUF(set.length);
        }
        set[x] = deleted;
    }

    public int successor(int x) {
        if (isInSet(x)) {
            return x;
        }
        else {
            // int temp = unionArr[x].findLarge(x) + 1;
            int temp = delElemUnion.findLarge(x) + 1;
            if (temp < set.length) {
                return temp;
            }
            else {
                StdOut.println("There are no such successor");
                return noSuccessor;
            }
        }
    }

    public void printSet() {
        for (int i : set) {
            if (i != deleted)
                StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public void printSuccessor() {
        for (int i = 0; i < set.length; i++) {
            StdOut.println(i + " : " + successor(i));
        }
    }

    private boolean isInSet(int x) {
        return set[x] != deleted;
    }

    private delNeighour deletedNeighbor(int x) {
        if (x == 0) {
            if (set[1] == deleted) {
                return new delNeighour(1);
            }
            else {
                return new delNeighour(noDelNeighbour);
            }
        }
        else if (x == set.length - 1) {
            if (set[x - 1] == deleted) {
                return new delNeighour(x - 1);
            }
            else {
                return new delNeighour(noDelNeighbour);
            }
        }
        else {
            if (set[x - 1] == deleted) {
                if (set[x + 1] == deleted) {
                    return new delNeighour(x - 1, x + 1);
                }
                else {
                    return new delNeighour(x - 1);
                }
            }
            else if (set[x + 1] == deleted) {
                return new delNeighour(x + 1);
            }
            else {
                return new delNeighour(noDelNeighbour);
            }
        }
    }

    private class delNeighour {
        private int neigh1, neigh2;
        // whether the two neighbors(if they both exist) are deleted
        private boolean doubleDeleted;

        public delNeighour(int n1) {
            neigh1 = n1;
            doubleDeleted = false;
        }

        public delNeighour(int n1, int n2) {
            neigh1 = n1;
            neigh2 = n2;
            doubleDeleted = true;
        }

    }

    public static void main(String[] args) {
        setDelSuccessor set = new setDelSuccessor(15);
        StdOut.println("The set in the beginning");
        set.printSet();
        set.delete(4);
        set.delete(7);
        StdOut.println("now the set is: ");
        set.printSet();
        StdOut.println("successor print:");
        set.printSuccessor();

        set.delete(5);
        set.delete(2);
        StdOut.println("now the set is: ");
        set.printSet();
        StdOut.println("successor print:");
        set.printSuccessor();

        set.delete(6);
        StdOut.println("now the set is: ");
        set.printSet();
        StdOut.println("successor print:");
        set.printSuccessor();

        set.delete(10);
        set.delete(9);
        set.delete(12);
        StdOut.println("now the set is: ");
        set.printSet();
        StdOut.println("successor print:");
        set.printSuccessor();
    }
}
