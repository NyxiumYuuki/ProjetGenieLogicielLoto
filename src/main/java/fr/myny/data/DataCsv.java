package fr.myny.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DataCsv {

    protected String destination;
    private ImportData imp;

    /**
     * Constructeur par defaut
     * @throws IOException
     */
    public DataCsv() throws IOException {
        this.destination = "src/main/resources/Download";
        ImportData imp = new ImportData("https://www.fdj.fr/jeux-de-tirage/loto/statistiques", this.destination);

    }

    /**
     * Constructeur avec deux parametres
     * @param s repertoire de destination
     * @throws IOException
     */
    public DataCsv(String s) throws IOException {
        this.destination = s;
        imp = new ImportData("https://www.fdj.fr/jeux-de-tirage/loto/statistiques", this.destination);

        for (String name : imp.tabNameZip) {
            name = this.destination.concat(name);
            getCsv(name);
        }
    }

    /**
     * La methode de recuperation dun fichier csv
     * @param fileZip : nom suivit du chemin du fichier zip
     */
    private void getCsv(String fileZip) throws IOException{
        File desDir = new File(destination);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null){
            File newFile = newFile(desDir, zipEntry);
            if (zipEntry.isDirectory()){
                if (!newFile.isDirectory() && !newFile.mkdirs()){
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else{
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0){
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    /**
     * Methode verifiant si l extraction a bien ete faite
     * @param destinationDir : chemin de destination
     * @param zipEntry : zip cible
     */
     public File newFile(File destinationDir, ZipEntry zipEntry) throws IOException{
         File destFile = new File(destinationDir, zipEntry.getName());

         String destDirPath = destinationDir.getCanonicalPath();
         String destFilePath = destFile.getCanonicalPath();

         if (!destFilePath.startsWith(destDirPath + File.separator)){
             throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
         }

         return destFile;
     }
}
