package fr.myny.database;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * La classe DataBase qui soccupera de la base de donnees
 */

public class DataBase {
    //public static String url="jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*";

    public static final int NBCOL =25;
    public static final String url="jdbc:mariadb://phpmyadmin.vachot.fr:3306?db=myny&user=mynynicolas&password=Bw0po64*";
    public static final String filePath="c:/Users/cocof/Bureau/nouveau_loto.csv";
    Connection conn;
    /**
     * Le constructeur de DataBase
     */
    public DataBase(){
    }




    /**
     * La methode dimport de donnees depuis la base de donnees
     */
    public void importDataBase(){

    }

    /**
     * La methode de creation de la base de donnees
     */
    public void createDataBase() {
        String sql="CREATE TABLE IF NOT EXISTS myny.Test_Table ("+
            " annee_numero_de_tirage INTEGER,"+                     //1
            " jour_de_tirage VARCHAR(10),"+                         //2 VARCHAR
            " date_de_tirage DATE,"+                                //3 DATE
            " date_de_forclusion DATE,"+                            //4 DATE
            " boule_1 TINYINT,"+                                    //5
            " boule_2 TINYINT,"+                                    //6
            " boule_3 TINYINT,"+                                    //7
            " boule_4 TINYINT,"+                                    //8
            " boule_5 TINYINT,"+                                    //9
            " numero_chance TINYINT,"+                              //10
            " combinaison_gagnante_en_ordre_croissant varchar(20),"+//11 VARCHAR
            " nombre_de_gagnant_au_rang1 INTEGER,"+                 //12
            " rapport_du_rang1 float(53),"+                         //13 flo
            " nombre_de_gagnant_au_rang2 INTEGER,"+                 //14
            " rapport_du_rang2 float(53),"+                         //15 flo
            " nombre_de_gagnant_au_rang3 INTEGER,"+                 //16
            " rapport_du_rang3 float(53),"+                         //17 flo
            " nombre_de_gagnant_au_rang4 INTEGER,"+                 //18
            " rapport_du_rang4 float(53),"+                         //19 flo
            " nombre_de_gagnant_au_rang5 INTEGER,"+                 //20
            " rapport_du_rang5 float(53),"+                         //21 flo
            " nombre_de_gagnant_au_rang6 INTEGER,"+                 //22
            " rapport_du_rang6 float(53),"+                         //23 flo
            " numero_jokerplus INTEGER,"+                           //24
            " devise VARCHAR(10));";                                //25 VARCHAR
            //System.out.println(sql);
        try {
            conn = DriverManager.getConnection(url);
            if(conn!=null) {
                Statement stmt = conn.createStatement();
                stmt.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("oskour");
            System.out.println(e.getMessage());
        }
        /*try(Statement mySt = conn.createStatement()){
            mySt.execute(//"CREATE SCHEMA IF NOT EXISTS Connection;"+
                    "CREATE TABLE IF NOT EXISTS myny.Test_Table ("+
                    "annee_numero_de_tirage INTEGER,"+
                    "jour_de_tirage CHAR(10),"+
                    "date_de_tirage DATE,"+
                    "date_de_forclusion DATE,"+
                    "boule_1 TINYINT,"+
                    "boule_2 TINYINT,"+
                    "boule_3 TINYINT,"+
                    "boule_4 TINYINT,"+
                    "boule_5 TINYINT,"+
                    "numero_chance TINYINT,"+
                    "combinaison_gagnante_en_ordre_croissantchar(20),"+
                    "nombre_de_gagnant_au_rang1 INTEGER,"+
                    "rapport_du_rang1 INTEGER,"+
                    "nombre_de_gagnant_au_rang2 INTEGER,"+
                    "rapport_du_rang2 INTEGER,"+
                    "nombre_de_gagnant_au_rang3 INTEGER,"+
                    "rapport_du_rang3 INTEGER,"+
                    "nombre_de_gagnant_au_rang4 INTEGER,"+
                    "rapport_du_rang4 INTEGER,"+
                    "nombre_de_gagnant_au_rang5 INTEGER,"+
                    "rapport_du_rang5 INTEGER,"+
                    "nombre_de_gagnant_au_rang6 INTEGER,"+
                    "rapport_du_rang6 INTEGER,"+
                    "numero_jokerplus INTEGER,"+
                    "deviseCHAR(10));");
            conn.commit();
            //mySt.close();
            //conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {
        }*/
        /*
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
    
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //    try {
    //        Class.forName("com.mysql.jdbc.Driver");
    //    }
    //    catch (ClassNotFoundException e) {
    //    }
    //
    //    try {
    //        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306");
    //    } catch (SQLException e) {
    //        System.out.println("oskour " +e.getStackTrace());
    //    }
    //
    //    Statement statement = null;
    //    ResultSet resultat = null;
    //    try {
    //        statement = conn.createStatement();
    //
    //        // Exécution de la requête
    //        resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");
    //
    //        // Récupération des données
    //        while (resultat.next()) {
    //            String nom = resultat.getString("nom");
    //            String prenom = resultat.getString("prenom");
    //        }
    //    }
    //    catch (SQLException e) {
    //        System.out.println(e);
    //    }
    }


    /**
     * La methode de remplissage de la base de donnees
     */
    public void fillDataBase(){
        int i=0,j, nbValAj=0;
        long val;
        String line="";
        String date, jour, mois, an;
        long[][] mesL=new long[4000][NBCOL+1];
        String[][]mesS=new String[4000][NBCOL+1];
        Double[][]mesD=new Double[4000][NBCOL+1];
        String sql="INSERT INTO myny.Test_Table" +
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
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(";|\\n");   //sets the delimiter pattern
            line=sc.nextLine();
            line="";
            while (sc.hasNext()){
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
            PreparedStatement ps = null;
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
                    ps.executeQuery();//puis on execute la requete
                    System.out.println("update validee");
                }
            } catch (SQLException e) {
                System.out.println("oskour into update");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * La methode de mise a jour de la base de donnees
     * @return sql String, contenant la requete SQL
     * @var sc Scanner, l objet qui servira a lire le fichier csv
     * @var line String, ligne contenant l'ajout a effectuer
     * @var i int, un compteur de valeurs lues,
     * @var j int, qui suit i%NBCOL
     * @var nbValAj int qui augmente si on doit ajouter des lignes
     * @var date String, la valeur de la date lue
     * @var jour String, le jour lu
     * @var mois String, le mois lu
     * @var an String, l annee lue
     * @var anEntre long, valeur lue pour l annee et le numero de tirage. sert a definir si la valeur est a ajouter dans la table
     */
    public String updateDataBase() throws FileNotFoundException {
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
                Scanner sc = new Scanner(new File(filePath));   //lire le fichier donne
                sc.useDelimiter(";|\\n");                       //les delimiteurs seront ; et \n
                sc.nextLine();                                  //on ne conserve pas la premiere ligne, contenant les metadonnees
                int i=0,j=0, nbValAj=0;
                String date, jour, mois, an;
                String line="";
                long anEntre=Long.parseLong(sc.next());         //lecture de l id en haut du tableau(donc le plus grand, cest ce qu on a constate en lisant le csv
                //System.out.println("numero en haut du fichier csv :"+anEntre);
                Statement stmt = conn.createStatement();        //recuperation du nb de lignes dans la table
                ResultSet rs = stmt.executeQuery("SELECT MAX(annee_numero_de_tirage) FROM myny.Test_Table;");
                rs.next();
                long maxvaldb=rs.getLong(1);
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
                                line = line + "\'" + sc.next().replace(",", ".") + "\',";
                            } else if (j == 3 || j == 4) {//pour les col 3 et 4, il s'agit d'une date, on va donc passer dela forme jj-mm-aaaa a la forme aaaa-mm-jj
                                date = sc.next().replace(",", ".");
                                jour = date.substring(0, 2);
                                mois = date.substring(3, 5);
                                an = date.substring(6, 10);
                                line = line + "\'" + an + "-" + mois + "-" + jour + "\',";
                            } else {//pour les autres col, on va simplement les remplir
                                line = line + sc.next().replace(",", ".");
                                line = line + ';';
                            }
                        }
                        if (j == NBCOL) {//pour la derniere col
                            line = line + "\'" + sc.next().replace(",", ".") + "\'";
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
                            stmt.executeQuery(sql);
                            conn.commit();
                            //conn.close();
                            System.out.println("update validee");
                        }
                    } catch (SQLException e) {
                        System.out.println("oskour into update");
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("oskour update");
            System.out.println(e.getMessage());
        }
        return sql;
    }

