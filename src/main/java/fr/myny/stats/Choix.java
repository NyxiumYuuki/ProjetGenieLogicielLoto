package fr.myny.stats;

import fr.myny.database.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Choix {
    DataBase maDB;
    Connection conn;
    long numselect[];
    long numId[][];
    long numCpt[][];
    long bonusid[][];
    long bonusCpt[][];
    long combi1Id[][];
    long combi1Cpt[];
    public Choix(){
        maDB=new DataBase();
        maDB.getConnection();
        numselect =new long[5];
        numId =new long[50][4000];
        numCpt = new long[50][2];
        combi1Id =new long[50][4000];
        combi1Cpt = new long[50];
        bonusid=new long[11][4000];
        bonusCpt=new long[11][2];
        conn= maDB.getConnection();
    }
    public void afficherChiffre1() throws SQLException {
        int k=0, numprec=0, l=0;
        ResultSet rs=null;
        String sql;
        int j = 0;
        for (int i=1; i<6;i++) {
            sql = "SELECT  Test_Table.boule_" + i + ",Count(*) as 'cnt' FROM myny.Test_Table GROUP BY Test_Table.boule_" + i + "; ";
            try {
                    conn = maDB.getConnection();
                if (conn != null) {
                    Statement ps = conn.createStatement();
                    rs = ps.executeQuery(sql);
                    conn.commit();
                    System.out.println("statement cree");
                }
            } catch (SQLException e) {
                System.out.println("chiffre1 probleme");
                System.out.println(e.getMessage());
            }

            //rs.next();
            while (rs.next()) {
                if (i == 1) {
                    numCpt[rs.getInt(1)][0] = rs.getInt(1);
                    numCpt[rs.getInt(1)][1] = rs.getLong(2);
                } else {
                    numCpt[rs.getInt(1)][1] += rs.getLong(2);
                }
                j++;
            }
            j=0;
        }
        for (int i=0; i<50;i++) {
            System.out.println(i+" :"+ numCpt[i][1]);
        }

        for (int i=1; i<=5;i++) {
            sql = "SELECT Test_Table.annee_numero_de_tirage, Test_Table.boule_" + i + " FROM myny.Test_Table ORDER BY Test_Table.boule_" + i + "; ";
            try {
                conn = maDB.getConnection();
                if (conn != null) {
                    Statement ps = conn.createStatement();
                    rs = ps.executeQuery(sql);
                    conn.commit();
                    System.out.println("statement cree");
                }
            } catch (SQLException e) {
                System.out.println("chiffre1 probleme");
                System.out.println(e.getMessage());
            }


            //rs.next();
            while (rs.next()) {
                //System.out.println(numprec+", "+rs.getInt(2));
                if(numprec!=rs.getInt(2)) {k = 0;}
                while(numId[rs.getInt(2)][k]!=0){
                    k++;
                }
                numId[rs.getInt(2)][k]=rs.getInt(1);
                System.out.println(numId[rs.getInt(2)][k]);
                numprec=rs.getInt(2);
            }
            k=0;
            //j=0;
        }
        for (int i=1; i<50;i++) {
            System.out.println(i+":");
            while(numId[i][l]!=0){
                System.out.print(numId[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }
    }

    public void afficherCombinaisons2(int chiffre1){
        int j=0, k=0, l=0, cpt=0;
        for(int i=1;i<50;i++) {
            if (i != chiffre1) {
                while (numId[chiffre1][j] != 0) {
                    while (numId[i][k] != 0) {
                        System.out.println(numId[i][k] +"=="+ numId[chiffre1][j]);
                        if (numId[i][k] == numId[chiffre1][j]) {
                            combi1Cpt[i]++;
                            combi1Id[i][cpt++]=numId[i][k];
                        }
                        k++;
                    }
                    k = 0;
                    j++;
                }
                j = 0;
            }
            cpt=0;
        }
        for (int i=1; i<50;i++) {
            /*for (int p=1; p<50;p++) {
                System.out.print(combi1Id[i][p]+", ");
            }*/
            System.out.println(i+": "+combi1Cpt[i]);
            while(combi1Id[i][l]!=0){
                System.out.print(combi1Id[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }

    }
    public void afficherNumBonus() throws SQLException {
        int k=0, numprec=0, l=0;
        ResultSet rs=null;
        String sql;
        sql = "SELECT  Test_Table.numero_chance,Count(*) as 'cnt' FROM myny.Test_Table GROUP BY Test_Table.numero_chance; ";
        try {
            conn = maDB.getConnection();
            if (conn != null) {
                Statement ps = conn.createStatement();
                rs = ps.executeQuery(sql);
                conn.commit();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("chiffre1 probleme");
            System.out.println(e.getMessage());
        }

        //rs.next();
        while (rs.next()) {
            bonusCpt[rs.getInt(1)][0] = rs.getInt(1);
            bonusCpt[rs.getInt(1)][1] = rs.getLong(2);
        }
        for (int i=0; i<11;i++) {
            System.out.println(i+" :"+ bonusCpt[i][1]);
        }
        sql = "SELECT Test_Table.annee_numero_de_tirage, Test_Table.numero_chance FROM myny.Test_Table ORDER BY Test_Table.numero_chance; ";
        try {
            conn = maDB.getConnection();
            if (conn != null) {
                Statement ps = conn.createStatement();
                rs = ps.executeQuery(sql);
                conn.commit();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("chiffre1 probleme");
            System.out.println(e.getMessage());
        }


        //rs.next();
        while (rs.next()) {
            //System.out.println(numprec+", "+rs.getInt(2));
            if(numprec!=rs.getInt(2)) {k = 0;}
            while(bonusid[rs.getInt(2)][k]!=0){
                k++;
            }
            bonusid[rs.getInt(2)][k]=rs.getInt(1);
            System.out.println(bonusid[rs.getInt(2)][k]);
            numprec=rs.getInt(2);
        }
        k=0;
        //j=0;
        for (int i=1; i<11;i++) {
            System.out.println(i+":");
            while(bonusid[i][l]!=0){
                System.out.print(bonusid[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }
    }
}
