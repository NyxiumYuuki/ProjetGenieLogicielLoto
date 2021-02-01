package fr.myny.grid;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class BuildGridTest {

    @Test
    void putValue() {
        BuildGrid grid = new BuildGrid(5);
        grid.putValue(18.5F, 2, 4);
        grid.displayGrid();
    }

    @Test
    void displayGrid() {
        BuildGrid grid = new BuildGrid(5);

        assertEquals(5, grid.getDimX());
        assertEquals(5, grid.getDimY());
        assertEquals(0.0, grid.getValue(0,0));

        float tabl[][] = new float[5][5];

        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                tabl[i][y] = 0;
            }
        }

        grid.displayGrid();
    }

    @Test
    void fillWith() {
        BuildGrid grid = new BuildGrid(5);
        grid.fillWith(5.5F);
        grid.displayGrid();
    }
}