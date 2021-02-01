package fr.myny.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportDataTest {

    @Test
    void downloadCsvZip() {

        ImportData csv = new ImportData();

        csv.DownloadCsvZip();

    }
}