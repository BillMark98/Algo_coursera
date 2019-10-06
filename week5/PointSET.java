/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {

    private SET<Point2D> pointSet;
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
        ArrayList<Point2D> pointsArr = new ArrayList<>();
        double dist = Double.MAX_VALUE;
        double temp_dist;
        Point2D nearPt = new Point2D(0, 0);
        for (Point2D pt : pointSet) {
            temp_dist = pt.distanceTo(p);
            if (temp_dist < dist) {
                dist = temp_dist;
                nearPt = pt;
            }
        }
        return new Point2D(nearPt.x(), nearPt.y());
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
