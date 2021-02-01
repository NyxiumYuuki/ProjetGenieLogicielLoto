package fr.myny.stats;

import fr.myny.database.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La classe Statistics qui sert a donner le nombre d'appartition de chaque chiffre/combinaison (et leurs ID si besoin)
 */


public class Statistics {
    public DataBase maDB;
    public Connection conn;
    public long numSelect[];
    public int bonusSelect;
    public long numId[][];          //liste des identifiants des lignes contenant un chiffre specifique
    public long numCpt[][];         //total d'apparitions de chaque chiffre
    public long bonusId[][];        //liste des identifiants des lignes contenant un chiffre bonus specifique
    public long bonusCpt[][];       //total d'apparitions de chaque bonus
    public long combi1Id[][];       //liste des identifiants des lignes cumulant 2 chiffres specifiques
    public long combi1Cpt[];        //total d'apparitions de chaque chiffre qui correspond egalement a un premier chiffre sur la meme ligne
    public long combi2Id[][];       //liste des identifiants des lignes cumulant 3 chiffres specifiques
    public long combi2Cpt[];        //total d'apparitions de chaque chiffre qui correspond egalement aux 2 premiers chiffres sur la meme ligne
    public long num1_bonusId[][];   //liste des identifiants des lignes cumulant 1 chiffre specifique et un numero bonus specifique
    public long num1_bonusCpt[];    //total d'apparitions de chaque bonus qui correspond egalement au premier chiffre sur la meme ligne
    public long num2_bonusId[][];   //liste des identifiants des lignes cumulant 2 chiffres specifiques et un numero bonus specifique
    public long num2_bonusCpt[];    //total d'apparitions de chaque bonus qui correspond egalement aux 2 premiers chiffres sur la meme ligne
    public long num3_bonusId[][];   //liste des identifiants des lignes cumulant 3 chiffres specifiques et un numero bonus specifique
    public long num3_bonusCpt[];    //total d'apparitions de chaque bonus qui correspond egalement aux 3 premiers chiffres sur la meme ligne
    public long taille;

    /**
     * Constructeur, charge d'initialiser les differents champs
     * <p>Pour les tableaux, leurs tailles (de 4000) sont surevaluees pour pouvoir augmenter quandla table augmentera</p>
     * <p>Une solution optimale serait de fonctionner par exmple avec des TreeMap ou des ArrayLists a la place, pour eviter</p>
     * <p>d'utiliser trop de memoire inutilement</p>
     */
    public Statistics(){
        maDB=new DataBase();
        conn= maDB.getConnection();
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
    }

