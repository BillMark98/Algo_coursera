/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue<String> myArray = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            myArray.enqueue(str);

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
