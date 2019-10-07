###  Notes on Program
how to avoid the warning **may not be initialized** for the code   
```
Point2D nearPt = new Point2D(0, 0);
        for (Point2D pt : pointSet) {
            temp_dist = pt.distanceTo(p);
            if (temp_dist < dist) {
                dist = temp_dist;
                nearPt = pt;
            }
        }
        return new Point2D(nearPt.x(), nearPt.y());

```  
since the variable `nearPt` has to be initialized.


* How to go the definitions of a function?









Correctness:  17/35 tests passed
Memory:       16/16 tests passed
Timing:       34/42 tests passed

Aggregate score: 65.33%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
 13K Oct  7 06:53 KdTree.java
3.7K Oct  7 06:53 PointSET.java




% pmd .
*-----------------------------------------------------------
PointSET.java:17: The private instance (or static) variable 'pointSet' can be made 'final'; it is initialized only in the declaration or constructor. [ImmutableField]
PMD ends with 1 warning.


================================================================


% checkstyle *.java
*-----------------------------------------------------------
[WARN] KdTree.java:276:63: Did you mean to use 'Math.pow()' instead of the bitwise XOR operator ('^') ? [BitwiseXor]
[WARN] KdTree.java:279:65: Did you mean to use 'Math.pow()' instead of the bitwise XOR operator ('^') ? [BitwiseXor]
[WARN] PointSET.java:89: Did you mean to use 'Double.POSITIVE_INFINITY' instead of 'Double.MAX_VALUE'? [Infinity]
[WARN] PointSET.java:93:20: The local variable 'temp_dist' must start with a lowercase letter and use camelCase. [LocalVariableName]
Checkstyle ends with 0 errors and 4 warnings.

% custom checkstyle checks for PointSET.java
*-----------------------------------------------------------

% custom checkstyle checks for KdTree.java
*-----------------------------------------------------------
[WARN] KdTree.java:295:34: The numeric literal '0.015' appears to be unnecessary. [NumericLiteral]
[WARN] KdTree.java:309:34: The numeric literal '0.015' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 2 warnings.


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************


================================================================
Testing correctness of KdTree
*-----------------------------------------------------------
Running 27 total tests.

In the tests below, we consider three classes of points and rectangles.

  * Non-degenerate points: no two points (or rectangles) share either an
                           x-coordinate or a y-coordinate

  * Distinct points:       no two points (or rectangles) share both an
                           x-coordinate and a y-coordinate

  * General points:        no restrictions on the x-coordinates or y-coordinates
                           of the points (or rectangles)

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m (inclusive).


Test 1c: insert distinct points; check size() and isEmpty() after each insertion
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 8-by-8 grid
    - failed after inserting point 4 of 10
    - student   size()    = 3
    - reference size()    = 4
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.375 0.375
      B  0.875 0.125
      C  0.125 0.875
      D  1.0 0.125

can see that the problem is that lacking one point  
and it's because the original code has not considered the degenerate  
case: having same x- or y-coordinates.

  * 20 random distinct points in a 16-by-16 grid
    - failed after inserting point 7 of 20
    - student   size()    = 6
    - reference size()    = 7
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.875 0.4375
      B  0.9375 0.4375
      C  0.4375 0.625
      D  0.5625 0.3125
      E  0.125 0.75
      F  0.0625 1.0
      G  0.875 0.5

  * 10000 random distinct points in a 128-by-128 grid
    - failed after inserting point 32 of 10000
    - student   size()    = 31
    - reference size()    = 32
    - student   isEmpty() = false
    - reference isEmpty() = false

  * 100000 random distinct points in a 1024-by-1024 grid
    - failed after inserting point 13 of 100000
    - student   size()    = 12
    - reference size()    = 13
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.876953125 0.4814453125
      B  0.640625 0.9599609375
      C  0.9970703125 0.705078125
      D  0.5205078125 0.3173828125
      E  0.9365234375 0.5302734375
      F  0.2958984375 0.357421875
      G  0.3837890625 0.421875
      H  0.5458984375 0.791015625
      I  0.35546875 0.849609375
      J  0.4921875 0.859375
      K  0.328125 0.501953125
      L  0.515625 0.8408203125
      M  0.212890625 0.357421875

  * 100000 random distinct points in a 65536-by-65536 grid
    - failed after inserting point 874 of 100000
    - student   size()    = 873
    - reference size()    = 874
    - student   isEmpty() = false
    - reference isEmpty() = false

==> FAILED

Test 1d: insert general points; check size() and isEmpty() after each insertion
  * 5 random general points in a 1-by-1 grid
    - failed after inserting point 2 of 5
    - student   size()    = 1
    - reference size()    = 2
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.0 1.0
      B  0.0 0.0

  * 10 random general points in a 4-by-4 grid
    - failed after inserting point 7 of 10
    - student   size()    = 6
    - reference size()    = 7
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.25 0.5
      B  0.0 0.0
      C  0.75 0.5
      D  0.75 1.0
      E  0.0 0.5
      F  1.0 0.75
      G  0.5 0.5

  * 50 random general points in a 8-by-8 grid
    - failed after inserting point 5 of 50
    - student   size()    = 4
    - reference size()    = 5
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.25 0.625
      B  0.125 0.125
      C  0.0 0.25
      D  0.375 0.875
      E  1.0 0.875

  * 100000 random general points in a 16-by-16 grid
    - failed after inserting point 12 of 100000
    - student   size()    = 11
    - reference size()    = 12
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.375 0.75
      B  0.75 0.5625
      C  1.0 0.625
      D  0.6875 0.0
      E  0.1875 0.6875
      F  0.125 0.375
      G  0.0625 0.9375
      H  0.625 0.8125
      I  0.4375 0.875
      J  0.3125 0.4375
      K  0.0 0.1875
      L  0.875 0.5625

  * 100000 random general points in a 128-by-128 grid
    - failed after inserting point 4 of 100000
    - student   size()    = 3
    - reference size()    = 4
    - student   isEmpty() = false
    - reference isEmpty() = false
    - sequence of points inserted: 
      A  0.609375 0.3828125
      B  0.1328125 0.953125
      C  0.96875 0.2421875
      D  0.609375 0.109375

  * 100000 random general points in a 1024-by-1024 grid
    - failed after inserting point 93 of 100000
    - student   size()    = 92
    - reference size()    = 93
    - student   isEmpty() = false
    - reference isEmpty() = false

