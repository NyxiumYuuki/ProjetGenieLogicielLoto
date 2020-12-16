package fr.myny.database;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import com.mysql.jdbc.Driver;

import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;


class DataBaseTest {
    Connection connexion;
    @Test
    void fillDataBase() {
    }

    @Test
    void importDataBase() {
    }

    @Test
    void createDataBase() {
            /*String url = "jdbc:sqlite:C:/Users/W7/Desktop/" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        //Class.forName("com.mysql.jdbc.Driver");
        /*try(Connection myCo = DriverManager.getConnection("jdbc:raima:rdm://C:/Users/W7/IdeaProjects/ProjetGenieLogicielLoto/src/"+fileName)){
            try(Statement mySt = myCo.createStatement()){
                mySt.execute("Create DataBase Test_DB");
                mySt.execute("Create Table Test_Table(" +
                        "annee_numero_de_tirage(integer)," +
                        "jour_de_tirage(char(10))," +
                        "date_de_tirage(Date)," +
                        "date_de_forclusion(Date)," +
                        "boule_1(tinyint)," +
                        "boule_2(tinyint)," +
                        "boule_3(tinyint)," +
                        "boule_4(tinyint)," +
                        "boule_5(tinyint)," +
                        "numero_chance(tinyint)," +
                        "combinaison_gagnante_en_ordre_croissant(char(20))," +
                        "nombre_de_gagnant_au_rang1(integer)," +
                        "rapport_du_rang1(integer)," +
                        "nombre_de_gagnant_au_rang2(integer)," +
                        "rapport_du_rang2(integer)," +
                        "nombre_de_gagnant_au_rang3(integer)," +
                        "rapport_du_rang3(integer)," +
                        "nombre_de_gagnant_au_rang4(integer)," +
                        "rapport_du_rang4(integer)," +
                        "nombre_de_gagnant_au_rang5(integer)," +
                        "rapport_du_rang5(integer)," +
                        "nombre_de_gagnant_au_rang6(integer)," +
                        "rapport_du_rang6(integer)," +
                        "numero_jokerplus(integer),"+
                        "devise(char(3)),"+
                        ")");
                myCo.commit();
                mySt.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
            finally {

            }
            myCo.close();
         */
        /*}
        catch (SQLException sqle) {
            System.out.println(sqle);
        }*/
        /*try {
            String line;
            Process p = Runtime.getRuntime().exec("C:/Users/W7/Desktop/database.sql");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }*/
        /*loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306");
        } catch (SQLException e) {
            System.out.println("oskour " +e.getStackTrace());
        }

        Statement statement = null;
        ResultSet resultat = null;
        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }*/
    }

    @Test
    void updateDataBase() {
    }

    @Test
    void getConnection() throws SQLException {
        DataBase maDB=new DataBase();
        Connection maCo=maDB.getConnection();
        createDataBase();
        assertNotNull(maCo);
        assertTrue(maDB.test);
    }
}