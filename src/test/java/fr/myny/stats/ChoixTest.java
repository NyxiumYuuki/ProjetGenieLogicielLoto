package fr.myny.stats;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ChoixTest {
    Choix c=new Choix();
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
        c.setTaille();
    }
    @Test
    public void afficherChiffre1() throws SQLException{
        c.afficherChiffre1();
    }
    @Test
    public void afficherCombinaisons2(int chiffre1) throws SQLException{
        c.afficherCombinaisons2(4);
    }

    @Test
    public void afficherCombinaisons3(int chiffre1, int chiffre2) throws SQLException{
        c.afficherCombinaisons3(32,16);
    }

    @Test
    public void afficherBonus() throws SQLException{
        c.afficherBonus();
    }

    @Test
    public void afficherNumBonus(int chiffre1) throws SQLException{
        c.afficherNumBonus(1);
    }

    @Test
    public void afficherNumBonusCombi2(int chiffre1, int chiffre2) throws SQLException{
        c.afficherNumBonusCombi2(5,10);
    }

    @Test
    public void afficherNumBonusCombi3(int chiffre1, int chiffre2, int chiffre3) throws SQLException{
        c.afficherNumBonusCombi3(28,14,37);
    }
}
