package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Hero {
    public float betumeret;
    public Szoveg warrior;
    public String cselekves = "  ";

    public double hp,e_hp, mana,e_mana, attack, defense, rd, def;
    public int hanyadik;
    public String nev;
    public boolean dead = false;

    Hero(kepernyo_os_obj j, float h, float w, String nev_, double hp_, double mana_, double attack_, double defense_, int hanyadik) {

        betumeret = h / 40;
        warrior = new Szoveg(j);
        if (hanyadik == 0) {
            warrior.ini_render_balra(w / 2 - betumeret * 25, (h / 2) + betumeret * 2, nev + cselekves, 20);
        }
        if (hanyadik == 1) {
            warrior.ini_render_balra(w / 2 - betumeret * 25, (h / 2) - betumeret * 2, nev + cselekves, 20);
        }
        nev = nev_;
        hp = hp_;
        e_hp=hp_;
        mana = mana_;
        e_mana=mana_;
        attack = attack_;
        defense = defense_;

        System.out.println("valami");
    }

    public void render(SpriteBatch batch) {
        warrior.render_balra(batch);
    }

    Random rnd = new Random();

    public void setHealth(double hp_) {

        hp = hp_;

    }

    public void remHealth(double hp_) {
        // cselekves=" lose health " + (int)hp_;
        // warrior.szoveg_valtoztat(nev + cselekves);
        setHealth(getHealth() - hp_);
        if (getHealth()<0){
            hp=0;
        }
        if (hp == 0) {
            dead = true;
        }
    }

    public void HealthOut() {
        cselekves = " Healt: " + (int)hp;
        warrior.szoveg_valtoztat(nev + cselekves);
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
    }

    public void useDSpell() {
        if (mana > 5) {
            int i = rnd.nextInt(6);
            def *= (1 + (i / 5));

        } else {
            double i = mana;
            def *= (1 + (i / 5));
        }
    }

    public double attack(Hero kit) {
        rd = 0;
        int i = rnd.nextInt(2);
        // System.out.println("At " + i);

        rd = attack * (1.0 + (1.15 - 1.0) * rnd.nextDouble());
        if (i == 1)
            useTSpell();
        if (!kit.dead) {
            kit.remHealth(rd);
            cselekves = " attack " + kit.nev + " power: " + (int) rd;
            warrior.szoveg_valtoztat(nev + cselekves);
        } else {
            cselekves = " attack " + kit.nev + " dead: " + !dead;
            warrior.szoveg_valtoztat(nev + cselekves);
        }
        return rd;
    }

    public double defense() {
        int i = rnd.nextInt(2);
        System.out.println("Def " + i);
        def = 0;
        def = defense * (0.5 + (1.3 - 0.5) * rnd.nextDouble());

        if (i == 1)
            useDSpell();
        return def;

    }
}