==> FAILED

Test 2a: insert points from file; check contains() with random query points
  * input0.txt
  * input1.txt
  * input5.txt
    - failed on trial 617 of 10000
    - query point          = (0.5, 0.4)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.7 0.2
      B  0.5 0.4
      C  0.2 0.3
      D  0.4 0.7
      E  0.9 0.6

  * input10.txt
==> FAILED

Test 2b: insert non-degenerate points; check contains() with random query points
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
    - failed on trial 64 of 10000
    - query point          = (0.125, 0.5)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  1.0 0.125
      B  0.25 0.25
      C  0.125 0.5
      D  0.75 0.625
      E  0.625 1.0

  * 10 random non-degenerate points in a 16-by-16 grid
    - failed on trial 26 of 10000
    - query point          = (0.25, 0.9375)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.6875 0.625
      B  0.375 0.875
      C  0.0625 0.8125
      D  0.25 0.9375
      E  0.5 0.5
      F  0.5625 0.0
      G  0.125 0.75
      H  0.1875 0.25
      I  1.0 0.4375
      J  0.875 0.6875

  * 20 random non-degenerate points in a 32-by-32 grid
    - failed on trial 31 of 10000
    - query point          = (0.59375, 0.28125)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.75 0.6875
      B  0.15625 0.59375
      C  0.59375 0.28125
      D  1.0 0.9375
      E  0.25 0.25
      F  0.5 0.5
      G  0.5625 0.625
      H  0.78125 0.09375
      I  0.09375 0.71875
      J  0.375 0.34375
      K  0.40625 0.03125
      L  0.46875 0.90625
      M  0.96875 0.46875
      N  0.3125 0.5625
      O  0.6875 0.53125
      P  0.8125 0.8125
      Q  0.65625 0.0
      R  0.28125 0.78125
      S  0.875 0.875
      T  0.125 0.21875

  * 500 random non-degenerate points in a 1024-by-1024 grid
    - failed on trial 6392 of 10000
    - query point          = (0.2353515625, 0.1708984375)
    - student   contains() = false
    - reference contains() = true

  * 10000 random non-degenerate points in a 65536-by-65536 grid
==> FAILED

Test 2c: insert distinct points; check contains() with random query points
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 4-by-4 grid
    - failed on trial 1 of 10000
    - query point          = (1.0, 0.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  1.0 0.25
      B  1.0 0.75
      C  0.0 0.5
      D  1.0 0.0
      E  0.25 0.75
      F  0.5 0.5
      G  0.75 0.5
      H  0.0 0.0
      I  0.5 0.0
      J  0.25 1.0

  * 20 random distinct points in a 8-by-8 grid
    - failed on trial 3 of 10000
    - query point          = (1.0, 0.25)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  1.0 0.0
      B  1.0 0.25
      C  0.5 0.0
      D  1.0 0.375
      E  0.625 0.625
      F  0.5 0.875
      G  0.5 1.0
      H  0.375 0.875
      I  0.25 0.0
      J  0.0 0.375
      K  0.0 0.0
      L  0.75 0.125
      M  0.5 0.25
      N  0.25 0.5
      O  0.375 1.0
      P  1.0 0.125
      Q  0.875 0.875
      R  0.125 0.375
      S  0.125 1.0
      T  0.5 0.625

  * 10000 random distinct points in a 128-by-128 grid
    - failed on trial 1 of 10000
    - query point          = (0.546875, 0.9296875)
    - student   contains() = false
    - reference contains() = true

  * 100000 random distinct points in a 1024-by-1024 grid
    - failed on trial 14 of 10000
    - query point          = (0.33984375, 0.701171875)
    - student   contains() = false
    - reference contains() = true

  * 100000 random distinct points in a 65536-by-65536 grid
==> FAILED

Test 2d: insert general points; check contains() with random query points
  * 10000 random general points in a 1-by-1 grid
    - failed on trial 1 of 10000
    - query point          = (1.0, 0.0)
    - student   contains() = false
    - reference contains() = true

  * 10000 random general points in a 16-by-16 grid
    - failed on trial 1 of 10000
    - query point          = (0.1875, 0.3125)
    - student   contains() = false
    - reference contains() = true

  * 10000 random general points in a 128-by-128 grid
    - failed on trial 1 of 10000
    - query point          = (0.5, 0.703125)
    - student   contains() = false
    - reference contains() = true

  * 10000 random general points in a 1024-by-1024 grid
    - failed on trial 39 of 10000
    - query point          = (0.2763671875, 0.59765625)
    - student   contains() = false
    - reference contains() = true

==> FAILED


Test 3c: insert distinct points; check range() with random query rectangles
  * 2 random distinct points and random rectangles in a 2-by-2 grid
  * 10 random distinct points and random rectangles in a 4-by-4 grid
  * 20 random distinct points and random rectangles in a 8-by-8 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.75, 0.875] x [0.0, 0.375]
    - sequence of points inserted: 
      A  0.625 0.75
      B  0.375 0.0
      C  0.125 0.625
      D  0.5 0.875
      E  0.0 0.625
      F  0.375 0.375
      G  0.25 0.0
      H  1.0 0.0
      I  0.0 0.75
      J  1.0 0.875
      K  0.375 0.25
      L  0.375 0.875
      M  0.875 0.125
      N  0.125 1.0
      O  0.0 0.25
      P  1.0 0.25
      Q  0.625 0.125
      R  0.875 0.75
      S  0.5 0.125
      T  0.875 0.25
    - student   range():  M 
    - reference range():  M T 

  * 100 random distinct points and random rectangles in a 16-by-16 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.5625, 0.8125] x [0.3125, 0.5625]
    - student   range():  N M U 
    - reference range():  N Y M T Y U 

  * 1000 random distinct points and random rectangles in a 64-by-64 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.84375, 0.921875] x [0.125, 0.328125]
    - student   range():  F M O C H M K U K X G G Q H 
    - reference range():  F M H O N F S C H M K U J K U H N X G Q T G H 

  * 10000 random distinct points and random rectangles in a 128-by-128 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.1015625, 0.109375] x [0.25, 0.6328125]
    - student   range():  W J W F I V F D F J V V C D M U V C X C C X L 
    - reference range():  W F B J W A F L A W I R V F D K B G H J G F P E O J ...

