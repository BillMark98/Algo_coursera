/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = { -4, -3, -2, -1, -1, -1, 4, 5, 7, 8, 10 };
        threeSum(arr);
    }

    // three sum array find 3-tuple (i,j,k) s.t ar[i] + a[j] + a[k] = 0
    public static void threeSum(int[] ar) {
        Arrays.sort(ar);
        for (int i = 0; i < ar.length; i++) {
            ArrayList<Tuple> arInd = findX(ar, i + 1, ar.length - 1, -ar[i]);
            for (Tuple p : arInd) {
                System.out.print(i + " , " + p);
                System.out.print("  |  ");
                printTuple(ar[i], ar[p.left], ar[p.right]);
            }
        }
    }

    // given array ar, start at i, end at j, find
    public static ArrayList<Tuple> findX(int[] ar, int i, int j, int x) {
        int n = ar.length;
        if (j >= n) {
            throw new ArrayIndexOutOfBoundsException("j index out of array");
        }
        int left = i;
        int right = j;
        ArrayList<Tuple> arr = new ArrayList<Tuple>();
        while (left < right) {
            int sum = ar[left] + ar[right] - x;
            if (sum > 0) {
                right--;
            }
            else if (sum < 0) {
                left++;
            }
            // such left,right found
            else {
                int origLeft = left;
                int origRight = right;
                int valLeft = ar[left];
                int valRight = ar[right];
                while (left < right && ar[left] == valLeft) {
                    left++;
                }
                while (right > left && ar[right] == valRight) {
                    right--;
                }
                for (int ix = origLeft; ix < left; ix++) {
                    for (int jx = origRight; jx > right; jx--) {
                        arr.add(new Tuple(ix, jx));
                    }
                }
            }
        }
        return arr;
    }

    private static void printTuple(int i, int j, int k) {
        String st = Integer.toString(i);
        if (j >= 0) {
            st += " + " + Integer.toString(j);
        }
        else {
            st += " - " + Integer.toString(-j);
        }
        if (k >= 0) {
            st += " + " + Integer.toString(k);
        }
        else {
            st += " - " + Integer.toString(-k);
        }
        st += " = 0";
        System.out.println(st);
    }

    private static class Tuple {
        private final int left;
        private final int right;

        public Tuple(int i, int j) {
            left = i;
            right = j;
        }

        public String toString() {
            return left + " , " + right;
        }
    }
}
