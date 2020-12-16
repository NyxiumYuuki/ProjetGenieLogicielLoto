package fr.myny.data;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class DataCsvTest {
    @Test
    void getCSV() throws IOException {
        DataCsv csv = new DataCsv();
        csv.getCsv("src/main/resources/loto_201911.zip");

        File f = new File("src/main/resources/loto_201911.csv");
        assertTrue(f.exists());
    }
}