==> FAILED

Test 3d: insert general points; check range() with random query rectangles
  * 5000 random general points and random rectangles in a 2-by-2 grid
  * 5000 random general points and random rectangles in a 16-by-16 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.125, 0.25] x [0.0, 0.9375]
    - student   range():  T W T I L M K C K U O B N Q H 
    - reference range():  T O V X R W M Z B W Y T G H I L L V B B V M K G K N ...

  * 5000 random general points and random rectangles in a 128-by-128 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.0390625, 0.4765625] x [0.8359375, 0.875]
    - student   range():  U Z M X X B C B G Q Y W U O B P O O C R V S C W O A ...
    - reference range():  U Z M X M X B C G S B Q G W U O B G V Z P O M Y N E ...

  * 5000 random general points and random rectangles in a 1024-by-1024 grid
    - failed on trial 1 of 10000
    - query rectangle = [0.2080078125, 0.275390625] x [0.078125, 0.8349609375]
    - student   range():  B P S H Z F L A O Q V U I E H N O P G M P P J S P B ...
    - reference range():  B P S H Z F L A O Q V U I E H N O P G M P P J S P B ...

==> FAILED


Test 5a: insert points from file; check nearest() with random query points
  * input0.txt
  * input1.txt

    java.lang.NullPointerException

    KdTree.nearest(KdTree.java:98)
    TestKdTree.checkNearest(TestKdTree.java:316)
    TestKdTree.checkNearest(TestKdTree.java:282)
    TestKdTree.test5a(TestKdTree.java:1755)
    TestKdTree.main(TestKdTree.java:1969)

```
    double selfDist = nd.pt.distanceSquaredTo(p);
    if (selfDist < minDist) {
    nearNeighbor = nd.pt;
    }
    return nearNeighbor;
```  
the problem is that if only one point and the minDist is set to  
the square Dist to the query point and the nearNeighbor wont be   
set to nd.pt since selfDist == minDist
  * input5.txt

    java.lang.NullPointerException

    KdTree.nearest(KdTree.java:98)
    TestKdTree.checkNearest(TestKdTree.java:316)
    TestKdTree.checkNearest(TestKdTree.java:282)
    TestKdTree.test5a(TestKdTree.java:1756)
    TestKdTree.main(TestKdTree.java:1969)

 


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Point2D
*-----------------------------------------------------------
Memory of Point2D object = 32 bytes

================================================================



Analyzing memory of RectHV
*-----------------------------------------------------------
Memory of RectHV object = 48 bytes

================================================================



Analyzing memory of PointSET
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a PointSET with n points (including Point2D and RectHV objects).
Maximum allowed memory is 96n + 200 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          272                264
=> passed        2          368                360
=> passed        5          656                648
=> passed       10         1136               1128
=> passed       25         2576               2568
=> passed      100         9776               9768
=> passed      400        38576              38568
=> passed      800        76976              76968
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 176.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)

================================================================



Analyzing memory of KdTree
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a KdTree with n points (including Point2D and RectHV objects).
Maximum allowed memory is 312n + 192 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          120                160
=> passed        2          208                288
=> passed        5          472                672
=> passed       10          912               1312
=> passed       25         2232               3232
=> passed      100         8832              12832
=> passed      400        35232              51232
=> passed      800        70432             102432
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 88.00 n + 32.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 128.00 n + 32.00  (R^2 = 1.000)

================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing PointSET
*-----------------------------------------------------------
Running 14 total tests.


Inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1239468         
=> passed   320000    1194960         
=> passed   640000    1327042         
=> passed  1280000    1092786         
==> 4/4 tests passed

Performing contains() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1092906         
=> passed   320000     769183         
=> passed   640000     687789         
=> passed  1280000     659172         
==> 4/4 tests passed

Performing range() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       4907         
=> passed    20000       1682         
=> passed    40000        767         
==> 3/3 tests passed

Performing nearest() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       7347         
=> passed    20000       1917         
=> passed    40000        813         
==> 3/3 tests passed

Total: 14/14 tests passed!


================================================================



Timing KdTree
*-----------------------------------------------------------
Running 28 total tests.


Test 1a-d: Insert n points into a 2d tree. The table gives the average number of calls
           to methods in RectHV and Point per call to insert().

                                                                                                Point2D
               n      ops per second       RectHV()           x()               y()             equals()
----------------------------------------------------------------------------------------------------------------
=> passed   160000    1676505               0.0              33.9              32.7               0.0         
=> passed   320000    1701921               0.0              35.5              33.2               0.0         
=> passed   640000    1534060               0.0              38.4              36.0               0.0         
=> passed  1280000    1132219               0.0              39.5              39.3               0.0         
==> 4/4 tests passed


Test 2a-h: Perform contains() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to contains().

                                                                               Point2D
               n      ops per second       x()               y()               equals()
