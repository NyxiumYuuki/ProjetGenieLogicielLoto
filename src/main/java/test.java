import fr.myny.grid.BuildGrid;

public class test{
    public static void main(String[] args){
        BuildGrid grid = new BuildGrid(5);
        grid.putValue(15.5F, 2,3);
        grid.displayGrid();
    }
}