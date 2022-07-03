package entity;

public class CellPosition {
    private final int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public final boolean equals (Object obj)
    {
        CellPosition p = (CellPosition) obj;
        return x == p.x && y == p.y;
    }

    // check README file for considerations on hashing
    @Override
    public final int hashCode ()
    {
        return x * 1217 + y * 1223;
    }

    @Override
    public String toString ()
    {
        return "(" + x + "," + y + ")";
    }

    public CellPosition[] neighbours ()
    {
        return new CellPosition[] {
                new CellPosition(x-1, y-1),
                new CellPosition(x-1, y),
                new CellPosition(x-1, y+1),
                new CellPosition(x, y-1),
                new CellPosition(x, y+1),
                new CellPosition(x+1, y-1),
                new CellPosition(x+1, y),
                new CellPosition(x+1, y+1)
        };
    }
}
