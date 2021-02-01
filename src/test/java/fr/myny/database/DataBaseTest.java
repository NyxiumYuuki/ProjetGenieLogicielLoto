package fr.myny.database;

import org.junit.jupiter.api.Test;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {
    Connection maCo;
    public static final String FILEPATH="c:/Users/cocof/Bureau/nouveau_loto.csv";
    @Test
    void fillDataBase() throws SQLException {

        DataBase maDB=new DataBase();
        maCo=maDB.getConnection();
        assertNotNull(maCo);
        int res= maDB.fillTable(FILEPATH);
        System.out.println(res);
        Statement stmt= maDB.conn.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT Count(*) From myny.Test_Table");
        rs.next();
        long nbcolret= rs.getLong(1);
        //System.out.println("nb lignes (changer la ligne juste en dessous si on modifie le nb de lignes): "+rs.getInt("total"));
        System.out.println("nb lignes (changer la ligne juste en dessous si on modifie le nb de lignes): "+rs.getLong(1));
        assertEquals(1321,rs.getLong(1));
    }

    @Test
    void importDataBase() {
    }

    @Test
    void createTable() throws SQLException {
        DataBase maDB=new DataBase();
        maDB.createTable();
        Statement stmt= maDB.conn.createStatement();
        ResultSet rs=stmt.executeQuery("Select * From myny.Test_Table");
        ResultSetMetaData rsmd =rs.getMetaData();
        int nbCol=rsmd.getColumnCount();
        System.out.println("nb Col (changer la ligne juste en dessous si on modifie le nb de col): "+rsmd.getColumnCount());
        assertEquals(nbCol,25);
    }

    @Test
    void updateTable() {
        DataBase maDB=new DataBase();
        maCo=maDB.getConnection();
        int res = maDB.updateTable(FILEPATH);
        System.out.println(res);
        assertNotNull(maCo);

    }

    @Test
    void updateTablev2() {
        DataBase maDB=new DataBase();
        maCo=maDB.getConnection();
        int res = maDB.updateTablev2(FILEPATH);
        System.out.println(res);
        assertNotNull(maCo);

    }

    @Test
    void getConnection() {
        DataBase maDB=new DataBase();
        maCo=maDB.getConnection();
        assertNotNull(maCo);
    }
    @Test
    public void showLine() throws SQLException {
        DataBase maDB=new DataBase();
        ResultSet rs=maDB.showLine(2017001);
        rs.next();
        for (int i=1;i<26;i++) {
            System.out.print(rs.getObject(i)+", ");
        }
    }


    @Test
    public void removeMultiplesLines(){
        DataBase maDB=new DataBase();
        int res= maDB.removeMultiplesLines(2017004);
        System.out.print(res);
    }
    @Test
    public void removeLine(){
        DataBase maDB=new DataBase();
        int res= maDB.removeLine(2017014);
        System.out.print(res);
    }

    @Test
    public void dropTable(){
        DataBase maDB=new DataBase();
        maDB.dropTable();
    }
}