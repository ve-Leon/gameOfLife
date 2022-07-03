#GAME OF LIFE

## Theory & research
There are two approaches to designing for the game of life.The naive matrix-based ones where you store a part of the universe (or the entire universe) as a matrix. The entire matrix is re-calculated on each step. These programs are very simple but have two disadvantages. These are are limited by the size of the matrix, and Life structures are known to be able to grow indefinitely depending on the pattern. Given most Life colonies contain much fewer live cells than squares in the matrix we can keep track of the occupied cells in a data structure and only process those. This  sophisticated approach is encapsuled in [Hashlife](https://en.wikipedia.org/wiki/Hashlife) where special data structures known as [QuadTree](https://en.wikipedia.org/wiki/Quadtree) are used for space optimization and further speed up (time-wise) is achieved by memoization of the hashing functions. More on this [here](https://www.drdobbs.com/jvm/an-algorithm-for-compressing-space-and-t/184406478). Given the simple pattern called to simulate here, instead I opted for a more common data structure and relevant implementation utilising `HashMap` that should run sufficiently well up to million of iterations before running out of memory.

## Hashing
Proper hashing plays an important role in the performance of the solution, initial thought was to use 2 lower prime numbers but without much difference in calculation speed, better quality was achieved using some higher primer numbers to calculate a hashcode based on the co-ordinates of each `CellPosition` .

## Run
Run using the main class of `GameOfLifeApp`

## Considerations
Testing for the operations and edge cases handling required but outside the scope of this sprint due to time constraints.