-----------------------------------------------------------------------------------------------
=> passed    10000    1682354               4.7               2.7               0.0         
=> passed    20000    1771614               8.8               6.9               0.0         
=> passed    40000    1783181              10.2               8.2               0.0         
=> passed    80000    1752293              10.2               8.5               0.0         
=> passed   160000    1746481              11.6              10.3               0.0         
=> passed   320000    1724882              12.6              11.3               0.0         
=> passed   640000    1764373               9.8               9.2               0.0         
=> passed  1280000    1697307              15.6              14.6               0.0         
==> 8/8 tests passed


Test 3a-h: Perform range() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to range().

               n      ops per second       intersects()      contains()        x()               y()
---------------------------------------------------------------------------------------------------------------
=> passed    10000     624893               0.0              31.1              64.9              26.2         
=> passed    20000     639168               0.0              32.6              68.5              31.4         
=> passed    40000     564208               0.0              39.3              82.0              32.3         
=> passed    80000     497497               0.0              40.7              84.8              33.8         
=> passed   160000     416219               0.0              42.5              90.9              40.7         
=> passed   320000     495155               0.0              40.2              84.3              34.6         
=> passed   640000     338339               0.0              43.3              91.1              39.7         
=> passed  1280000     313912               0.0              47.0              97.8              36.0         
==> 8/8 tests passed


Test 4a-h: Perform nearest() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to nearest().

                                         Point2D                 RectHV
               n      ops per second     distanceSquaredTo()     distanceSquaredTo()        x()               y()
------------------------------------------------------------------------------------------------------------------------

    java.lang.NullPointerException

    KdTree.nearest(KdTree.java:98)
    TimeKdTree.nearest(TimeKdTree.java:334)
    TimeKdTree.test4(TimeKdTree.java:555)
    TimeKdTree.main(TimeKdTree.java:593)

==> 0/8 tests passed



Total: 20/28 tests passed!


================================================================



See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Spotbugs:     PASSED
PMD:          FAILED (1 warning)
Checkstyle:   FAILED (0 errors, 2 warnings)

Correctness:  28/35 tests passed
Memory:       16/16 tests passed
Timing:       38/42 tests passed

Aggregate score: 86.10%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
 14K Oct  7 18:31 KdTree.java
3.7K Oct  7 18:31 PointSET.java



% pmd .
*-----------------------------------------------------------
PointSET.java:17: The private instance (or static) variable 'pointSet' can be made 'final'; it is initialized only in the declaration or constructor. [ImmutableField]
PMD ends with 1 warning.


================================================================


% checkstyle *.java
*-----------------------------------------------------------

% custom checkstyle checks for PointSET.java
*-----------------------------------------------------------

% custom checkstyle checks for KdTree.java
*-----------------------------------------------------------
[WARN] KdTree.java:322:34: The numeric literal '0.02' appears to be unnecessary. [NumericLiteral]
[WARN] KdTree.java:336:34: The numeric literal '0.02' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 2 warnings.


================================================================




================================================================
Testing correctness of KdTree
*-----------------------------------------------------------
Running 27 total tests.

In the tests below, we consider three classes of points and rectangles.

  * Non-degenerate points: no two points (or rectangles) share either an
                           x-coordinate or a y-coordinate

  * Distinct points:       no two points (or rectangles) share both an
                           x-coordinate and a y-coordinate

  * General points:        no restrictions on the x-coordinates or y-coordinates
                           of the points (or rectangles)

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m (inclusive).

Test 1a: insert points from file; check size() and isEmpty() after each insertion
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 1b: insert non-degenerate points; check size() and isEmpty() after each insertion
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
  * 50 random non-degenerate points in a 128-by-128 grid
  * 500 random non-degenerate points in a 1024-by-1024 grid
  * 50000 random non-degenerate points in a 65536-by-65536 grid
==> passed

Test 1c: insert distinct points; check size() and isEmpty() after each insertion
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 8-by-8 grid
  * 20 random distinct points in a 16-by-16 grid
  * 10000 random distinct points in a 128-by-128 grid
  * 100000 random distinct points in a 1024-by-1024 grid
  * 100000 random distinct points in a 65536-by-65536 grid
==> passed

Test 1d: insert general points; check size() and isEmpty() after each insertion
  * 5 random general points in a 1-by-1 grid
  * 10 random general points in a 4-by-4 grid
  * 50 random general points in a 8-by-8 grid
  * 100000 random general points in a 16-by-16 grid
  * 100000 random general points in a 128-by-128 grid
  * 100000 random general points in a 1024-by-1024 grid
==> passed

Test 2a: insert points from file; check contains() with random query points
  * input0.txt
  * input1.txt
  * input5.txt
    - failed on trial 2072 of 10000
    - query point          = (0.9, 0.6)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.7 0.2
      B  0.5 0.4
      C  0.2 0.3
      D  0.4 0.7
      E  0.9 0.6

  * input10.txt
==> FAILED

