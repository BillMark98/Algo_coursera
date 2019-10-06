/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private int size;
    private Node root;

    // construct an empty set of points
    public KdTree() {
        root = new Node();
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {

    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {

    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {

    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }

    private class Node {
        private Point2D pt;
        // indicate this Node should use x or y coordinate as a comparison key
        // turns = 0 use x coord, turns = 1 use y coord
        private int turns;
        private Node leftNode;
        private Node rightNode;

        public Node() {

        }

        public Node(Point2D p, int turn) {
            pt = new Point2D(p.x(), p.y());
            turns = turn;
        }
    }
}
