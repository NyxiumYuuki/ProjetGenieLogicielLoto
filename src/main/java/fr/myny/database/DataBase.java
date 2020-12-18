package fr.myny.database;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import com.mysql.jdbc.Driver;
import org.mariadb.jdbc.internal.com.read.dao.Results;

import javax.sql.DataSource;

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
        /*try{getConnection();}
        catch(SQLException sqle){
            System.out.print(sqle);
        }*/
    }

    /**
     * La methode de remplissage de la base de donnees
     */
    public void fillDataBase(){

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
            " annee_numero_de_tirage INTEGER,"+
            " jour_de_tirage VARCHAR(10),"+
            " date_de_tirage DATE,"+
            " date_de_forclusion DATE,"+
            " boule_1 TINYINT,"+
            " boule_2 TINYINT,"+
            " boule_3 TINYINT,"+
            " boule_4 TINYINT,"+
            " boule_5 TINYINT,"+
            " numero_chance TINYINT,"+
            " combinaison_gagnante_en_ordre_croissant varchar(20),"+
            " nombre_de_gagnant_au_rang1 INTEGER,"+
            " rapport_du_rang1 INTEGER,"+
            " nombre_de_gagnant_au_rang2 INTEGER,"+
            " rapport_du_rang2 INTEGER,"+
            " nombre_de_gagnant_au_rang3 INTEGER,"+
            " rapport_du_rang3 INTEGER,"+
            " nombre_de_gagnant_au_rang4 INTEGER,"+
            " rapport_du_rang4 INTEGER,"+
            " nombre_de_gagnant_au_rang5 INTEGER,"+
            " rapport_du_rang5 INTEGER,"+
            " nombre_de_gagnant_au_rang6 INTEGER,"+
            " rapport_du_rang6 INTEGER,"+
            " numero_jokerplus INTEGER,"+
            " devise VARCHAR(10));";
            //System.out.println(sql);
        try {
            conn = DriverManager.getConnection(url);
            if(conn!=null) {
                Statement stmt = conn.createStatement();
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
    public void updateDataBase(){

    }
    public Connection getConnection() {
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //conn = DriverManager.getConnection("jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb://phpmyadmin.vachot.fr:3306/myny?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb:../../../../../../test.db");
            System.out.println("Connected to database");
        }catch(SQLException e) {
            System.out.println("on a des pb:" + e.getMessage());
        }
        return conn;
        //
    }
}