Test 2b: insert non-degenerate points; check contains() with random query points
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
    - failed on trial 15 of 10000
    - query point          = (0.25, 1.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.0 0.75
      B  1.0 0.125
      C  0.5 0.875
      D  0.125 0.25
      E  0.25 1.0

  * 10 random non-degenerate points in a 16-by-16 grid
    - failed on trial 33 of 10000
    - query point          = (0.4375, 0.4375)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.0625 0.3125
      B  0.75 0.5625
      C  0.625 1.0
      D  0.0 0.6875
      E  0.6875 0.8125
      F  1.0 0.9375
      G  0.9375 0.0
      H  0.4375 0.4375
      I  0.5625 0.0625
      J  0.8125 0.375

  * 20 random non-degenerate points in a 32-by-32 grid
    - failed on trial 30 of 10000
    - query point          = (0.21875, 0.84375)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.71875 0.65625
      B  0.25 0.0
      C  0.59375 0.5
      D  0.6875 0.09375
      E  0.34375 0.75
      F  0.375 0.34375
      G  0.125 0.46875
      H  0.90625 0.625
      I  0.3125 0.6875
      J  0.03125 0.25
      K  0.46875 0.875
      L  0.4375 0.03125
      M  0.875 0.375
      N  0.40625 0.59375
      O  0.96875 0.78125
      P  0.53125 0.90625
      Q  0.5625 0.3125
      R  0.0 0.0625
      S  0.65625 0.71875
      T  0.21875 0.84375

  * 500 random non-degenerate points in a 1024-by-1024 grid
    - failed on trial 1352 of 10000
    - query point          = (0.337890625, 0.9013671875)
    - student   contains() = false
    - reference contains() = true

  * 10000 random non-degenerate points in a 65536-by-65536 grid
==> FAILED

Test 2c: insert distinct points; check contains() with random query points
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 4-by-4 grid
    - failed on trial 2 of 10000
    - query point          = (0.0, 0.75)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.75 1.0
      B  0.5 0.0
      C  0.25 0.25
      D  1.0 0.25
      E  0.0 0.75
      F  0.25 1.0
      G  0.25 0.75
      H  0.5 0.75
      I  1.0 0.0
      J  1.0 0.75

  * 20 random distinct points in a 8-by-8 grid
    - failed on trial 4 of 10000
    - query point          = (0.125, 1.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted: 
      A  0.125 0.75
      B  0.75 0.625
      C  0.5 0.625
      D  0.625 0.875
      E  0.625 0.5
      F  0.25 0.625
      G  0.125 0.0
      H  0.5 1.0
      I  0.125 1.0
      J  0.0 1.0
      K  0.5 0.5
      L  0.5 0.75
      M  0.0 0.125
      N  0.5 0.25
      O  0.625 0.75
      P  0.625 0.375
      Q  0.25 0.75
      R  0.5 0.0
      S  0.125 0.25
      T  0.5 0.875

  * 10000 random distinct points in a 128-by-128 grid
    - failed on trial 3 of 10000
    - query point          = (0.078125, 0.6640625)
    - student   contains() = false
    - reference contains() = true

  * 100000 random distinct points in a 1024-by-1024 grid
    - failed on trial 73 of 10000
    - query point          = (0.5771484375, 0.5732421875)
    - student   contains() = false
    - reference contains() = true

  * 100000 random distinct points in a 65536-by-65536 grid
    - failed on trial 811 of 10000
    - query point          = (0.9581298828125, 0.5256805419921875)
    - student   contains() = false
    - reference contains() = true

==> FAILED

Test 2d: insert general points; check contains() with random query points
  * 10000 random general points in a 1-by-1 grid
    - failed on trial 2 of 10000
    - query point          = (1.0, 0.0)
    - student   contains() = false
    - reference contains() = true

  * 10000 random general points in a 16-by-16 grid
    - failed on trial 1 of 10000
    - query point          = (0.8125, 0.375)
    - student   contains() = false
    - reference contains() = true

  * 10000 random general points in a 128-by-128 grid
    - failed on trial 1 of 10000
    - query point          = (0.2890625, 0.421875)
    - student   contains() = false
    - reference contains() = true

  * 10000 random general points in a 1024-by-1024 grid
    - failed on trial 1 of 10000
    - query point          = (0.0224609375, 0.20703125)
    - student   contains() = false
    - reference contains() = true

==> FAILED





Test 6a: insert points from file; check nearest() with random query points
         and check traversal of kd-tree
  * input5.txt
  * input10.txt
    - student   nearest() = (0.32, 0.708)
    - reference nearest() = (0.32, 0.708)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.47, 0.75)
    - sequence of points inserted: 
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B H I G J C F 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B H I C F 
    - failed on trial 5 of 1000

==> FAILED


Reason: initial has chosen minDist as the squareDist from the root to the query  
point and in this case minDist = 0.073  and at Node I the dist is 0.09...  
so greater than the minDist and will not be updated  
  

Test 6b: insert non-degenerate points; check nearest() with random query points
         and check traversal of kd-tree
  * 5 random non-degenerate points in a 8-by-8 grid
    - student   nearest() = (1.0, 1.0)
    - reference nearest() = (1.0, 1.0)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.875, 0.875)
    - sequence of points inserted: 
      A  1.0 1.0
      B  0.125 0.375
      C  0.625 0.25
      D  0.25 0.625
      E  0.5 0.0
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B D C E 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B D 
    - failed on trial 6 of 1000

  * 10 random non-degenerate points in a 16-by-16 grid
    - student   nearest() = (0.4375, 0.0625)
    - reference nearest() = (0.4375, 0.0625)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.5625, 0.0)
    - sequence of points inserted: 
      A  0.6875 0.125
      B  0.5 1.0
      C  0.875 0.375
      D  0.75 0.5625
      E  0.125 0.875
      F  0.375 0.6875
      G  0.0 0.3125
      H  0.25 0.625
      I  0.4375 0.0625
      J  0.3125 0.75
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B E F H I C D 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B E F H I C 
    - failed on trial 14 of 1000

  * 20 random non-degenerate points in a 32-by-32 grid
    - student   nearest() = (0.59375, 0.78125)
    - reference nearest() = (0.59375, 0.78125)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.53125, 0.875)
    - sequence of points inserted: 
      A  0.4375 0.0625
      B  0.5625 0.46875
      C  0.75 0.40625
      D  0.84375 0.625
      E  0.5 0.3125
      F  0.96875 0.6875
      G  0.8125 0.375
      H  0.21875 1.0
      I  0.65625 0.59375
      J  0.875 0.75
      K  0.09375 0.71875
      L  0.46875 0.1875
      M  0.78125 0.25
      N  0.59375 0.78125
      O  0.03125 0.09375
      P  0.28125 0.90625
      Q  0.3125 0.15625
      R  0.90625 0.9375
      S  0.375 0.0
      T  0.0 0.4375
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B D I N H K P Q S O T 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B D I N H K P Q S 
    - failed on trial 1 of 1000

  * 30 random non-degenerate points in a 64-by-64 grid
    - student   nearest() = (0.0625, 0.0625)
    - reference nearest() = (0.0625, 0.0625)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 27
    - number of reference entries = 14
    - entry 6 of the two sequences are not equal
    - student   entry 6 = (0.234375, 0.71875)
    - reference entry 6 = (0.90625, 0.109375)

    - failed on trial 1 of 1000

  * 50 random non-degenerate points in a 128-by-128 grid
    - student   nearest() = (0.296875, 0.625)
    - reference nearest() = (0.296875, 0.625)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 18
    - number of reference entries = 15
    - entry 12 of the two sequences are not equal
    - student   entry 12 = (0.109375, 0.6875)
    - reference entry 12 = (0.6328125, 0.3515625)

    - failed on trial 2 of 1000

  * 1000 random non-degenerate points in a 2048-by-2048 grid
    - student   nearest() = (0.7001953125, 0.14306640625)
    - reference nearest() = (0.7001953125, 0.14306640625)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 53
    - number of reference entries = 27
    - entry 13 of the two sequences are not equal
    - student   entry 13 = (0.57568359375, 0.12451171875)
    - reference entry 13 = (0.75, 0.16748046875)

    - failed on trial 1 of 1000

