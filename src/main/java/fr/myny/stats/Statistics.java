package fr.myny.stats;

import fr.myny.database.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistics {
    public DataBase maDB;
    public Connection conn;
    public long numSelect[];
    public int bonusSelect;
    public long numId[][];
    public long numCpt[][];
    public long bonusId[][];
    public long bonusCpt[][];
    public long combi1Id[][];
    public long combi1Cpt[];
    public long combi2Id[][];
    public long combi2Cpt[];
    public long num1_bonusId[][];
    public long num1_bonusCpt[];
    public long num2_bonusId[][];
    public long num2_bonusCpt[];
    public long num3_bonusId[][];
    public long num3_bonusCpt[];


    /**
     * Constructeur par defaut : initialise toutes les variables et les tableaux
     */
    public Statistics(){
        maDB=new DataBase();
        maDB.getConnection();
        numSelect =new long[5];
        numId =new long[50][4000];
        numCpt = new long[50][2];
        combi1Id =new long[50][4000];
        combi1Cpt = new long[50];
        num1_bonusId =new long[11][4000];
        num1_bonusCpt = new long[11];
        num2_bonusId =new long[11][4000];
        num2_bonusCpt = new long[11];
        num3_bonusId =new long[11][4000];
        num3_bonusCpt = new long[11];
        combi2Id=new long[50][4000];
        combi2Cpt=new long[50];
        bonusId =new long[11][4000];
        bonusCpt=new long[11][2];
        conn= maDB.getConnection();
    }

    /**
     * Methode affichant les numeros avec leur frequence d'apparition
     * @throws SQLException
     */
    public void afficherChiffre1() throws SQLException {
        System.out.println("debut afficherChiffre1");
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
        //for (int i=0; i<50;i++) {            System.out.println(i+" :"+ numCpt[i][1]);        }

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
                //System.out.println(numId[rs.getInt(2)][k]);
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

    /**
     * Methode affichant les combinaisons a deux numeros en fonction du premier numero choisi
     * @param chiffre1 premiere combinaison
     * @throws SQLException
     */
    public void afficherCombinaisons2(int chiffre1) throws SQLException {
        afficherChiffre1();
        System.out.println("debut afficherCombinaisons2");
        numSelect[1]=chiffre1;
        int j=0, k=0, l=0, cpt=0;
        for(int i=1;i<50;i++) {
            if (i != chiffre1) {
                while (numId[chiffre1][j] != 0) {
                    while (numId[i][k] != 0) {
                        //System.out.println(numId[i][k] +"=="+ numId[chiffre1][j]);
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

    /**
     * Methode affichant les combinaisons a trois numeros en fonction des deux numeros choisi
     * @param chiffre1
     * @param chiffre2
     * @throws SQLException
     */
    public void afficherCombinaisons3(int chiffre1, int chiffre2) throws SQLException {
        afficherChiffre1();
        afficherCombinaisons2(chiffre1);
        System.out.println("debut afficherCombinaisons3");
        numSelect[2]=chiffre2;
        int j=0, k=0, m=0, cpt=0;
        for(int i=1;i<50;i++) {
            if (i != chiffre1 && i!=chiffre2) {
                while (combi1Id[chiffre2][j] != 0){
                    while (numId[i][k] != 0) {
                        //System.out.println(numId[i][k] +"=="+ combi1Id[chiffre2][j]);
                        if (numId[i][k] == combi1Id[chiffre2][j]) {
                            combi2Cpt[i]++;
                            combi2Id[i][cpt++]=numId[i][k];
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
            System.out.println(i+": "+combi2Cpt[i]);
            while(combi2Id[i][k]!=0){
                System.out.print(combi2Id[i][k]+", ");
                k++;
            }
            System.out.println();
            k=0;
        }

    }

    /**
     * @throws SQLException
     */
    public void afficherBonus() throws SQLException {
        System.out.println("debut afficherBonus");
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
            while(bonusId[rs.getInt(2)][k]!=0){
                k++;
            }
            bonusId[rs.getInt(2)][k]=rs.getInt(1);
            System.out.println(bonusId[rs.getInt(2)][k]);
            numprec=rs.getInt(2);
        }
        k=0;
        //j=0;
        for (int i=1; i<11;i++) {
            System.out.println(i+":"+bonusCpt[i][1]);
            while(bonusId[i][l]!=0){
                System.out.print(bonusId[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }
    }

    /**
     * Methode affichant les numeros bonus avec leur frequence d'apparition
     * @param chiffre1
     * @throws SQLException
     */
    public void afficherNumBonus(int chiffre1) throws SQLException {
        afficherChiffre1();
        this.afficherBonus();
        System.out.println("debut afficherNumBonus");
        numSelect[1]=chiffre1;
        int j=0, k=0, l=0, cpt=0;
        for(int i=1;i<11;i++) {
            while (numId[chiffre1][j] != 0) {
                while (bonusId[i][k] != 0) {
                    //System.out.println(bonusId[i][k] +"=="+ numId[chiffre1][j]);
                    if (bonusId[i][k] == numId[chiffre1][j]) {
                        num1_bonusCpt[i]++;
                        num1_bonusId[i][cpt++]=bonusId[i][k];
                        j++;
                    }
                    k++;
                }
                k = 0;
                j++;
            }
            j = 0;
            cpt=0;
        }
        for (int i=1; i<11;i++) {
            /*for (int p=1; p<50;p++) {
                System.out.print(combi1Id[i][p]+", ");
            }*/
            System.out.println(i+": "+num1_bonusCpt[i]);
            while(num1_bonusId[i][l]!=0){
                System.out.print(num1_bonusId[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }

    }

    /**
     * Methode affichant les numeros bonus en fonction des combinaisons à deux numeros
     * @param chiffre1
     * @param chiffre2
     * @throws SQLException
     */
    public void afficherNumBonusCombi2(int chiffre1, int chiffre2) throws SQLException {
        afficherCombinaisons2(chiffre1);
        afficherBonus();
        System.out.println("debut afficherNumBonusCombi2");
        numSelect[1]=chiffre1;
        numSelect[2]=chiffre2;
        int j=0, k=0, l=0, cpt=0;
        for(int i=1;i<11;i++) {
            while (combi1Id[chiffre2][j] != 0) {
                while (bonusId[i][k] != 0) {
                    //System.out.println(bonusId[i][k] +"=="+ combi1Id[chiffre2][j]);
                    if (bonusId[i][k] == combi1Id[chiffre2][j]) {
                        num2_bonusCpt[i]++;
                        num2_bonusId[i][cpt++]=bonusId[i][k];
                        j++;
                    }
                    k++;
                }
                k = 0;
                j++;
            }
            j = 0;
            cpt=0;
        }
        for (int i=1; i<11;i++) {
            /*for (int p=1; p<50;p++) {
                System.out.print(combi1Id[i][p]+", ");
            }*/
            System.out.println(i+": "+num2_bonusCpt[i]);
            while(num2_bonusId[i][l]!=0){
                System.out.print(num2_bonusId[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }

    }

    /**
     * Methode affichant les numeros bonus en fonction des combinaisons à trois numeros
     * @param chiffre1
     * @param chiffre2
     * @param chiffre3
     * @throws SQLException
     */
    public void afficherNumBonusCombi3(int chiffre1, int chiffre2, int chiffre3) throws SQLException {
        afficherCombinaisons3(chiffre1, chiffre2);
        afficherBonus();
        System.out.println("debut afficherNumBonusCombi3");
        numSelect[1]=chiffre1;
        numSelect[2]=chiffre2;
        numSelect[3]=chiffre3;
        int j=0, k=0, l=0, cpt=0;
        while (combi2Id[chiffre3][j] != 0) {
            for(int i=1;i<11;i++) {
                while (bonusId[i][k] != 0) {
                    //System.out.println(combi2Id[chiffre3][j] +"=="+  bonusId[i][k]);
                    if (bonusId[i][k] == combi2Id[chiffre3][j]) {
                        num3_bonusCpt[i]++;
                        num3_bonusId[i][cpt++]=bonusId[i][k];
                        j++;
                    }
                    k++;
                }
                k = 0;
            }
            cpt=0;
            j++;
        }
        for (int i=1; i<11;i++) {
            /*for (int p=1; p<50;p++) {
                System.out.print(combi1Id[i][p]+", ");
            }*/
            System.out.println(i+": "+num3_bonusCpt[i]);
            while(num3_bonusId[i][l]!=0){
                System.out.print(num3_bonusId[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }

    }
}
