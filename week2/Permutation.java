/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        int elementsRead = 0;
        RandomizedQueue<String> myArray = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            elementsRead++;
            if (elementsRead <= count) {
                myArray.enqueue(str);
            }
            else {
                int randIndex = StdRandom.uniform(elementsRead);
                if (randIndex < count) {
                    // will discard an item from queue and insert the new one
                    myArray.dequeue();
                    myArray.enqueue(str);
                }
            }

        }

        String temp;
        for (int i = 0; i < count; i++) {
            if (myArray.isEmpty()) {
                break;
            }
            temp = myArray.dequeue();
            StdOut.println(temp);
        }
    }

}
