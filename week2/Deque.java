/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // private variable
    private Node first;
    private Node last;
    private int numberOfNodes;

    // define the Node class type
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        // first = new Node();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return numberOfNodes == 0;
    }

    // return the number of items on the deque
    public int size() {
        return numberOfNodes;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldfirst.previous = first;
        }
        numberOfNodes++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        numberOfNodes++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        // since item should be a reference to an object so
        // if every empty list gives back a null argument
        // even for elementary data types, using wrapper Integer, Double
        // also an object so it's legal to give back null argument

        // using exception instead
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        // eliminate loitering
        first = first.next;
        numberOfNodes--;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.previous = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        // eliminate loitering
        last = last.previous;
        numberOfNodes--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Dont support this operation");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("Deque is empty");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deqInt = new Deque<Integer>();
        StdOut.println("deque newly created: isEmpty? " + deqInt.isEmpty());
        deqInt.addFirst(3);
        deqInt.addLast(4);
        deqInt.addFirst(1);
        StdOut.println("adding some elements, print deque");
        for (Integer i : deqInt) {
            StdOut.print(i + "  ");
        }
        StdOut.println();
        Integer temp = deqInt.removeFirst();
        StdOut.println("Removing first : " + temp);
        temp = deqInt.removeLast();
        StdOut.println("Remvoing last: " + temp);
        deqInt.addLast(9);
        StdOut.println("Now after modifying: ");
        for (Integer i : deqInt) {
            StdOut.print(i + "  ");
        }

        StdOut.println("Test a new deque");

        // from the test
        Deque<Integer> deque = new Deque<Integer>();
        //{1}
        deque.addFirst(1);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();
        // deque.iterator();
        // {1,2}
        deque.addLast(2);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();
        StdOut.println();
        // { 2 }
        deque.removeFirst();
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 4,2}
        deque.addFirst(4);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 2 }
        deque.removeFirst();
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 6 2 }
        deque.addFirst(6);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 2 }
        deque.removeFirst();
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 8 2 }
        deque.addFirst(8);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 8 2 9 }
        deque.addLast(9);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        // { 8 2}
        deque.removeLast();
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        deque.removeLast();
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        deque.removeFirst();
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();

        StdOut.println("Empty: " + deque.isEmpty());
        deque.addFirst(4);
        for (Integer i : deque) {
            StdOut.print(i + "  ");
        }
        StdOut.println();
        deque.removeFirst();
        StdOut.println("is empty: " + deque.isEmpty());
    }


}