==> FAILED

Test 7: check with no points
  * size() and isEmpty()
  * contains()
  * nearest()
  * range()
==> passed

Test 8: check that the specified exception is thrown with null arguments
  * argument to insert() is null
  * argument to contains() is null
  * argument to range() is null
  * argument to nearest() is null
==> passed

Test 9a: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with non-degenerate points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 9b: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with distinct points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 9c: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with general points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 12 of 20000
    - student   contains() = false
    - reference contains() = true
    - sequence of operations was:
           st.isEmpty()  ==>  true
           st.insert(1.0, 1.0)
           st.contains((1.0, 0.0))  ==>  false
           st.nearest((1.0, 1.0))   ==>  (1.0, 1.0)
           st.contains((1.0, 1.0))  ==>  true
           st.insert(1.0, 1.0)
           st.range([0.0, 1.0] x [0.0, 1.0])  ==>  Z 
           st.range([0.0, 1.0] x [0.0, 0.0])  ==>  empty
           st.insert(1.0, 1.0)
           st.insert(1.0, 0.0)
           st.insert(0.0, 1.0)
           st.contains((1.0, 0.0))  ==>  false

  * 20000 calls with general points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 257 of 20000
    - student   contains() = false
    - reference contains() = true

  * 20000 calls with general points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 471 of 20000
    - student   contains() = false
    - reference contains() = true

  * 20000 calls with general points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 128 of 20000
    - student   contains() = false
    - reference contains() = true

  * 20000 calls with general points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 10040 of 20000
    - student   contains() = false
    - reference contains() = true

  * 20000 calls with general points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> FAILED

Test 10: insert n random points into two different KdTree objects;
        check that repeated calls to size(), contains(), range(),
        and nearest() with the same arguments yield same results
  * 10 random general points in a 4-by-4 grid
  * 20 random general points in a 8-by-8 grid
  * 100 random general points in a 128-by-128 grid
  * 1000 random general points in a 65536-by-65536 grid
==> passed


Total: 20/27 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Point2D
*-----------------------------------------------------------
Memory of Point2D object = 32 bytes

================================================================



Analyzing memory of RectHV
*-----------------------------------------------------------
Memory of RectHV object = 48 bytes

================================================================



Analyzing memory of PointSET
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a PointSET with n points (including Point2D and RectHV objects).
Maximum allowed memory is 96n + 200 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          272                264
=> passed        2          368                360
=> passed        5          656                648
=> passed       10         1136               1128
=> passed       25         2576               2568
=> passed      100         9776               9768
=> passed      400        38576              38568
=> passed      800        76976              76968
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 176.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)

================================================================



Analyzing memory of KdTree
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a KdTree with n points (including Point2D and RectHV objects).
Maximum allowed memory is 312n + 192 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          120                160
=> passed        2          208                288
=> passed        5          472                672
=> passed       10          912               1312
=> passed       25         2232               3232
=> passed      100         8832              12832
=> passed      400        35232              51232
=> passed      800        70432             102432
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 88.00 n + 32.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 128.00 n + 32.00  (R^2 = 1.000)

================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing PointSET
*-----------------------------------------------------------
Running 14 total tests.


Inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1290096         
=> passed   320000    1163243         
=> passed   640000    1068384         
=> passed  1280000     906399         
==> 4/4 tests passed

Performing contains() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000     808517         
=> passed   320000     754533         
=> passed   640000     774832         
=> passed  1280000     757396         
==> 4/4 tests passed

Performing range() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       5627         
=> passed    20000       1865         
=> passed    40000        816         
==> 3/3 tests passed

Performing nearest() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       7368         
=> passed    20000       2029         
=> passed    40000        850         
==> 3/3 tests passed

Total: 14/14 tests passed!


================================================================



Timing KdTree
*-----------------------------------------------------------
Running 28 total tests.


Test 1a-d: Insert n points into a 2d tree. The table gives the average number of calls
           to methods in RectHV and Point per call to insert().

                                                                                                Point2D
               n      ops per second       RectHV()           x()               y()             equals()
----------------------------------------------------------------------------------------------------------------
=> passed   160000    1597641               0.0              33.9              32.7               0.0         
=> passed   320000    1755510               0.0              35.5              33.2               0.0         
=> passed   640000    1311763               0.0              38.4              36.0               0.0         
=> passed  1280000    1026956               0.0              39.5              39.3               0.0         
==> 4/4 tests passed


Test 2a-h: Perform contains() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to contains().

                                                                               Point2D
               n      ops per second       x()               y()               equals()
