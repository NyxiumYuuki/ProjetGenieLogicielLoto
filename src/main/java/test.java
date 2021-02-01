//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;
import fr.myny.stats.Statistics;

import java.sql.*;
//import com.opencsv.CSVReader;

public class test{
    public static void main(String[] args) throws SQLException {
        System.out.println("Il n'y a rien a voir !");
        System.out.println("nico fait un test");
        System.out.println("Il Je test les branchs");


        Statistics c=new Statistics();
        //c.setTaille();
        //c.afficherChiffre1();
        //c.afficherNumBonus();
        c.afficherCombinaisons2(4);
        //c.afficherNumBonus(1);
        //c.afficherCombinaisons3(32,16);
        //c.afficherNumBonusCombi2(5,10);
        //c.afficherNumBonusCombi3(28,14,37);
    }
}