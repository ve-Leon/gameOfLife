import entity.CellGrid;
import entity.CellPosition;

import java.util.Set;


public class GameOfLifeApp {
    public static void main(String[] args) {

        CellGrid cg = new CellGrid();
        initialize(cg, GLIDER);
        int totalGenerations = 100;
        //print initial state, could print the String[] instead but we print the initialized cg to verify initial state
        System.out.println(cg.toString());

        for (int i = 0; i < totalGenerations; i++) {
            Set<CellPosition> set = cg.getGrid();
            cg.applyRules();
            // print state of the cell grid after deriving the next gen
            System.out.println("Current generation: " + (i + 1));
            System.out.println(cg.toString());
        }
    }

    private static void initialize(CellGrid cg, String[][] p) {
        for (int x = 0; x < p.length; x++) {
            for (int y = 0; y < p[x].length; y++) {
                if (p[x][y] == "#") {
                    cg.put(x, y);
                }
            }
        }
    }

    // https://conwaylife.appspot.com/pattern/glider
    private static final String[][] GLIDER = {
            {"", "", "#", "", ""},
            {"", "", "", "", "#"},
            {"", "#", "#", "#", ""}
    };

    // https://conwaylife.appspot.com/pattern/acorn
    private static final String[][] ACORN = {
            {"", "#", "", "", "", "",""},
            {"", "", "", "#", "", "",""},
            {"#", "#","", "", "#", "#", "#"}
    };

}
