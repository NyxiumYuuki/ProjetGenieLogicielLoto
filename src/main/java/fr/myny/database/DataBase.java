package fr.myny.database;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.opencsv.CSVReader;
/**
 * La classe DataBase qui soccupera de la base de donnees
 */

public class DataBase {
    //public static String url="jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*";

    public static String url="jdbc:mariadb://phpmyadmin.vachot.fr:3306?db=myny&user=mynynicolas&password=Bw0po64*";
    Connection conn;
    /**
     * Le constructeur de DataBase
     */
    public DataBase(){
    }


    /**
     * La methode de remplissage de la base de donnees
     */
    public void fillDataBase(){
        /*String sql="INSERT INTO 'myny.Test_Table'" +
                " ('annee_numero_de_tirage', "+
                " 'jour_de_tirage', "+
                " 'date_de_tirage',"+
                " 'date_de_forclusion',"+
                " 'boule_1' ,"+
                " 'boule_2' ,"+
                " 'boule_3' ,"+
                " 'boule_4' ,"+
                " 'boule_5' ,"+
                " 'numero_chance' ,"+
                " 'combinaison_gagnante_en_ordre_croissant' ,"+
                " 'nombre_de_gagnant_au_rang1' ,"+
                " 'rapport_du_rang1' ,"+
                " 'nombre_de_gagnant_au_rang2' ,"+
                " 'rapport_du_rang2' ,"+
                " 'nombre_de_gagnant_au_rang3' ,"+
                " 'rapport_du_rang3' ,"+
                " 'nombre_de_gagnant_au_rang4' ,"+
                " 'rapport_du_rang4' ,"+
                " 'nombre_de_gagnant_au_rang5' ,"+
                " 'rapport_du_rang5' ,"+
                " 'nombre_de_gagnant_au_rang6' ,"+
                " 'rapport_du_rang6' ,"+
                " 'numero_jokerplus' ,"+
                " 'devise' ) VALUES";*/
        String sql="INSERT INTO myny.Test_Table" +
                " (annee_numero_de_tirage, "+
                " jour_de_tirage, "+
                " date_de_tirage,"+
                " date_de_forclusion,"+
                " boule_1 ,"+
                " boule_2 ,"+
                " boule_3 ,"+
                " boule_4 ,"+
                " boule_5 ,"+
                " numero_chance ,"+
                " combinaison_gagnante_en_ordre_croissant ,"+
                " nombre_de_gagnant_au_rang1 ,"+
                " rapport_du_rang1 ,"+
                " nombre_de_gagnant_au_rang2 ,"+
                " rapport_du_rang2 ,"+
                " nombre_de_gagnant_au_rang3 ,"+
                " rapport_du_rang3 ,"+
                " nombre_de_gagnant_au_rang4 ,"+
                " rapport_du_rang4 ,"+
                " nombre_de_gagnant_au_rang5 ,"+
                " rapport_du_rang5 ,"+
                " nombre_de_gagnant_au_rang6 ,"+
                " rapport_du_rang6 ,"+
                " numero_jokerplus ,"+
                " devise ) VALUES \n";
        try {
            Scanner sc = new Scanner(new File("c:/Users/cocof/Bureau/nouveau_loto.csv"));
            sc.useDelimiter(";|\\n");   //sets the delimiter pattern
            int i=0,j;
            String line=sc.nextLine();
            line="";
            String date, jour, mois, an;
            while (sc.hasNext()){
                j=(i%25)+1;
                if(j==1) {
                    line=line+'(';
                    line=line+sc.next().replace(",",".");
                    line=line+';';
                }
                if(j<25 && j>1) {
                    if (j==2 ||j==11){
                        line=line+"\'"+sc.next().replace(",",".")+"\',";
                    }
                    else if (j==3 ||j==4){
                        date=sc.next().replace(",",".");
                        jour=date.substring(0,2);
                        mois=date.substring(3,5);
                        an=date.substring(6,10);
                        line=line+"\'"+an+"-"+mois+"-"+jour+"\',";
                    }
                    else{
                        line=line+sc.next().replace(",",".");
                        line=line+';';
                    }
                }
                if(j==25) {
                    line=line+"\'"+sc.next().replace(",",".")+"\'";
                    line=line+')';
                    line=line.replaceAll("\\s","");
                    line = line.replaceAll(";", ",");
                    if(sc.hasNext()) {
                        line = line+ ",";
                    }
                    else{
                        line = line+ ";";
                    }
                    //System.out.println(line);
                    sql=sql+line;
                    line="";
                }
                i++;
                //System.out.println(sc.nextLine());
                //System.out.print(sc.next());  //find and returns the next complete token from this scanner
            }

            System.out.println(sql);
            //System.out.println(sql);
            /*CSVReader reader = new CSVReader(new FileReader("c:/Users/cocof/Bureau/nouveau_loto.csv"));
            String[] nextLine=reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                for (int j = 0; j < nextLine.length - 1; j++) {
                    if (j==0){
                        sql=sql+"(";
                    }
                    sql=sql+nextLine[j];
                    if (j==nextLine.length-1){
                        sql=sql+"),";
                    }
                    else{
                        sql=sql+",";
                    }
                    //System.out.println(nextLine[j]);
                }
                sql=sql+";";
            }
            System.out.println(sql);

            conn = DriverManager.getConnection(url);
            if(conn!=null) {
                Statement stmt = conn.createStatement();
                stmt.executeQuery(sql);
                conn.commit();
                System.out.println("statement cree");
            }*/
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement stmt = conn.createStatement();
                stmt.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("oskour");
            System.out.println(e.getMessage());
        }
    }

