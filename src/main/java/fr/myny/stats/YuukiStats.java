package fr.myny.stats;

import fr.myny.database.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;

public class YuukiStats {
    private DataBase dataBase;
    private Connection connection;

    private TreeMap<String,String> treeMap;

    public YuukiStats() throws SQLException {
        setDataBase(new DataBase());
        setConnection(getDataBase().getConnection());
        statsNumero();
    }

    public DataBase getDataBase() { return dataBase; }
    public Connection getConnection() { return connection; }
    public TreeMap<String, String> getTreeMap() { return treeMap; }

    public void setConnection(Connection connection) { this.connection = connection; }
    public void setDataBase(DataBase dataBase) { this.dataBase = dataBase; }
    public void setTreeMap(TreeMap<String, String> treeMap) { this.treeMap = treeMap; }

    public void statsNumero() throws SQLException {
        setTreeMap(new TreeMap<String,String>());
    }

    public void statsCombinaison2(int chiffre1) throws SQLException {
        setTreeMap(new TreeMap<String,String>());
    }

    public void statsCombinaison3(int chiffre1, int chiffre2) throws SQLException {
        setTreeMap(new TreeMap<String,String>());
    }

    public void statsNumeroBonus() throws SQLException {
        setTreeMap(new TreeMap<String,String>());
    }

}
