package fr.myny.data;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class ImportData {

    protected String url;

    /**
     * Le constructeur de ImportData
     */
    ImportData(){
        this.url = "https://www.fdj.fr/jeux-de-tirage/loto/statistiques";
    }

    /**
     * Le constructeur de ImportData
     * @param url string contenant l URL dou recuperer les fichiers csv
     */
    public ImportData(String url){
        this.url=url;
    }

    private boolean UrlExist() {
        try {
            URL site = new URL(url);
            try {
                site.openStream();
                return true;
            } catch (IOException e) {
                return false;
            }
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private boolean ZipNotExist(String filename) {
        File directory = new File("src/main/java/resources/");
        File[] files = directory.listFiles();
        for (File f : files) {
            if (f.getName().equals(filename))
                return false;
        }
        return true;
    }

    public void DownloadCsvZip() {
        if (UrlExist()) {
            BufferedReader in = null;

            try {
                URL site = new URL(url);
                in = new BufferedReader(new InputStreamReader(site.openStream()));

                String test = "loto_";
                String nameZip;
                String urlZipLink;
                while ((urlZipLink = in.readLine()) != null) {
                    if (urlZipLink.length() > 128) {
                        if (test.equals(urlZipLink.substring(50, 55))) {
                            nameZip = urlZipLink.substring(50,65);
                            urlZipLink = urlZipLink.substring(13,65);
                            if (ZipNotExist(nameZip)) {
                                try (BufferedInputStream bis = new BufferedInputStream(new URL(urlZipLink).openStream());
                                     FileOutputStream fos = new FileOutputStream("src/main/java/resources/" + nameZip)) {
                                    byte data[] = new byte[1024];
                                    int byteContent;
                                    while ((byteContent = bis.read(data, 0, 1024)) != -1) {
                                        fos.write(data, 0, byteContent);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace(System.out);
                                }
                            }

                        }
                    }
                }
                in.close();
            } catch (IOException e) {
                System.out.println("Error URL can't open : " + e);
            }
            finally {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Error close buffer : " + e);
                }
            }
        }
        else
            System.out.println("Web sit don't exist");
    }

}
