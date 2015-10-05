package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepBekeres extends kepernyo_os_obj {
    public KepBekeres(GdxAblak ablak) {
        super(ablak);
    }

    public float betumeret = h / 40, szovegmeret;
    public String nev = "  ", hp = "  ", mana = "  ", attack = "  ", defense = "  ";
    public int bevitel_szam = 0;
    public Szoveg nev_be, hp_be, mana_be, attack_be, defense_be;
    public alakzat aVissza, aNext, aHatter;
    public String hos1, hos2, hos3, hos4, hos5;
    public double hos1_hp, hos2_hp, hos3_hp, hos4_hp, hos5_hp;
    public double hos1_mana, hos2_mana, hos3_mana, hos4_mana, hos5_mana;
    public double hos1_attack, hos2_attack, hos3_attack, hos4_attack, hos5_attack;
    public double hos1_defense, hos2_defense, hos3_defense, hos4_defense, hos5_defense;
    public int jatekosok = 0;
    public boolean bekerve;

    @Override
    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        aNext = new alakzat(this, "Next.png", 100, false);
        aNext.atHelyez(w - aNext.getSzelesseg() - betumeret, betumeret);
        aHatter = new alakzat(this, "frame.png", 10, false);
        aHatter.atmeretez(w, h);
        aVissza = new alakzat(this, "vissza.png", 50, false);
        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);
        nev = " ";
        hp = " ";
        mana = " ";
        attack = " ";
        defense = " ";

        nev_be = new Szoveg(this);
        nev_be.ini_render_balra(w / 2 - betumeret * 4, h / 2 + betumeret * 4, "Nev:" + nev, szovegmeret);

        hp_be = new Szoveg(this);
        hp_be.ini_render_balra(nev_be.x, h / 2, "HP:" + hp, szovegmeret);
    }

    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        nev_be.hely_valtoztat(w / 2 - betumeret * 4, h / 2 + betumeret * 4);
        hp_be.hely_valtoztat(nev_be.x, h / 2);
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        aHatter.atmeretez(w, h);
        aNext.atHelyez(w - aNext.getSzelesseg() - betumeret, betumeret);
        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);
    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {
        aHatter.rajzol(batch);
        nev_be.render_balra(batch);
        hp_be.render_balra(batch);
        aVissza.rajzol(batch);
        aNext.rajzol(batch);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (aNext.benneVaneXY(x, y)) {
            bevitel_szam = 0;
            if (ablakRef.kepernyotoltes.jat_2) {
                System.out.println(jatekosok);
                if (jatekosok == 0) {
                    hos1 = nev;
                    hos1_hp = Double.parseDouble(hp);

                    nev = " ";
                    hp = " ";
                } else if (jatekosok == 1) {
                    hos2 = nev;
                    hos2_hp = Double.parseDouble(hp);

                    nev = " ";
                    hp = " ";
                    if (!ablakRef.kepernyotoltes.jat_3) {
                        bekerve = true;
                        ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
                    }
                }

                nev_be.szoveg_valtoztat("nev:" + nev);
                hp_be.szoveg_valtoztat("HP:" + hp);
            }
            if (ablakRef.kepernyotoltes.jat_3) {
                if (jatekosok == 2) {
                    hos3 = nev;
                    hos3_hp = Double.parseDouble(hp);
                    nev = " ";
                    hp = " ";
                    if (!ablakRef.kepernyotoltes.jat_4) {
                        bekerve = true;
                        ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
                    }
                }

            }
            if (ablakRef.kepernyotoltes.jat_4) {
                jatekosok++;

            }
            if (ablakRef.kepernyotoltes.jat_5) {
                jatekosok++;

            }
            jatekosok++;
        }
        if (aVissza.benneVaneXY(x, y)) {
            ablakRef.kepernyo_csere(ablakRef.kepernyotoltes);
            ablakRef.kepernyotoltes.valasztva = false;
            ablakRef.kepernyotoltes.jat_2 = false;
            ablakRef.kepernyotoltes.jat_3 = false;
            ablakRef.kepernyotoltes.jat_4 = false;
            ablakRef.kepernyotoltes.jat_5 = false;
        }
        if (nev_be.erintve(x, y)) {
            bevitel_szam = 0;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {
            Gdx.input.setOnscreenKeyboardVisible(false);
        }
        if (hp_be.erintve(x, y)) {
            bevitel_szam = 1;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {
            Gdx.input.setOnscreenKeyboardVisible(false);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        if (character==9){
            bevitel_szam++;
            System.out.println(bevitel_szam);
        }
        if (bevitel_szam == 0) {
            if (nev.length() > 0) {
                if (character == 8) {
                    if (nev.length() > 1) {
                        nev = nev.substring(0, nev.length() - 1);
                    }
                } else {
                    if (character > 64 && character < 98 || character > 96 && character < 123 || character > 47 && character < 58) {
                        nev += character;
                    }
                }

            }
            nev_be.szoveg_valtoztat("nev:" + nev);
        }
        if (bevitel_szam == 1) {
            if (hp.length() > 0) {
                if (character == 8) {
                    if (hp.length() > 1) {
                        hp = hp.substring(0, hp.length() - 1);
                    }
                } else {
                    if (character > 47 && character < 58) {
                        hp += character;
                    }
                }
            }
            hp_be.szoveg_valtoztat("HP:" + hp);
        }

        return false;
    }

    @Override
    public void jatekmenet_megszuntet() {

    }

}
