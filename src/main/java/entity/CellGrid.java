package entity;

import java.util.*;
import java.util.stream.Collectors;

public class CellGrid {
    // arbitrary number, just to have a sufficiently big size for our DS without need to re-size too soon
    // or ever in the case of simple patterns
    private static final int HASH_SIZE = 8192;

    //hash set below corresponds to the grid
    private HashSet<CellPosition> grid = new HashSet<>(HASH_SIZE);

    // map contains the number of live neighbours for each square
    // if square not on grid assume value of 0
    // square removed from the structure when neighbour count equals 0
    // to be updated when a cell is created or destroyed
    private HashMap<CellPosition, Integer> neighbours = new HashMap<>(HASH_SIZE);

    private void increment(CellPosition p) {
        Integer c = neighbours.get(p);
        neighbours.put(p, c == null ? 1 : c + 1);
    }

    private void decrease(CellPosition p) {
        int c = neighbours.get(p) - 1;
        if (c != 0) {
            neighbours.put(p, c);
        } else {
            neighbours.remove(p);
        }
    }

    void set(CellPosition p) {
        for (CellPosition w : p.neighbours()) {
            increment(w);
        }

        grid.add(p);
    }

    void reset(CellPosition p) {
        for (CellPosition w : p.neighbours()) {
            decrease(w);
        }
        grid.remove(p);
    }

    public void reset() {
        grid.clear();
        neighbours.clear();
    }

    public void put(int x, int y) {

        set(new CellPosition(x, y));
    }

    public Set<CellPosition> getGrid() {
        return grid;
    }

    // apply rules and advance to next generation's state grid
    public void applyRules() {
        // gather all cells in need of re-adjustment
        ArrayList<CellPosition> cellsToReset = new ArrayList<>();
        ArrayList<CellPosition> cellsToSet = new ArrayList<>();

        // Any live cell with fewer than two live neighbors dies
        // Any live cell with more than three live neighbors dies
        for (CellPosition p : grid) {
            Integer c = neighbours.get(p);
            if (c == null || c < 2 || c > 3) {
                cellsToReset.add(p);
            }
        }

        // Any dead cell with exactly three live neighbors becomes a live cell
        for (CellPosition p : neighbours.keySet()) {
            if (neighbours.get(p) == 3 && !grid.contains(p)) {
                cellsToSet.add(p);
            }
        }
        //Any live cell with two or three live neighbors lives - NOTHING CHANGES / DO NOTHING

        cellsToSet.forEach(p -> set(p));
        cellsToReset.forEach(p -> reset(p));
    }

    @Override
    public String toString()  {
        // determine size of array needed
        int largestX = grid.stream().mapToInt(CellPosition::getX).max().getAsInt();
        int largestY = grid.stream().mapToInt(CellPosition::getY).max().getAsInt();

        String[][] gridArr = new String[largestX+1][largestY+1];

        // Grid set contains only the coordinates of live cells, default to dead for anything else
        grid.stream().forEach(p-> gridArr[p.getX()][p.getY()]="#");

       return Arrays.deepToString(gridArr).replace("], ", "]\n");
    }
}