    /**
     * La methode dimport de donnees depuis la base de donnees
     */
    public void importDataBase(){

    }

    /**
     * La methode de creation de la base de donnees
     */
    public void createDataBase() {
        String sql="CREATE TABLE IF NOT EXISTS myny.Test_Table ("+
            " annee_numero_de_tirage INTEGER,"+                     //1
            " jour_de_tirage VARCHAR(10),"+                         //2 VARCHAR
            " date_de_tirage DATE,"+                                //3 DATE
            " date_de_forclusion DATE,"+                            //4 DATE
            " boule_1 TINYINT,"+                                    //5
            " boule_2 TINYINT,"+                                    //6
            " boule_3 TINYINT,"+                                    //7
            " boule_4 TINYINT,"+                                    //8
            " boule_5 TINYINT,"+                                    //9
            " numero_chance TINYINT,"+                              //10
            " combinaison_gagnante_en_ordre_croissant varchar(20),"+//11 VARCHAR
            " nombre_de_gagnant_au_rang1 INTEGER,"+                 //12
            " rapport_du_rang1 INTEGER,"+                           //13
            " nombre_de_gagnant_au_rang2 INTEGER,"+                 //14
            " rapport_du_rang2 INTEGER,"+                           //15
            " nombre_de_gagnant_au_rang3 INTEGER,"+                 //16
            " rapport_du_rang3 INTEGER,"+                           //17
            " nombre_de_gagnant_au_rang4 INTEGER,"+                 //18
            " rapport_du_rang4 INTEGER,"+                           //19
            " nombre_de_gagnant_au_rang5 INTEGER,"+                 //20
            " rapport_du_rang5 INTEGER,"+                           //21
            " nombre_de_gagnant_au_rang6 INTEGER,"+                 //22
            " rapport_du_rang6 INTEGER,"+                           //23
            " numero_jokerplus INTEGER,"+                           //24
            " devise VARCHAR(10));";                                //25 VARCHAR
            //System.out.println(sql);
        try {
            conn = DriverManager.getConnection(url);
            if(conn!=null) {
                Statement stmt = conn.createStatement();
                stmt.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("oskour");
            System.out.println(e.getMessage());
        }
        /*try(Statement mySt = conn.createStatement()){
            mySt.execute(//"CREATE SCHEMA IF NOT EXISTS Connection;"+
                    "CREATE TABLE IF NOT EXISTS myny.Test_Table ("+
                    "annee_numero_de_tirage INTEGER,"+
                    "jour_de_tirage CHAR(10),"+
                    "date_de_tirage DATE,"+
                    "date_de_forclusion DATE,"+
                    "boule_1 TINYINT,"+
                    "boule_2 TINYINT,"+
                    "boule_3 TINYINT,"+
                    "boule_4 TINYINT,"+
                    "boule_5 TINYINT,"+
                    "numero_chance TINYINT,"+
                    "combinaison_gagnante_en_ordre_croissantchar(20),"+
                    "nombre_de_gagnant_au_rang1 INTEGER,"+
                    "rapport_du_rang1 INTEGER,"+
                    "nombre_de_gagnant_au_rang2 INTEGER,"+
                    "rapport_du_rang2 INTEGER,"+
                    "nombre_de_gagnant_au_rang3 INTEGER,"+
                    "rapport_du_rang3 INTEGER,"+
                    "nombre_de_gagnant_au_rang4 INTEGER,"+
                    "rapport_du_rang4 INTEGER,"+
                    "nombre_de_gagnant_au_rang5 INTEGER,"+
                    "rapport_du_rang5 INTEGER,"+
                    "nombre_de_gagnant_au_rang6 INTEGER,"+
                    "rapport_du_rang6 INTEGER,"+
                    "numero_jokerplus INTEGER,"+
                    "deviseCHAR(10));");
            conn.commit();
            //mySt.close();
            //conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {
        }*/
        /*
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
    
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //    try {
    //        Class.forName("com.mysql.jdbc.Driver");
    //    }
    //    catch (ClassNotFoundException e) {
    //    }
    //
    //    try {
    //        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306");
    //    } catch (SQLException e) {
    //        System.out.println("oskour " +e.getStackTrace());
    //    }
    //
    //    Statement statement = null;
    //    ResultSet resultat = null;
    //    try {
    //        statement = conn.createStatement();
    //
    //        // Exécution de la requête
    //        resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");
    //
    //        // Récupération des données
    //        while (resultat.next()) {
    //            String nom = resultat.getString("nom");
    //            String prenom = resultat.getString("prenom");
    //        }
    //    }
    //    catch (SQLException e) {
    //        System.out.println(e);
    //    }
    }


    /**
     * La methode de mise a jour de la base de donnees
     */
    public void updateDataBase() throws FileNotFoundException {
 /*       String sql="INSERT INTO myny.Test_Table" +
                " (annee_numero_de_tirage, "+
                " jour_de_tirage, "+
                " date_de_tirage,"+
                " date_de_forclusion,"+
                " boule_1 ,"+
                " boule_2 ,"+
                " boule_3 ,"+
                " boule_4 ,"+
                " boule_5 ,"+
                " numero_chance ,"+
                " combinaison_gagnante_en_ordre_croissant ,"+
                " nombre_de_gagnant_au_rang1 ,"+
                " rapport_du_rang1 ,"+
                " nombre_de_gagnant_au_rang2 ,"+
                " rapport_du_rang2 ,"+
                " nombre_de_gagnant_au_rang3 ,"+
                " rapport_du_rang3 ,"+
                " nombre_de_gagnant_au_rang4 ,"+
                " rapport_du_rang4 ,"+
                " nombre_de_gagnant_au_rang5 ,"+
                " rapport_du_rang5 ,"+
                " nombre_de_gagnant_au_rang6 ,"+
                " rapport_du_rang6 ,"+
                " numero_jokerplus ,"+
                " devise ) VALUES \n";



        try {
            Scanner sc = new Scanner(new File("c:/Users/cocof/Bureau/nouveau_loto.csv"));
            sc.useDelimiter(";|\\n");   //sets the delimiter pattern
            int i=0,j;
            String line=sc.nextLine();
            line="";
            String date, jour, mois, an;
            String annee_numero_de_tirage=sc.next();
            while (sc.hasNext()&&annee_numero_de_tirage!=){
                j=(i%25)+1;
                if(j==1) {
                    line=line+'(';
                    line=line+sc.next().replace(",",".");
                    line=line+';';
                }
                if(j<25 && j>1) {
                    if (j==2 ||j==11){
                        line=line+"\'"+sc.next().replace(",",".")+"\',";
                    }
                    else if (j==3 ||j==4){
                        date=sc.next().replace(",",".");
                        jour=date.substring(0,2);
                        mois=date.substring(3,5);
                        an=date.substring(6,10);
                        line=line+"\'"+an+"-"+mois+"-"+jour+"\',";
                    }
                    else{
                        line=line+sc.next().replace(",",".");
                        line=line+';';
                    }
                }
                if(j==25) {
                    line=line+"\'"+sc.next().replace(",",".")+"\'";
                    line=line+')';
                    line=line.replaceAll("\\s","");
                    line = line.replaceAll(";", ",");
                    if(sc.hasNext()) {
                        line = line+ ",";
                    }
                    else{
                        line = line+ ";";
                    }
                    //System.out.println(line);
                    sql=sql+line;
                    line="";
                }
                i++;
                //System.out.println(sc.nextLine());
                //System.out.print(sc.next());  //find and returns the next complete token from this scanner
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        */
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Scanner sc = new Scanner(new File("c:/Users/cocof/Bureau/nouveau_loto.csv"));
                sc.useDelimiter(";|\\n");   //sets the delimiter pattern
                String line=sc.nextLine();
                line="";
                long annee_numero_de_tirage=Long.parseLong(sc.next());
                //sc.nextLine();
                //annee_numero_de_tirage=Long.parseLong(sc.next());
                System.out.println("numero en haut du fichier csv :"+annee_numero_de_tirage);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MAX(annee_numero_de_tirage) FROM myny.Test_Table;");
                rs.next();
                //conn.close();
                System.out.println("numero max de la db :"+rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println("oskour update");
            System.out.println(e.getMessage());
        }
    }





    /**
     * La methode de connection
     */
    public Connection getConnection() {
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //conn = DriverManager.getConnection("jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb://phpmyadmin.vachot.fr:3306/myny?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb:../../../../../../test.db");
            if(conn!=null) {
                System.out.println("Connected to database");
                System.out.println(conn);
            }
        }catch(SQLException e) {
            System.out.println("on a des pb:" + e.getMessage());
        }
        return conn;
    }
}
