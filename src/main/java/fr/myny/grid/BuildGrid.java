package fr.myny.grid;

import java.util.Arrays;
import java.util.Objects;

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

    public int getDimX ()
    {
        return m_dim_x;
    }

    public int getDimY ()
    {
        return m_dim_y;
    }

    public void setDimX (int value)
    {
        m_dim_x = value;
    }

    public void setDimY (int value)
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

    public float getValue(int posX, int posY)
    {
        return m_grid[posX][posY];
    }

    public float[][] getM_grid() {
        return m_grid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildGrid buildGrid = (BuildGrid) o;
        return m_dim_x == buildGrid.m_dim_x && m_dim_y == buildGrid.m_dim_y && Arrays.equals(m_grid, buildGrid.m_grid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(m_dim_x, m_dim_y);
        result = 31 * result + Arrays.hashCode(m_grid);
        return result;
    }
}