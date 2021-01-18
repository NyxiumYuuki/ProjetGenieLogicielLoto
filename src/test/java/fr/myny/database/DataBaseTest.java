package fr.myny.database;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
import fr.myny.database.*;

class DataBaseTest {
    Connection connexion;
    @Test
    void fillDataBase() throws SQLException {

        DataBase maDB=new DataBase();
        Connection maCo=maDB.getConnection();
        assertNotNull(maCo);
        //maDB.fillDataBase();
        Statement stmt= maDB.conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT Count(*) As total From myny.Test_Table Where 1=1");
        System.out.println("nb lignes (changer la ligne juste en dessous si on modifie le nb de col): "+rs.getInt("total"));
        assertEquals(1317,rs);
    }

    @Test
    void importDataBase() {
    }

    @Test
    void createDataBase() throws SQLException {
        DataBase maDB=new DataBase();
        maDB.createDataBase();
        Statement stmt= maDB.conn.createStatement();
        ResultSet rs=stmt.executeQuery("Select * From myny.Test_Table");
        ResultSetMetaData rsmd =rs.getMetaData();
        int nbCol=rsmd.getColumnCount();
        System.out.println("nb Col (changer la ligne juste en dessous si on modifie le nb de col): "+rsmd.getColumnCount());
        assertEquals(nbCol,25);
    }

    @Test
    void updateDataBase() {
    }

    @Test
    void getConnection() {
        DataBase maDB=new DataBase();
        Connection maCo=maDB.getConnection();
        assertNotNull(maCo);
    }
}