    /**
     * La methode de mise a jour de la base de donnees 2 qui evite les injections sql
     * @return sql String, contenant la requete SQL
     * @var mesL tableau dans lequel seront stockés tous les long
     * @var mesS tableau dans lequel seront stockés tous les String
     * @var mesD tableau dans lequel seront stockés tous les Double
     * @var sc Scanner, l objet qui servira a lire le fichier csv
     * @var line String, ligne contenant l'ajout a effectuer
     * @var i int, un compteur de valeurs lues,
     * @var j int, qui suit i%NBCOL
     * @var a int, un compteur de lignes modifiees
     * @var b int, un compteur de champs modifiees
     * @var nbValAj int qui augmente si on doit ajouter des lignes
     * @var date String, la valeur de la date lue
     * @var jour String, le jour lu
     * @var mois String, le mois lu
     * @var an String, l annee lue
     * @var anEntre long, valeur lue pour l annee et le numero de tirage. sert a definir si la valeur est a ajouter dans la table
     * @var ps PreparedStatement pour executer la requete sans injection sql
     * @var stmt Statement pour executer la requete de depart
     */
    public String updateDataBasev2() throws FileNotFoundException {
        //Ligne maL=new Ligne();
        Statement stmt;
        long[][] mesL=new long[4000][NBCOL+1];
        String[][]mesS=new String[4000][NBCOL+1];
        Double[][]mesD=new Double[4000][NBCOL+1];
        Scanner sc;
        ResultSet rs;
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
                    PreparedStatement ps = null;
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
                            ps.executeQuery();//puis on execute la requete
                            System.out.println("update validee");
                        }
                    } catch (SQLException e) {
                        System.out.println("oskour into update");
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("oskour update");
            System.out.println(e.getMessage());
        }
        return sql;
    }

    public void removeLines(int i) throws FileNotFoundException{
        String sql="delete from myny.Test_Table where annee_numero_de_tirage >"+i;
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement ps = conn.createStatement();
                ps.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("oskour");
            System.out.println(e.getMessage());
        }

    }

    public void dropTable() throws FileNotFoundException{
        String sql="drop table myny.Test_Table";
        try {
            conn = this.getConnection();
            if(conn!=null) {
                Statement ps = conn.createStatement();
                ps.executeQuery(sql);
                conn.commit();
                //conn.close();
                System.out.println("statement cree");
            }
        } catch (SQLException e) {
            System.out.println("oskour");
            System.out.println(e.getMessage());
        }

    }




    /**
     * La methode de connection
     */
    public Connection getConnection() {
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //conn = DriverManager.getConnection("jdbc:mariadb://vachot.fr:3306?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb://phpmyadmin.vachot.fr:3306/myny?user=mynynicolas&password=Bw0po64*");
            //conn = DriverManager.getConnection("jdbc:mariadb:../../../../../../test.db");
            if(conn!=null) {
                System.out.println("Connected to database");
                System.out.println(conn);
            }
        }catch(SQLException e) {
            System.out.println("on a des pb:" + e.getMessage());
        }
        return conn;
    }
}
