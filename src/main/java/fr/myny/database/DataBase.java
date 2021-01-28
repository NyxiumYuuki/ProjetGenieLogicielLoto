package fr.myny.database;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * La classe DataBase qui soccupera de la base de donnees
 */

public class DataBase {
    //public static String url="jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*";
    public static final int NBCOL =25;//le nombre de coldu fichier csv
    public static final String url="jdbc:mariadb://phpmyadmin.vachot.fr:3306?db=myny&user=mynynicolas&password=Bw0po64*";//l'acces à la db
    Connection conn;//la connexion a la db

    /**
     * La methode de connection
     * @return conn la connection a la db
     */
    public Connection getConnection() {
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //conn = DriverManager.getConnection("jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb://phpmyadmin.vachot.fr:3306/myny?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb:../../../../../../test.db");
            if(conn!=null) {
                System.out.println("Connecte a la db");
                System.out.println(conn);
            }
        }catch(SQLException e) {
            System.out.println("getConnection on a des pb:" + e.getMessage());
        }
        return conn;
    }

    /**
     * La methode de creation de la table
     * <p>stmt Statement la variable pour creer la declaration</p>
     * <p>sql String la requete</p>
     * @return res, int le resultat de la requete (nb de ligne affectees)
     */
    public int createTable() {
        int res=-1;
        Statement stmt;
        String sql="CREATE TABLE IF NOT EXISTS myny.Test_Table ("+  //declaration de la table, de ses colonnes et de leur type et leur taille (on aurai pu ajouter des param supplementaires)
            " annee_numero_de_tirage INTEGER,"+                     //1 long
            " jour_de_tirage VARCHAR(10),"+                         //2 String
            " date_de_tirage DATE,"+                                //3 String
            " date_de_forclusion DATE,"+                            //4 String
            " boule_1 TINYINT,"+                                    //5 long
            " boule_2 TINYINT,"+                                    //6 long
            " boule_3 TINYINT,"+                                    //7 long
            " boule_4 TINYINT,"+                                    //8 long
            " boule_5 TINYINT,"+                                    //9 long
            " numero_chance TINYINT,"+                              //10 long
            " combinaison_gagnante_en_ordre_croissant varchar(20),"+//11 String
            " nombre_de_gagnant_au_rang1 INTEGER,"+                 //12 long
            " rapport_du_rang1 float(53),"+                         //13 double
            " nombre_de_gagnant_au_rang2 INTEGER,"+                 //14 long
            " rapport_du_rang2 float(53),"+                         //15 double
            " nombre_de_gagnant_au_rang3 INTEGER,"+                 //16 long
            " rapport_du_rang3 float(53),"+                         //17 double
            " nombre_de_gagnant_au_rang4 INTEGER,"+                 //18 long
            " rapport_du_rang4 float(53),"+                         //19 double
            " nombre_de_gagnant_au_rang5 INTEGER,"+                 //20 long
            " rapport_du_rang5 float(53),"+                         //21 double
            " nombre_de_gagnant_au_rang6 INTEGER,"+                 //22 long
            " rapport_du_rang6 float(53),"+                         //23 double
            " numero_jokerplus INTEGER,"+                           //24 long
            " devise VARCHAR(10));";                                //25 String
            //System.out.println(sql);
        try {
            conn = DriverManager.getConnection(url);    //connection a la db
            if(conn!=null) {
                stmt = conn.createStatement();          //creation de la declaration
                res=stmt.executeUpdate(sql);                 //execution de la declaration
                //stmt.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("create table probleme, verifier si le pb ne vient pas de executeupdate au lieu executequery");
            System.out.println(e.getMessage());
        }
        return res;
    }


    /**
     * La methode de remplissage de la base de donnees
     * <p>line String la ligne a ajouter</p>
     * <p>i int, un compteur de valeurs lues,</p>
     * <p>j int, qui suit i%NBCOL</p>
     * <p>nbValAj int qui augmente si on doit ajouter des lignes</p>
     * <p>date String, la valeur de la date lue</p>
     * <p>jour String, le jour lu</p>
     * <p>mois String, le mois lu</p>
     * <p>an String, l annee lue</p>
     * <p>mesL tableau dans lequel seront stockes tous les long</p>
     * <p>mesS tableau dans lequel seront stockes tous les String</p>
     * <p>mesD tableau dans lequel seront stockes tous les Double</p>
     * <p>sc Scanner, l objet qui servira a lire le fichier csv</p>
     * @param filePath String le fichier et son chemin
     * @return res int le resultat de la requete(nb de ligne affectees)
     */
    public int  fillTable(String filePath){
        int res=-1;
        Scanner sc;
        int i=0,j, nbValAj=0;
        String line="";
        String date, jour, mois, an;
        long[][] mesL=new long[4000][NBCOL+1];
        String[][]mesS=new String[4000][NBCOL+1];
        Double[][]mesD=new Double[4000][NBCOL+1];
        String sql="INSERT INTO myny.Test_Table" +//definit la requete
                " (annee_numero_de_tirage, "+
                " jour_de_tirage, "+
                " date_de_tirage,"+
                " date_de_forclusion,"+
                " boule_1 ,"+
                " boule_2 ,"+
                " boule_3 ,"+
                " boule_4 ,"+
                " boule_5 ,"+
                " numero_chance ,"+
                " combinaison_gagnante_en_ordre_croissant ,"+
                " nombre_de_gagnant_au_rang1 ,"+
                " rapport_du_rang1 ,"+
                " nombre_de_gagnant_au_rang2 ,"+
                " rapport_du_rang2 ,"+
                " nombre_de_gagnant_au_rang3 ,"+
                " rapport_du_rang3 ,"+
                " nombre_de_gagnant_au_rang4 ,"+
                " rapport_du_rang4 ,"+
                " nombre_de_gagnant_au_rang5 ,"+
                " rapport_du_rang5 ,"+
                " nombre_de_gagnant_au_rang6 ,"+
                " rapport_du_rang6 ,"+
                " numero_jokerplus ,"+
                " devise ) VALUES \n";
        try {
            sc = new Scanner(new File(filePath));//lire le fichier csv
            sc.useDelimiter(";|\\n");   //definit les separateurs utilisés
            sc.nextLine();//passe la premiere ligne, les metadonnees
            while (sc.hasNext()){//tant qu'on a des champs non-vides, on va remplir la ligne et les cases specifiques des tableaux a 2 dim correspondant au champ lu
                j=(i% NBCOL)+1;
                if(j==1) {
                    line=line+"(?,";
                    mesL[nbValAj][j]=Long.parseLong(sc.next());
                }
                if(j< NBCOL && j>1) {
                    line = line + "?,";
                    if (j==2 ||j==11){
                        //line=line+"\'"+sc.next().replace(",",".")+"\',";
                        mesS[nbValAj][j] = sc.next().replaceAll("\\s", "");

                    }
                    else if (j==3 ||j==4){
                        date=sc.next().replace(",",".");
                        jour=date.substring(0,2);
                        mois=date.substring(3,5);
                        an=date.substring(6,10);
                        mesS[nbValAj][j]=an+"-"+mois+"-"+jour;
                    }
                    else if (j >= 13 && j <= 23 && j % 2 == 1) {//pour les col 3 et 4, il s'agit d'une date, on va donc passer dela forme jj-mm-aaaa a la forme aaaa-mm-jj
                        mesD[nbValAj][j] = Double.parseDouble(sc.next().replace(",", "."));

                    }
                    else {//pour les autres col, on va simplement les remplir
                        mesL[nbValAj][j] = Long.parseLong(sc.next().replaceAll("\\s", ""));
                    }
                }
                if(j== NBCOL) {
                    nbValAj++;
                    line=line+"?)";
                    mesS[nbValAj][j] = sc.next().replaceAll("\\s", "");
                    if(sc.hasNext()) {
                        line = line+ ",";
                    }
                    else{
                        line = line+ ";";
                    }
                    //System.out.println(line);
                    sql=sql+line;
                    line="";
                }
                i++;
            }
            System.out.println(sql);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (nbValAj>0) {//si on a des modifications
            PreparedStatement ps;
            try {
                conn = this.getConnection();
                if (conn != null) {
                    ps=conn.prepareStatement(sql);
                    for(int a=0;a<nbValAj;a++){//pour chaque case de chaque ligne ajoutée, on va ajouter la valeur
                        for(int b=1;b<=NBCOL;b++){//dans la declaration preparee, pour eviter les injections sql
                            if((b>1&&b<5)||b==11||b==25){
                                System.out.println("a: "+a+" et b: "+b);
                                ps.setString((a)*25+b, mesS[a][b]);
                            }
                            else if(b > 12 && b < 24 && b % 2 == 1){
                                System.out.println("a: "+a+" et b: "+b);
                                if (b == 13) {
                                    System.out.println(mesD[a][b]);
                                }
                                ps.setString((a)*25+b, mesD[a][b]+"");
                            }
                            else{
                                System.out.println("a: "+a+" et b: "+b);
                                ps.setString((a)*25+b, mesL[a][b]+"");
                            }
                        }
                    }
                    res=ps.executeUpdate();
                    //ps.executeQuery();//puis on execute la requete
                    System.out.println("update validee");
                }
            } catch (SQLException e) {
                System.out.println("filldatebase probleme, verifier si le pb ne viens pas de executeupdate au lieu executequery");
                System.out.println(e.getMessage());
            }
        }
        return res;
    }

    /**
     * La methode de mise a jour de la base de donnees
     * @return res int, le resultat de la requete(nb de ligne affectees)
     * <p>sc Scanner, l objet qui servira a lire le fichier csv</p>
     * <p>line String, ligne contenant l'ajout a effectuer</p>
     * <p>i int, un compteur de valeurs lues,</p>
     * <p>j int, qui suit i%NBCOL</p>
     * <p>nbValAj int qui augmente si on doit ajouter des lignes</p>
     * <p>date String, la valeur de la date lue</p>
     * <p>jour String, le jour lu</p>
     * <p>mois String, le mois lu</p>
     * <p>an String, l annee lue</p>
     * <p>anEntre long, valeur lue pour l annee et le numero de tirage. sert a definir si la valeur est a ajouter dans la table</p>
     * @deprecated
     */
    public int updateTable(String filePath) throws FileNotFoundException {
        ResultSet rs=null;
        Scanner sc;
        int res=-1;
        int i,j, nbValAj=0;
        String date, jour, mois, an;
        String line="";
        long anEntre;
        Statement stmt;
        long maxvaldb;
        String sql="INSERT INTO myny.Test_Table" +           //le debut de la requete, specifiant laction a effectuer, la table et la db
                " (annee_numero_de_tirage, "+
                " jour_de_tirage, "+
                " date_de_tirage,"+
                " date_de_forclusion,"+
                " boule_1 ,"+
                " boule_2 ,"+
                " boule_3 ,"+
                " boule_4 ,"+
                " boule_5 ,"+
                " numero_chance ,"+
                " combinaison_gagnante_en_ordre_croissant ,"+
                " nombre_de_gagnant_au_rang1 ,"+
                " rapport_du_rang1 ,"+
                " nombre_de_gagnant_au_rang2 ,"+
                " rapport_du_rang2 ,"+
                " nombre_de_gagnant_au_rang3 ,"+
                " rapport_du_rang3 ,"+
                " nombre_de_gagnant_au_rang4 ,"+
                " rapport_du_rang4 ,"+
                " nombre_de_gagnant_au_rang5 ,"+
                " rapport_du_rang5 ,"+
                " nombre_de_gagnant_au_rang6 ,"+
                " rapport_du_rang6 ,"+
                " numero_jokerplus ,"+
                " devise ) VALUES \n";
        try {
            conn = this.getConnection();    //initialisation de la connexion
            if(conn!=null) {                //si la co est bonne
                sc = new Scanner(new File(filePath));   //lire le fichier donne
                sc.useDelimiter(";|\\n");                       //les delimiteurs seront ; et \n
                sc.nextLine();                                  //on ne conserve pas la premiere ligne, contenant les metadonnees
                anEntre=Long.parseLong(sc.next());         //lecture de l id en haut du tableau(donc le plus grand, cest ce qu on a constate en lisant le csv
                //System.out.println("numero en haut du fichier csv :"+anEntre);
                stmt = conn.createStatement();        //recuperation du nb de lignes dans la table
                rs = stmt.executeQuery("SELECT MAX(annee_numero_de_tirage) FROM myny.Test_Table;");
                rs.next();
                maxvaldb=rs.getLong(1);
                //System.out.println("numero max de la db :"+rs.getLong(1));
                /*comme les nombres de la premiere col sont decroissants, le nb le plus grand est lu en premier,
                on va donc lire les nombres jusqua arriver a la plus haute valeur entree dans la table. On ne
                pourrait probablement pas utiliser cette methode pour remplir la table en entier*/
                while(anEntre>maxvaldb) {
                    nbValAj++;//des quon rentre dans la boucle, on sait qu'on va devoir mettre a jour la table car cette valeur est>0
                    for (i = 0; i < NBCOL; i++) {//on remplit la ligne en effectuant une lecture par col
                        j = (i % NBCOL) + 1;
                        if (j == 1) {
                            line = line + '(';//debut de la ligne a jouter
                            line = line + anEntre;
                            line = line + ';';//on met un point virgule mais on le remplacera plus tard
                            //sc.next();
                        }
                        if (j < NBCOL && j > 1) {//pour chaque colonne, on va faire en sorte de mettre le champ dans la onne mise en fore pour qu'il soit accepté par la db
                            if (j == 2 || j == 11) {
                                line = line + "'" + sc.next().replace(",", ".") + "',";
                            } else if (j == 3 || j == 4) {//pour les col 3 et 4, il s'agit d'une date, on va donc passer dela forme jj-mm-aaaa a la forme aaaa-mm-jj
                                date = sc.next().replace(",", ".");
                                jour = date.substring(0, 2);
                                mois = date.substring(3, 5);
                                an = date.substring(6, 10);
                                line = line + "'" + an + "-" + mois + "-" + jour + "',";
                            } else {//pour les autres col, on va simplement les remplir
                                line = line + sc.next().replace(",", ".");
                                line = line + ';';
                            }
                        }
                        if (j == NBCOL) {//pour la derniere col
                            line = line + "'" + sc.next().replace(",", ".") + "'";
                            line = line + ')';//on la remplit
                            line = line.replaceAll("\\s", "");//on remplace les caracteres qui nous derangent
                            line = line.replaceAll(";", ",");
                            anEntre = Long.parseLong(sc.next());//on lit le prochain id de l'entree
                            if (anEntre > maxvaldb) {//on defini  si on doit encore ajouter des lignes a la requete
                                line = line + ",";
                            } else {//ou si on doit cloturer la requete
                                line = line + ";";
                            }
                            //System.out.println(line);
                            sql = sql + line + "\n";//on ajoute la ligne a la requete
                            line = "";
                        }
                    }
                }
                System.out.println(sql);
                if (nbValAj>0) {//on effectue la requete si on a des maj a faire
                    try {
                        conn = this.getConnection();
                        if (conn != null) {
                            stmt = conn.createStatement();
                            res=stmt.executeUpdate(sql);
                            //stmt.executeQuery(sql);
                            conn.commit();
                            //conn.close();
                            System.out.println("update validee");
                        }
                    } catch (SQLException e) {
                        System.out.println("updateDataBase probleme");
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("updateDataBase probleme");
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * La methode de mise a jour de la base de donnees 2 qui evite les injections sql
     * @return rs, int, le resultat de la requete (nb de lignes affectees)
     * <p>mesL tableau dans lequel seront stockes tous les long</p>
     * <p>mesS tableau dans lequel seront stockes tous les String</p>
     * <p>mesD tableau dans lequel seront stockes tous les Double</p>
     * <p>sc Scanner, l objet qui servira a lire le fichier csv</p>
     * <p>line String, ligne contenant l'ajout a effectuer</p>
     * <p>i int, un compteur de valeurs lues,</p>
     * <p>j int, qui suit i%NBCOL</p>
     * <p>a int, un compteur de lignes modifiees</p>
     * <p>b int, un compteur de champs modifiees</p>
     * <p>nbValAj int qui augmente si on doit ajouter des lignes</p>
     * <p>date String, la valeur de la date lue</p>
     * <p>jour String, le jour lu</p>
     * <p>mois String, le mois lu</p>
     * <p>an String, l annee lue</p>
     * <p>anEntre long, valeur lue pour l annee et le numero de tirage. sert a definir si la valeur est a ajouter dans la table</p>
     * <p>ps PreparedStatement pour executer la requete sans injection sql</p>
     * <p>stmt Statement pour executer la requete de depart</p>
     */
    public int updateTablev2(String filePath) throws FileNotFoundException {
        //Ligne maL=new Ligne();
        ResultSet rs=null;
        int res=-1;
        Statement stmt;
        long[][] mesL=new long[4000][NBCOL+1];
        String[][]mesS=new String[4000][NBCOL+1];
        Double[][]mesD=new Double[4000][NBCOL+1];
        Scanner sc;
        long anEntre;
        int i,j, nbValAj=0;
        String date, jour, mois, an;
        String line="";
        String sql="INSERT INTO myny.Test_Table" +           //le debut de la requete, specifiant laction a effectuer, la table et la db
                " (annee_numero_de_tirage, "+
                " jour_de_tirage, "+
                " date_de_tirage,"+
                " date_de_forclusion,"+
                " boule_1 ,"+
                " boule_2 ,"+
                " boule_3 ,"+
                " boule_4 ,"+
                " boule_5 ,"+
                " numero_chance ,"+
                " combinaison_gagnante_en_ordre_croissant ,"+
                " nombre_de_gagnant_au_rang1 ,"+
                " rapport_du_rang1 ,"+
                " nombre_de_gagnant_au_rang2 ,"+
                " rapport_du_rang2 ,"+
                " nombre_de_gagnant_au_rang3 ,"+
                " rapport_du_rang3 ,"+
                " nombre_de_gagnant_au_rang4 ,"+
                " rapport_du_rang4 ,"+
                " nombre_de_gagnant_au_rang5 ,"+
                " rapport_du_rang5 ,"+
                " nombre_de_gagnant_au_rang6 ,"+
                " rapport_du_rang6 ,"+
                " numero_jokerplus ,"+
                " devise ) VALUES \n";
        try {
            conn = this.getConnection();                        //initialisation de la connexion
            if(conn!=null) {                                    //si la co est bonne
                sc = new Scanner(new File(filePath));   //lire le fichier donne
                sc.useDelimiter(";|\\n");                       //les delimiteurs seront ; et \n
                sc.nextLine();                                  //on ne conserve pas la premiere ligne, contenant les metadonnees
                anEntre=Long.parseLong(sc.next());         //lecture de l id en haut du tableau(donc le plus grand, cest ce qu on a constate en lisant le csv
                stmt = conn.createStatement();        //recuperation du nb de lignes dans la table
                rs = stmt.executeQuery("SELECT MAX(annee_numero_de_tirage) FROM myny.Test_Table;");
                rs.next();
                long maxvaldb=rs.getLong(1);
                if (maxvaldb<0){
                    maxvaldb=0;
                }
                /*comme les nombres de la premiere col sont decroissants, le nb le plus grand est lu en premier,
                on va donc lire les nombres jusqua arriver a la plus haute valeur entree dans la table. On ne
                pourrait probablement pas utiliser cette methode pour remplir la table en entier*/
                while(anEntre>maxvaldb&&sc.hasNext()) {
                    nbValAj++;//des quon rentre dans la boucle, on sait qu'on va devoir mettre a jour la table car cette valeur est>0
                    for (i = 0; i < NBCOL; i++) {//on remplit la ligne en effectuant une lecture par col
                        j = (i % NBCOL) + 1;
                        if (j == 1) {
                            line = line + "(?,";//debut de la ligne a jouter
                            mesL[nbValAj][j]=anEntre;
                        }
                        if (j < NBCOL && j > 1) {//pour chaque colonne, on va faire en sorte de mettre le champ dans la onne mise en fore pour qu'il soit accepté par la db
                            line = line + "?,";
                            if (j == 2 || j == 11) {
                                mesS[nbValAj][j] = sc.next().replaceAll("\\s", "");
                            } else if (j == 3 || j == 4) {//pour les col 3 et 4, il s'agit d'une date, on va donc passer dela forme jj-mm-aaaa a la forme aaaa-mm-jj
                                date = sc.next().replace(",", ".");
                                jour = date.substring(0, 2);
                                mois = date.substring(3, 5);
                                an = date.substring(6, 10);
                                mesS[nbValAj][j] =  an + "-" + mois + "-" + jour ;
                            }
                            else if (j >= 13 && j <= 23 && j % 2 == 1) {//pour les col 3 et 4, il s'agit d'une date, on va donc passer dela forme jj-mm-aaaa a la forme aaaa-mm-jj
                                mesD[nbValAj][j] = Double.parseDouble(sc.next().replace(",", "."));
                            }
                            else {//pour les autres col, on va simplement les remplir
                                mesL[nbValAj][j] = Long.parseLong(sc.next().replaceAll("\\s", ""));
                            }
                        }
                        else if (j == NBCOL) {//pour la derniere col
                            line=line+"?)";
                            mesS[nbValAj][j] = sc.next().replaceAll("\\s", "");
                            anEntre = Long.parseLong(sc.next());//on lit le prochain id de l'entree
                            if (anEntre > maxvaldb) {//on defini  si on doit encore ajouter des lignes a la requete
                                line = line + ",";
                            } else {//ou si on doit cloturer la requete
                                line = line + ";";
                            }
                            //System.out.println(line);
                            sql = sql + line + "\n";//on ajoute la ligne a la requete
                            line = "";
                        }
                    }
                }
                System.out.println(sql);
                if (nbValAj>0) {//si on a des modifications
                    PreparedStatement ps;
                    try {
                        conn = this.getConnection();
                        if (conn != null) {
                            ps=conn.prepareStatement(sql);
                            for(int a=1;a<=nbValAj;a++){//pour chaque case de chaque ligne ajoutée, on va ajouter la valeur
                                for(int b=1;b<=NBCOL;b++){//dans la declaration preparee, pour eviter les injections sql
                                    if((b>1&&b<5)||b==11||b==25){
                                        System.out.println("a: "+a+" et b: "+b);
                                        ps.setString((a-1)*25+b, mesS[a][b]);
                                    }
                                    else if(b > 12 && b < 24 && b % 2 == 1){
                                        System.out.println("a: "+a+" et b: "+b);
                                        ps.setString((a-1)*25+b, mesD[a][b]+"");
                                    }
                                    else{
                                        System.out.println("a: "+a+" et b: "+b);
                                        ps.setString((a-1)*25+b, mesL[a][b]+"");
                                    }
                                }
                            }
                            res=ps.executeUpdate();//puis on execute la requete
                            //res=ps.executeQuery();//puis on execute la requete
                            System.out.println("update validee");
                        }
                    } catch (SQLException e) {
                        System.out.println("updateDataBasev2 probleme");
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("updateDataBasev2 probleme, verifier si le pb ne vient pas de executeupdate au lie de executequery");
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * la methode de selection de la ligne ayant la valeur i dans annee_numero_de_tirage
     * @param i la valeur de reference
     * @return rs, ResultSet le resultat de la requete
     */
    public ResultSet showLine(int i){
        ResultSet rs=null;
        String sql="select * from myny.Test_Table where annee_numero_de_tirage ="+i;
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement ps = conn.createStatement();
                rs=ps.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("removeLine probleme");
            System.out.println(e.getMessage());
        }
        return rs;
    }

    /**
     * la methode de suppression de la ligne ayant la valeur i dans annee_numero_de_tirage
     * @param i la valeur de reference
     * @return res, int le resultat de la requete(nb de lignes affectees)
     */
    public int removeLine(int i){
        int res=-1;
        String sql="delete from myny.Test_Table where annee_numero_de_tirage ="+i;
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement ps = conn.createStatement();
                res=ps.executeUpdate(sql);
                //res=ps.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("removeLine probleme, voir si le pb ne vient pas d'execute query et executeupdate");
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * la methode de supression des lignes strictement superieures a la valeur i dans annee_numero_de_tirage
     * @param i la valeur de reference
     * @return res, int le resultat de la requete (nb de lignes affectees)
     */
    public int removeMultiplesLines(int i){
        int res=-1;
        String sql="delete from myny.Test_Table where annee_numero_de_tirage >"+i;
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement ps = conn.createStatement();
                res=ps.executeUpdate(sql);
                //ps.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("removeLines probleme");
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * La methode pour supprimer la table entiere
     * @return res int le resultat de la requete(nb de lignes affectees)
     */
    public int dropTable(){
        String sql="drop table myny.Test_Table";
        int res=-1;
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement ps = conn.createStatement();
                //rs=ps.executeQuery(sql);
                res=ps.executeUpdate(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("dropTable probleme, voir si le pb ne vient pas d'execute query et executeupdate");
            System.out.println(e.getMessage());
        }
        return res;
    }
}
