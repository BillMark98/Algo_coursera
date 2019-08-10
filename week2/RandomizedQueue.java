/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int numberOfItems;
    // denote the number of places, where there weree an item(but deleted using deque)

    // private int NumberOfSlots;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return numberOfItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null argument");
        }
        if (numberOfItems == array.length) {
            resize(2 * numberOfItems);
        }
        array[numberOfItems++] = item;
        // NumberOfSlots++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int index = StdRandom.uniform(numberOfItems);
        Item item = array[index];
        // swap the array[index] with the last item;
        if (index != numberOfItems - 1) {
            swap(index, numberOfItems - 1);
        }
        array[numberOfItems - 1] = null;
        numberOfItems--;
        if (numberOfItems > 0 && numberOfItems == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int index = StdRandom.uniform(numberOfItems);
        Item item = array[index];
        // i guess dont need to swap the item
        // if (index != (NumberOfItems - 1)) {
        //     swap(index, NumberOfItems - 1);
        // }
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private final int[] indexArray;
        private int count;

        public ArrayIterator() {
            indexArray = new int[numberOfItems];
            for (int i = 0; i < numberOfItems; i++) {
                indexArray[i] = i;
            }
            StdRandom.shuffle(indexArray);
        }

        public boolean hasNext() {
            return count < numberOfItems;
        }

        public void remove() {
            throw new UnsupportedOperationException("dont support remove function");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue is empty");
            }
            Item item = array[indexArray[count]];
            count++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> myArray = new RandomizedQueue<Integer>();
        StdOut.println("Array empty ? : " + myArray.isEmpty());
        myArray.enqueue(3);
        myArray.enqueue(5);
        myArray.enqueue(9);
        myArray.enqueue(2);
        myArray.enqueue(11);
        myArray.enqueue(20);
        myArray.enqueue(120);
        myArray.enqueue(32);
        StdOut.println("Array empty ? : " + myArray.isEmpty());
        StdOut.println("Sample: " + myArray.sample());
        StdOut.println("Print the array");
        for (Integer i : myArray) {
            StdOut.print(" " + i);
        }
        StdOut.println("\nPrint again: ");
        for (Integer i : myArray) {
            StdOut.print(" " + i);
        }

        // dequeue
        myArray.enqueue(22);
        StdOut.println("\ndeque item: " + myArray.dequeue());
        StdOut.println("Now the array");
        for (Integer i : myArray) {
            StdOut.print(" " + i);
        }

    }

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < numberOfItems; i++) {
            temp[i] = array[i];
        }
        // for (int i = 0, j = 0; i < NumberOfSlots; i++) {
        //     if (array[i] != null) {
        //         temp[j] = array[i];
        //         j++;
        //     }
        // }
        array = temp;
        // NumberOfSlots = NumberOfItems;
    }

    private void swap(int i, int j) {
        if (i >= array.length || j >= array.length || i < 0 || j < 0) {
            throw new ArrayIndexOutOfBoundsException("index invalid to swap");
        }
        Item temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
