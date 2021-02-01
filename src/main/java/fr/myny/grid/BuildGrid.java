package fr.myny.grid;

import java.util.Arrays;
import java.util.Objects;

public class BuildGrid {
    /** Tableau à deux dimension qui va contenir les grilles proposees par le logiciel */
    private double m_grid[][];

    /** nombre de lignes du tableau, soit le nombre de grilles à jouer que va proposer le tableau */
    private int m_dim_x;

    /** nombre de colonnes du tableau */
    private int m_dim_y;

    /*
     CONSTRUCTOR
     */

    /** Constructeur par defaut*/
    public BuildGrid()
    {
        System.out.print("Creating a new grid: ");

        m_grid = new double[1][5];
        setDimX(1);
        setDimY(5);

        this.fillWith(0);
        System.out.println("Done");
    }

    /** Constructeur avec parametres (Le tableau cree aura autant de lignes que de colonnes)*/
    public BuildGrid(int dim)
    {
        System.out.print("Creating a new grid: ");

        m_grid = new double[dim][dim];
        setDimX(dim);
        setDimY(dim);

        this.fillWith(0);
        System.out.println("Done");
    }

    /** Constructeur avec parametres*/
    public BuildGrid( int dim_x, int dim_y)
    {
        System.out.print("Creating a new grid: ");

        m_grid = new double[dim_x][dim_y];
        setDimX(dim_x);
        setDimY(dim_y);

        this.fillWith(0);
        System.out.println("Done");
    }

    /*
     SETTERS & GETTERS
     */

    /** Methode retournant le nombre de lignes du tableau*/
    public int getDimX ()
    {
        return m_dim_x;
    }

    /** Methode retournant le nombre de colonnes du tableau*/
    public int getDimY ()
    {
        return m_dim_y;
    }

    /** Modifie le nombre de lignes du tableau*/
    public void setDimX (int value)
    {
        m_dim_x = value;
    }

    /** Modifie le nombre de colonnes du tableau*/
    public void setDimY (int value)
    {
        m_dim_y = value;
    }

    /*
     TRANSFORM THE GRID
     */

    /** Methode permettant de mettre a jour la case (X,Y) du tableau avec le parametre 'value'*/
    public void putValue(float value, int X, int Y)
    {
        m_grid[X][Y] = value;
    }

    /** Methode permettant de recuperer la valeur de la case (X,Y) du tableau */
    public double getValue(int X, int Y)
    {
        return m_grid[X][Y];
    }

    /** Methode renvoyant une reference du tableau */
    public double[][] getM_grid() {
        return m_grid;
    }

    /** Methode permettant d'afficher le contenu du tableau a deux dimension sur un terminal*/
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

    /** Methode permettant de remplir toutes les cases du tableau avec la valeur du parametre 'value' */
    public void fillWith ( float value)
    {
        for (int i = 0; i < getDimX(); i++) {
            for (int y = 0; y < getDimY(); y++) {
                m_grid[i][y] = value;
            }
        }
    }

    /** Methode permettant de determiner les grilles a jouer que va proposer le systeme reducteur*/
     public void systemeReduc(int nb_numeros, int[] serie_de_numeros, float garantie)
    {
    }

    /** Methode surchargee permettant de verifier si deux tableaux sont egaux en comparant leur contenu */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildGrid buildGrid = (BuildGrid) o;
        return m_dim_x == buildGrid.m_dim_x && m_dim_y == buildGrid.m_dim_y && Arrays.equals(m_grid, buildGrid.m_grid);
    }

    /** Methode assignant un nombre unique a chaque instance de la classe Grid pour les differencier entre elles*/
    @Override
    public int hashCode() {
        int result = Objects.hash(m_dim_x, m_dim_y);
        result = 31 * result + Arrays.hashCode(m_grid);
        return result;
    }
}