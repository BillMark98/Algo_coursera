/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import java.util.Random;

public class MyUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    // saves the largest element to which the element is connected
    // private int[] largestElement;

    public MyUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1; // ? sz[i] = i;?
        }
        // largestElement = new int[N];
        // for (int i = 0; i < N; i++) {
        //     largestElement[i] = i;
        // }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    // // find the largest element to which p is connected
    // public int findLarge(int p) {
    //     return largestElement[find(p)];
    // }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // random version
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            // if (largestElement[i] > largestElement[j]) {
            //     largestElement[j] = largestElement[i];
            // }
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
            // if (largestElement[j] > largestElement[i]) {
            //     largestElement[i] = largestElement[j];
            // }
        }

        count--;
    }

    public static void main(String[] args) {
        MyUnionUF myU = new MyUnionUF(10);
        myU.union(1, 3);
        myU.union(2, 4);
        StdOut.println("Connected 1,3? : " + myU.connected(1, 3));
        StdOut.println("Connected 2, 1 ? : " + myU.connected(1, 2));
        StdOut.println("Largest connected to 1? " + myU.findLarge(1));
        myU.union(6, 1);
        myU.union(8, 2);
        myU.union(9, 4);
        myU.union(4, 3);
        StdOut.println("Connected 6,8?: " + myU.connected(6, 8));
        StdOut.println("Largest connected to 3? " + myU.findLarge(3));
    }
}
