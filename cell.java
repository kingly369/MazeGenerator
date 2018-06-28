public class cell {
    private boolean[] cells;
    private boolean visited;
    private int X;
    private int Y;


    public cell(int i, int j)
    {
        //{top,right,bottom,left}
        cells = new boolean[]{true, true, true, true};
        visited = false;
        X = i;
        Y = j;
    }

    public int getX()
    {
        //returns the row position of the cell
        return X;
    }

    public int getY()
    {
        //returns the col position of the cell
        return Y;
    }


    public void setTop()
    {
        cells[0] = false;
    }

    public void setRight()
    {
        cells[1] = false;
    }
    public void setBottom()
    {
        cells[2] = false;
    }
    public void setLeft()
    {
        cells[3] = false;
    }

    public boolean getTop()
    {
        return cells[0];
    }

    public boolean getRight()
    {
        return cells[1];
    }

    public boolean getBottom()
    {
        return cells[2];
    }

    public boolean getLeft()
    {
        return cells[3];
    }

    public boolean ifVisited()
    {
        return visited;
    }

    public void getVisited()
    {
       visited = true;
    }

}
