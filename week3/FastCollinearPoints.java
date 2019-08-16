/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private Node first;
    private int numberOfLines;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null) {
            throw new IllegalArgumentException("points array null");
        }
        if (checkPointNull(points)) {
            throw new IllegalArgumentException("some points are null");
        }
        if (checkDuplicatePoint(points)) {
            throw new IllegalArgumentException("duplicate points");
        }
        BuildListSegments(points);
        BuildLineSegments();
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfLines;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.01);
        Point[] arrP = new Point[10];
        arrP[0] = new Point(5, 10);
        arrP[1] = new Point(10, 6);
        arrP[2] = new Point(20, 30);
        arrP[3] = new Point(40, 20);
        arrP[4] = new Point(10, 10);
        arrP[5] = new Point(40, 60);
        arrP[6] = new Point(10, 15);
        arrP[7] = new Point(6, 10);
        arrP[8] = new Point(60, 90);
        arrP[9] = new Point(100, 10);
        for (Point p : arrP) {
            p.draw();
        }
        StdOut.println("Line built");
        FastCollinearPoints brt = new FastCollinearPoints(arrP);
        LineSegment[] lines = brt.segments();
        for (LineSegment lineSeg : lines) {
            StdOut.println(lineSeg);
            lineSeg.draw();
        }

    }

    private void BuildLineSegments() {
        lineSegments = new LineSegment[numberOfLines];
        // int lines = numberOfLines;
        for (int i = 0; i < numberOfLines; i++) {
            lineSegments[i] = pop();
        }
    }

    private void BuildListSegments(Point[] pointAll) {
        // array too small impossible to have collinear four points
        if (pointAll.length < 4) {
            return;
        }
        for (Point p : pointAll) {
            Arrays.sort(pointAll, p.slopeOrder());
            if (p.slopeTo(pointAll[1]) == p.slopeTo(pointAll[3])) {
                // collinear found find the furthest point
                int j = 4;
                double slope = p.slopeTo(pointAll[1]);
                while (p.slopeTo(pointAll[j]) == slope) {
                    j++;
                }
                Point leftEnd = pointAll[1];
                Point rightEnd = pointAll[--j];
                if (p.compareTo(leftEnd) < 0) {
                    leftEnd = p;
                }
                else if (p.compareTo(rightEnd) > 0) {
                    rightEnd = p;
                }
                push(new LineSegment(leftEnd, rightEnd));
            }
        }
    }

    private boolean checkPointNull(Point[] pointAll) {
        for (Point p : pointAll) {
            if (p == null) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDuplicatePoint(Point[] pointAll) {
        // first sort the Point array
        Arrays.sort(pointAll);
        for (int i = 0; i < pointAll.length; i++) {
            for (int j = i + 1; j < pointAll.length; j++) {
                if (pointAll[i].compareTo(pointAll[j]) == 0) {
                    return true;
                }
                else {
                    // since the array is sorted, this means that pointAll[j] > pointAll[i], so
                    // dont need to consider other points, check the next point instead ( break the inner loop, increment i)
                    break;
                }
            }
        }
        return false;
    }

    private class Node {
        LineSegment lines;
        Node next;
    }

    private void push(LineSegment line) {
        Node oldfirst = first;
        first = new Node();
        first.lines = line;
        first.next = oldfirst;
        numberOfLines++;
    }

    private LineSegment pop() {
        if (isEmpty()) {
            return null;
        }
        LineSegment res = first.lines;
        first = first.next;
        // numberOfLines--;
        return res;
    }

    private boolean isEmpty() {
        return first == null;
    }
}
