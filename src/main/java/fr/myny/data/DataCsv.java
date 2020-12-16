package fr.myny.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DataCsv {
    /**
     * Le constructeur de DataCsv
     */
    public DataCsv(){

    }

    /**
     * La methode de recuperation dun fichier csv
     */
    public void getCsv() throws IOException{
        String fileZip = "src/main/java/resources/loto_201911.zip";
        File desDir = new File("src/main/java/resources/");
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
     * Methode d'extraction de fichier zip
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
