package fr.myny.grid;

import org.junit.jupiter.api.Test;

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
        grid.displayGrid();
    }

    @Test
    void fillWith() {
        BuildGrid grid = new BuildGrid(5);
        grid.fillWith(5.5F);
        grid.displayGrid();
    }
}