-----------------------------------------------------------------------------------------------
=> passed    10000    1568168               4.7               2.7               0.0         
=> passed    20000    1731239               8.8               6.9               0.0         
=> passed    40000    1660017              10.2               8.2               0.0         
=> passed    80000    1756511              10.2               8.5               0.0         
=> passed   160000    1742893              11.6              10.3               0.0         
=> passed   320000    1766529              12.6              11.3               0.0         
=> passed   640000    1814511               9.8               9.2               0.0         
=> passed  1280000    1750330              15.6              14.6               0.0         
==> 8/8 tests passed


Test 3a-h: Perform range() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to range().

               n      ops per second       intersects()      contains()        x()               y()
---------------------------------------------------------------------------------------------------------------
=> passed    10000     610836               0.0              31.1              64.9              26.2         
=> passed    20000     708487               0.0              32.6              68.5              31.4         
=> passed    40000     621450               0.0              39.3              82.0              32.3         
=> passed    80000     578556               0.0              40.7              84.8              33.8         
=> passed   160000     504811               0.0              42.5              90.9              40.7         
=> passed   320000     379064               0.0              40.2              84.3              34.6         
=> passed   640000     391661               0.0              43.3              91.1              39.7         
=> passed  1280000     390633               0.0              47.0              97.8              36.0         
==> 8/8 tests passed


Test 4a-h: Perform nearest() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to nearest().

                                         Point2D                 RectHV
               n      ops per second     distanceSquaredTo()     distanceSquaredTo()        x()               y()
------------------------------------------------------------------------------------------------------------------------
=> passed    10000   297907                 157.0                    0.0                   170.6             170.5         
=> passed    20000   203080                 235.8                    0.0                   252.0             250.1         
=> passed    40000    94642                 415.6                    0.0                   434.7             432.8         
=> passed    80000    67536                 531.8                    0.0                   551.6             549.9         
=> FAILED   160000    43925                 797.8   (1.3x)           0.0                   818.4             818.8         
=> FAILED   320000    24955                1116.2   (1.9x)           0.0                  1140.4   (1.4x)   1136.1   (1.4x)
=> FAILED   640000    20398                1694.1   (2.8x)           0.0                  1719.8   (2.1x)   1715.5   (2.1x)
=> FAILED  1280000     8978   (0.4x)       2082.1   (3.5x)           0.0                  2107.7   (2.6x)   2104.9   (2.6x)
==> 4/8 tests passed



Total: 24/28 tests passed!


===============================================================


using `zip kdtree.zip KdTree.java PointSET.java`  gives the warning:  
```
updating: PointSET.java
	zip warning: Local Entry CRC does not match CD: PointSET.java
 (deflated 72%)
updating: KdTree.java
	zip warning: Local Entry CRC does not match CD: KdTree.java
 (deflated 79%) 
```







API:          PASSED

Spotbugs:     PASSED
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 2 warnings)

Correctness:  33/35 tests passed
Memory:       16/16 tests passed
Timing:       38/42 tests passed

Aggregate score: 94.67%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
 14K Oct  7 19:14 KdTree.java
3.7K Oct  7 19:14 PointSET.java





% checkstyle *.java
*-----------------------------------------------------------

% custom checkstyle checks for PointSET.java
*-----------------------------------------------------------

% custom checkstyle checks for KdTree.java
*-----------------------------------------------------------
[WARN] KdTree.java:334:34: The numeric literal '0.02' appears to be unnecessary. [NumericLiteral]
[WARN] KdTree.java:348:34: The numeric literal '0.02' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 2 warnings.


================================================================



Test 6a: insert points from file; check nearest() with random query points
         and check traversal of kd-tree
  * input5.txt
  * input10.txt
    - student   nearest() = (0.32, 0.708)
    - reference nearest() = (0.32, 0.708)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.34, 0.8)
    - sequence of points inserted: 
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A C F B H I G J 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A C F B H I 
    - failed on trial 7 of 1000

==> FAILED

Test 6b: insert non-degenerate points; check nearest() with random query points
         and check traversal of kd-tree
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
    - student   nearest() = (0.3125, 0.625)
    - reference nearest() = (0.3125, 0.625)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.1875, 0.75)
    - sequence of points inserted: 
      A  0.9375 0.5625
      B  0.75 0.1875
      C  0.25 0.0
      D  0.3125 0.625
      E  0.0 0.9375
      F  0.5625 1.0
      G  0.0625 0.5
      H  0.375 0.6875
      I  0.5 0.25
      J  0.875 0.375
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B D E G F H I J 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B D E G F H 
    - failed on trial 2 of 1000

  * 20 random non-degenerate points in a 32-by-32 grid
    - student   nearest() = (0.25, 0.46875)
    - reference nearest() = (0.25, 0.46875)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.21875, 0.5)
    - sequence of points inserted: 
      A  0.1875 0.71875
      B  0.75 0.375
      C  0.53125 0.84375
      D  0.0 0.21875
      E  0.625 0.4375
      F  0.25 0.46875
      G  0.34375 1.0
      H  0.28125 0.1875
      I  0.96875 0.25
      J  0.65625 0.5625
      K  0.40625 0.3125
      L  0.59375 0.59375
      M  0.4375 0.96875
      N  0.125 0.65625
      O  0.03125 0.90625
      P  0.9375 0.28125
      Q  0.8125 0.9375
      R  0.375 0.125
      S  0.3125 0.15625
      T  0.15625 0.6875
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B C F G M D N T O 
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B C F G D N T 
    - failed on trial 1 of 1000

  * 30 random non-degenerate points in a 64-by-64 grid
    - student   nearest() = (0.109375, 0.828125)
    - reference nearest() = (0.109375, 0.828125)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 9
    - number of reference entries = 6
    - failed on trial 1 of 1000

  * 50 random non-degenerate points in a 128-by-128 grid
    - student   nearest() = (0.859375, 0.21875)
    - reference nearest() = (0.859375, 0.21875)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 11
    - number of reference entries = 10
    - failed on trial 2 of 1000

  * 1000 random non-degenerate points in a 2048-by-2048 grid
    - student   nearest() = (0.95849609375, 0.89501953125)
    - reference nearest() = (0.95849609375, 0.89501953125)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 49
    - number of reference entries = 17
    - failed on trial 1 of 1000

