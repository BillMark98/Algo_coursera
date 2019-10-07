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
