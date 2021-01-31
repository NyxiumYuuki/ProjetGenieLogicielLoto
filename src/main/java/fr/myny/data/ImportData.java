package fr.myny.data;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class ImportData {

    protected String url;
    protected String repDes;
    public ArrayList<String> tabNameZip;

    /**
     * Le constructeur de ImportData
     * Initialise l'URL par défaut ainsi que le dossier de destination de téléchargement
     */
    ImportData(){
        this.url = "https://www.fdj.fr/jeux-de-tirage/loto/statistiques";
        this.repDes = "src/main/resources/Download/";
        tabNameZip = new ArrayList<>();
        DownloadCsvZip();
    }

    /**
     * Le constructeur de ImportData
     * @param url string contenant l URL dou recuperer les fichiers csv
     */
    public ImportData(String url, String des){
        this.url=url;
        this.repDes = des;
        tabNameZip = new ArrayList<>();
        DownloadCsvZip();
    }

    /**
     * Méthode permettant de tester si l'URL est valide
     * @return true si l'URL est valide si non false
     */
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

    /**
     * Méthode permettant de télécharger un fichier à partir d'un URL
     */
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
                            tabNameZip.add(nameZip);
                            urlZipLink = urlZipLink.substring(13,65);
                            try (BufferedInputStream bis = new BufferedInputStream(new URL(urlZipLink).openStream());
                                 FileOutputStream fos = new FileOutputStream(repDes + nameZip)) {
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
