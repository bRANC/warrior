package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Hero {
    public float betumeret, szovegmeret;
    public Szoveg warrior;
    public Szoveg hp_ki;

    public String cselekves = "  ";
    // public Szoveg nev_be;

    public alakzat big_face;

    public double hp, e_hp, mana, e_mana, attack, defense, rd, def;
    public int hanyadik;
    public String nev;
    public boolean dead = false, defense_helyez = false, nyert=false;
    public float h, w;

    Hero(kepernyo_os_obj j, float h_, float w_, String nev_, double hp_, double mana_, double attack_, double defense_, int hanyadik) {


        h = h_;
        w = w_;
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;

        hp_ki = new Szoveg(j);
        warrior = new Szoveg(j);
        hp_ki.ini_render_balra(0, 0, "", betumeret * 2 - 5);

        if (hanyadik == 0) {
            big_face = new alakzat(j, "card_big_1.png", 400, false);

        }
        if (hanyadik == 1) {
            big_face = new alakzat(j, "card_big_2.png", 400, false);
        }

        nev = nev_;
        hp = hp_;
        e_hp = hp_;
        mana = mana_;
        e_mana = mana_;
        attack = attack_;
        defense = defense_;
        big_face.atmeretez(w / 2, h);


        warrior.ini_render_balra(0, 0, nev, szovegmeret);
    }

    public void render(SpriteBatch batch) {
        big_face.rajzol(batch);

        hp_ki.render_balra(batch);
        warrior.render_balra(batch);
    }

    public void atmeretez(float h_, float w_) {
        h = h_;
        w = w_;
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        big_face.atmeretez(w / 2, h);
        warrior.meretez(szovegmeret);
        hp_ki.meretez(szovegmeret);
    }

    Random rnd = new Random();

    public void setHealth(double hp_) {

        hp = hp_;
        HealthOut();
    }

    public void remHealth(double hp_) {
        setHealth(getHealth() - hp_);
        if (getHealth() < 0) {
            hp = 0;
        }
        if (hp == 0) {
            dead = true;
        }
        HealthOut();
    }

    public void HealthOut() {
        hp_ki.szoveg_valtoztat((int) e_hp + " : " + (int) hp);
    }

    public double getHealth() {

        return hp;
    }

    public void useTSpell() {
        if (mana > 5) {
            int i = rnd.nextInt(6);
            rd *= (1 + (i / 5));

        } else {
            double i = mana;
            rd *= (1 + (i / 5));
        }
        HealthOut();
    }

    public void useDSpell() {
        if (mana > 5) {
            int i = rnd.nextInt(6);
            def *= (1 + (i / 5));

        } else {
            double i = mana;
            def *= (1 + (i / 5));
        }
        HealthOut();
    }

    public double attack(Hero kit) {
        rd = 0;
        if (!kit.dead) {
            rd = attack * (1.0 + (1.15 - 1.0) * rnd.nextDouble());
            if (rnd.nextBoolean())
                useTSpell();
            if (!kit.dead) {
                kit.remHealth(rd);
            }
            HealthOut();
        }else{
            return 0;
        }
        return rd;
    }

    public double defense() {

        def = 0;
        def = defense * (0.5 + (1.3 - 0.5) * rnd.nextDouble());

        if (rnd.nextBoolean())
            useDSpell();
        HealthOut();
        return def;

    }

    public void varakoz_1() {

    }

    public void varakoz_2() {

    }

    public void varakoz_3() {

    }

    public void attack_ki_helyez() {
        defense_helyez = false;
        big_face.atmeretez(w / 2, h);
        big_face.atHelyez(0, 0);
        at_helyezi_a_szoveget();
    }

    public void defense_ki_helyez() {
        big_face.atmeretez(w / 2, h);
        big_face.atHelyez(w / 2, 0);
        defense_helyez = true;
        at_helyezi_a_szoveget();

    }

    public void at_helyezi_a_szoveget() {
        if (defense_helyez) {
            warrior.hely_valtoztat(big_face.getX() + big_face.getSzelesseg() - betumeret * 3 - warrior.hatter.getSzelesseg(), h - hp_ki.hatter.getMagassag() - betumeret * 3 + betumeret / 2);
        } else {
            warrior.hely_valtoztat(big_face.getX() + betumeret * 3, h - hp_ki.hatter.getMagassag() - betumeret * 3 + betumeret / 2);
        }
        hp_ki.hely_valtoztat(big_face.getX() + betumeret * 3, big_face.getY() + betumeret * 15 + betumeret / 2);

    }


}
