import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class BruteCollinearPoints {
    Node first;
    int numberOfLines;
    Point[] pointAll;
    LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        pointAll = points;
        BuildListSegments();
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
        BruteCollinearPoints brt = new BruteCollinearPoints(arrP);
        LineSegment[] lines = brt.segments();
        for (LineSegment lineSeg : lines) {
            StdOut.println(lineSeg);
            lineSeg.draw();
        }

    }

    private void BuildLineSegments() {
        lineSegments = new LineSegment[numberOfLines];
        for (int i = 0; i < numberOfLines; i++) {
            lineSegments[i] = pop();
        }
    }

    private void BuildListSegments() {
        // here rightEnd has the largest y value (this.compareTo(other) returns 1
        Point leftEnd, rightEnd;
        for (int i1 = 0; i1 < pointAll.length; i1++) {
            for (int i2 = i1 + 1; i2 < pointAll.length; i2++) {
                if (pointAll[i1].compareTo(pointAll[i2]) == 0) {
                    continue;
                }
                else if (pointAll[i1].compareTo(pointAll[i2]) < 0) {
                    leftEnd = pointAll[i1];
                    rightEnd = pointAll[i2];
                }
                else {
                    leftEnd = pointAll[i2];
                    rightEnd = pointAll[i1];
                }
                for (int i3 = i2 + 1; i3 < pointAll.length; i3++) {
                    if ((pointAll[i1].compareTo(pointAll[i3]) == 0) || (
                            pointAll[i2].compareTo(pointAll[i3])
                                    == 0)) {
                        continue;
                    }
                    // if p1,p2,p3 not collinearr
                    if (pointAll[i1].slopeTo(pointAll[i2]) != pointAll[i2].slopeTo(pointAll[i3])) {
                        continue;
                    }
                    if (leftEnd.compareTo(pointAll[i3]) > 0) {
                        leftEnd = pointAll[i3];
                    }
                    else if (rightEnd.compareTo(pointAll[i3]) < 0) {
                        rightEnd = pointAll[i3];
                    }
                    for (int i4 = i3 + 1; i4 < pointAll.length; i4++) {
                        if ((pointAll[i1].compareTo(pointAll[i4]) == 0) || (
                                pointAll[i2].compareTo(pointAll[i4]) == 0)
                                || (
                                pointAll[i3].compareTo(pointAll[i4])
                                        == 0)) {
                            continue;
                        }
                        // already sure that p1,p2,p3 collinear
                        // only have to test for example, p1 and p4 collinear
                        if (pointAll[i1].slopeTo(pointAll[i4]) == pointAll[i1]
                                .slopeTo(pointAll[i2])) {
                            // line found
                            if (leftEnd.compareTo(pointAll[i4]) > 0) {
                                leftEnd = pointAll[i4];
                            }
                            else if (rightEnd.compareTo(pointAll[i4]) < 0) {
                                rightEnd = pointAll[i4];
                            }
                            push(new LineSegment(leftEnd, rightEnd));
                        }
                    }
                }
            }
        }
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
        numberOfLines--;
        return res;
    }

    private boolean isEmpty() {
        return numberOfLines == 0;
    }
}
