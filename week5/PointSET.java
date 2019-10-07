/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class PointSET {

    private final SET<Point2D> pointSet;
    private int size;

    // construct an empty set of points
    public PointSET() {
        pointSet = new SET<Point2D>();
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
        if (pointSet.contains(p)) {
            return;
        }
        else {
            pointSet.add(p);
            size++;
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point p is null");
        }
        return pointSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : pointSet) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Rect is null");
        }
        if (isEmpty()) {
            return null;
        }
        ArrayList<Point2D> pointsArr = new ArrayList<>();
        for (Point2D p : pointSet) {
            if (rect.contains(p)) {
                pointsArr.add(p);
            }
        }
        return pointsArr;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        if (isEmpty()) {
            return null;
        }
        double dist = Double.POSITIVE_INFINITY;
        Point2D nearPt = new Point2D(0, 0);
        for (Point2D pt : pointSet) {
            // taking root is unnecessary and time-consuming
            double tempDist = pt.distanceSquaredTo(p);
            if (tempDist < dist) {
                dist = tempDist;
                nearPt = pt;
            }
        }
        return new Point2D(nearPt.x(), nearPt.y());
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0.1, 0.2));
        ps.insert(new Point2D(0.3, 0.6));
        ps.insert(new Point2D(0.5, 0.7));
        ps.insert(new Point2D(0.1, 0.2));
        StdOut.println("the size is: " + ps.size());

        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(0.01);
        ps.draw();

        RectHV rect = new RectHV(0, 0, 0.25, 0.4);

        rect.draw();
        Iterable<Point2D> iter = ps.range(rect);
        StdOut.println("the points in the rect are: ");
        StdDraw.setPenColor(StdDraw.RED);
        for (Point2D p : iter) {
            StdOut.println(p);
        }

        Point2D pt = new Point2D(0.4, 0.5);
        StdDraw.setPenColor(StdDraw.GREEN);
        pt.draw();

        Point2D neigh = ps.nearest(pt);
        StdDraw.setPenColor(StdDraw.BLUE);
        neigh.draw();
        StdOut.println("the nearest point is : ");
        StdOut.println(neigh);
    }
}
