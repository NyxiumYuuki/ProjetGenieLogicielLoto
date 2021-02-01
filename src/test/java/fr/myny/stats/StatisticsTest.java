package fr.myny.stats;


import java.sql.SQLException;

public class StatisticsTest {
    //c.setTaille();
    //c.afficherChiffre1();
    //c.afficherNumBonus();
    //c.afficherCombinaisons2(4);
    //c.afficherCombinaisons3(32,16);
    //c.afficherNumBonus(1);
    //c.afficherNumBonusCombi2(5,10);
    //c.afficherNumBonusCombi3(28,14,37);

    public void setTaille(){
        Statistics s =new Statistics();
        s.setTaille();
    }


    public void afficherChiffre1() throws SQLException{
        Statistics s =new Statistics();
        s.afficherChiffre1();
    }

    public void afficherCombinaisons2() throws SQLException{
        Statistics s =new Statistics();
        s.afficherCombinaisons2(4);
    }


    public void afficherCombinaisons3() throws SQLException{
        Statistics s =new Statistics();
        s.afficherCombinaisons3(32,16);
    }


    public void afficherBonus() throws SQLException{
        Statistics s =new Statistics();
        s.afficherBonus();
    }


    public void afficherNumBonus() throws SQLException{
        Statistics s =new Statistics();
        s.afficherNumBonus(1);
    }


    public void afficherNumBonusCombi2() throws SQLException{
        Statistics s =new Statistics();
        s.afficherNumBonusCombi2(5,10);
    }

    public void afficherNumBonusCombi3() throws SQLException{
        Statistics s =new Statistics();
        s.afficherNumBonusCombi3(28,14,37);
    }
}
