/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

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
        // now be certain that all the elements are not null
        // so can do the statement pointAll[i] = points[i]
        Point[] pointAll = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointAll[i] = points[i];
        }
        // since checkDuplicatePoint will sort the array
        // will use pointAll instead of points
        if (checkDuplicatePoint(pointAll)) {
            throw new IllegalArgumentException("duplicate points");
        }

        buildListSegments(pointAll);
        buildLineSegments();
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfLines;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lines = new LineSegment[lineSegments.length];
        for (int i = 0; i < lineSegments.length; i++) {
            lines[i] = lineSegments[i];
        }
        return lines;
    }

    public static void main(String[] args) {
        // StdDraw.setXscale(0, 120);
        // StdDraw.setYscale(0, 120);
        // StdDraw.setPenRadius(.01);
        // Point[] arrP = new Point[15];
        // arrP[0] = new Point(5, 10);
        // arrP[1] = new Point(10, 6);
        // arrP[2] = new Point(20, 30);
        // arrP[3] = new Point(40, 20);
        // arrP[4] = new Point(10, 10);
        // arrP[5] = new Point(40, 60);
        // arrP[6] = new Point(10, 15);
        // arrP[7] = new Point(6, 10);
        // arrP[8] = new Point(60, 90);
        // arrP[9] = new Point(100, 10);
        // arrP[10] = new Point(30, 45);
        // arrP[11] = new Point(50, 75);
        // arrP[12] = new Point(10, 20);
        // arrP[13] = new Point(10, 40);
        // arrP[14] = new Point(50, 10);
        // for (Point p : arrP) {
        //     p.draw();
        // }
        // StdOut.println("Line built");
        // FastCollinearPoints brt = new FastCollinearPoints(arrP);
        // LineSegment[] lines = brt.segments();
        // for (LineSegment lineSeg : lines) {
        //     StdOut.println(lineSeg);
        //     lineSeg.draw();
        // }

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

    private void buildLineSegments() {
        lineSegments = new LineSegment[numberOfLines];
        // int lines = numberOfLines;
        for (int i = 0; i < numberOfLines; i++) {
            lineSegments[i] = pop();
        }
    }

    private void buildListSegments(Point[] pointAll) {
        // array too small impossible to have collinear four points
        if (pointAll.length < 4) {
            return;
        }
        MyPoint[] originArr = new MyPoint[pointAll.length];
        MyPoint[] primitiveArr = new MyPoint[pointAll.length];
        int[] pointVisited = new int[pointAll.length];
        final int visited = 1;
        for (int i = 0; i < pointAll.length; i++) {
            originArr[i] = new MyPoint(pointAll[i], i);
            primitiveArr[i] = new MyPoint(pointAll[i], i);
        }
        for (MyPoint pt : primitiveArr) {
            // sort the originArr
            Arrays.sort(originArr, pt.slopeOrder());
            // Arrays.sort(pointAll, pt.p.slopeOrder());
            // for (int i = 0; i < pointAll.length; i++) {
            //     StdOut.println("i : " + i + "  point is : " + pointAll[i] + " slope to p : " + p
            //             .slopeTo(pointAll[i]));
            //     if (i < pointAll.length - 1) {
            //         StdOut.println(
            //                 "i and i + 1 compare: " + p
            //                         .compareBySlope(pointAll[i], pointAll[i + 1]));
            //     }
            // }
            pointVisited[pt.arrIndex] = visited;
            int i = 1, j = 2;
            while (i < pointAll.length && j < pointAll.length) {
                if (pt.p.slopeTo(originArr[i].p) == pt.p.slopeTo(originArr[j].p)) {

                    double slope = pt.p.slopeTo(originArr[i].p);

                    if (pointVisited[originArr[i].arrIndex] == visited
                            || pointVisited[originArr[j].arrIndex] == visited) {
                        // if there is a collinear set then it's already considered so jump it
                        j++;
                        while (j < pointAll.length && pt.p.slopeTo(originArr[j].p) == slope) {
                            j++;
                        }
                        if (j >= pointAll.length - 2) {
                            break;
                        }
                        i = j;
                        j++;
                        continue;
                    }
                    // set the two ends
                    // leftEnd has the smallest value rightEnd the largest value (in respect of the
                    // compareTo method)
                    Point leftEnd = (originArr[i].p.compareTo(originArr[j].p) < 0) ?
                                    originArr[i].p :
                                    originArr[j].p;
                    Point rightEnd = (leftEnd == originArr[i].p) ? originArr[j].p : originArr[i].p;
                    leftEnd = (leftEnd.compareTo(pt.p) < 0) ? leftEnd : pt.p;
                    rightEnd = (rightEnd.compareTo(pt.p) < 0) ? pt.p : rightEnd;
                    j++;
                    int count = 2;
                    // flag indicating whether the line already considered
                    boolean lineVisited = false;
                    while (j < pointAll.length && slope == pt.p.slopeTo(originArr[j].p)) {

                        // test whether the line already considered
                        if (pointVisited[originArr[j].arrIndex] == visited) {
                            j++;
                            lineVisited = true;
                            while (j < pointAll.length && pt.p.slopeTo(originArr[j].p) == slope) {
                                j++;
                            }
                            i = j;
                            j++;
                            break;

                        }
                        leftEnd = (leftEnd.compareTo(originArr[j].p) > 0) ? originArr[j].p :
                                  leftEnd;
                        rightEnd = (rightEnd.compareTo(originArr[j].p) < 0) ? originArr[j].p :
                                   rightEnd;
                        j++;
                        count++;
                    }
                    // if a new collinear set of points found
                    if (count >= 3) {
                        // test duplicate
                        if (!lineVisited) {
                            LineSegment line = new LineSegment(leftEnd, rightEnd);
                            push(line);
                        }
                        // else {
                        //     line = null;
                        // }
                    }

                    // since there could be maximal three points collinear
                    // so dont need to consider that
                    if (j >= pointAll.length - 2) {
                        break;
                    }
                    i = j;
                    j++;
                }
                else {
                    i++;
                    j++;
                }

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
        public LineSegment lines;
        public Node next;
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

    // private boolean isAlreadyInList(LineSegment line) {
    //     Node oldfirst = first;
    //     while (oldfirst != null) {
    //         if (oldfirst.lines.toString().equals(line.toString())) {
    //             return true;
    //         }
    //         oldfirst = oldfirst.next;
    //     }
    //     return false;
    // }


    private static class MyPoint {
        public final Point p;
        public final int arrIndex;

        public MyPoint(Point pref, int index) {
            p = pref;
            arrIndex = index;
        }

        public Comparator<MyPoint> slopeOrder() {
            return new BySlope();
        }

        private class BySlope implements Comparator<MyPoint> {
            public int compare(MyPoint a, MyPoint b) {
                if (a == null || b == null) {
                    throw new IllegalArgumentException("at least one of the arguments is null");
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
                return Double.compare(p.slopeTo(a.p), p.slopeTo(b.p));
            }
        }
    }
}
