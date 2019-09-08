### Notes on intellij
* use `cmd + shift + a` to search for a particular action
* use `option + cmd + o` to go to a method
* at first, markdown is not supported, go to ** Preferences -> Plugin -> Install JetBrains plugin **
* here is also a link to [Markdown](https://www.markdowntutorial.com/lesson/1/)

### programming style and grammar
* inner classes cannot have static classes  
here is a link to explain it [inner classes](https://www.quora.com/Why-can%E2%80%99t-inner-class-methods-be-static-in-Java)  
another link [inner cl](https://stackoverflow.com/questions/31956340/recyclerview-inner-classes-cannot-have-static-declaration)

* Comparator or Comparable?  
notes says that MinPQ uses Key implements Comparable  
but the documentation says uses Key implements Comparator  

* innerclass Accessability?  
Outer classes have access to all memeber of the inner class, no matter it is private or public  
here is a link to explain it [inner class access](https://stackoverflow.com/questions/19747812/why-can-the-private-member-of-an-nested-class-be-accessed-by-the-methods-of-the?lq=1)

### programming issues
* by implementation of A*  
dont need to update the moves of the  searchNode.  
since each time a minNode is deleted from the MinPQ,  
it's neighbors will all be considered, meanwhile, all neighbors will have moves that is the moves  
of the minNode + 1. it is possible that the MinPQ will contain multiple SearchNode which have the same board  
with different moves.  So the space waste may be large, but it's optimal in time, since we dont have to search  
for the SearchNode in the MinPQ, which has the same board with one of the neighbor of the minNode  


* In the method Solver(Board initial)

minPQ.insert(new SearchNode(initial))
```
Method invocation 'insert' may produce 'java.lang.NullPointerException
```
because minPQ is not initialized first.
should add :
``` minPQ = new MinPQ<SearchNode>();
```

* the Solver for A* implementation 
use one MinPQ for the Solver:

```
    ================================================================
    ********************************************************************************
    *  MEMORY
    ********************************************************************************

    Analyzing memory of Board
    *-----------------------------------------------------------
    Running 10 total tests.

    Memory usage of an n-by-n board
    [ must be at most 4n^2 + 32n + 64 bytes ]


                  n       student (bytes)    reference (bytes)
    ----------------------------------------------------------
    => passed     2           144                  128
    => passed     3           184                  192
    => passed     4           224                  240
    => passed     8           448                  560
    => passed    12           736                 1008
    => passed    16          1088                 1584
    => passed    20          1504                 2288
    => passed    37          4208                 6856
    => passed    72         12736                23088
    => passed   120         32704                61488
    ==> 10/10 tests passed

    Total: 10/10 tests passed!

    Student   memory = 2.00 n^2 + 32.00 n + 64.00   (R^2 = 1.000)
    Reference memory = 4.00 n^2 + 32.00 n + 48.00   (R^2 = 1.000)


    ================================================================




    ================================================================
    ********************************************************************************
    *  MEMORY (substituting reference Board)
    ********************************************************************************

    Analyzing memory of Solver
    *-----------------------------------------------------------
    Running 12 total tests.

    Maximum allowed time per puzzle is 5.0 seconds.
    Maximum allowed memory per puzzle = 200000000 bytes.

    Test 1: Measure memory of Solver.

                   filename   moves   memory
    ---------------------------------------------
    => passed  puzzle10.txt      10     4640
    => passed  puzzle15.txt      15     5568
    => passed  puzzle20.txt      20     2752
    => passed  puzzle25.txt      25     3392
    => passed  puzzle30.txt      30     4032
    => passed  puzzle35.txt      35     5536
    ==> 6/6 tests passed



    Test 2: Measure memory of MinPQ.

                                  deep              max           ending
                   filename     memory             size             size
    --------------------------------------------------------------------
    => passed  puzzle10.txt      13000               18               17
    => passed  puzzle15.txt      16296               27               26
    => passed  puzzle20.txt     197880              511              510
    => passed  puzzle25.txt    1712600             4402             4401
    => passed  puzzle30.txt    6668136            16679            16678
    => passed  puzzle35.txt  100982384           282762           282761
    ==> 6/6 tests passed


    Total: 12/12 tests passed!


    ================================================================



    ********************************************************************************
    *  TIMING (substituting reference Board)
    ********************************************************************************

    Timing Solver
    *-----------------------------------------------------------
    Running 125 total tests.

    Maximum allowed time per puzzle is 5.0 seconds.

    Test 1: Measure CPU time and check correctness

                   filename   moves    n  seconds
    ---------------------------------------------
    => passed  puzzle20.txt      20    3     0.01
    => passed  puzzle22.txt      22    3     0.00
    => passed  puzzle21.txt      21    3     0.00
    => passed  puzzle23.txt      23    3     0.01
    => passed  puzzle24.txt      24    3     0.00
    => passed  puzzle25.txt      25    3     0.01
    => passed  puzzle27.txt      27    3     0.01
    => passed  puzzle29.txt      29    3     0.01
    => passed  puzzle26.txt      26    3     0.01
    => passed  puzzle28.txt      28    3     0.01
    => passed  puzzle30.txt      30    3     0.02
    => passed  puzzle31.txt      31    3     0.02
    => passed  puzzle39.txt      39    4     0.29
    => passed  puzzle41.txt      41    5     0.04
    => passed  puzzle34.txt      34    4     0.13
    => passed  puzzle37.txt      37    4     0.14
    => passed  puzzle44.txt      44    5     0.09
    => passed  puzzle32.txt      32    4     1.99
    => passed  puzzle35.txt      35    4     0.36
    => passed  puzzle33.txt      33    4     0.30
    => passed  puzzle43.txt      43    4     0.71
    => passed  puzzle46.txt      46    4     0.64
    => passed  puzzle40.txt      40    4     0.67
    => passed  puzzle36.txt      36    4     1.64
    => passed  puzzle45.txt      45    4     0.56
    ==> 25/25 tests passed



    Test 2: Count MinPQ operations

                   filename   insert()         delMin()
    ---------------------------------------------------
    => passed  puzzle20.txt       1270              760
    => passed  puzzle22.txt       2951             1743
    => passed  puzzle21.txt       4853             2860
    => passed  puzzle23.txt       7424             4431
    => passed  puzzle24.txt       5725             3441
    => passed  puzzle25.txt      10830             6429
    => passed  puzzle27.txt      11779             7091
    => FAILED  puzzle29.txt      25049   (1.2x)   15008   (1.2x)
    => passed  puzzle26.txt      13208             7875
    => passed  puzzle28.txt      23157            13872
    => passed  puzzle30.txt      42141            25463
    => passed  puzzle31.txt      41174            24756
    => FAILED  puzzle39.txt     544867   (4.4x)  266262   (4.3x)
    => passed  puzzle41.txt      63117            26659
    => passed  puzzle34.txt     270266           129997
    => passed  puzzle37.txt     290824           139449
    => passed  puzzle44.txt     151576            67343
    => FAILED  puzzle32.txt    2239484   (2.5x) 1065163   (2.4x)
    => passed  puzzle35.txt     550435           267674
    => passed  puzzle33.txt     529425           252306
    => passed  puzzle43.txt    1194331           576567
    => passed  puzzle46.txt    1187683           591596
    => passed  puzzle40.txt    1269942           620018
    => passed  puzzle36.txt    2283713          1091940
    => passed  puzzle45.txt    1054801           515556
    ==> 22/25 tests passed



    Test 3: Count Board operations (that should not get called)

                   filename    hamming()   toString()
    -------------------------------------------------
    => passed  puzzle20.txt            0            0
    => passed  puzzle22.txt            0            0
    => passed  puzzle21.txt            0            0
    => passed  puzzle23.txt            0            0
    => passed  puzzle24.txt            0            0
    => passed  puzzle25.txt            0            0
    => passed  puzzle27.txt            0            0
    => passed  puzzle29.txt            0            0
    => passed  puzzle26.txt            0            0
    => passed  puzzle28.txt            0            0
    => passed  puzzle30.txt            0            0
    => passed  puzzle31.txt            0            0
    => passed  puzzle39.txt            0            0
    => passed  puzzle41.txt            0            0
    => passed  puzzle34.txt            0            0
    => passed  puzzle37.txt            0            0
    => passed  puzzle44.txt            0            0
    => passed  puzzle32.txt            0            0
    => passed  puzzle35.txt            0            0
    => passed  puzzle33.txt            0            0
    => passed  puzzle43.txt            0            0
    => passed  puzzle46.txt            0            0
    => passed  puzzle40.txt            0            0
    => passed  puzzle36.txt            0            0
    => passed  puzzle45.txt            0            0
    ==> 25/25 tests passed



    Test 4a: Count Board operations (that should get called)

                   filename    Board()            equals()         manhattan()
    --------------------------------------------------------------------------
    => passed  puzzle20.txt       2027                2017                2030
    => passed  puzzle22.txt       4691                4685                4694
    => passed  puzzle21.txt       7710                7702                7713
    => passed  puzzle23.txt      11852               11844               11855
    => passed  puzzle24.txt       9163                9153                9166
    => passed  puzzle25.txt      17256               17248               17259
    => passed  puzzle27.txt      18867               18859               18870
    => FAILED  puzzle29.txt      40054   (1.2x)      40046   (1.2x)      40057
    => passed  puzzle26.txt      21080               21074               21083
    => passed  puzzle28.txt      37026               37016               37029
    => passed  puzzle30.txt      67601               67595               67604
    => passed  puzzle31.txt      65927               65919               65930
    => FAILED  puzzle39.txt     811126   (4.4x)     811118   (4.4x)     811129
    => passed  puzzle41.txt      89773               89763               89776
    => passed  puzzle34.txt     400260              400254              400263
    => passed  puzzle37.txt     430270              430262              430273
    => passed  puzzle44.txt     218916              218906              218919
    => FAILED  puzzle32.txt    3304644   (2.4x)    3304634   (2.4x)    3304647
    => passed  puzzle35.txt     818106              818096              818109
    => passed  puzzle33.txt     781728              781720              781731
    => passed  puzzle43.txt    1770895             1770887             1770898
    => passed  puzzle46.txt    1779276             1779268             1779279
    => passed  puzzle40.txt    1889957             1889951             1889960
    => passed  puzzle36.txt    3375650             3375640             3375653
    => passed  puzzle45.txt    1570354             1570346             1570357
    ==> 22/25 tests passed



    Test 4b: count Board operations (that should get called),
             rejecting if doesn't adhere to stricter caching limits

                   filename    Board()            equals()         manhattan()
    --------------------------------------------------------------------------
    => passed  puzzle20.txt       2027                2017                2030
    => passed  puzzle22.txt       4691                4685                4694
    => passed  puzzle21.txt       7710                7702                7713
    => passed  puzzle23.txt      11852               11844               11855
    => passed  puzzle24.txt       9163                9153                9166
    => passed  puzzle25.txt      17256               17248               17259
    => passed  puzzle27.txt      18867               18859               18870
    => FAILED  puzzle29.txt      40054   (1.2x)      40046   (1.2x)      40057   (1.2x)
    => passed  puzzle26.txt      21080               21074               21083
    => passed  puzzle28.txt      37026               37016               37029
    => passed  puzzle30.txt      67601               67595               67604
    => passed  puzzle31.txt      65927               65919               65930
    => FAILED  puzzle39.txt     811126   (4.4x)     811118   (4.4x)     811129   (4.4x)
    => passed  puzzle41.txt      89773               89763               89776
    => passed  puzzle34.txt     400260              400254              400263
    => passed  puzzle37.txt     430270              430262              430273
    => passed  puzzle44.txt     218916              218906              218919
    => FAILED  puzzle32.txt    3304644   (2.4x)    3304634   (2.4x)    3304647   (2.4x)
    => passed  puzzle35.txt     818106              818096              818109
    => passed  puzzle33.txt     781728              781720              781731
    => passed  puzzle43.txt    1770895             1770887             1770898
    => passed  puzzle46.txt    1779276             1779268             1779279
    => passed  puzzle40.txt    1889957             1889951             1889960
    => passed  puzzle36.txt    3375650             3375640             3375653
    => passed  puzzle45.txt    1570354             1570346             1570357
    ==> 22/25 tests passed


    Total: 116/125 tests passed!


```


We see that some tests of timing does not meet the requirement  
while using  two MinPQ:

```

================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Board
*-----------------------------------------------------------
Running 10 total tests.

Memory usage of an n-by-n board
[ must be at most 4n^2 + 32n + 64 bytes ]


              n       student (bytes)    reference (bytes)
----------------------------------------------------------
=> passed     2           144                  128
=> passed     3           184                  192
=> passed     4           224                  240
=> passed     8           448                  560
=> passed    12           736                 1008
=> passed    16          1088                 1584
=> passed    20          1504                 2288
=> passed    37          4208                 6856
=> passed    72         12736                23088
=> passed   120         32704                61488
==> 10/10 tests passed

Total: 10/10 tests passed!

Student   memory = 2.00 n^2 + 32.00 n + 64.00   (R^2 = 1.000)
Reference memory = 4.00 n^2 + 32.00 n + 48.00   (R^2 = 1.000)


================================================================






================================================================
********************************************************************************
*  MEMORY (substituting reference Board)
********************************************************************************

Analyzing memory of Solver
*-----------------------------------------------------------
Running 12 total tests.

Maximum allowed time per puzzle is 5.0 seconds.
Maximum allowed memory per puzzle = 200000000 bytes.

Test 1: Measure memory of Solver.

               filename   moves   memory
---------------------------------------------
=> passed  puzzle10.txt      10     4640
=> passed  puzzle15.txt      15     5568
=> passed  puzzle20.txt      20     2752
=> passed  puzzle25.txt      25     3392
=> passed  puzzle30.txt      30     4032
=> passed  puzzle35.txt      35     5536
==> 6/6 tests passed



Test 2: Measure memory of MinPQ.

                              deep              max           ending
               filename     memory             size             size
--------------------------------------------------------------------
=> passed  puzzle10.txt      29400               34               33
=> passed  puzzle15.txt      36904               52               51
=> passed  puzzle20.txt     230480              587              586
=> passed  puzzle25.txt    1637928             4214             4213
=> passed  puzzle30.txt    6817336            17038            17037
=> passed  puzzle35.txt   97161368           271122           271121
==> 6/6 tests passed


Total: 12/12 tests passed!


================================================================



********************************************************************************
*  TIMING (substituting reference Board)
********************************************************************************

Timing Solver
*-----------------------------------------------------------
Running 125 total tests.

Maximum allowed time per puzzle is 5.0 seconds.

Test 1: Measure CPU time and check correctness

               filename   moves    n  seconds
---------------------------------------------
=> passed  puzzle20.txt      20    3     0.01
=> passed  puzzle22.txt      22    3     0.00
=> passed  puzzle21.txt      21    3     0.00
=> passed  puzzle23.txt      23    3     0.00
=> passed  puzzle24.txt      24    3     0.00
=> passed  puzzle25.txt      25    3     0.01
=> passed  puzzle27.txt      27    3     0.01
=> passed  puzzle29.txt      29    3     0.01
=> passed  puzzle26.txt      26    3     0.01
=> passed  puzzle28.txt      28    3     0.01
=> passed  puzzle30.txt      30    3     0.02
=> passed  puzzle31.txt      31    3     0.02
=> passed  puzzle39.txt      39    4     0.03
=> passed  puzzle41.txt      41    5     0.06
=> passed  puzzle34.txt      34    4     0.08
=> passed  puzzle37.txt      37    4     0.08
=> passed  puzzle44.txt      44    5     0.16
=> passed  puzzle32.txt      32    4     0.27
=> passed  puzzle35.txt      35    4     0.30
=> passed  puzzle33.txt      33    4     0.32
=> passed  puzzle43.txt      43    4     0.89
=> passed  puzzle46.txt      46    4     0.58
=> passed  puzzle40.txt      40    4     0.62
=> passed  puzzle36.txt      36    4     1.73
=> passed  puzzle45.txt      45    4     1.42
==> 25/25 tests passed



Test 2: Count MinPQ operations

               filename   insert()         delMin()
---------------------------------------------------
=> passed  puzzle20.txt       1439              853
=> passed  puzzle22.txt       3481             2071
=> passed  puzzle21.txt       3541             2081
=> passed  puzzle23.txt       5299             3149
=> passed  puzzle24.txt       5427             3259
=> passed  puzzle25.txt      10316             6103
=> passed  puzzle27.txt      11209             6741
=> passed  puzzle29.txt      11637             7077
=> passed  puzzle26.txt      11894             7099
=> passed  puzzle28.txt      26974            16231
=> passed  puzzle30.txt      43094            26057
=> passed  puzzle31.txt      46007            27805
=> passed  puzzle39.txt      71417            35045
=> passed  puzzle41.txt     116491            50009
=> passed  puzzle34.txt     151673            73159
=> passed  puzzle37.txt     166811            80085
=> passed  puzzle44.txt     275661           123165
=> passed  puzzle32.txt     521596           249495
=> passed  puzzle35.txt     528418           257297
=> passed  puzzle33.txt     622352           298883
=> passed  puzzle43.txt    1056805           508833
=> passed  puzzle46.txt    1032320           516741
=> passed  puzzle40.txt    1108443           541467
=> passed  puzzle36.txt    2086331          1011485
=> passed  puzzle45.txt    2418079          1189753
==> 25/25 tests passed



Test 3: Count Board operations (that should not get called)

               filename    hamming()   toString()
-------------------------------------------------
=> passed  puzzle20.txt            0            0
=> passed  puzzle22.txt            0            0
=> passed  puzzle21.txt            0            0
=> passed  puzzle23.txt            0            0
=> passed  puzzle24.txt            0            0
=> passed  puzzle25.txt            0            0
=> passed  puzzle27.txt            0            0
=> passed  puzzle29.txt            0            0
=> passed  puzzle26.txt            0            0
=> passed  puzzle28.txt            0            0
=> passed  puzzle30.txt            0            0
=> passed  puzzle31.txt            0            0
=> passed  puzzle39.txt            0            0
=> passed  puzzle41.txt            0            0
=> passed  puzzle34.txt            0            0
=> passed  puzzle37.txt            0            0
=> passed  puzzle44.txt            0            0
=> passed  puzzle32.txt            0            0
=> passed  puzzle35.txt            0            0
=> passed  puzzle33.txt            0            0
=> passed  puzzle43.txt            0            0
=> passed  puzzle46.txt            0            0
=> passed  puzzle40.txt            0            0
=> passed  puzzle36.txt            0            0
=> passed  puzzle45.txt            0            0
==> 25/25 tests passed



Test 4a: Count Board operations (that should get called)

               filename    Board()            equals()         manhattan()
--------------------------------------------------------------------------
=> passed  puzzle20.txt       2289                2279                2292
=> passed  puzzle22.txt       5549                5543                5552
=> passed  puzzle21.txt       5619                5611                5622
=> passed  puzzle23.txt       8445                8437                8448
=> passed  puzzle24.txt       8683                8673                8686
=> passed  puzzle25.txt      16416               16408               16419
=> passed  puzzle27.txt      17947               17939               17950
=> passed  puzzle29.txt      18711               18703               18714
=> passed  puzzle26.txt      18990               18984               18993
=> passed  puzzle28.txt      43202               43192               43205
=> passed  puzzle30.txt      69148               69142               69151
=> passed  puzzle31.txt      73809               73801               73812
=> passed  puzzle39.txt     106459              106451              106462
=> passed  puzzle41.txt     166497              166487              166500
=> passed  puzzle34.txt     224829              224823              224832
=> passed  puzzle37.txt     246893              246885              246896
=> passed  puzzle44.txt     398823              398813              398826
=> passed  puzzle32.txt     771088              771078              771091
=> passed  puzzle35.txt     785712              785702              785715
=> passed  puzzle33.txt     921232              921224              921235
=> passed  puzzle43.txt    1565635             1565627             1565638
=> passed  puzzle46.txt    1549058             1549050             1549061
=> passed  puzzle40.txt    1649907             1649901             1649910
=> passed  puzzle36.txt    3097813             3097803             3097816
=> passed  puzzle45.txt    3607829             3607821             3607832
==> 25/25 tests passed



Test 4b: count Board operations (that should get called),
         rejecting if doesn't adhere to stricter caching limits

               filename    Board()            equals()         manhattan()
--------------------------------------------------------------------------
=> passed  puzzle20.txt       2289                2279                2292
=> passed  puzzle22.txt       5549                5543                5552
=> passed  puzzle21.txt       5619                5611                5622
=> passed  puzzle23.txt       8445                8437                8448
=> passed  puzzle24.txt       8683                8673                8686
=> passed  puzzle25.txt      16416               16408               16419
=> passed  puzzle27.txt      17947               17939               17950
=> passed  puzzle29.txt      18711               18703               18714
=> passed  puzzle26.txt      18990               18984               18993
=> passed  puzzle28.txt      43202               43192               43205
=> passed  puzzle30.txt      69148               69142               69151
=> passed  puzzle31.txt      73809               73801               73812
=> passed  puzzle39.txt     106459              106451              106462
=> passed  puzzle41.txt     166497              166487              166500
=> passed  puzzle34.txt     224829              224823              224832
=> passed  puzzle37.txt     246893              246885              246896
=> passed  puzzle44.txt     398823              398813              398826
=> passed  puzzle32.txt     771088              771078              771091
=> passed  puzzle35.txt     785712              785702              785715
=> passed  puzzle33.txt     921232              921224              921235
=> passed  puzzle43.txt    1565635             1565627             1565638
=> passed  puzzle46.txt    1549058             1549050             1549061
=> passed  puzzle40.txt    1649907             1649901             1649910
=> passed  puzzle36.txt    3097813             3097803             3097816
=> passed  puzzle45.txt    3607829             3607821             3607832
==> 25/25 tests passed


Total: 125/125 tests passed!


================================================================
```

here all the requirements for time are meeted.  
I guess it's because when we use only one MinPQ, the binary tree  
will be larger, so the sink operation after each delMin will be more expensive    
another issue is that since we have kind of merged the two MinPQ, it is highly possible  
that we have created many SearchNodes that points to the same board. Since
the twin generated is very close to the board (just change the 0,0 and 1 1, modification  
of choose of points if needed) the neighbors of each minPQ may already pushed onto  
the MinPQ, so we round up in building tons of boards that already pushed into the MinPQ  
so the call of Board() and equals() and manhattan() will be more

Question is in the ** Enrichment ** in the specification  
it says that one way to speed up the algorithms is to use one MinPQ? Hmmm.....
