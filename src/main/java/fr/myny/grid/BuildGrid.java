package fr.myny.grid;

public class BuildGrid {

    private int m_dim_x;
    private int m_dim_y;
    private float m_grid[][];

    /*
     CONSTRUCTOR
     */

    public BuildGrid(int dim)
    {
        System.out.print("Creating a new grid: ");

        m_grid = new float[dim][dim];
        setDimX(dim);
        setDimY(dim);

        this.fillWith(0);
        System.out.println("Done");
    }

    public BuildGrid( int dim_x, int dim_y)
    {
        System.out.print("Creating a new grid: ");

        m_grid = new float[dim_x][dim_y];
        setDimX(dim_x);
        setDimY(dim_y);

        this.fillWith(0);
        System.out.println("Done");
    }

    /*
     SETTERS & GETTERS
     */

    private int getDimX ()
    {
        return m_dim_x;
    }

    private int getDimY ()
    {
        return m_dim_y;
    }

    private void setDimX (int value)
    {
        m_dim_x = value;
    }

    private void setDimY (int value)
    {
        m_dim_y = value;
    }

    /*
     TRANSFORM THE GRID
     */

    public void putValue(float value, int posX, int posY)
    {
        m_grid[posX][posY] = value;
    }

    public void displayGrid ()
    {
        for (int i = 0; i < getDimX(); i++) {
            System.out.print("[  ");
            for (int y = 0; y < getDimY(); y++) {
                System.out.print(m_grid[i][y]);
                System.out.print("  ");
            }
            System.out.println("]");
        }
    }

    public void fillWith ( float value)
    {
        for (int i = 0; i < getDimX(); i++) {
            for (int y = 0; y < getDimY(); y++) {
                m_grid[i][y] = value;
            }
        }
    }
}