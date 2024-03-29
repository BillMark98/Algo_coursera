/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private static final int UPPERBOUND = 32767;
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point


    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally, if the two points are
     * (x0, y0) and (x1, y1), then the slope is (y1 - y0) / (x1 - x0). For completeness, the slope
     * is defined to be +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and Double.NEGATIVE_INFINITY if
     * (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        assert isValidCoord(x, y);
        assert isValidCoord(that.x, that.y);
        if (x == that.x) {
            if (y == that.y) {
                // degenerate line
                return Double.NEGATIVE_INFINITY;
            }
            else {
                return Double.POSITIVE_INFINITY;
            }
        }
        else {
            if (y == that.y) {
                return +0.0;
            }
            else {
                return (that.y - y) * 1.0 / (that.x - x);
            }
        }

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally, the invoking
     * point (x0, y0) is less than the argument point (x1, y1) if and only if either y0 < y1 or if
     * y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point (x0 = x1 and y0 =
     * y1); a negative integer if this point is less than the argument point; and a positive integer
     * if this point is greater than the argument point
     */
    public int compareTo(Point that) {
        if (y < that.y) {
            return -1;
        }
        else if (y == that.y) {
            return Integer.compare(x, that.x);
        }
        else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point. The slope is defined as in the
     * slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }


    /**
     * Returns a string representation of this point. This method is provide for debugging; your
     * program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenRadius(0.01);
        Point[] arrP = new Point[5];
        arrP[0] = new Point(5, 10);
        arrP[1] = new Point(10, 6);
        arrP[2] = new Point(20, 30);
        arrP[3] = new Point(40, 20);
        arrP[4] = new Point(0, 10);
        StdOut.println("p1 > p2 ? : " + arrP[0].compareTo(arrP[1]));
        StdOut.println("p3 to p4 slope: " + arrP[2].slopeTo(arrP[3]));
        for (Point p : arrP) {
            StdOut.println("( " + p.x + " , " + p.y + " )");
            p.draw();
        }
        StdOut.println("Now sort");
        Arrays.sort(arrP);
        for (Point p : arrP) {
            StdOut.println("( " + p.x + " , " + p.y + " )");
        }
        StdOut.println("choose the point 5 as the base point using slopeOrder to sort");
        Arrays.sort(arrP, arrP[4].slopeOrder());
        for (Point p : arrP) {
            StdOut.println("( " + p.x + " , " + p.y + " )");
        }
    }

    private boolean isValidCoord(int xcoord, int ycoord) {
        return (xcoord >= 0) && (xcoord <= UPPERBOUND) && (ycoord >= 0) && (ycoord <= UPPERBOUND);
    }

    private class BySlope implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a == null || b == null) {
                throw new NullPointerException("at least one of the arguments is null");
            }
            // int res = Double.compare(slopeTo(a), slopeTo(b));
            // if (res == 0) {
            //     // for sorting in the Fstcollinear
            //     // but not allowed according to OJ
            //     return a.compareTo(b);
            // }
            // else {
            //     return res;
            // }
            return Double.compare(slopeTo(a), slopeTo(b));
        }
    }

    // private int compareBySlope(Point a, Point b) {
    //     int res = Double.compare(slopeTo(a), slopeTo(b));
    //     if (res == 0) {
    //         // for sorting in the Fstcollinear
    //         return a.compareTo(b);
    //     }
    //     else {
    //         return res;
    //     }
    // }
}
