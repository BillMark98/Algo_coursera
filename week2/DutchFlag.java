/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

public class DutchFlag {
    private Pebble[] peArr;
    private static int swapCount, colorCount;

    public DutchFlag(int n) {
        peArr = new Pebble[n];
        for (int i = 0; i < peArr.length; i++) {
            int col = StdRandom.uniform(3);
            switch (col) {
                case 0:
                    peArr[i] = new Pebble("red");
                    break;
                case 1:
                    peArr[i] = new Pebble("white");
                    break;
                case 2:
                    peArr[i] = new Pebble("blue");
                    break;
                default:
                    throw new NoSuchElementException("Not a valid random number");
            }
        }
    }

    public void printPebble() {
        for (Pebble peb : peArr) {
            StdOut.print(peb.color + " ");
        }
    }

    public void MakeDutch() {
        sortPeb(peArr);
    }

    public void printCounts() {
        StdOut.println("The swap count is : " + swapCount);
        StdOut.println("The color count is : " + colorCount);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        DutchFlag ndF = new DutchFlag(n);
        StdOut.println("The generated random pebble bucket is : ");
        ndF.printPebble();
        StdOut.println("\nNow sorting");
        ndF.MakeDutch();
        ndF.printPebble();
        StdOut.println();
        ndF.printCounts();
    }

    private class Pebble {
        public String color;

        public Pebble(String str) {
            color = str;
        }

        public String color() {
            colorCount++;
            return color;
        }
    }

    private static void sortPeb(Pebble[] peArr) {
        int redPos = -1, whitePos = -1, bluePos = -1;
        int n = peArr.length;
        int i = 0, j = n - 1;
        while (i <= j) {
            // should save the color at first
            // otherwise it would call color at each branch
            // so at least 3 calls to color per loop
            String col = peArr[i].color();
            if (col.equals("red")) {
                redPos = i;
                if (whitePos != -1 && i > whitePos) {
                    swap(peArr[i], peArr[whitePos]);
                    // r r r w w w r .....   b b b
                    // so whitePos = 4, i = 7
                    // now swap 4,7
                    // r r r r w w w ...   b b b
                    // whitePos = 5
                    whitePos++;
                }
                i++;
            }
            else if (col.equals("white")) {
                // whitePos not set yet
                if (whitePos == -1) {
                    whitePos = i;
                }
                i++;
            }
            else {
                if (bluePos == -1) {
                    swap(peArr[i], peArr[j]);
                    bluePos = j;
                    // // minus i so that next round still consider the swapped to the front element
                    // i--;
                    j--;
                }
                else {
                    swap(peArr[i], peArr[--bluePos]);
                    j--;
                }
            }
        }
    }

    private static void swap(Pebble a, Pebble b) {
        // wrong swap
        // since temp will have the b's color as a does
        // after a.color = b.color
        // Pebble temp = a;
        // a.color = b.color;
        // b.color = temp.color;
        swapCount++;
        String color = a.color;
        a.color = b.color;
        b.color = color;
    }
}
