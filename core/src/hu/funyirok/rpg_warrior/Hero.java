package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Hero {
    public float betumeret, szovegmeret;
    public Szoveg warrior;
    public Szoveg hp_ki, mana_ki, att_ki, def_ki;

    public String cselekves = "  ";
    // public Szoveg nev_be;

    public alakzat big_face, mini_face;

    public double hp, e_hp, mana, e_mana, attack, defense, rd, def;
    public int hanyadik;
    public String nev;
    public boolean dead = false, defense_helyez = false, nyert = false;
    public float h, w,minimeret;


    Hero(kepernyo_os_obj j, float h_, float w_, String nev_, double hp_, double mana_, double attack_, double defense_, int hanyadik) {


        h = h_;
        w = w_;
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        minimeret = betumeret * 4;

        warrior = new Szoveg(j);
        hp_ki = new Szoveg(j);
        mana_ki = new Szoveg(j);
        att_ki = new Szoveg(j);
        def_ki = new Szoveg(j);

        hp_ki.ini_render_balra(0, 0, "", betumeret * 2 - 5);
        mana_ki.ini_render_balra(0, 0, "", betumeret * 2 - 5);
        hp_ki.ini_render_balra(0, 0, "", betumeret * 2 - 5);


        if (hanyadik == 0) {
            big_face = new alakzat(j, "card_big_1.png", 400, false);
            mini_face = new alakzat(j, "card_mini_1.png", 40, false);
        }
        if (hanyadik == 1) {
            big_face = new alakzat(j, "card_big_2.png", 400, false);
            mini_face = new alakzat(j, "card_mini_2.png", 40, false);
        }
        if (hanyadik == 2) {
            big_face = new alakzat(j, "card_big_3.png", 400, false);
            mini_face = new alakzat(j, "card_mini_3.png", 40, false);
        }

        if (hanyadik == 3) {
            big_face = new alakzat(j, "card_big_4.png", 400, false);
            mini_face = new alakzat(j, "card_mini_4.png", 40, false);
        }

        if (hanyadik == 4) {
            big_face = new alakzat(j, "card_big_5.png", 400, false);
            mini_face = new alakzat(j, "card_mini_5.png", 40, false);
        }


        nev = nev_;
        hp = hp_;
        e_hp = hp_;
        mana = mana_;
        e_mana = mana_;
        attack = attack_;
        defense = defense_;
        big_face.atmeretez(w / 2, h);

        att_ki.ini_render_balra(0, 0, "atk: " + (int) attack, betumeret * 2 - 5);
        def_ki.ini_render_balra(0, 0, "def: " + (int) defense, betumeret * 2 - 5);
        warrior.ini_render_balra(0, 0, nev, szovegmeret);
        mini_face.atmeretez(minimeret,minimeret);
        kiteszi_big();
    }

    public void render(SpriteBatch batch) {
        big_face.rajzol(batch);

        hp_ki.render_balra(batch);
        warrior.render_balra(batch);
        mana_ki.render_balra(batch);
        att_ki.render_balra(batch);
        def_ki.render_balra(batch);

    }

    public void render_que(SpriteBatch batch) {
        if (!dead) {
            mini_face.rajzol(batch);
        }else{
            //mini_face_dead(batch);
        }
    }


    public void kiteszi_big() {
        big_face.atHelyez(0 - big_face.getSzelesseg(), 0 - big_face.getMagassag());
        at_helyezi_a_szoveget();
    }


    public void atmeretez(float h_, float w_) {
        h = h_;
        w = w_;
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        minimeret = betumeret * 4;
        mini_face.atmeretez(minimeret,minimeret);
        big_face.atmeretez(w / 2, h);
        warrior.meretez(szovegmeret);
        hp_ki.meretez(szovegmeret);
        def_ki.meretez(szovegmeret);
        att_ki.meretez(szovegmeret);
        mana_ki.meretez(szovegmeret);
    }

    Random rnd = new Random();

    public void setHealth(double hp_) {

        hp = hp_;
        HealthOut();
        ManahOut();
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
        ManahOut();
    }

    public void HealthOut() {
        hp_ki.szoveg_valtoztat("HP: " + (int) hp + " : " + (int) e_hp);
    }

    public void ManahOut() {
        if (mana < 0) {
            mana = 0;
        }
        mana_ki.szoveg_valtoztat("Mana: " + (int) mana + " : " + (int) e_mana);
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
        mana -= rd;
        ManahOut();
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
        mana -= def;
        ManahOut();
        HealthOut();
    }

    public double attack(Hero kit) {
        this.attack_ki_helyez();
        kit.defense_ki_helyez();
        rd = 0;
        if (!kit.dead) {
            rd = attack * (1.0 + (1.15 - 1.0) * rnd.nextDouble());
            if (rnd.nextBoolean())
                if (mana > 0) {
                    useTSpell();
                }
            rd -= defense(kit);
            if (!kit.dead) {
                kit.remHealth(rd);
            }
            ManahOut();
            HealthOut();
        } else {
            return 0;
        }
        return rd;
    }

    public double defense(Hero kit) {

        def = 0;
        def = kit.defense * (0.5 + (1.3 - 0.5) * rnd.nextDouble());

        if (rnd.nextBoolean())
            if (mana > 0) {
                kit.useDSpell();
            }
        HealthOut();
        ManahOut();
        return def;

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
        warrior.hely_valtoztat(big_face.getX() + betumeret * 3 + 5, big_face.getY() + betumeret * 15 + betumeret / 2);
        hp_ki.hely_valtoztat(big_face.getX() + betumeret * 3 + 5, big_face.getY() + betumeret * 12 + betumeret / 2);
        mana_ki.hely_valtoztat(big_face.getX() + betumeret * 3 + 5, big_face.getY() + betumeret * 9 + betumeret / 2);
        att_ki.hely_valtoztat(big_face.getX() + betumeret * 3 + 5, big_face.getY() + betumeret * 4 + betumeret / 2);
        def_ki.hely_valtoztat(big_face.getX() + big_face.getSzelesseg() - betumeret * 3 - def_ki.hatter.getSzelesseg() - 5, big_face.getY() + betumeret * 4 + betumeret / 2);
    }


}
