package fr.myny.stats;

import org.junit.jupiter.api.Test;

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

    @Test
    public void setTaille(){
        Statistics s =new Statistics();
        s.setTaille();
    }
    @Test
    public void afficherChiffre1() throws SQLException{
        Statistics s =new Statistics();
        s.afficherChiffre1();
    }
    @Test
    public void afficherCombinaisons2() throws SQLException{
        Statistics s =new Statistics();
        s.afficherCombinaisons2(4);
    }

    @Test
    public void afficherCombinaisons3() throws SQLException{
        Statistics s =new Statistics();
        s.afficherCombinaisons3(32,16);
    }

    @Test
    public void afficherBonus() throws SQLException{
        Statistics s =new Statistics();
        s.afficherBonus();
    }

    @Test
    public void afficherNumBonus() throws SQLException{
        Statistics s =new Statistics();
        s.afficherNumBonus(1);
    }

    @Test
    public void afficherNumBonusCombi2() throws SQLException{
        Statistics s =new Statistics();
        s.afficherNumBonusCombi2(5,10);
    }

    @Test
    public void afficherNumBonusCombi3() throws SQLException{
        Statistics s =new Statistics();
        s.afficherNumBonusCombi3(28,14,37);
    }
}
