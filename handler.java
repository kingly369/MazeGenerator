import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class handler {
    private final int ROWS = 40;
    private final int COLUMNS = 40;
    private cell grid [][];
    private cell current;
    private ArrayList<cell> timeRewind;
    private int positionX;
    private int positionY;


    public handler(){
        grid = new cell[ROWS][COLUMNS];

        for(int i = 0; i < ROWS; i++)
        {
            for(int j = 0; j < COLUMNS; j++)
            {
                grid[i][j] = new cell(i,j);
            }
        }
        positionX = 0;
        positionY = 0;
        current = grid[0][0];
        //timeRewind records where current has moved
        timeRewind = new ArrayList<cell>();
        timeRewind.add(current);

    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        int rows = 0;
        int columns = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (grid[i][j].ifVisited()) {
                    g2.setColor(Color.red);
                    g2.fillRect(rows, columns, 20, 20);
                }
                if (grid[i][j].getTop()) {
                    g2.setColor(Color.blue);
                    g2.drawLine(rows, columns, rows, columns+20);

                }
                if (grid[i][j].getRight()) {
                    g2.setColor(Color.blue);
                    g2.drawLine(rows, columns+20, rows + 20, columns + 20);

                }
                if (grid[i][j].getBottom()) {
                    g2.setColor(Color.blue);
                    g2.drawLine(rows+20, columns, rows + 20, columns + 20);

                }
                if (grid[i][j].getLeft()) {
                    g2.setColor(Color.blue);
                    g2.drawLine(rows, columns, rows+20, columns);

                }

                rows += 20;
            }

            columns += 20;
            rows = 0;
        }
        g2.setColor(Color.CYAN);
        g2.fillRect(current.getY()*20,current.getX()*20,20,20);
    }

        public cell checkNeighbor()
        {
            ArrayList<cell> neighborsAvailable;
            neighborsAvailable = new ArrayList<cell>();

            if(positionY>0) {
                cell top = grid[positionX][positionY - 1];
                if(top!=null && !top.ifVisited())
                {
                    neighborsAvailable.add(top);
                }
            }

            if(positionX<ROWS-1)
            {
                cell right = grid[positionX+1][positionY];
                if(right!=null && !right.ifVisited())
                {
                    neighborsAvailable.add(right);
                }
            }
            if(positionY<COLUMNS-1)
            {
                cell bottom = grid[positionX][positionY+1];
                if(bottom !=null && !bottom.ifVisited())
                {
                    neighborsAvailable.add(bottom);
                }
            }
            if(positionX>0)
            {
                cell left = grid[positionX-1][positionY];
                if(left != null && !left.ifVisited())
                {
                    neighborsAvailable.add(left);
                }
            }


            if(neighborsAvailable.size() > 0)
            {
                //Randomly selecting a neighbor
                Random rand = new Random();
                int random = rand.nextInt(neighborsAvailable.size());
                //Updates the current's path
                timeRewind.add(current);


                if(neighborsAvailable.get(random).getX()>positionX)
                {
                    current.setRight();
                    neighborsAvailable.get(random).setLeft();
                }
                else if(neighborsAvailable.get(random).getX()<positionX)
                {
                    current.setLeft();
                    neighborsAvailable.get(random).setRight();
                }
                else if(neighborsAvailable.get(random).getY()>positionY)
                {
                    current.setBottom();
                    neighborsAvailable.get(random).setTop();
                }
                else if(neighborsAvailable.get(random).getY()<positionY)
                {
                    current.setTop();
                    neighborsAvailable.get(random).setBottom();
                }
                return neighborsAvailable.get(random);
            }
            else
            {
                //If there's no available neighbors, it goes a cell backwards
                return timeRewind.remove(timeRewind.size()-1);
            }
        }


        public void game()
        {
            //if timeRewind length equals 0, current visited every cell
                if(timeRewind.size() >0)
                {
                    current.getVisited();
                    grid[positionX][positionY] = current;
                    current = checkNeighbor();
                    positionX = current.getX();
                    positionY = current.getY();
                    current.getVisited();
                }
        }

}