==> FAILED

Test 7: check with no points
  * size() and isEmpty()
  * contains()
  * nearest()
  * range()
==> passed

Test 8: check that the specified exception is thrown with null arguments
  * argument to insert() is null
  * argument to contains() is null
  * argument to range() is null
  * argument to nearest() is null
==> passed

Test 9a: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with non-degenerate points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 9b: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with distinct points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 9c: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with general points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 10: insert n random points into two different KdTree objects;
        check that repeated calls to size(), contains(), range(),
        and nearest() with the same arguments yield same results
  * 10 random general points in a 4-by-4 grid
  * 20 random general points in a 8-by-8 grid
  * 100 random general points in a 128-by-128 grid
  * 1000 random general points in a 65536-by-65536 grid
==> passed


Total: 25/27 tests passed!




Analyzing memory of PointSET
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a PointSET with n points (including Point2D and RectHV objects).
Maximum allowed memory is 96n + 200 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          272                264
=> passed        2          368                360
=> passed        5          656                648
=> passed       10         1136               1128
=> passed       25         2576               2568
=> passed      100         9776               9768
=> passed      400        38576              38568
=> passed      800        76976              76968
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 176.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)

================================================================



Analyzing memory of KdTree
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a KdTree with n points (including Point2D and RectHV objects).
Maximum allowed memory is 312n + 192 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          120                160
=> passed        2          208                288
=> passed        5          472                672
=> passed       10          912               1312
=> passed       25         2232               3232
=> passed      100         8832              12832
=> passed      400        35232              51232
=> passed      800        70432             102432
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 88.00 n + 32.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 128.00 n + 32.00  (R^2 = 1.000)

================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing PointSET
*-----------------------------------------------------------
Running 14 total tests.


Inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1270077         
=> passed   320000    1378324         
=> passed   640000    1435342         
=> passed  1280000    1219615         
==> 4/4 tests passed

Performing contains() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1028848         
=> passed   320000     960677         
=> passed   640000     818789         
=> passed  1280000     663046         
==> 4/4 tests passed

Performing range() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       4820         
=> passed    20000       1739         
=> passed    40000        762         
==> 3/3 tests passed

Performing nearest() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       7153         
=> passed    20000       1948         
=> passed    40000        811         
==> 3/3 tests passed

Total: 14/14 tests passed!


================================================================



Timing KdTree
*-----------------------------------------------------------
Running 28 total tests.


Test 1a-d: Insert n points into a 2d tree. The table gives the average number of calls
           to methods in RectHV and Point per call to insert().

                                                                                                Point2D
               n      ops per second       RectHV()           x()               y()             equals()
----------------------------------------------------------------------------------------------------------------
=> passed   160000    1853189               0.0              33.9              32.7               0.0         
=> passed   320000    1757441               0.0              35.5              33.2               0.0         
=> passed   640000    1504942               0.0              38.4              36.0               0.0         
=> passed  1280000    1332296               0.0              39.5              39.3               0.0         
==> 4/4 tests passed


Test 2a-h: Perform contains() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to contains().

                                                                               Point2D
               n      ops per second       x()               y()               equals()
-----------------------------------------------------------------------------------------------
=> passed    10000    1363300              18.5              17.5               0.0         
=> passed    20000    1442982              19.7              18.7               0.0         
=> passed    40000    1272908              21.8              20.8               0.0         
=> passed    80000    1188013              22.0              21.0               0.0         
=> passed   160000    1137177              23.2              22.2               0.0         
=> passed   320000    1010624              25.0              24.0               0.0         
=> passed   640000     908798              25.7              24.7               0.0         
=> passed  1280000     803187              27.2              26.2               0.0         
==> 8/8 tests passed


Test 3a-h: Perform range() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to range().

               n      ops per second       intersects()      contains()        x()               y()
---------------------------------------------------------------------------------------------------------------
=> passed    10000     601954               0.0              31.1              64.9              26.2         
=> passed    20000     655190               0.0              32.6              68.5              31.4         
=> passed    40000     600184               0.0              39.3              82.0              32.3         
=> passed    80000     545912               0.0              40.7              84.8              33.8         
=> passed   160000     415456               0.0              42.5              90.9              40.7         
=> passed   320000     431281               0.0              40.2              84.3              34.6         
=> passed   640000     353356               0.0              43.3              91.1              39.7         
=> passed  1280000     257846               0.0              47.0              97.8              36.0         
==> 8/8 tests passed


Test 4a-h: Perform nearest() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to nearest().

                                         Point2D                 RectHV
               n      ops per second     distanceSquaredTo()     distanceSquaredTo()        x()               y()
------------------------------------------------------------------------------------------------------------------------
=> passed    10000   276341                 155.5                    0.0                   170.2             170.1         
=> passed    20000   173042                 234.2                    0.0                   251.4             249.5         
=> passed    40000    84555                 413.6                    0.0                   433.8             431.9         
=> passed    80000    59961                 530.8                    0.0                   551.6             549.9         
=> FAILED   160000    36346                 794.7   (1.3x)           0.0                   816.3             816.6         
=> FAILED   320000    21190                1125.6   (1.9x)           0.0                  1150.8   (1.4x)   1146.6   (1.4x)
=> FAILED   640000    32139                1363.1   (2.3x)           0.0                  1386.8   (1.7x)   1386.6   (1.7x)
=> FAILED  1280000     5088   (0.3x)       2614.0   (4.4x)           0.0                  2642.0   (3.3x)   2640.7   (3.3x)
==> 4/8 tests passed



Total: 24/28 tests passed!