    /**
     * Sert a recuperer la taille de la table pour calculer la frequence d'apparition de la combinaison/du nombre considere
     */
    //TODO: enlever l'affichage
    public void setTaille(){
        /*on effectue un requete "SELECT Count(*) From myny.Test_Table"
         puis on l'affecte a une variable de l'objet Statistics*/
        try {
            conn = maDB.getConnection();
            if (conn != null) {
                Statement ps = conn.createStatement();
                ResultSet rs=ps.executeQuery("SELECT Count(*) From myny.Test_Table");
                rs.next();
                long nbcolret= rs.getLong(1);
                conn.commit();
                System.out.println("statement cree");
                taille =nbcolret;
                System.out.println(taille);
            }
        } catch (SQLException e) {
            System.out.println("chiffre1 probleme");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Sert a recuperer le nombre d'apparitions de chaque numero ainsi que leurs id (annee_numero_de_tirage) de la table dans la db
     * <p>i, k, l (int) sont des compteurs</p>
     * <p>numprec(int) verifie si on change de chiffre ou non</p>
     * <p>rs (ResultSet) sert a avoir des retours sur les requetes realisees</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
    public void afficherChiffre1() throws SQLException {
        System.out.println("debut afficherChiffre1");
        int k=0, numprec=0, l=0;
        ResultSet rs=null;
        String sql;
        for (int i=1; i<6;i++) {
            //pour chaque colonne contenant une boule (non bonus) de la table, on va recuperer le nombre d'apparitions de chaque boule et le stocker dans un tableau
            sql = "SELECT  Test_Table.boule_" + i + ",Count(*) as 'cnt' FROM myny.Test_Table GROUP BY Test_Table.boule_" + i + "; ";
            try {
                if(conn==null) {
                    conn = maDB.getConnection();
                }
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
            while (rs.next()) {
                if (i == 1) {
                    numCpt[rs.getInt(1)][0] = rs.getInt(1);
                    numCpt[rs.getInt(1)][1] = rs.getLong(2);
                } else {
                    numCpt[rs.getInt(1)][1] += rs.getLong(2);
                }
            }
        }
        for (int i=1; i<=5;i++) {
            //pour chaque colonne contenant une boule (non bonus) de la table, on va
            // recuperer les annee_numero_de_tirage correspondants et les stocker dans un tableau
            sql = "SELECT Test_Table.annee_numero_de_tirage, Test_Table.boule_" + i + " FROM myny.Test_Table ORDER BY Test_Table.boule_" + i + "; ";
            try {
                if(conn==null) {
                    conn = maDB.getConnection();
                }
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
        }
        for (int i=1; i<50;i++) {
            System.out.println(i+":"+numCpt[i][1]);
            while(numId[i][l]!=0){
                System.out.print(numId[i][l]+", ");
                l++;
            }
            System.out.println();
            l=0;
        }
    }

    /**
     * On va essayer de trouver les correspondances d'identifiants entre le chiffre entre en parametre et les chiffres dans les lignes de la tables, pour les stocker dans un tableau, et sur un second qui va compter les correspondances entre le premier et les seconds chiffres potentiels
     * @param  chiffre1 (int) le chiffre donne;
     * <p>i, j, k, l (int) sont des compteurs</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
    public void afficherCombinaisons2(int chiffre1) throws SQLException {
        afficherChiffre1();
        System.out.println("debut afficherCombinaisons2");
        numSelect[1]=chiffre1;
        int j=0, k=0, l=0;
        while (numId[chiffre1][j] != 0) {
            for(int i=1;i<50;i++) {
                if (i != chiffre1) {
                    while (numId[i][k] != 0) {
                        if (numId[i][k] == numId[chiffre1][j]) {
                            //System.out.println("[i:"+i+"][k:"+k+"][j:"+j+"]");
                            //System.out.println(numId[i][k] +"=="+ numId[chiffre1][j]);
                            //combi1Cpt[i]++;
                            combi1Id[i][(int)combi1Cpt[i]++]=numId[i][k];
                            //System.out.println("[cpt:"+cpt+"]");
                        }
                        k++;
                    }
                    k = 0;
                }
            }
            j++;
        }
        for (int i=1; i<50;i++) {
            /*for (int p=0; p<50;p++) {
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
     * On va essayer de trouver les correspondances d'identifiants entre les 2 chiffres entres en parametre et les chiffres dans les lignes de la tables, pour les stocker dans un tableau, et sur un second qui va compter les correspondances entre le premier, le second et les troisièmes chiffres potentiels
     * @param  chiffre1, (int) le premier chiffre donne;
     * @param  chiffre2, (int) le second chiffre donne;
     * <p>i, j, k (int) sont des compteurs</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
    public void afficherCombinaisons3(int chiffre1, int chiffre2) throws SQLException {
        //afficherChiffre1();
        afficherCombinaisons2(chiffre1);
        System.out.println("debut afficherCombinaisons3");
        numSelect[2]=chiffre2;
        int j=0, k=0;
        while (combi1Id[chiffre2][j] != 0){
            for(int i=1;i<50;i++) {
                if (i != chiffre1 && i!=chiffre2) {
                    while (numId[i][k] != 0) {
                        if (numId[i][k] == combi1Id[chiffre2][j]) {
                            combi2Id[i][(int)combi2Cpt[i]++]=numId[i][k];
                        }
                        k++;
                    }
                    k = 0;
                }
            }
            j++;
        }
        k = 0;
        for (int i=1; i<50;i++) {
            /*for (int p=0; p<50;p++) {
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
     * Sert a recuperer le nombre d'apparitions de chaque numero bonus ainsi que leurs id (annee_numero_de_tirage) de la table dans la db
     * <p>i, k, l (int) sont des compteurs</p>
     * <p>numprec(int) verifie si on change de chiffre ou non</p>
     * <p>rs (ResultSet) sert a avoir des retours sur les requetes realisees</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
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
        /*for (int i=0; i<11;i++) {
            System.out.println(i+" :"+ bonusCpt[i][1]);
        }*/
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
            //System.out.println(bonusId[rs.getInt(2)][k]);
            numprec=rs.getInt(2);
        }
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
     * On va essayer de trouver les correspondances d'identifiants entre le chiffre entre en parametre et les chiffres bonus potentiels dans les lignes de la tables, pour les stocker dans un tableau, et sur un second qui va compter les correspondances entre le premier chiffre et les seconds chiffres bonus potentiels
     * @param  chiffre1, (int) le chiffre donne;
     * <p>i, j, k, l (int) sont des compteurs</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
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
                        k=0;
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
     * On va essayer de trouver les correspondances d'identifiants entre les 2 chiffres entres en parametre et les chiffres bonus potentiels dans les lignes de la tables, pour les stocker dans un tableau, et sur un second qui va compter les correspondances entre le premier chifre, le second chiffre et les chiffres bonus potentiels
     * @param  chiffre1, (int) le premier chiffre donne;
     * @param  chiffre2, (int) le second chiffre donne;
     * <p>i, j, k, l, cpt (int) sont des compteurs</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
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
                        k=0;
                    }
                    k++;
                }
                k = 0;
                j++;
            }
            j = 0;
            cpt=0;
        }
        for (int i=0; i<11;i++) {
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
     * On va essayer de trouver les correspondances d'identifiants entre les 3 chiffres entres en parametre et les chiffres bonus potentiels dans les lignes de la tables, pour les stocker dans un tableau, et sur un second qui va compter les correspondances entre le premier chifre, le second chiffre, le troisieme chiffre et les chiffres bonus potentiels
     * @param  chiffre1, (int) le premier chiffre donne;
     * @param  chiffre2, (int) le second chiffre donne;
     * @param  chiffre3, (int) le troisieme chiffre donne;
     * <p>i, j, k, l (int) sont des compteurs</p>
     * @throws SQLException si erreur SQL
     */
    //TODO: enlever l'affichage
    public void afficherNumBonusCombi3(int chiffre1, int chiffre2, int chiffre3) throws SQLException {
        afficherCombinaisons3(chiffre1, chiffre2);
        afficherBonus();
        System.out.println("debut afficherNumBonusCombi3");
        numSelect[1]=chiffre1;
        numSelect[2]=chiffre2;
        numSelect[3]=chiffre3;
        int j=0, k=0, l=0;
        while (combi2Id[chiffre3][j] != 0) {
            for(int i=1;i<11;i++) {
                while (bonusId[i][k] != 0) {
                    //System.out.println(combi2Id[chiffre3][j] +"=="+  bonusId[i][k]);
                    if (bonusId[i][k] == combi2Id[chiffre3][j]) {
                        //num3_bonusCpt[i]++;
                        //num3_bonusId[i][cpt++]=bonusId[i][k];
                        num3_bonusId[i][(int) num3_bonusCpt[i]++]=bonusId[i][k];
                        j++;
                        k=0;
                    }
                    k++;
                }
                k = 0;
            }
            j++;
        }
        for (int i=1; i<11;i++) {
            /*for (int p=0; p<50;p++) {
                System.out.print(num3_bonusId[i][p]+", ");
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