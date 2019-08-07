/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int NumberOfItems;
    // denote the number of places, where there weree an item(but deleted using deque)

    private int NumberOfSlots;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return NumberOfItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return NumberOfItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (NumberOfItems == array.length) {
            resize(2 * NumberOfItems);
        }
        array[NumberOfItems++] = item;
        NumberOfSlots++;
    }

    // remove and return a random item
    public Item dequeue() {
        int index = StdRandom.uniform(NumberOfItems);
        Item item = array[index];
        array[index] = null;
        NumberOfItems--;
        if (NumberOfItems > 0 && NumberOfItems == array.length / 4) {
            resize(array.length / 2);
        }
    }

    // return a random item (but do not remove it)
    public Item sample()

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args) {

    }

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        // for (int i = 0; i < NumberOfItems; i++) {
        //     temp[i] = array[i];
        // }
        for (int i = 0, j = 0; i < NumberOfSlots; i++) {
            if (array[i] != null) {
                temp[j] = array[i];
                j++;
            }
        }
        array = temp;
        NumberOfSlots = NumberOfItems;
    }


}
