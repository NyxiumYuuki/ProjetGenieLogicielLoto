//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;
import fr.myny.database.DataBase;
import fr.myny.stats.Choix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import java.io.*;
//import com.opencsv.CSVReader;

public class test{
    public static void main(String[] args) throws SQLException {
        System.out.println("Il n'y a rien a voir !");
        System.out.println("nico fait un test");
        System.out.println("Il Je test les branchs");


        Choix c=new Choix();
        c.afficherChiffre1();
        //c.afficherNumBonus();
        c.afficherCombinaisons2(4);
    }
}