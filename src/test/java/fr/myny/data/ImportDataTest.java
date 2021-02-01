package fr.myny.data;

import org.junit.jupiter.api.Test;

class ImportDataTest {

    @Test
    void downloadCsvZip() {

        ImportData csv = new ImportData();

        csv.DownloadCsvZip();

    }
}