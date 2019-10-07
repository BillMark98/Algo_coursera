/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class KdTree {

    private int size;
    private Node root;


    // construct an empty set of points
    public KdTree() {
        // root = new Node();
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
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        // for insert show the turns of the root of the subtree
        int turnRoot = 0;
        root = nodeInsert(root, p, turnRoot);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        Node x = root;
        while (x != null) {
            int valueComp = x.comparePoint(p);
            if (valueComp < 0) {
                x = x.leftNode;
            }
            else if (valueComp > 0) {
                x = x.rightNode;
            }
            else {
                return x.equalPoint(p);
            }
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {
        if (isEmpty()) {
            return;
        }
        nodeDraw(root, 0, 1, 0, 1);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("rect is null");
        }
        if (isEmpty()) {
            return null;
        }
        ArrayList<Point2D> array = new ArrayList<Point2D>();
        nodeRange(root, rect, array);
        return array;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        if (isEmpty()) {
            return null;
        }
        double minDist = root.pt.distanceSquaredTo(p);
        Point2D nearNeighbor = nodeNearPoint(root, p, minDist);
        return new Point2D(nearNeighbor.x(), nearNeighbor.y());
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // KdTree ps = new KdTree();
        // ps.insert(new Point2D(0.1, 0.2));
        // ps.insert(new Point2D(0.3, 0.6));
        // ps.insert(new Point2D(0.5, 0.7));
        // ps.insert(new Point2D(0.1, 0.2));
        // StdOut.println("the size is: " + ps.size());
        //
        // StdDraw.setXscale(0, 1);
        // StdDraw.setYscale(0, 1);
        // StdDraw.setPenRadius(0.01);
        // ps.draw();
        //
        // RectHV rect = new RectHV(0, 0, 0.25, 0.4);
        //
        // rect.draw();
        // Iterable<Point2D> iter = ps.range(rect);
        // StdOut.println("the points in the rect are: ");
        // StdDraw.setPenColor(StdDraw.RED);
        // for (Point2D p : iter) {
        //     StdOut.println(p);
        // }
        //
        // Point2D pt = new Point2D(0.4, 0.5);
        // StdDraw.setPenColor(StdDraw.GREEN);
        // pt.draw();
        //
        // Point2D neigh = ps.nearest(pt);
        // StdDraw.setPenColor(StdDraw.BLUE);
        // neigh.draw();
        // StdOut.println("the nearest point is : ");
        // StdOut.println(neigh);


        String filename = "circle10.txt";
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        kdtree.draw();
        Point2D query = new Point2D(0.81, 0.3);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.setPenRadius(0.04);
        query.draw();

        Point2D neigh = kdtree.nearest(query);
        StdOut.println("neigh: " + neigh);
        StdDraw.setPenColor(StdDraw.BLUE);
        neigh.draw();
        StdDraw.show();
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

        // be careful it means the root pt is compared to p
        // i.e if -1: p is larger than pt, p may be at the right subtree
        // 1: p is smaller than pt, p may be at the left subtree
        public int comparePoint(Point2D p) {
            // use x coord to compare
            if (turns == 0) {
                double diff = pt.x() - p.x();
                if (diff < 0) {
                    return -1;
                }
                else if (diff > 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                double diff = pt.y() - p.y();
                if (diff < 0) {
                    return -1;
                }
                else if (diff > 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }

        // since the tree just test the x-coord or the y-coord
        // to proof equality have to test the other coordinate as well
        public boolean equalPoint(Point2D p) {
            return pt.compareTo(p) == 0;
        }

        // find the relative position of rect to the point's corresponding x-line or y-line
        // -1 for rect at the right subtree ( pt is smaller than rect)
        // 0 for intersecting
        // 1 for rect at the left subtree( pt is larger than rect)
        public int ndIntersectsRect(RectHV rect) {
            // x-line
            if (turns == 0) {
                double xval = pt.x();
                double xmin = rect.xmin();
                double xmax = rect.xmax();
                if (xval < xmin) {
                    return -1;
                }
                else if (xval > xmax) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                // y-line
                double yval = pt.y();
                double ymin = rect.ymin();
                double ymax = rect.ymax();
                if (yval < ymin) {
                    return -1;
                }
                else if (yval > ymax) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }

        // for finding the nearest neighbor
        // according to the turns, find the possible MinDist to a given point
        // e.g turns = 0, so the pt uses x-line to differentiate points.
        // now if p lies on the right hand side of the pt, then the possible MinDist is
        // p.x() - pt.x()
        public double possibleMinDist(Point2D p) {
            // assume p is not null
            if (turns == 0) {
                double diff = pt.x() - p.x();
                return diff * diff;
            }
            else {
                double diff = pt.y() - p.y();
                return diff * diff;
            }
        }
    }

    private Node nodeInsert(Node nd, Point2D p, int turnRoot) {
        // assume p is not null
        if (nd == null) {
            size++;
            return new Node(p, turnRoot);
        }
        if (nd.comparePoint(p) > 0) {
            nd.leftNode = nodeInsert(nd.leftNode, p, turnRoot ^ 1);
        }
        else if (nd.comparePoint(p) < 0) {
            nd.rightNode = nodeInsert(nd.rightNode, p, turnRoot ^ 1);
        }
        // else point already inserted
        return nd;
    }

    private void nodeDraw(Node nd, double globalXLow, double globalXUp, double globalYLow,
                          double globalYUp) {
        if (nd == null) {
            return;
        }
        if (nd.turns == 0) {
            nodeDraw(nd.leftNode, globalXLow, nd.pt.x(), globalYLow, globalYUp);
            nodeDraw(nd.rightNode, nd.pt.x(), globalXUp, globalYLow, globalYUp);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.015);
            nd.pt.draw();

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.01);
            double xval = nd.pt.x();
            StdDraw.line(xval, globalYLow, xval, globalYUp);
        }
        else {
            nodeDraw(nd.leftNode, globalXLow, globalXUp, globalYLow, nd.pt.y());
            nodeDraw(nd.rightNode, globalXLow, globalXUp, nd.pt.y(), globalYUp);


            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.015);
            nd.pt.draw();


            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.01);
            double yval = nd.pt.y();
            StdDraw.line(globalXLow, yval, globalXUp, yval);
        }


    }

    private void nodeRange(Node nd, RectHV rect, ArrayList<Point2D> array) {
        if (nd == null) {
            return;
        }
        if (rect.contains(nd.pt)) {
            array.add(nd.pt);
            nodeRange(nd.leftNode, rect, array);
            nodeRange(nd.rightNode, rect, array);
        }
        else {
            // if rect intersects with the node
            int pos = nd.ndIntersectsRect(rect);
            // rect at the right tree
            if (pos < 0) {
                nodeRange(nd.rightNode, rect, array);
            }
            else if (pos > 0) {
                nodeRange(nd.leftNode, rect, array);
            }
            else {
                nodeRange(nd.leftNode, rect, array);
                nodeRange(nd.rightNode, rect, array);
            }
        }

    }

    private Point2D nodeNearPoint(Node nd, Point2D p, double minDist) {
        if (nd == null) {
            return null;
        }
        // minDist means the closest square Dist found so far
        int pos = nd.comparePoint(p);
        Point2D nearNeighbor = null;
        if (pos <= 0) {
            Point2D pRightTemp = nodeNearPoint(nd.rightNode, p, minDist);
            if (pRightTemp != null) {
                double tempDist = pRightTemp.distanceSquaredTo(p);
                double possibleDist = nd.possibleMinDist(p);
                if (tempDist < possibleDist) {
                    return pRightTemp;
                }
                // update the minDist
                minDist = tempDist;
                nearNeighbor = pRightTemp;
            }
            Point2D pLeftTemp = nodeNearPoint(nd.leftNode, p, minDist);
            if (pLeftTemp != null) {
                minDist = pLeftTemp.distanceSquaredTo(p);
                nearNeighbor = pLeftTemp;
            }
            double selfDist = nd.pt.distanceSquaredTo(p);
            if (selfDist < minDist) {
                nearNeighbor = nd.pt;
            }
            return nearNeighbor;
        }
        else {
            Point2D pLeftTemp = nodeNearPoint(nd.leftNode, p, minDist);
            if (pLeftTemp != null) {
                double tempDist = pLeftTemp.distanceSquaredTo(p);
                double possibleDist = nd.possibleMinDist(p);
                if (tempDist < possibleDist) {
                    return pLeftTemp;
                }
                minDist = tempDist;
                nearNeighbor = pLeftTemp;
            }

            Point2D pRightTemp = nodeNearPoint(nd.rightNode, p, minDist);
            if (pRightTemp != null) {
                minDist = pRightTemp.distanceSquaredTo(p);
                nearNeighbor = pRightTemp;
            }
            double selfDist = nd.pt.distanceSquaredTo(p);
            if (selfDist < minDist) {
                nearNeighbor = nd.pt;
            }
            return nearNeighbor;
        }
    }
}
